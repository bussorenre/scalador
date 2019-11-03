package com.bussorenre.scalador.model

case class Board(
  size: Int,
  walls: Seq[Wall],
  pieces: Seq[Piece]
) {
  def verticalWalls   = walls.filter(_.isVertical)
  def horizontalWalls = walls.filter(_.isHorizontal)

  def firstpiece  = pieces(0)
  def secondpiece = pieces(1)

  def getpiece(order: Order) = pieces.find(_.order == order).get
}
