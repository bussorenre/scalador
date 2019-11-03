package com.bussorenre.scalador.cui

import com.bussorenre.scalador.model.Order._
import com.bussorenre.scalador.model.WallDirection._
import com.bussorenre.scalador.model._

class DrawService {
  import com.bussorenre.scalador.cui.Text._

  def drawBoard(board: Board): Unit = {
    convert(board).map(println(_))
  }

  def getOrderColor(order: Order): Text.TextColor = order match {
    case First  => Color.Red
    case Second => Color.Green
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

        direction match {
          case Vertical if x != board.size => {
            // Render player
            board.players.find(_.pos == Pos(x, y)) match {
              case Some(player) => draw(getOrderColor(player.order), Element.DefaultPlayer)
              case _            => draw(Color.Gray, Element.EmptyTile)
            }

            // Render vertical wall
            board.verticalWalls.find(wall => wall.pos == Pos(x, y) || wall.pos == Pos(x, y - 1)) match {
              case Some(wall) => draw(getOrderColor(wall.order), Element.VerticalWall)
              case None       => draw(Color.Gray, Element.VerticalWall)
            }
          }
          case Horizontal if y != board.size => {
            // Render horizontal wall
            board.horizontalWalls.find(wall => wall.pos == Pos(x, y) || wall.pos == Pos(x - 1, y)) match {
              case Some(wall) => draw(getOrderColor(wall.order), Element.HorizontalWall)
              case None       => draw(Color.Gray, Element.HorizontalWall)
            }

            // Render vertex
            board.walls.find(_.pos == Pos(x, y)) match {
              case Some(wall) =>
                wall.direction match {
                  case Horizontal => draw(getOrderColor(wall.order), Element.HorizontalVertex)
                  case Vertical   => draw(getOrderColor(wall.order), Element.VerticalVertex)
                }
              case _ => draw(Color.Gray, Element.EmptyVertex)
            }
          }
          case _ => Nil
        }
      }
      b.result()
    }
  }
}
