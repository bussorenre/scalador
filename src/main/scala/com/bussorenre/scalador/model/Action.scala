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
  def execute(player: Player, board: Board): Either[ActionError, Board]
}

case class MoveTo(pos: Pos) extends Action {
  override def execute(player: Player, board: Board): Either[ActionError, Board] = {
    ???
  }
}

case class PlaceWall(wall: Wall) extends Action {
  override def execute(player: Player, board: Board): Either[ActionError, Board] = {
    if (!wall.pos.isInside(board.size)) Left(ActionError.OUT_OF_BOARD)
    else if (board.walls.exists(_.conflictTo(wall))) Left(ActionError.ALREADY_EXISTS)
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
