package com.bussorenre.scalador.model

import com.bussorenre.scalador.model.Direction._
import com.bussorenre.scalador.model.WallDirection.{ Horizontal, Vertical }

case class Wall(
  pos: Pos,
  direction: WallDirection,
  order: Order
) {

  def isHorizontal = this.direction == Horizontal
  def isVertical   = this.direction == Vertical

  def conflictTo(other: Wall): Boolean = (this.direction, other.direction) match {
    case (Horizontal, Horizontal) =>
      this.pos == other.pos || this.pos + East == other.pos || this.pos == other.pos + East
    case (Vertical, Vertical)   => this.pos == other.pos || this.pos + South == other.pos || this.pos == other.pos + South
    case (Horizontal, Vertical) => this.pos == other.pos
    case (Vertical, Horizontal) => this.pos == other.pos
  }
}
