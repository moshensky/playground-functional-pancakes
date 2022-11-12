package infra

import domain._
import fs2.Pipe
import cats.effect.IO
import sttp.capabilities.WebSockets
import sttp.capabilities.fs2.Fs2Streams
import sttp.tapir._
import sttp.tapir.Codec.JsonCodec
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import Json._

object HttpApiEndpoints {
  type Output = Pipe[IO, PancakeIngredient, PancakeStatus]
  type Capabilities = WebSockets with Fs2Streams[IO]

  val pancakesEndpoint: Endpoint[Unit, Int, String, Output, Capabilities] = {
    val queryPans = query[Int]("pans")
      .description("The number of frying pans to use in parallel")
      .example(2)
      .validate(Validator.min(1))

    val out = webSocketBody[
      PancakeIngredient,
      CodecFormat.Json,
      PancakeStatus,
      CodecFormat.Json
    ](Fs2Streams[IO])

    endpoint
      .in("pancakes")
      .in(queryPans)
      .errorOut(stringBody)
      .out(out)
  }
}
