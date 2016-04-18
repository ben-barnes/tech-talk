package io.chronon.techtalk.future

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

object FutureExample {
  def main(args: Array[String]): Unit = {
    val calc = for {
      factors <- findRSAFactors
      halts <- solveHaltingProblem
    } yield (factors, halts)

    val result = Await.result(calc, 4 seconds)
    println(result)
  }

  def findRSAFactors = Future {
    println("Finding RSA factors.")
    Thread.sleep(1000)
    println("RSA factors are 7 and 13.")
    Seq(7, 13)
  }

  def solveHaltingProblem = Future {
    println("Solving halting problem.")
    Thread.sleep(2000)
    println("The program halts!")
    true
  }
}
