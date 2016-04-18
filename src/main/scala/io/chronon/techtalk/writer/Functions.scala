package io.chronon.techtalk.writer

import scalaz.Writer

object Functions {
  def isEven(n: Int) = {
    val result = (n % 2) == 0
    val message = result match {
      case true => s"$n is even.\n"
      case false => s"$n is NOT even.\n"
    }
    Writer(message, result)
  }

  def enumerate(n: Int) = {
    val start = 1
    val range = start to n
    Writer(s"Generated numbers from $start to $n\n", range)
  }

  def sum(ns: Seq[Int]) = {
    val result = ns.sum
    Writer(s"Summed ${ns.length} numbers.\n", result)
  }

  def square(n: Int) = {
    val result = n * n
    Writer(s"Squared $n.\n", result)
  }
}
