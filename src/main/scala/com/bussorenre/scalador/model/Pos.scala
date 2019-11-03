package com.bussorenre.scalador.model

case class Pos(x: Int, y: Int) {
  def ==(other: Pos) = this.x == other.x && this.y == other.y
  def !=(other: Pos) = !(==(other))

  def +(other: Pos): Pos = Pos(this.x + other.x, this.y + other.y)
  def -(other: Pos): Pos = Pos(this.x - other.x, this.y - other.y)
}

object Pos {
  implicit def toPos(p: (Int, Int)) = Pos(p._1, p._2)
}
