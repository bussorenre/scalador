package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model.Direction.{ East, North, South, West }
import com.bussorenre.scalador.model.{ Board, Direction, Order, Pos }

case class MoveToAction(direction: Direction) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val piece = board.getPiece(order)
    for {
      _ <- movingOverWallValidation(piece.pos, direction)(board)
      _ <- movingToOutsideValidation(piece.pos, direction)(board)
    } yield {
      board.copy(
        pieces = board.pieces.map { self =>
          self.id match {
            case piece.id => self.copy(pos = self.pos + direction)
            case _        => self
          }
        }
      )
    }
  }

  private def movingOverWallValidation(pos: Pos, direction: Direction)(
    implicit board: Board
  ): Either[ActionError, Unit] = {
    board.canMoveTo(pos, direction) match {
      case true  => Right()
      case false => Left(ActionError.THWARTED)
    }
  }

  private def movingToOutsideValidation(pos: Pos, direction: Direction)(
    implicit board: Board
  ): Either[ActionError, Unit] = {
    board.movingToInside(pos, direction) match {
      case true  => Right()
      case false => Left(ActionError.OUT_OF_BOARD)
    }
  }
}
