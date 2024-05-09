package foo

import java.io.BufferedReader
import scala.annotation.tailrec

object Assignment {

  def main(args: Array[String]): Unit = {
    // assignment 1
    def flexibleFizzBuzz(callback: String => Unit): Unit = {

      for (i <- Range.inclusive(1, 100)) {
        callback(
          if (i % 3 == 0 && i % 5 == 0) "Fizzbuzz"
          else if (i % 3 == 0) "Fizz"
          else if (i % 5 == 0) "buzz"
          else i.toString
        )
      }

    }

    flexibleFizzBuzz(x => println(x))
    println()

    // assignment 2
    class Msg(val id: Int, val parent: Option[Int], val text: String)

    def printMessage(messages: Array[Msg]): Unit = {

      def helper(prevParent: Option[Int], indent: String): Unit = {
        for (msg <- messages; if msg.parent == prevParent) {
          println(s"$indent#${msg.id} ${msg.text}")
          helper(Some(msg.id), indent + "  ")
        }
      }
      helper(None, "  ")
    }

    printMessage(
      Array(
        new Msg(0, None, "Hello"),
        new Msg(1, Some(0), "World"),
        new Msg(2, None, "I am Cow"),
        new Msg(3, Some(2), "Hear me moo"),
        new Msg(4, Some(2), "Here I stand"),
        new Msg(5, Some(2), "I am Cow"),
        new Msg(6, Some(5), "Here me moo, moo")
      )
    )

    def printMessages(messages: Array[Msg]): Unit = {

      def printFrag(parent: Option[Int], indent: String): Unit = {

        for (msg <- messages if msg.parent == parent) {
          println(s"$indent#${msg.id} ${msg.text}")
          printFrag(Some(msg.id), indent + "    ")
        }

      }

      printFrag(None, "")
    }

    println()
    printMessages(
      Array(
        new Msg(0, None, "Hello"),
        new Msg(1, Some(0), "World"),
        new Msg(2, None, "I am Cow"),
        new Msg(3, Some(2), "Hear me moo"),
        new Msg(4, Some(2), "Here I stand"),
        new Msg(5, Some(2), "I am Cow"),
        new Msg(6, Some(5), "Here me moo, moo")
      )
    )

    println()

    (2, 4, 6, 8)

    def flatten(input: List[List[Int]]): List[Int] = {
      val res: List[Int] =
        for (
          i <- input;
          j <- i;
          if j % 2 == 0
        ) yield j
      res
    }

    println(
      flatten(List(List(1), List(2), List(3), List(4, 5), List(6), List(8)))
    )

    def withFileWriter[T](
        fileName: String
    )(handler: java.io.BufferedWriter => T) = {
      val output =
        java.nio.file.Files.newBufferedWriter(java.nio.file.Paths.get(fileName))
      try handler(output)
      finally output.close()

    }

    withFileWriter("first file.txt") { writer =>
      writer.write("Hello\n")
      writer.write("world!\n")
    }

    def withFileReader[T](
        fileName: String
    )(handle: java.io.BufferedReader => T) = {
      val output =
        java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get(fileName))
      try handle(output)
      finally output.close()
    }

    val res = withFileReader("first file.txt") { reader =>
      reader.readLine() + "\n" + reader.readLine()

    }
    println(res)
    assert(res == "Hello\nworld!")

    // chater 4
    val sudokuGrid = Array(
      Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
      Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
      Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
      Array(2, 6, 3, 0, 1, 0, 0, 8, 0),
      Array(9, 7, 4, 8, 6, 3, 0, 0, 5),
      Array(8, 5, 1, 0, 9, 0, 6, 0, 0),
      Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
      Array(0, 0, 5, 2, 0, 6, 3, 0, 0)
    )

    def isValidSudoku(grid: Array[Array[Int]]) = {
      !Range(0, 9).exists { i =>
        val rows = Range(0, 9).map(grid(i)(_)).filter(_ != 0)

        val col = Range(0, 9).map(grid(_)(i)).filter(_ != 0)

        val square =
          Range(0, 9)
            .map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3))
            .filter(_ != 0)

        rows.distinct.length != rows.length ||
        col.distinct.length != col.length ||
        square.distinct.length != square.length

      }
    }

    println(isValidSudoku(sudokuGrid))

    assert(
      isValidSudoku(
        Array(
          Array(3, 0, 6, 5, 0, 8, 4, 0, 0),
          Array(5, 2, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 8, 7, 0, 0, 0, 0, 3, 1),
          Array(0, 0, 3, 0, 1, 0, 0, 8, 0),
          Array(9, 0, 0, 8, 6, 3, 0, 0, 5),
          Array(0, 5, 0, 0, 9, 0, 6, 0, 0),
          Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
          Array(0, 0, 5, 2, 0, 6, 3, 0, 0)
        )
      )
    )

    assert(
      !isValidSudoku(
        Array(
          Array(3, 0, 6, 5, 0, 8, 4, 0, 0),
          Array(5, 2, 0, 0, 0, 0, 0, 0, 0),
          Array(0, 8, 7, 0, 0, 0, 0, 3, 1),
          Array(0, 0, 3, 0, 1, 0, 0, 8, 0),
          Array(9, 0, 0, 8, 6, 3, 0, 0, 5),
          Array(0, 5, 0, 0, 9, 0, 6, 0, 0),
          Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
          Array(0, 0, 5, 2, 0, 6, 2, 0, 0)
        )
      )
    )

    assert(
      isValidSudoku(
        Array(
          Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
          Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
          Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
          Array(2, 6, 3, 0, 1, 0, 0, 8, 0),
          Array(9, 7, 4, 8, 6, 3, 0, 0, 5),
          Array(8, 5, 1, 0, 9, 0, 6, 0, 0),
          Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
          Array(0, 0, 5, 2, 0, 6, 3, 0, 0)
        )
      )
    )

    assert(
      isValidSudoku(
        Array(
          Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
          Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
          Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
          Array(2, 6, 3, 0, 1, 0, 0, 8, 0),
          Array(9, 7, 4, 8, 6, 3, 0, 0, 5),
          Array(8, 5, 1, 0, 9, 0, 6, 0, 0),
          Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
          Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
          Array(0, 0, 5, 2, 0, 6, 2, 0, 0)
        )
      ) == false
    )

    assert(
      isValidSudoku(
        Array(
          Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
          Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
          Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
          Array(2, 6, 3, 4, 1, 5, 9, 8, 7),
          Array(9, 7, 4, 8, 6, 3, 1, 2, 5),
          Array(8, 5, 1, 7, 9, 2, 6, 4, 3),
          Array(1, 3, 8, 9, 4, 7, 2, 5, 6),
          Array(6, 9, 2, 3, 5, 1, 8, 7, 4),
          Array(7, 4, 5, 2, 8, 6, 3, 1, 9)
        )
      ) == true
    )

    assert(
      isValidSudoku(
        Array(
          Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
          Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
          Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
          Array(2, 6, 3, 4, 1, 5, 9, 8, 7),
          Array(9, 7, 4, 8, 6, 3, 1, 2, 5),
          Array(8, 5, 1, 7, 9, 2, 6, 4, 3),
          Array(1, 3, 8, 9, 4, 7, 2, 5, 6),
          Array(6, 9, 2, 3, 5, 1, 8, 7, 4),
          Array(7, 4, 5, 2, 8, 6, 3, 1, 8)
        )
      ) == false
    )

    def renderSudoku(grids: Array[Array[Int]]) = {
      println("+-------+-------+-------+")
      Range(0, 9).foreach{ index =>
        val tempArr = grids(index).grouped(3).toArray
        for (ele <- tempArr) {
          print("| ")
          ele.foreach(x => if (x != 0) print(s"$x ") else print("  "))
        }
        println("|")
        if( (index + 1) % 3 == 0) println("+-------+-------+-------+")
      }

    }

    renderSudoku(Array(
      Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
      Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
      Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
      Array(2, 6, 3, 4, 1, 5, 9, 8, 7),
      Array(9, 7, 4, 8, 6, 3, 1, 2, 5),
      Array(8, 5, 1, 7, 9, 2, 6, 4, 3),
      Array(1, 3, 8, 9, 4, 7, 2, 5, 6),
      Array(6, 9, 2, 3, 5, 1, 8, 7, 4),
      Array(7, 4, 5, 2, 8, 6, 3, 1, 8)
    ))

    println()
    println()

    renderSudoku(Array(
      Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
      Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
      Array(4, 8, 7, 6, 2, 9, 5, 3, 1),

      Array(2, 6, 3, 0, 1, 0, 0, 8, 0),
      Array(9, 7, 4, 8, 6, 3, 0, 0, 5),
      Array(8, 5, 1, 0, 9, 0, 6, 0, 0),

      Array(1, 3, 0, 0, 0, 0, 2, 5, 0),
      Array(0, 0, 0, 0, 0, 0, 0, 7, 4),
      Array(0, 0, 5, 2, 0, 6, 3, 0, 0)
    ))

  }

}
