package forcomp

object forcomp3 {
  val l1 = List(1, 2)                             //> l1  : List[Int] = List(1, 2)
  val l2 = List(3, 4)                             //> l2  : List[Int] = List(3, 4)
  l1 zip l2                                       //> res0: List[(Int, Int)] = List((1,3), (2,4))
  List(1) ::: List()                              //> res1: List[Int] = List(1)
  List() ::: List(1)                              //> res2: List[Int] = List(1)

  val l3 = List(
    List(
      List(('a', 2)),
      List(('a', 1)),
      List()),
    List(
      List(('b', 2)),
      List(('b', 1)),
      List()))                                    //> l3  : List[List[List[(Char, Int)]]] = List(List(List((a,2)), List((a,1)), Li
                                                  //| st()), List(List((b,2)), List((b,1)), List()))
      
	l3.head flatMap (x => for (v <- (l3.tail.head)) yield List(x ::: v).flatten)
                                                  //> res3: List[List[(Char, Int)]] = List(List((a,2), (b,2)), List((a,2), (b,1)),
                                                  //|  List((a,2)), List((a,1), (b,2)), List((a,1), (b,1)), List((a,1)), List((b,2
                                                  //| )), List((b,1)), List())
	
	l3.tail.head                              //> res4: List[List[(Char, Int)]] = List(List((b,2)), List((b,1)), List())
}