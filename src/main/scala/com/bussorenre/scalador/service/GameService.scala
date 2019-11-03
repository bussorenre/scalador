package com.bussorenre.scalador.service

import com.bussorenre.scalador.model.{ Board, Order, Piece, Pos }

class GameService {
  def initialize(id1: String, id2: String): Board = Board(
    size = 9,
    walls = Seq(),
    pieces = Seq(
      Piece(id1, Order.First, Pos(5, 9), 10),
      Piece(id2, Order.Second, Pos(5, 1), 10)
    )
  )

}
