package foo

object Understanding {
  def main(args: Array[String]): Unit = {

  }

  trait StrParser[T]{
    def parse(s: String): T
  }

  object ParseInt extends StrParser[Int] {
    override def parse(s: String): Int = s.toInt
  }
  object ParseBoolean extends StrParser[Boolean] {
    override def parse(s: String): Boolean = s.toBoolean
  }
  object ParseDouble extends StrParser[Double] {
    override def parse(s: String): Double = s.toDouble
  }
   val args = Seq("123", "true", "34.34")

  println(ParseInt.parse(args.head))
  println(ParseBoolean.parse(args(1)))
  println(ParseDouble.parse(args(2)))

}
