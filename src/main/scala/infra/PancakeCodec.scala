package infra

import domain._
import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec

trait PancakeIngredientCodecs {
  private implicit val config: Configuration = Configuration.default
    .withDiscriminator("ingredient")
    .withSnakeCaseConstructorNames

  implicit val pancakeIngredientCodec: Codec[PancakeIngredient] =
    deriveConfiguredCodec[PancakeIngredient]
}

trait PancakeStatusCodecs extends PancakeIngredientCodecs {
  private implicit val config: Configuration = Configuration.default
    .withDiscriminator("status")
    .withSnakeCaseConstructorNames

  implicit val pancakeStatusCodec: Codec[PancakeStatus] =
    deriveConfiguredCodec[PancakeStatus]
}

object Json extends PancakeIngredientCodecs with PancakeStatusCodecs