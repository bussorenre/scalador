package com.bussorenre.scalador.model.action

sealed abstract class ActionError(value: String) {
  def toException = new Exception(value)
}

object ActionError {
  case object OUT_OF_BOARD   extends ActionError("out of game board.")
  case object THWARTED       extends ActionError("a wall thwarts to moving.")
  case object CONFLICT       extends ActionError("a wall is already set in same place.")
  case object NO_WALL_REMAIN extends ActionError("a player has no more walls.")
}
