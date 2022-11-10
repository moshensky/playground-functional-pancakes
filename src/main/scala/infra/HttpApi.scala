package infra

import sttp.tapir.Endpoint

object HttpApi {
  val pancakesEndpoint: Endpoint[Int, Strig, Pipe[
    Task,
    PancakeIngredient,
    PancakeStatus
  ], WebSockets with Fs2Streams[Task]] =
    endpoint
      .in("pancakes")

}
