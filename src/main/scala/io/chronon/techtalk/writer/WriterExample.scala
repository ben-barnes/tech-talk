package io.chronon.techtalk.writer

import scala.language.postfixOps
import scala.util.Try

import scalaz.std.string.stringInstance

object WriterExample {
  def main(args: Array[String]): Unit = {
    import Functions._

    val limit = Try(args(0) toInt) getOrElse 1

    val calc1 = for {
      nums <- enumerate(limit)
      summed <- sum(nums)
      result <- square(summed)
    } yield result

    val calc2 = for {
      squared <- square(limit)
      even <- isEven(squared)
    } yield even

    val calc = for {
      res1 <- calc1
      res2 <- calc2
    } yield (res1, res2)

    val (log, result) = calc.run
    println(s"Result: $result")
    println(s"Log:\n$log")
  }
}
