package com.bussorenre.scalador.player

import com.bussorenre.scalador.model.action.Action
import com.bussorenre.scalador.model.{ Board, Order }

class HumanPlayer(override val id: String, override val order: Order) extends Player {
  override def decide(board: Board): Action = {
    ???
  }
}
