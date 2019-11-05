package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model._

case class PlaceWallAction(pos: Pos, direction: WallDirection) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val pawn = board.getPawn(order)
    val wall = Wall(pos, direction, order)

    for {
      _     <- conflictsValidation(wall)(board)
      _     <- noRemainsValidation(pawn)(board)
      _     <- outsideValidation(wall)(board)
      board <- Right(applyAction(wall, pawn)(board))
      _     <- noRouteToGoalValidation(board)
    } yield {
      board
    }
  }

  private def applyAction(wall: Wall, pawn: Pawn)(board: Board): Board = {
    board.copy(
      walls = wall +: board.walls,
      pawns = board.pawns.map { self =>
        self.id match {
          case pawn.id => self.copy(remains = self.remains - 1)
          case _       => self
        }
      }
    )
  }

  private def conflictsValidation(wall: Wall)(board: Board): Either[ActionError, Unit] = {
    board.walls.exists(_.conflictTo(wall)) match {
      case true  => Left(ActionError.CONFLICT)
      case false => Right()
    }
  }

  private def noRemainsValidation(pawn: Pawn)(board: Board): Either[ActionError, Unit] = {
    pawn.remains match {
      case 0 => Left(ActionError.NO_WALL_REMAIN)
      case _ => Right()
    }
  }

  private def outsideValidation(wall: Wall)(board: Board): Either[ActionError, Unit] = {
    wall.pos.x > 0 && wall.pos.x < board.size && wall.pos.y > 0 && wall.pos.y < board.size match {
      case true  => Right()
      case false => Left(ActionError.OUT_OF_BOARD)
    }
  }

  private def noRouteToGoalValidation(board: Board): Either[ActionError, Unit] = {
    (board.costs(board.firstPawn), board.costs(board.secondPawn)) match {
      case (Some(_), Some(_)) => Right()
      case _                  => Left(ActionError.NO_ROUTE_TO_GOAL)
    }
  }
}
