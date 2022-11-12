import infra.Server
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.ExitCode
object Main extends IOApp {
  println("Hello, World!")

  def run(args: List[String]): IO[ExitCode] =
    Server.start().use(_ => IO.never).as(ExitCode.Success)
}
