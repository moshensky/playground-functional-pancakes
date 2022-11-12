package domain

case class PortionIngredients(ingredients: Ingredients, pancakeCount: Int)

object PortionIngredients {
  val SinglePortion: Ingredients = Ingredients(250, 0.35, 2)
  val SinglePortionPancakeCount = 10

  def from(
      ingredients: Ingredients
  ): (Ingredients, Option[PortionIngredients]) = {
    val portions = Math.min(
      ingredients.flourGrams / SinglePortion.flourGrams,
      Math.min(
        (ingredients.milkLiters / SinglePortion.milkLiters).toInt,
        ingredients.eggsCount / SinglePortion.eggsCount
      )
    )

    if (portions == 0) {
      (ingredients, None)
    } else {
      val portionIngredients = SinglePortion * portions
      (
        ingredients - portionIngredients,
        Some(
          PortionIngredients(
            portionIngredients,
            SinglePortionPancakeCount * portions
          )
        )
      )
    }
  }
}
