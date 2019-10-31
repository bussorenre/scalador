package com.bussorenre.scalador

object Main {
  def main(args: Array[String]): Unit = {
    val board = Board(
      9,
      Seq(),
      Seq(),
      Seq()
    )
    board.show()
  }
}
