package com.bussorenre.scalador.model

case class Board(
  size: Int,
  walls: Seq[Wall],
  pieces: Seq[Piece]
) {
  def verticalWalls   = walls.filter(_.isVertical)
  def horizontalWalls = walls.filter(_.isHorizontal)

  def firstPiece  = pieces(0)
  def secondPiece = pieces(1)

  def getPiece(order: Order) = pieces.find(_.order == order).get
}
