package infra

import cats.effect.IO
import cats.effect.std.Queue
import cats.effect.unsafe.implicits.global
import cats.syntax.all._
import domain._
import fs2.{Stream, Pipe}
import scala.concurrent.duration._

object ServerLogic {
  def accumulate(
      input: Stream[IO, PancakeIngredient],
      fryingQueue: Queue[IO, PortionIngredients]
  ): Stream[IO, IngredientsReceived] = {
    val xxx: Stream[IO, (Ingredients, IngredientsReceived)] =
      input.evalMapAccumulate(Ingredients(0, 0, 0)) {
        (ingredients, ingredient) =>
          val newIngredients = ingredients + ingredient
          val (remainingIngredients, portionIngredients) =
            PortionIngredients.from(newIngredients)
          val enqueueFrying = portionIngredients match {
            case None        => IO.pure()
            case Some(value) => fryingQueue.offer(value)
          }
          enqueueFrying.map { _ =>
            (remainingIngredients, IngredientsReceived(ingredient))
          }
      }

    xxx.map(_._2)
  }

  def fryingPan(count: Int): Stream[IO, PancakeReady.type] =
    Stream.awakeEvery[IO](1.second).map(_ => PancakeReady).take(count)

  def fry(
      fryingPans: Int,
      fryingQueue: Queue[IO, PortionIngredients]
  ): Stream[IO, PancakeReady.type] = {
    val xxx = fryingQueue.take.map[Stream[IO, PancakeReady.type]] {
      portionIngredients =>
        val singlePanPancakesCount =
          portionIngredients.pancakeCount / fryingPans
        val extraPancakesCount = portionIngredients.pancakeCount % fryingPans
        val pancaktesPerFryingPan = (1 to fryingPans).map(x =>
          singlePanPancakesCount + (if (x <= extraPancakesCount) 1 else 0)
        )

        val yyy: Stream[IO, PancakeReady.type] =
          Stream(pancaktesPerFryingPan: _*).map(fryingPan).parJoinUnbounded

        // TODO: How to go from IO[Strem[IO, PancakeReady.type]] to Stream[IO, PancakeReady.type]

        yyy
    }

    ???
  }

  def bakePancakes(
      fryingPans: Int
  ): IO[Either[String, Pipe[IO, PancakeIngredient, PancakeStatus]]] = {
    Queue.bounded[IO, PortionIngredients](10).map { fryingQueue =>
      Right { input: Stream[IO, PancakeIngredient] =>
        accumulate(input, fryingQueue).merge(fry(fryingPans, fryingQueue))
      }
    }
  }
}
