package foo

object ChapterSixAlgo {
  def main2(args: Array[String]): Unit = {
    println(mergeSort(Array(3, 5, 7, 2, 5, 3, 2)).mkString("Array(", ", ", ")"))

    println(mergeSort(Array(4,0,1,5,2,3)).mkString("Array(", ", ", ")"))

    println()
    val temp = Array("k", "e", "j", "b", "m", "a")
    println(mergeSortGeneric(Array[String]("k", "e", "j", "b", "m", "a")).mkString("Array(", ", ", ")"))
  }

  // merge sort
  def mergeSort(arr: Array[Int]): Array[Int] ={
    if (arr.length == 1 ) arr
    else {
      val (left, right) = arr.splitAt(arr.length / 2)
      val (sortedLeft, sortedRight) = (mergeSort(left), mergeSort(right))
      var (leftIdx, rightIdx) = (0, 0)
      val out = Array.newBuilder[Int]

      while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
        val takeLeft = (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
          case (true, false) => true
          case (false, true) => false
          case (true, true) => sortedLeft(leftIdx) < sortedRight(rightIdx)
        }

        if(takeLeft){
          out += sortedLeft(leftIdx)
          leftIdx += 1
        }else{
          out += sortedRight(rightIdx)
          rightIdx += 1
        }
      }
      out.result()
    }
  }

  def mergeSortGeneric[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
    if (items.length == 1) items
    else {
      val (left, right) = items.splitAt(items.length / 2)
      val (sortedLeft, sortedRight) = (mergeSortGeneric(left), mergeSortGeneric(right))
      var (leftIdx, rightIdx) = (0, 0)
      val out = IndexedSeq.newBuilder[T]

      while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
        val takeLeft = (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
          case (true, false) => true
          case (false, true) => false
          case (true, true) => Ordering[T].lt(sortedLeft(leftIdx), sortedRight(rightIdx))
        }

        if (takeLeft) {
          out += sortedLeft(leftIdx)
          leftIdx += 1
        } else {
          out += sortedRight(rightIdx)
          rightIdx += 1
        }
      }
      out.result()
    }

  }
}
