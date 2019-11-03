package com.bussorenre.scalador.model

case class Piece(
  id: String,
  order: Order,
  pos: Pos,
  remains: Int
)
