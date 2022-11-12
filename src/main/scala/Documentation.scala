import infra.HttpApiEndpoints

import sttp.apispec.asyncapi.AsyncAPI
import sttp.tapir._
import sttp.tapir.docs.asyncapi.AsyncAPIInterpreter
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._
import sttp.apispec.asyncapi.circe.yaml._


// import sttp.tapir.asy

object Documentation extends App {
  val apiDocs = HttpApiEndpoints.pancakesEndpoint
    // .toAsyncAPI()

  val docs: AsyncAPI = AsyncAPIInterpreter().toAsyncAPI(apiDocs, "Echo web socket", "1.0")

  println(docs.toYaml)
}
