package streams

object t2 {
  def x = (a: Int, b: Int) => a + b               //> x: => (Int, Int) => Int
  
  x(3,4)                                          //> res0: Int = 7
}