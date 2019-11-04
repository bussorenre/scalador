package com.bussorenre.scalador.cui

import com.bussorenre.scalador.model.Order
import com.bussorenre.scalador.player.{ ComputerPlayer, ComputerPlayerA, ComputerPlayerB }
import com.bussorenre.scalador.service.game.{ GameService, GameStatus }

object Main {
  def main(args: Array[String]): Unit = {

    val player1 = new ComputerPlayerA
    val player2 = new ComputerPlayerB
    val game    = new GameService(player1, player2)
    val cui     = new DrawService

    game.run match {
      case Right(status) => {
        status match {
          case GameStatus.WIN_FIRST  => println(s"${player1.id} wins!!")
          case GameStatus.WIN_SECOND => println(s"${player2.id} wins!!")
        }
      }
      case Left(e) => println(e.toException.toString)
    }
    cui.showHistory(game.boardHistory)
  }
}
