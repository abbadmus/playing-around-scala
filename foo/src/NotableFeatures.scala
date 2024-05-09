package foo

object NotableFeatures {
  def main(args: Array[String]): Unit = {

    def getDayMonthYear(s: String) = s match {
      case s"$day-$month-$year" => println(s"found day: $day, month: $month, year: $year")
      case _ => println("not a date")
    }

    getDayMonthYear("10-12-1994")
    getDayMonthYear("12-1994")


  }

  sealed trait Json
  case class Null() extends Json
  case class Bool(value: Boolean) extends Json
  case class Str(value: String) extends Json
  case class Num(value: Double) extends Json
  case class Arr(value: Seq[Json]) extends Json
  case class Dict(value: Map[String, Json]) extends Json


}
