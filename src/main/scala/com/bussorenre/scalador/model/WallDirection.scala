package com.bussorenre.scalador.model

sealed trait WallDirection

object WallDirection {
  case object Horizontal extends WallDirection
  case object Vertical   extends WallDirection
}
