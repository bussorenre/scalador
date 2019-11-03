package com.bussorenre.scalador.model

sealed trait Direction

object Direction {
  case object North extends Direction
  case object South extends Direction
  case object East  extends Direction
  case object West  extends Direction

  implicit def toPos(self: Direction): Pos = self match {
    case North => Pos(0, -1)
    case South => Pos(0, 1)
    case East  => Pos(1, 0)
    case West  => Pos(-1, 0)
  }
}
