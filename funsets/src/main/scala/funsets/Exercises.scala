package funsets

object Exercises {
  def prod(a: Int, b: Int): Int = {
    if (a > b)
      1
    else
      a * prod(a + 1, b)
  }

  def prodTail(a: Int)(b: Int): Int = {
    def ptr(a: Int, b: Int, acc: Int): Int = {
      if (a > b)
        acc
      else
        ptr(a + 1, b, acc * a)
    }
    ptr(a, b, 1)
  }
}