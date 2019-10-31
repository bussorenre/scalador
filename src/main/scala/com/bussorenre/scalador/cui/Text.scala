package com.bussorenre.scalador.cui

object Text {

  sealed abstract class TextColor(ascii: String) {
    override def toString: String = ascii
  }

  object Color {
    case object Default extends TextColor("\u001b[00m")
    case object Red     extends TextColor("\u001b[31m")
    case object Green   extends TextColor("\u001b[32m")
    case object Gray    extends TextColor("\u001b[90m")
  }

  sealed abstract class TextElement(ascii: String) {
    override def toString: String = ascii
  }

  object Element {
    sealed abstract class Player(id: String) extends TextElement("<" + id + ">")
    case object DefaultPlayer                extends Player("P")
    case object PlayerA                      extends Player("A")
    case object PlayerB                      extends Player("B")

    case object EmptyTile        extends TextElement("   ")
    case object VerticalWall     extends TextElement("|")
    case object HorizontalWall   extends TextElement("---")
    case object EmptyVertex      extends TextElement(" ")
    case object HorizontalVertex extends TextElement("-")
    case object VerticalVertex   extends TextElement("|")
  }
}