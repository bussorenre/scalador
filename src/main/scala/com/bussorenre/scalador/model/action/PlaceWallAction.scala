package com.bussorenre.scalador.model.action

import com.bussorenre.scalador.model._

case class PlaceWallAction(pos: Pos, direction: WallDirection) extends Action {
  override def execute(order: Order, board: Board): Either[ActionError, Board] = {
    val player = board.getPlayer(order)
    val wall   = Wall(pos, direction, order)

    for {
      _ <- conflictsValidation(wall)(board)
      _ <- noRemainsValidation(player)(board)
      _ <- outsideValidation(wall)(board)
    } yield {
      board.copy(
        walls = wall +: board.walls,
        players = board.players.map { self =>
          self.id match {
            case player.id => self.copy(remains = self.remains - 1)
            case _         => self
          }
        }
      )
    }
  }

  private def conflictsValidation(wall: Wall)(implicit board: Board): Either[ActionError, Unit] = {
    board.walls.exists(_.conflictTo(wall)) match {
      case true  => Left(ActionError.CONFLICT)
      case false => Right()
    }
  }

  private def noRemainsValidation(player: Player)(implicit board: Board): Either[ActionError, Unit] = {
    player.remains match {
      case 0 => Left(ActionError.NO_WALL_REMAIN)
      case _ => Right()
    }
  }

  private def outsideValidation(wall: Wall)(implicit board: Board): Either[ActionError, Unit] = {
    wall.pos.x > 0 && wall.pos.x < board.size && wall.pos.y > 0 && wall.pos.y < board.size match {
      case true  => Right()
      case false => Left(ActionError.OUT_OF_BOARD)
    }
  }
}
