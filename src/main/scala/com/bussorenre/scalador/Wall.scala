package com.bussorenre.scalador

trait Wall {
  val x: Int
  val y: Int
}

case class HorizontalWall(override val x: Int, override val y: Int) extends Wall {
  val direction = WallDirection.Horizontal
}

case class VerticalWall(override val x: Int, override val y: Int) extends Wall {
  val direction = WallDirection.Vertical
}
