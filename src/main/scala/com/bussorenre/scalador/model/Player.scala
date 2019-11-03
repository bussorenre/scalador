package com.bussorenre.scalador.model

sealed trait Order

object Order {
  case object First  extends Order
  case object Second extends Order
}

case class Player(
  id: String,
  order: Order,
  pos: Pos,
  remains: Int
)
