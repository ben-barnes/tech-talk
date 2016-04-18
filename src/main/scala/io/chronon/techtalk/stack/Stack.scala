package io.chronon.techtalk.stack

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Try

import stack.WithOption
import scalaz._
import Scalaz._

object Stack {
  def main(args: Array[String]): Unit = {
    val check = for {
      positive <- isPositive
      even <- isEven
    } yield even

    val x = Try(args(0) toInt) getOrElse 0
    val resultFuture = check.run.run.run(Config(x))
    val (log, result) = Await.result(resultFuture, 10 seconds)
    println(s"Result: $result")
    println(s"Log:\n$log")
  }

  def isEven = WithOption {
    (c: Config) => Future {
      Thread.sleep(4000)
      if(c.x > 0) {
        val result = (c.x % 2) == 0
        (s"Checked whether ${c.x} was even.\n", Some(result))
      }
      else ("Cannot check evenness of negative number.\n", None)
    }
  }

  def isPositive = WithOption {
    (c: Config) => Future {
      Thread.sleep(4000)
      if(c.x > 0) {
        (s"${c.x} is positive.\n", Some(true))
      }
      else {
        (s"${c.x} is negative!\n", None)
      }
    }
  }
}
