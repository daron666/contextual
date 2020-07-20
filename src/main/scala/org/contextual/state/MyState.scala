package org.contextual.state

import cats.effect.IO
import cats.effect.concurrent.Ref

final case class MyState(int: Int, string: String)

object MyState {

  val empty = MyState(0, "")

  val asRef = Ref.of[IO, MyState](empty)
}
