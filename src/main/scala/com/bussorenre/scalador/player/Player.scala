package com.bussorenre.scalador.player

import com.bussorenre.scalador.model.action.Action
import com.bussorenre.scalador.model.{ Board, Order }

trait Player {
  val id: String
  val order: Order

  def decide(board: Board): Action
}
