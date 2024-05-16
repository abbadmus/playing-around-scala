package foo

object Example {
  def main2(args: Array[String]): Unit = {
    println(hello())

    val multi = Array(Array(1, 2, 3), Array(4, 5, 6))

    println()

    for (arr <- multi; num <- arr) {
      println(num)
    }
    println()

    for (arr <- multi; num <- arr; if num % 2 == 0) println(num)

    println()

    var total = 0
    val items = Array(1, 10, 100, 1000)

    for (item <- items) {
      total += item
    }

    println(total)

    println()

    val items1 = Array("hello", "world")

    for (item <- items; item1 <- items1) {
      println(item + " " + item1)
    }

    val res: Array[String] = for (item <- items; item1 <- items1) yield {
      item + " " + item1
    }

    println(res.mkString("Array(", ", ", ")"))
    println()

    val flatten = for {
      a <- items
      b <- items1
    } yield (a, b)

    println(flatten.mkString("Array(", ", ", ")"))

    println(
      for (i <- Range.inclusive(1, 100); if i % 2 == 0) yield i
    )

    val flatten2 = for {
      i <- Range.inclusive(1, 100)
      if i % 2 == 0
    } yield i

    println(flatten2)

  }

  def hello(): String = "hello world"

  for (i <- Range.inclusive(1, 100, 2)) {
    println(
      if (i % 3 == 0 && i % 5 == 0) "Fizzbuzz"
      else if (i % 3 == 0) "Fizz"
      else if (i % 5 == 0) "buzz"
      else i
    )
  }

  def hello(title: String, firstName: String, lastNameOpt: Option[String]) = {
    lastNameOpt match {
      case Some(lastName) => println(s"Hello $title. $lastName")
      case None           => println(s"Hello $firstName")
    }
  }

}
