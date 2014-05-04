object TestSheet {
  import funsets.Exercises._
  
  prod(2, 8)                                      //> res0: Int = 40320
  prodTail(2)(3)                                  //> res1: Int = 6
  
  prod(1,5)                                       //> res2: Int = 120
  def fact = prodTail(1)_                         //> fact: => Int => Int
  fact(5)                                         //> res3: Int = 120
}