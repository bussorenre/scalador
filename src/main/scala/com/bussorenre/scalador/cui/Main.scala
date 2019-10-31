package com.bussorenre.scalador.cui

import com.bussorenre.scalador.model._

object Main {
  def main(args: Array[String]): Unit = {
    val board = Board(
      9,
      Seq(HorizontalWall(1, 1), HorizontalWall(5, 7)),
      Seq(VerticalWall(3, 1)),
      Seq(Player(5, 2), Player(5, 8))
    )
    val service = new DrawService
    service.drawBoard(board)
  }
}
