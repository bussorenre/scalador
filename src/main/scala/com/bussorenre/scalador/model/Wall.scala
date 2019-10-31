package com.bussorenre.scalador.model

trait Wall {
  val pos: Pos
}

case class HorizontalWall(override val pos: Pos) extends Wall {
  val direction = WallDirection.Horizontal
}

case class VerticalWall(override val pos: Pos) extends Wall {
  val direction = WallDirection.Vertical
}
