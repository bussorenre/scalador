package com.bussorenre.scalador

case class Board(
  size: Int,
  horizontalWalls: Seq[HorizontalWall],
  verticalWalls: Seq[VerticalWall],
  players: Seq[Player]
) {
  def convert(): List[String] = {
    for {
      y         <- (1 to size).toList
      direction <- List(WallDirection.Vertical, WallDirection.Horizontal)
    } yield {
      val s = new StringBuilder
      for {
        x <- (1 to size).toList
      } yield {
        if (direction == WallDirection.Vertical && x != size) s.append("   |")
        if (direction == WallDirection.Horizontal && y != size) s.append("--- ")
      }
      s.result()
    }
  }

  def show(): Unit = {
    convert().map(println(_))
  }

}
