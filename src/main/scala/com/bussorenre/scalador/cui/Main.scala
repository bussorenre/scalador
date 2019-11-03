package com.bussorenre.scalador.cui

import com.bussorenre.scalador.model.WallDirection.{ Horizontal, Vertical }
import com.bussorenre.scalador.model.{ PlaceWall, _ }

object Main {
  def main(args: Array[String]): Unit = {

    val board = Board.initialize("player1", "player2")

    val actions = Seq(
      (Order.First, PlaceWall(Wall(Pos(5, 1), Vertical))),
      (Order.Second, PlaceWall(Wall(Pos(7, 1), Vertical))),
      (Order.First, PlaceWall(Wall(Pos(3, 1), Vertical))),
      (Order.Second, PlaceWall(Wall(Pos(3, 6), Horizontal))),
      (Order.First, PlaceWall(Wall(Pos(2, 4), Horizontal))),
      (Order.Second, MoveTo(Direction.West)),
      (Order.First, PlaceWall(Wall(Pos(3, 3), Horizontal))),
      (Order.Second, MoveTo(Direction.South)),
      (Order.Second, MoveTo(Direction.South)),
      (Order.First, PlaceWall(Wall(Pos(3, 2), Horizontal))),
      (Order.Second, MoveTo(Direction.West)),
      (Order.First, PlaceWall(Wall(Pos(4, 1), Horizontal))),
      (Order.Second, PlaceWall(Wall(Pos(5, 2), Horizontal))),
      (Order.First, PlaceWall(Wall(Pos(5, 3), Vertical))),
      (Order.First, PlaceWall(Wall(Pos(3, 5), Vertical)))
    )

    val service = new DrawService

    val result = actions.foldLeft(board)((b, a) => {
      val (player, action) = a
      action.execute(player, b) match {
        case Left(e: ActionError) => {
          service.drawBoard(b)
          throw e.toException
        }
        case Right(b) => b
      }
    })

    service.drawBoard(result)
  }
}
