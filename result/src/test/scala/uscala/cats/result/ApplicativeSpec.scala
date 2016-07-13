package uscala.cats.result

import cats.std.all._
import cats.syntax.cartesian._
import org.specs2.{ScalaCheck, Specification}
import uscala.cats.syntax.result._
import applicative._
import org.scalacheck.{Gen, Prop}
import uscala.result.Result
import uscala.result.specs2.ResultMatchers

class ApplicativeSpec extends Specification with ResultMatchers with ScalaCheck  {
  import ApplicativeSpec._

  override def is =
    s2"""
        Can collect multiple failures on the left of a result $test
      """

  def test = Prop.forAllNoShrink(list)(errs =>
    errs.foldLeft(ResultNel.ok[String, String](new String))((acc, v) =>
      (acc |@| Result.fail[String, String](v).toResultNel).map(_ + _)
    ) must beFail.like { case a => a.unwrap must containTheSameElementsAs(errs) }
  )
}

object ApplicativeSpec {
  val stringGen = Gen.alphaStr.suchThat(_.nonEmpty)
  val list = Gen.listOf(stringGen).suchThat(_.nonEmpty)
}
