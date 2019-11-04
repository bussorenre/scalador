package com.bussorenre.scalador.service.game

import com.bussorenre.scalador.model.action.{ Action, ActionError }
import com.bussorenre.scalador.model._
import com.bussorenre.scalador.player.Player

class GameService(player1: Player, player2: Player) {

  def board = boardHistory.head

  var boardHistory: List[Board] = List(
    Board(
      size = 9,
      walls = Seq(),
      pieces = Seq(
        Piece(player1.id, Order.First, Pos(5, 9), 10),
        Piece(player2.id, Order.Second, Pos(5, 1), 10)
      )
    )
  )
  private var actionHistory: List[Action] = List()
  private var order: Order                = Order.First
  private var turn: Int                   = 0

  private def player = order match {
    case Order.First  => player1
    case Order.Second => player2
  }

  private def nextTurn: Boolean = {
    order = order.next
    turn = turn + 1
    true
  }

  private def executeAction(actor: Player, action: Action): Either[ActionError, Board] = {
    for {
      board <- action.execute(actor.order, board)
    } yield {
      nextTurn
      boardHistory ::= board
      board
    }
  }

  private def checkGameStatus(board: Board): GameStatus = {
    if (board.firstPiece.pos.y == 1) GameStatus.WIN_FIRST
    else if (board.secondPiece.pos.y == board.size) GameStatus.WIN_SECOND
    else GameStatus.CONTINUE
  }

  def run(): Either[ActionError, GameStatus] = {
    def innerLoop(): Either[ActionError, GameStatus] = {
      for {
        status <- executeAction(player, player.decide(board)).map { board =>
          checkGameStatus(board)
        }
        result <- status match {
          case GameStatus.CONTINUE => innerLoop()
          case _                   => Right(status)
        }
      } yield {
        result
      }
    }

    innerLoop
  }
}
