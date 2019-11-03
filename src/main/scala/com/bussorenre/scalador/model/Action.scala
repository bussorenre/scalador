package com.bussorenre.scalador.model

sealed abstract class ActionError(value: String) {
  def toException = new Exception(value)
}

object ActionError {
  case object OUT_OF_BOARD   extends ActionError("out of game board.")
  case object THWARTED       extends ActionError("a wall thwarts to moving.")
  case object ALREADY_EXISTS extends ActionError("a wall is already set in same place.")
  case object NO_WALL_REMAIN extends ActionError("a player has no more walls.")
}

sealed trait Action {
  def execute(order: Order, board: Board): Either[ActionError, Board]
}

case class MoveTo(direction: Direction) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val player = board.getPlayer(order)
    if (board.isMovingOverWall(player.pos, direction)) Left(ActionError.THWARTED)
    else if (board.isMovingToOudside(player.pos, direction)) Left(ActionError.OUT_OF_BOARD)
    else
      Right(
        board.copy(
          players = board.players.map { self =>
            self.id match {
              case player.id => self.copy(pos = self.pos + direction)
              case _         => self
            }
          }
        )
      )
  }
}

case class PlaceWall(wall: Wall) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val player = board.getPlayer(order)
    if (board.isPlacingWallToOutside(wall)) Left(ActionError.OUT_OF_BOARD)
    else if (board.isPlacingWallWithConflicts(wall)) Left(ActionError.ALREADY_EXISTS)
    else if (player.remains - 1 == 0) Left(ActionError.NO_WALL_REMAIN)
    else
      Right(
        board.copy(
          walls = wall +: board.walls,
          players = board.players.map { self =>
            self.id match {
              case player.id => self.copy(remains = self.remains - 1)
              case _         => self
            }
          }
        )
      )
  }
}
