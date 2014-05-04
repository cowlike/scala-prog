package funsets

object Exercises {
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b)
      0
    else
      f(a) + sum(f)(a + 1, b)
  }

  def prod(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b)
      1
    else
      f(a) * prod(f)(a + 1, b)
  }

  def rangeOp(f: Int => Int)(g: (Int, Int) => Int, base: Int)(a: Int, b: Int): Int = {
    if (a > b)
      base
    else
      g(f(a), rangeOp(f)(g, base)(a + 1, b))
  }

  def prodInt(a: Int, b: Int): Int = {
    if (a > b)
      1
    else
      a * prodInt(a + 1, b)
  }

  def prodIntTail(a: Int)(b: Int): Int = {
    def ptr(a: Int, b: Int, acc: Int): Int = {
      if (a > b)
        acc
      else
        ptr(a + 1, b, acc * a)
    }
    ptr(a, b, 1)
  }
}