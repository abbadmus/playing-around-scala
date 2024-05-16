package foo

import scala.collection.mutable

object CollectsOps {
  def main(args: Array[String]): Unit = {
    val arr: mutable.ArrayBuilder[Int] = Array.newBuilder[Int]

    arr += 1
    arr += 3
    arr += 4

    println(arr.result().mkString("Array(", ", ", ")"))

    val arr2 = Array.fill(5, 3)("hello")
    println(arr2.mkString("Array(", ", ", ")"))
    println(arr2.length)
    println(arr2(1).mkString("Array(", ", ", ")"))

    // transform
    val arr3 = Array(1, 2, 3, 4, 5, 6, 6, 4, 3, 64)

    println(arr3.map(x => x * 2).mkString("[", ", ", "]"))
    println()
    println(arr3.filter(x => x % 2 == 0).mkString("Array(", ", ", ")"))
    println()
    println(arr3.take(4).mkString("Array(", ", ", ")"))
    println()
    println(arr3.drop(4).mkString("Array(", ", ", ")"))
    println()
    println(arr3.slice(2, 4).mkString("Array(", ", ", ")"))
    println()
    println(arr3.distinct.mkString("Array(", ", ", ")"))

    // Queries
    println()
    println()
    println(arr3.find(x => x == 4))
    println(arr3.contains(3))
    // or
    println(arr3.exists(x => x == 3))

    // agg
    println(arr3.foldLeft(0)((x, y) => x + y))

    println(arr3.foldLeft(1)((x, y) => x * y))

    println(arr3.foldRight(1)((x, y) => x + y))

    // grouping
    println()
    println(arr3.groupBy(x => x % 2)(0).mkString("Array(", ", ", ")"))

    // combining function
    def std(arr: Array[Int]): Double = {
      val mean = arr.foldLeft(0.0)(_ + _) // arr.sum
      val sqrError = arr.map(x => x - mean).map(x => x * x)
      Math.sqrt(sqrError.sum / sqrError.length)
    }

    println()

    def isValidSudoku(grid: Array[Array[Int]]): Boolean = {
      !Range(0, 9).exists { i =>
        val row = Range(0, 9).map(grid(i)(_))
        val col = Range(0, 9).map(grid(_)(i))

        val square =
          Range(0, 9).map(j => grid((i % 3) * 3 + j % 3)((i / 3) * 3 + j / 3))
        row.distinct.length != row.length || col.distinct.length != col.length || square.distinct.length != square.length
      }
    }

    val invalidSudoku: Array[Array[Int]] = Array(
      Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
      Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
      Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
      Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
      Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
      Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
      Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
      Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
      Array(3, 4, 5, 2, 8, 6, 1, 7, 3) // Last row has a duplicate "3"
    )

    val isInvalid = isValidSudoku(invalidSudoku)
    println(isInvalid)

    val sudoku1: Array[Array[Int]] = Array(
      Array(5, 3, 4, 6, 7, 8, 9, 1, 2),
      Array(6, 7, 2, 1, 9, 5, 3, 4, 8),
      Array(1, 9, 8, 3, 4, 2, 5, 6, 7),
      Array(8, 5, 9, 7, 6, 1, 4, 2, 3),
      Array(4, 2, 6, 8, 5, 3, 7, 9, 1),
      Array(7, 1, 3, 9, 2, 4, 8, 5, 6),
      Array(9, 6, 1, 5, 3, 7, 2, 8, 4),
      Array(2, 8, 7, 4, 1, 9, 6, 3, 5),
      Array(3, 4, 5, 2, 8, 6, 1, 7, 9)
    )

    val sudoku2: Array[Array[Int]] = Array(
      Array(6, 5, 9, 1, 3, 8, 7, 2, 4),
      Array(4, 8, 7, 6, 5, 2, 9, 1, 3),
      Array(3, 1, 2, 7, 9, 4, 5, 8, 6),
      Array(8, 6, 1, 9, 7, 3, 4, 5, 2),
      Array(9, 4, 3, 5, 2, 1, 6, 7, 8),
      Array(7, 2, 5, 4, 8, 6, 1, 3, 9),
      Array(1, 7, 4, 3, 6, 5, 8, 9, 2),
      Array(2, 3, 8, 8, 1, 7, 3, 4, 5),
      Array(5, 9, 6, 2, 4, 9, 2, 6, 7)
    )

    val sudoku3: Array[Array[Int]] = Array(
      Array(3, 1, 6, 5, 7, 8, 4, 9, 2),
      Array(5, 2, 9, 1, 3, 4, 7, 6, 8),
      Array(4, 8, 7, 6, 2, 9, 5, 3, 1),
      Array(2, 6, 3, 4, 1, 7, 8, 5, 9),
      Array(9, 7, 4, 8, 5, 3, 6, 1, 2),
      Array(8, 5, 1, 9, 6, 2, 3, 4, 7),
      Array(1, 3, 8, 7, 9, 6, 2, 4, 5),
      Array(6, 9, 2, 3, 4, 5, 1, 7, 3),
      Array(7, 4, 5, 2, 8, 1, 9, 2, 6)
    )

    val sudoku4: Array[Array[Int]] = Array(
      Array(8, 2, 7, 1, 5, 4, 3, 9, 6),
      Array(9, 6, 5, 3, 2, 7, 1, 4, 8),
      Array(3, 4, 1, 6, 8, 9, 7, 5, 2),
      Array(5, 9, 3, 4, 6, 8, 2, 7, 1),
      Array(4, 7, 2, 9, 1, 3, 6, 8, 5),
      Array(6, 1, 8, 2, 7, 5, 9, 3, 4),
      Array(7, 8, 6, 5, 3, 2, 4, 1, 9),
      Array(1, 5, 9, 7, 4, 6, 8, 2, 3),
      Array(2, 3, 4, 8, 9, 1, 5, 6, 7)
    )

    val sudoku5: Array[Array[Int]] = Array(
      Array(1, 5, 6, 8, 7, 9, 3, 2, 4),
      Array(9, 2, 3, 4, 5, 1, 6, 8, 7),
      Array(8, 4, 7, 3, 2, 6, 5, 9, 1),
      Array(5, 1, 9, 7, 3, 4, 8, 6, 2),
      Array(3, 7, 4, 6, 1, 8, 2, 5, 9),
      Array(2, 6, 8, 9, 4, 5, 1, 7, 3),
      Array(7, 3, 2, 5, 6, 7, 4, 1, 8),
      Array(6, 8, 1, 2, 9, 3, 7, 4, 5),
      Array(4, 9, 5, 1, 8, 2, 9, 3, 6)
    )

    val sudokus = Seq(sudoku1, sudoku2, sudoku3, sudoku4, sudoku5)

    sudokus.zipWithIndex.foreach { case (sudoku, index) =>
      val isValid = isValidSudoku(sudoku)
      println(s"Sudoku Grid ${index + 1} is valid: $isValid")
    }

    val myVec = Vector.newBuilder[Int]
    val myVec2 = myVec += 22

    val myVec3 = Vector(1, 2, 3, 4, 5, 3, 834, 23, 2)

    val myVec4 = Vector.tabulate(3)(n => n)
    println(myVec4)

    val myVec5 = myVec3.map(_ * 3).filter(x => x % 2 == 0).take(3)
    println(myVec5)

    val myVec6 = myVec3.view.map(_ * 3).filter(x => x % 2 == 0).take(3).toVector
    println(myVec6)

    val myVec7 = myVec6 :+ 4

    myVec.addOne(1)

    val mySet = Set(1, 2, 5, 3, 6, 3)

    val mySet2 = Set.newBuilder[Int]

    mySet2 += 2
    mySet2 += 5

    for (i <- mySet) println(i)
    println()
    println()

    val myMap: Map[Int, Int] = Map((1, 4), (2, 5), (344, 543))
    println(myMap.get(1))
    println(myMap.get(12))
    println(myMap.contains(43))
    myMap.getOrElse(4, 5)
    myMap.get(3)

    Map.newBuilder[Int, String]

    // Mutable collection
    val myArrayDeque = mutable.ArrayDeque(1, 2, 4, 7, 5)
    myArrayDeque.removeHead()
    println(myArrayDeque)
    myArrayDeque.addAll(Seq(1, 2, 3, 4))

    val newArr = Array(1, 2, 3, 45, 4)
    newArr :+ 3
    println(newArr.mkString("Array(", ", ", ")"))

    val mutableMap = collection.mutable.Map((1, 2), (3, 4))
    mutableMap.map { case (x, y) => (x + 1, y + 1) }
    println(mutableMap)

    mutableMap.mapValuesInPlace((x, y) => (y + 1))

    def getIndexTwoAndFour[T](items: IndexedSeq[T]) = (items(2), items(4))

    println(getIndexTwoAndFour(Vector(1, 2, 3, 4, 5, 5)))

    val myList = List(1, 2, 3, 4, 5)
    myList(2)

    val num: List[Int] = List(1, 2, 4, 5, 7, 8, 9)
    println(num.partition(x => x < 4))

  }

}
