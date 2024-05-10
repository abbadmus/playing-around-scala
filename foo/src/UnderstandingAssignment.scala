object UnderstandingAssignment {
  def main(args: Array[String]): Unit = {


  }

  trait StringParser[T]{
    def parse(s: String): T
  }

  object StringParser{
    implicit object IntParser extends StringParser[Int] {
      override def parse(s: String): Int = s.toInt
    }
    implicit object BoolParser extends StringParser[Boolean] {
      override def parse(s: String): Boolean = s.toBoolean
    }
    implicit object DoubleParser extends StringParser[Double] {
      override def parse(s: String): Double = s.toDouble
    }
  }

  def fromStringParser[T: StringParser](s: String) = {
    val parser = implicitly[StringParser[T]]
    parser.parse(s)

  }

  import StringParser.IntParser

  println(fromStringParser("45"))


}
