package com.bussorenre.scalador.model

sealed trait Direction

object Direction {
  case object North extends Direction
  case object South extends Direction
  case object East  extends Direction
  case object West  extends Direction
}

sealed trait WallDirection

object WallDirection {
  case object Horizontal extends WallDirection
  case object Vertical   extends WallDirection
}
