package infra

import cats.effect.IO

object Server {
  def bakePancakes(count: Int): IO[Either[String, HttpApiEndpoints.Output]] =
    ???

  val pancakesServerEndpoint =
    HttpApiEndpoints.pancakesEndpoint.serverLogic(bakePancakes)
}
