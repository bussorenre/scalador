package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model._

case class PlaceWallAction(pos: Pos, direction: WallDirection) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val piece = board.getPiece(order)
    val wall  = Wall(pos, direction, order)

    for {
      _ <- conflictsValidation(wall)(board)
      _ <- noRemainsValidation(piece)(board)
      _ <- outsideValidation(wall)(board)
      current = board.copy(
        walls = wall +: board.walls,
        pieces = board.pieces.map { self =>
          self.id match {
            case piece.id => self.copy(remains = self.remains - 1)
            case _        => self
          }
        }
      )
      _ <- noRouteToGoalValidation(current)
    } yield {
      current
    }
  }

  private def conflictsValidation(wall: Wall)(implicit board: Board): Either[ActionError, Unit] = {
    board.walls.exists(_.conflictTo(wall)) match {
      case true  => Left(ActionError.CONFLICT)
      case false => Right()
    }
  }

  private def noRemainsValidation(piece: Piece)(implicit board: Board): Either[ActionError, Unit] = {
    piece.remains match {
      case 0 => Left(ActionError.NO_WALL_REMAIN)
      case _ => Right()
    }
  }

  private def outsideValidation(wall: Wall)(implicit board: Board): Either[ActionError, Unit] = {
    wall.pos.x > 0 && wall.pos.x < board.size && wall.pos.y > 0 && wall.pos.y < board.size match {
      case true  => Right()
      case false => Left(ActionError.OUT_OF_BOARD)
    }
  }

  private def noRouteToGoalValidation(board: Board): Either[ActionError, Unit] = {
    (board.costs(board.firstPiece), board.costs(board.secondPiece)) match {
      case (Some(_), Some(_)) => Right()
      case _                  => Left(ActionError.NO_ROUTE_TO_GOAL)
    }
  }
}
