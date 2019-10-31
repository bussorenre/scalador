package com.bussorenre.scalador.cui

import com.bussorenre.scalador.model.{ Board, Pos, WallDirection }

class DrawService {
  import com.bussorenre.scalador.cui.Text._

  def drawBoard(board: Board): Unit = {
    convert(board).map(println(_))
  }

  def convert(board: Board): List[String] = {
    for {
      y         <- (1 to board.size).toList
      direction <- List(WallDirection.Vertical, WallDirection.Horizontal)
    } yield {
      val b                                            = new StringBuilder
      def draw(color: TextColor, element: TextElement) = b.append(color.toString + element.toString)

      for {
        x <- (1 to board.size).toList
      } yield {
        if (direction == WallDirection.Vertical && x != board.size) {
          // Render player
          if (board.players.exists(_.pos == Pos(x, y))) draw(Color.Red, Element.DefaultPlayer)
          else draw(Color.Gray, Element.EmptyTile)

          // Render vertical wall
          if (board.verticalWalls.exists(_.pos == Pos(x, y))) draw(Color.Red, Element.VerticalWall)
          else if (board.verticalWalls.exists(_.pos == Pos(x, y - 1))) draw(Color.Red, Element.VerticalWall)
          else draw(Color.Gray, Element.VerticalWall)
        }
        if (direction == WallDirection.Horizontal && y != board.size) {
          // Render horizontal wall
          if (board.horizontalWalls.exists(_.pos == Pos(x, y))) draw(Color.Red, Element.HorizontalWall)
          else if (board.horizontalWalls.exists(_.pos == Pos(x - 1, y))) draw(Color.Red, Element.HorizontalWall)
          else draw(Color.Gray, Element.HorizontalWall)

          // Render vertex
          if (board.horizontalWalls.exists(_.pos == Pos(x, y))) draw(Color.Red, Element.HorizontalVertex)
          else if (board.verticalWalls.exists(_.pos == Pos(x, y))) draw(Color.Red, Element.VerticalVertex)
          else draw(Color.Gray, Element.EmptyVertex)
        }
      }
      b.result()
    }
  }
}
