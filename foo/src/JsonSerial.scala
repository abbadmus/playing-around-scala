package foo

import scala.collection.mutable.ArrayBuffer

object JsonSerial {
  def main2(args: Array[String]): Unit = {}

}

sealed trait Value
case class Str(value: String) extends Value
case class Num(value: Double) extends Value
case class Arr(value: ArrayBuffer[Value]) extends Value
case class Obj(value: Map[String, Value]) extends Value

sealed trait Bool extends Value
case object False extends Bool
case object True extends Bool
case object Null extends Value
