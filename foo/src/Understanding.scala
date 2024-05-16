package foo

object Understanding {
  def main(args: Array[String]): Unit = {
    val args = Seq("123", "true", "34.34")

    object ParseInt extends StrParser[Int] {
      override def parse(s: String): Int = s.toInt
    }
    object ParseBoolean extends StrParser[Boolean] {
      override def parse(s: String): Boolean = s.toBoolean
    }
    object ParseDouble extends StrParser[Double] {
      override def parse(s: String): Double = s.toDouble
    }

    println(ParseInt.parse(args.head))
    println(ParseBoolean.parse(args(1)))
    println(ParseDouble.parse(args(2)))
    println()
    println()
    println(ParseDouble.parse(args(2)))


  }

  trait StrParser[T]{
    def parse(s: String): T
  }

  object StrParser{
    implicit object ParseInt extends StrParser[Int] {
      override def parse(s: String): Int = s.toInt
    }

    implicit object ParseBoolean extends StrParser[Boolean] {
      override def parse(s: String): Boolean = s.toBoolean
    }

    implicit object ParseDouble extends StrParser[Double] {
      override def parse(s: String): Double = s.toDouble
    }



  }

  val args = Seq("323", "false", "34.34")
  println(parseFromStr2[Int](args.head))
  println()


  // context bounded
  def parseFromStr1[T](s: String)(implicit parser: StrParser[T])={
    parser.parse(s)
  }
  //or
  def parseFromStr2[T: StrParser](s: String) = {
    val parser = implicitly[StrParser[T]]
    parser.parse(s)
  }

  implicit def ParseSeq[T](implicit p: StrParser[T]): StrParser[Seq[T]] = new StrParser[Seq[T]] {
    override def parse(s: String): Seq[T] = s.split(",").toSeq.map(p.parse)
  }

  println(parseFromStr2[Seq[Boolean]]("true,false,false,true"))

  implicit def ParseTuble[T, V](implicit parser1: StrParser[T],  parser2: StrParser[V]): StrParser[(T, V)] = new StrParser[(T, V)] {
    override def parse(s: String): (T, V) = {
      val Array(left, right) = s.split("=")
      (parser1.parse(left), parser2.parse(right))
    }
  }

  println(parseFromStr2[(Int, Boolean)]("123=true"))



}
