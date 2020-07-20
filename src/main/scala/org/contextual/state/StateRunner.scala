package org.contextual.state

import cats.effect.concurrent.Ref
import cats.effect.{ExitCode, IO, IOApp}

object StateRunner extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    for {
      state <- iniState
      _     <- program(state)
    } yield ExitCode.Success

  def iniState: IO[Ref[IO, MyState]] = MyState.asRef

  def program(state: Ref[IO, MyState]): IO[Unit] =
    for {
      current <- state.get
      _       <- IO.delay(println(s"Current state is $current"))
      _       <- state.update(s => MyState(s.int + 1, s.string + "1"))
      updated <- state.get
      _       <- IO.delay(println(s"Updated state is $updated"))
    } yield ()
}
