package foo

object WorkingWithOption {
  def main(args: Array[String]): Unit = {
    println(Option(3))

    def nameLength(name: Option[String]) = { name.map(_.length).getOrElse(-1) }

    println(nameLength(Some("the first option")))
  }

}
