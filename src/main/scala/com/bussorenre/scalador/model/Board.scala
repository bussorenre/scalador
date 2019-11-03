package com.bussorenre.scalador.model

import com.bussorenre.scalador.model.Direction._

case class Board(
  size: Int,
  walls: Seq[Wall],
  players: Seq[Player]
) {
  def verticalWalls   = walls.filter(_.isVertical)
  def horizontalWalls = walls.filter(_.isHorizontal)

  def firstPlayer  = players(0)
  def secondPlayer = players(1)

  def isPlacingWallToOutside(wall: Wall): Boolean =
    !(wall.pos.x > 0 && wall.pos.x < size && wall.pos.y > 0 && wall.pos.y < size)

  def isPlacingWallWithConflicts(wall: Wall): Boolean = walls.exists(_.conflictTo(wall))

  def isMovingOverWall(pos: Pos, direction: Direction): Boolean = {
    val moveTo = pos + direction
    direction match {
      case South => horizontalWalls.exists(wall => wall.pos == pos || wall.pos + East == pos)
      case North => horizontalWalls.exists(wall => wall.pos == moveTo || wall.pos + East == moveTo)
      case East  => verticalWalls.exists(wall => wall.pos == pos || wall.pos + South == pos)
      case West  => verticalWalls.exists(wall => wall.pos == moveTo || wall.pos + South == moveTo)
    }
  }

  def isMovingToOudside(pos: Pos, direction: Direction): Boolean = {
    val moveTo = pos + direction
    !(moveTo.x > 0 && moveTo.x <= size && moveTo.y > 0 && moveTo.y <= size)
  }

  def getPlayer(order: Order) = players.find(_.order == order).get
}

object Board {
  def initialize(id1: String, id2: String): Board = Board(
    size = 9,
    walls = Seq(),
    players = Seq(
      Player(id1, Order.First, Pos(5, 9), 10),
      Player(id2, Order.Second, Pos(5, 1), 10)
    )
  )
}
