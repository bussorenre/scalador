package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model.Direction.{ East, North, South, West }
import com.bussorenre.scalador.model.{ Board, Direction, Order, Pos }

case class MoveToAction(direction: Direction) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val pawn = board.getPawn(order)
    for {
      _ <- movingOverWallValidation(pawn.pos, direction)(board)
      _ <- movingToOutsideValidation(pawn.pos, direction)(board)
    } yield {
      board.copy(
        pawns = board.pawns.map { self =>
          self.id match {
            case pawn.id => self.copy(pos = self.pos + direction)
            case _       => self
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
