package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model.{ Board, Order }

trait Action {
  def execute(order: Order, board: Board): Either[ActionError, Board]
}
