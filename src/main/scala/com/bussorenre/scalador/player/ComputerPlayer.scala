package com.bussorenre.scalador.player

import com.bussorenre.scalador.model.Direction._
import com.bussorenre.scalador.model.WallDirection.{ Horizontal, Vertical }
import com.bussorenre.scalador.model.action.{ Action, MoveToAction, PlaceWallAction }
import com.bussorenre.scalador.model.{ Board, Order, Pos }

class ComputerPlayer(override val order: Order) extends Player {
  override val id                           = "computer"
  override def decide(board: Board): Action = ???
}

class ComputerPlayerA extends ComputerPlayer(Order.First) {
  override val id: String = "computerA"

  var turn = 0
  val actions = Seq(
    PlaceWallAction(Pos(5, 1), Horizontal),
    MoveToAction(North),
    MoveToAction(North),
    MoveToAction(East),
    PlaceWallAction(Pos(7, 1), Horizontal),
    PlaceWallAction(Pos(8, 2), Horizontal),
    PlaceWallAction(Pos(6, 2), Horizontal),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(East),
    PlaceWallAction(Pos(4, 8), Horizontal),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(South),
    MoveToAction(South),
    PlaceWallAction(Pos(5, 3), Vertical),
    PlaceWallAction(Pos(4, 4), Horizontal),
    PlaceWallAction(Pos(3, 3), Vertical),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(North),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(North),
    PlaceWallAction(Pos(6, 4), Vertical),
    PlaceWallAction(Pos(5, 5), Horizontal),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(North),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(North),
    MoveToAction(North),
    MoveToAction(North)
  )
  override def decide(board: Board): Action = {
    val ret = actions(turn)
    turn = turn + 1
    ret
  }
}

class ComputerPlayerB extends ComputerPlayer(Order.Second) {
  override val id: String = "computerB"

  var turn = 0
  val actions = Seq(
    MoveToAction(East),
    MoveToAction(East),
    PlaceWallAction(Pos(5, 6), Horizontal),
    PlaceWallAction(Pos(6, 7), Vertical),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(South),
    PlaceWallAction(Pos(4, 1), Vertical),
    PlaceWallAction(Pos(3, 6), Horizontal),
    MoveToAction(West),
    PlaceWallAction(Pos(1, 6), Horizontal),
    PlaceWallAction(Pos(3, 7), Vertical),
    PlaceWallAction(Pos(2, 7), Horizontal),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(West),
    MoveToAction(South),
    MoveToAction(South),
    MoveToAction(West),
    PlaceWallAction(Pos(2, 1), Vertical),
    MoveToAction(North),
    MoveToAction(North),
    MoveToAction(West),
    PlaceWallAction(Pos(2, 3), Vertical),
    MoveToAction(South),
    MoveToAction(South),
    PlaceWallAction(Pos(7, 7), Horizontal),
    MoveToAction(South),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(North),
    MoveToAction(North),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(East),
    MoveToAction(South),
    MoveToAction(South),
    MoveToAction(South),
    MoveToAction(South),
    MoveToAction(South),
    MoveToAction(South)
  )
  override def decide(board: Board): Action = {
    val ret = actions(turn)
    turn = turn + 1
    ret
  }
}
