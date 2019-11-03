package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model.Direction.{ East, North, South, West }
import com.bussorenre.scalador.model.{ Board, Direction, Order, Pos }

case class MoveToAction(direction: Direction) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val piece = board.getpiece(order)
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
    val moveTo = pos + direction
    val canMove = direction match {
      case South => board.horizontalWalls.exists(wall => wall.pos == pos || wall.pos + East == pos)
      case North => board.horizontalWalls.exists(wall => wall.pos == moveTo || wall.pos + East == moveTo)
      case East  => board.verticalWalls.exists(wall => wall.pos == pos || wall.pos + South == pos)
      case West  => board.verticalWalls.exists(wall => wall.pos == moveTo || wall.pos + South == moveTo)
    }
    canMove match {
      case true  => Left(ActionError.THWARTED)
      case false => Right()
    }
  }

  private def movingToOutsideValidation(pos: Pos, direction: Direction)(
    implicit board: Board
  ): Either[ActionError, Unit] = {
    val moveTo = pos + direction
    moveTo.x > 0 && moveTo.x <= board.size && moveTo.y > 0 && moveTo.y <= board.size match {
      case true  => Right()
      case false => Left(ActionError.OUT_OF_BOARD)
    }
  }
}
