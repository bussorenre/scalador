package com.bussorenre.scalador.cui

import com.bussorenre.scalador.player.{ ComputerPlayer, ComputerPlayerA, ComputerPlayerB }
import com.bussorenre.scalador.service.game.GameService

object Main {
  def main(args: Array[String]): Unit = {

    val player1 = new ComputerPlayerA
    val player2 = new ComputerPlayerB
    val game    = new GameService(player1, player2)
    val service = new DrawService

    game.start()

    service.showHistory(game.boardHistory)
  }
}
