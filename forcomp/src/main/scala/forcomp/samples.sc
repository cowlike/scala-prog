package forcomp

object samples {
  def scalarProducts(xs: List[Double], ys: List[Double]): Double =
    (for ((x, y) <- xs zip ys) yield x * y).sum   //> scalarProducts: (xs: List[Double], ys: List[Double])Double

  def scalarProducts2(xs: List[Double], ys: List[Double]): Double =
    (for {
      x <- xs
      y <- ys
    } yield x * y).sum                            //> scalarProducts2: (xs: List[Double], ys: List[Double])Double

  val xs = List(1.0, 2, 3, 4)                     //> xs  : List[Double] = List(1.0, 2.0, 3.0, 4.0)
  val ys = List(5.0, 6, 7, 8)                     //> ys  : List[Double] = List(5.0, 6.0, 7.0, 8.0)
  scalarProducts(xs, ys)                          //> res0: Double = 70.0
  scalarProducts2(xs, ys)                         //> res1: Double = 260.0
  xs zip ys map { case (x, y) => x * y } sum      //> res2: Double = 70.0

  type Word = String
  type Occurrences = List[(Char, Int)]

  def wordOccurrences(w: Word): Occurrences = {
    def addLetter(w: List[Char], acc: Map[Char,Int]): Map[Char,Int] =
      w match {
        case List() => acc
        case x :: xs => acc get x match {
        	case Some(n) => addLetter(xs, acc updated(x, n + 1))
        	case None => addLetter(xs, acc updated(x, 1))
        }
      }

    addLetter(w.toLowerCase().toList, Map()).foldLeft(List(): List[(Char, Int)]) {(t, v) => (v._1, v._2) :: t}
  }                                               //> wordOccurrences: (w: forcomp.samples.Word)forcomp.samples.Occurrences

	wordOccurrences("bc")                     //> res3: forcomp.samples.Occurrences = List((c,1), (b,1))
}