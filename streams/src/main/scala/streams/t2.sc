package streams

object t2 {
	(3 #:: List(1,2).toStream).toSet          //> res0: scala.collection.immutable.Set[Int] = Set(3, 1, 2)
	
	List(1,2) ++ List(3,4)                    //> res1: List[Int] = List(1, 2, 3, 4)
	val list1 = List(1,2)                     //> list1  : List[Int] = List(1, 2)
	val list2 = List(1,2)                     //> list2  : List[Int] = List(1, 2)
	list1 == list2                            //> res2: Boolean = true
	
	val s1 = List(1,2,3).toStream             //> s1  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
	val s2 = List(4).toStream                 //> s2  : scala.collection.immutable.Stream[Int] = Stream(4, ?)
	(s1 #::: s2).toSet                        //> res3: scala.collection.immutable.Set[Int] = Set(1, 2, 3, 4)
	0 #:: s1                                  //> res4: scala.collection.immutable.Stream[Int] = Stream(0, ?)
	val x: (Int, Char) = (1, 'a')             //> x  : (Int, Char) = (1,a)
	
	List(List(),List(1,2),List(),List(1,2,4,5)) flatMap(l => l map (_ * 2))
                                                  //> res5: List[Int] = List(2, 4, 2, 4, 8, 10)
}