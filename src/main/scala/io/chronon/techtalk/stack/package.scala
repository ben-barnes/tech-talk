package io.chronon.techtalk.stack

import scala.concurrent.Future
import scalaz.{OptionT, ReaderT, WriterT}

package object stack {
  type WithReader[A] = ReaderT[Future, Config, A]
  def WithReader[A](a: Config => Future[A]) =
    ReaderT[Future, Config, A](a)

  type WithWriter[A] = WriterT[WithReader, String, A]
  def WithWriter[A](a: Config => Future[(String, A)]) =
    WriterT[WithReader, String, A](WithReader(a))

  type WithOption[A] = OptionT[WithWriter, A]
  def WithOption[A](a: Config => Future[(String, Option[A])]) =
    OptionT[WithWriter, A](WithWriter(a))
}
