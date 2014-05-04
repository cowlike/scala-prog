object TestSheet {
  import funsets.Exercises._
  
  prodInt(2, 8)                                   //> res0: Int = 40320
  prodIntTail(2)(3)                               //> res1: Int = 6
  
  prodInt(1, 5)                                   //> res2: Int = 120
  def fact = prodIntTail(1)_                      //> fact: => Int => Int
  fact(5)                                         //> res3: Int = 120
  
  sum(a => a)(1, 5)                               //> res4: Int = 15
  sum(identity)(1,5)                              //> res5: Int = 15
  
  prod(x => x)(1, 5)                              //> res6: Int = 120
  
  rangeOp(x => x)((x,y) => x * y, 1)(1, 5)        //> res7: Int = 120
  rangeOp(x => x)((x,y) => x + y, 0)(1, 5)        //> res8: Int = 15
  
  //pass all the parameters
  def sum1(a: Int, b: Int) = rangeOp(x => x)((x,y) => x + y, 0)(a,b)
                                                  //> sum1: (a: Int, b: Int)Int
  //partially apply the parameters
  def prod1 = rangeOp(x => x)((x,y) => x * y, 1)_ //> prod1: => (Int, Int) => Int
  sum1(1,5)                                       //> res9: Int = 15
  prod1(1,5)                                      //> res10: Int = 120
  
  sum(a => a * a)(1,5)                            //> res11: Int = 55
  rangeOp(a => a * a)((x,y)=> x + y, 0)(1,5)      //> res12: Int = 55
}