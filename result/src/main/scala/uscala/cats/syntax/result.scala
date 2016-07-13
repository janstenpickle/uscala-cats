package uscala.cats.syntax

import cats.SemigroupK
import cats.data._
import uscala.result.Result
import cats.std.all._

object result {
  implicit def ResultNelSemigroup[T] = SemigroupK[NonEmptyList].algebra[T]

  type ResultNel[A, B] = Result[NonEmptyList[A], B]

  object ResultNel {
    def fail[A, B](a: A): ResultNel[A, B] = Result.fail(NonEmptyList(a))
    def ok[A, B](b: B): ResultNel[A, B] = Result.ok(b)
  }

  implicit class ResultExtensions[A, B](result: Result[A, B]) {
    def toResultNel: ResultNel[A, B] = result.mapFail(NonEmptyList(_))
  }
}
