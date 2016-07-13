# [Cats](https://github.com/typelevel/cats) extensions for [uscala](https://github.com/albertpastrana/uscala)

[![Build Status](https://travis-ci.org/janstenpickle/uscala-cats.svg?branch=master)](https://travis-ci.org/janstenpickle/uscala-cats)

This currently has just one module:

|Name|Description|Download|
|---|---|---|
|**Result** | Provides `ResultsNel` type and a Cats Applicative instance for `Result` | [![Download](https://api.bintray.com/packages/janstenpickle/maven/uscala-cats-result/images/download.svg)](https://bintray.com/janstenpickle/maven/uscala-cats-result/_latestVersion) |


## Install with SBT
Add the following to your sbt `project/plugins.sbt` file:
```scala
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
```
Then add the following to your `build.sbt`
```scala
resolvers += Resolver.bintrayRepo("janstenpickle", "maven")
libraryDependencies += "uscala-cats" %% "uscala-cats-result" % "0.1.0"

## Usage

This library allows usage of the cats scream operator (`|@|`) on the uscala Result type.

```scala
import cats.std.all._
import cats.syntax.cartesian._
import uscala.cats.result.applicative._
import uscala.cats.syntax.result._

object Example {
  
  val result = 
    (ResultNel.ok[String, String]("test1") |@|
     ResultNel.fail[String, String]("test2") |@|
     Result.fail[String, String]("test3").toResultNel).map(_ + _ + _) // = Fail(NonEmptyList("test2", "test3"))
}
```