package domain

case class Ingredients(flourGrams: Int, milkLiters: Double, eggsCount: Int) {
  def +(x: PancakeIngredient): Ingredients = x match {
    case Eggs(count)  => copy(eggsCount = eggsCount + count)
    case Flour(grams) => copy(flourGrams = flourGrams + grams)
    case Milk(liters) => copy(milkLiters = milkLiters + liters)
  }
  def -(x: Ingredients): Ingredients = Ingredients(
    flourGrams - x.flourGrams,
    milkLiters - x.milkLiters,
    eggsCount - x.eggsCount
  )
  def *(x: Int): Ingredients =
    Ingredients(x * flourGrams, x * milkLiters, x * eggsCount)
}
