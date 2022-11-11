package infra

import domain._
import sttp.tapir.{CodecFormat, Endpoint, endpoint, query, stringBody, webSocketBody}
import fs2.{Pipe, Stream}
import cats.effect.IO
import sttp.capabilities.WebSockets

// https://github.com/softwaremill/sttp-shared/blob/86309df2ea9c668d88f82fef073d64088b5c5921/fs2/src/main/scala/sttp/capabilities/fs2/Fs2Streams.scala
// TODO: Where are my types!
// import sttp.capabilities.fs2.Fs2Streams

object HttpApi {
  type Output = Pipe[IO, PancakeIngredient, PancakeStatus]
  type Capabilities = WebSockets with Fs2Streams[IO]

   val pancakesEndpoint: Endpoint[Int, String, Output, Capabilities] =
     endpoint
       .in("pancakes")
       .in(query[Int]("pans"))
       .errorOut(stringBody)
       .out(webSocketBody[
         PancakeIngredient, CodecFormat.Json,
         PancakeStatus, CodecFormat.Json](Fs2Streams[IO]))
}
