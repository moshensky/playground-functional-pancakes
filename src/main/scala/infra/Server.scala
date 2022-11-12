package infra

import cats.effect.IO
import sttp.tapir.server.http4s.Http4sServerInterpreter
import org.http4s.HttpRoutes
import sttp.tapir._
import org.http4s.server.{Server => Http4sServer}
import org.http4s.server.websocket.WebSocketBuilder2
import org.http4s.blaze.server.BlazeServerBuilder
import scala.concurrent.ExecutionContext
import org.http4s.server.Router
import cats.effect.kernel.Resource

object Server {
  def bakePancakes(count: Int): IO[Either[String, HttpApiEndpoints.Output]] =
    ???

  val pancakesServerEndpoint =
    HttpApiEndpoints.pancakesEndpoint.serverLogic(bakePancakes)

  val wsRoutes: WebSocketBuilder2[IO] => HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toWebSocketRoutes(pancakesServerEndpoint)

  def countCharacters(s: String): IO[Either[Unit, Int]] =
    IO.pure(Right[Unit, Int](s.length))

  val countCharactersEndpoint: PublicEndpoint[String, Unit, Int, Any] =
    endpoint.in(stringBody).out(plainBody[Int])
  val countCharactersRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(
      countCharactersEndpoint.serverLogic(countCharacters _)
    )

  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global

  def start(): Resource[IO, Http4sServer] = BlazeServerBuilder[IO]
    .withExecutionContext(ec)
    .bindHttp(8080, "localhost")
    .withHttpWebSocketApp(wsb => Router("/" -> wsRoutes(wsb)).orNotFound)
    .resource
}
