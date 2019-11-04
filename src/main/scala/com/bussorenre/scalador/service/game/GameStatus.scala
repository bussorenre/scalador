package com.bussorenre.scalador.service.game

sealed trait GameStatus

object GameStatus {
  case object CONTINUE   extends GameStatus
  case object WIN_FIRST  extends GameStatus
  case object WIN_SECOND extends GameStatus
}
