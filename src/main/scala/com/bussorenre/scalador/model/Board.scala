package com.bussorenre.scalador.model

import com.bussorenre.scalador.model.Direction.{ East, North, South, West }

case class Board(
  size: Int,
  walls: Seq[Wall],
  pawns: Seq[Pawn]
) {
  def verticalWalls   = walls.filter(_.isVertical)
  def horizontalWalls = walls.filter(_.isHorizontal)

  def firstPawn  = pawns(0)
  def secondPawn = pawns(1)

  def getPawn(order: Order) = pawns.find(_.order == order).get

  def costs(pawn: Pawn): Option[Int] = {
    val goals = pawn.order match {
      case Order.First  => (1 to 9).map(Pos(_, 1))
      case Order.Second => (1 to 9).map(Pos(_, 9))
    }
    goals.map(costTo(_, pawn)).sorted.head
  }

  // TODO: refactor
  private def costTo(goal: Pos, pawn: Pawn): Option[Int] = {
    val tiles = Array.ofDim[Int](9, 9)
    tiles.map(_.map(_ => 0))

    def getTile(pos: Pos): Int = tiles(pos.y - 1)(pos.x - 1)
    def setTile(pos: Pos, n: Int): Unit = {
      tiles(pos.y - 1)(pos.x - 1) = n
    }

    setTile(goal, 1)

    def innerCostTo(pos: Pos): Unit = {
      for {
        direction <- List(South, North, East, West)
      } yield {
        if (canMoveTo(pos, direction) && movingToInside(pos, direction)) {
          val moveTo = pos + direction
          if (getTile(moveTo) == 0 || getTile(moveTo) > getTile(pos) + 1) {
            setTile(moveTo, getTile(pos) + 1)
            innerCostTo(moveTo)
          } else {
            // nothing
          }
        }
      }
    }
    innerCostTo(goal)
    getTile(pawn.pos) match {
      case 0       => None
      case _ @cost => Some(cost - 1)
    }
  }

  def canMoveTo(pos: Pos, direction: Direction): Boolean = {
    val moveTo = pos + direction
    val can = direction match {
      case South => horizontalWalls.exists(wall => wall.pos == pos || wall.pos + East == pos)
      case North => horizontalWalls.exists(wall => wall.pos == moveTo || wall.pos + East == moveTo)
      case East  => verticalWalls.exists(wall => wall.pos == pos || wall.pos + South == pos)
      case West  => verticalWalls.exists(wall => wall.pos == moveTo || wall.pos + South == moveTo)
    }
    !can
  }

  def movingToInside(pos: Pos, direction: Direction): Boolean = {
    val moveTo = pos + direction
    moveTo.x > 0 && moveTo.x <= size && moveTo.y > 0 && moveTo.y <= size
  }
}
