package com.bussorenre.scalador.model

case class Board(
  size: Int,
  horizontalWalls: Seq[HorizontalWall],
  verticalWalls: Seq[VerticalWall],
  players: Seq[Player]
)
