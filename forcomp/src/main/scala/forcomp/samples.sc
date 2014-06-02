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
}