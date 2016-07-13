package uscala.cats.result

import cats.{Applicative, Semigroup}
import uscala.result.Result
import uscala.result.Result.{Fail, Ok}

object applicative {
  implicit def resultApplicative[E : Semigroup]: Applicative[Result[E, ?]] =
    new Applicative[Result[E, ?]] {
      override def pure[A](x: A): Result[E, A] = Result.ok(x)

      override def ap[A, B](f: Result[E, (A) => B])
                           (fa: Result[E, A]): Result[E, B] =
        (fa, f) match {
          case (Ok(a), Ok(fab)) => Ok(fab(a))
          case (Fail(e1), Fail(e2)) => Fail(Semigroup[E].combine(e1, e2))
          case (Ok(_), i@Fail(_)) => i
          case (i@Fail(_), Ok(_)) => i
        }

      override def product[A, B](fa: Result[E, A],fb: Result[E, B]): Result[E, (A, B)] = ap(map(fa)(a => (b: B) => (a, b)))(fb)

      override def map[A, B](fa: Result[E, A])(f: A => B): Result[E, B] = ap(pure(f))(fa)
    }
}
