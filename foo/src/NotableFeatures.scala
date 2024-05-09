package foo

object NotableFeatures {
  def main(args: Array[String]): Unit = {

    def getDayMonthYear(s: String) = s match {
      case s"$day-$month-$year" => println(s"found day: $day, month: $month, year: $year")
      case _ => println("not a date")
    }

    getDayMonthYear("10-12-1994")
    getDayMonthYear("12-1994")

    // patter matching
    def dayOfWeek(x: Int) = x match {
      case 1 => "Mon"; case 2 => "Tue";
      case 3 => "Wed"; case 4 => "Thu";
      case 5 => "Fri"; case 6 => "Sat";
      case 7 => "Sun"; case _ => "Unknown";
    }

    println(dayOfWeek(2))
    println(dayOfWeek(9))
    println(dayOfWeek(3))

    for(i <- Range(0, 100)){
      val modulus: String = (i % 3, i % 5) match {
        case (0, 0) => "FizzBuzz"
        case (0, _) => "Fizz"
        case (_, 0) => "Buzz"
        case (_, _) => i.toString
      }

      println(modulus)
    }

    case class Person(name: String, title: String)

    def greeting(p: Person) = p match {
      case Person(s"$firstName $lastName", title) => println(s"Hello $title $lastName")
      case Person(name, title) => println(s"Hello $title $name")
    }

    println()
    greeting(Person("Abdulwaheed", "Mr"))
    greeting(Person("Abdulwaheed Badmus", "Mr"))

    // destructure
    val person = Person("Abdul", "Mr")
    val Person(x, y) = person

    println()
    println(x)
    println(y)

    // flip
    val s"$first $second" = "hello world"

    val flipped = s"$second $first"
    println(flipped)


  }

  sealed trait Json
  case class Null() extends Json
  case class Bool(value: Boolean) extends Json
  case class Str(value: String) extends Json
  case class Num(value: Double) extends Json
  case class Arr(value: Seq[Json]) extends Json
  case class Dict(value: Map[String, Json]) extends Json


}
