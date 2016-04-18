package io.chronon.techtalk.future

import scalaz.Reader
import scala.util.Try
import scala.language.postfixOps

object ReaderExample {
  case class Config(x: Int)

  def checkEven = Reader {
    (c: Config) => {
      (c.x % 2) == 0
    }
  }

  def checkPositive = Reader {
    (c: Config) => {
      c.x > 0
    }
  }

  def main(args: Array[String]): Unit = {
    val check = for {
      even <- checkEven
      positive <- checkPositive
    } yield even && positive

    val x = Try(args(0) toInt) getOrElse 0
    val conf = Config(x)

    val result = check.run(conf)
    result match {
      case true => println("Config is OK.")
      case false => println("Config is NOT OK!")
    }
  }
}
