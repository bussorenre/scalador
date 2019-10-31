package com.bussorenre.scalador.model

case class Board(
  size: Int,
  walls: Seq[Wall],
  players: Seq[Player]
) {
  def verticalWalls   = walls.filter(_.isVertical)
  def horizontalWalls = walls.filter(_.isHorizontal)

  def firstPlayer  = players(0)
  def secondPlayer = players(1)
}

object Board {
  def initialize(id1: String, id2: String): Board = Board(
    size = 9,
    walls = Seq(),
    players = Seq(
      Player(id1, Pos(5, 9), 10),
      Player(id2, Pos(5, 1), 10)
    )
  )
}
