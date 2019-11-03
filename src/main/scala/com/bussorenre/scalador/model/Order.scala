package com.bussorenre.scalador.model

sealed trait Order {
  def next: Order
}

object Order {
  case object First extends Order {
    override def next = Second
  }
  case object Second extends Order {
    override def next = First
  }
}
