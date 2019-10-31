package com.bussorenre.scalador.cui

import com.bussorenre.scalador.model.WallDirection.{ Horizontal, Vertical }
import com.bussorenre.scalador.model.{ PlaceWall, _ }

object Main {
  def main(args: Array[String]): Unit = {

    val board = Board.initialize("player1", "player2")

    val actions = Seq(
      (board.firstPlayer, PlaceWall(Wall(Pos(5, 1), Vertical))),
      (board.secondPlayer, PlaceWall(Wall(Pos(7, 1), Vertical))),
      (board.firstPlayer, PlaceWall(Wall(Pos(8, 1), Vertical))),
      (board.secondPlayer, PlaceWall(Wall(Pos(3, 6), Horizontal))),
      (board.firstPlayer, PlaceWall(Wall(Pos(2, 4), Horizontal)))
    )

    val service = new DrawService

    val result = actions.foldLeft(board)((board, acts) => {
      val (player, action) = acts
      action.execute(player, board) match {
        case Left(e: ActionError) => throw e.toException
        case Right(board)         => board
      }
    })

    service.drawBoard(result)
  }
}
