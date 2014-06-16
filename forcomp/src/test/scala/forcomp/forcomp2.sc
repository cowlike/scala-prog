package forcomp

object forcomp2 {
  import Anagrams._

	val dic = List("eat", "ate", "tea")       //> dic  : List[String] = List(eat, ate, tea)

 	def subtractx(x: Occurrences, y: Occurrences): Occurrences = {
 		def count(o: (Char, Int), os: Occurrences): Int =
 			os.filter (x => x._1 == o._1) match {
 				case List() => 0
 				case x :: xs => x._2
 			}
    for {
      x1 <- x
    }  yield (x1._1, x1._2 - count(x1, y))
  }.filter (x => x._2 > 0)                        //> subtractx: (x: forcomp.Anagrams.Occurrences, y: forcomp.Anagrams.Occurrences
                                                  //| )forcomp.Anagrams.Occurrences

	val jimmy = wordOccurrences("jimmy")      //> jimmy  : forcomp.Anagrams.Occurrences = List((i,1), (j,1), (m,2), (y,1))
	val my = wordOccurrences("my")            //> my  : forcomp.Anagrams.Occurrences = List((m,1), (y,1))
	subtractx(jimmy, my)                      //> res0: forcomp.Anagrams.Occurrences = List((i,1), (j,1), (m,1))
	
	
  dic.foldLeft(Map(): Map[Occurrences, List[String]]) { (t, v) =>
    val value = t get wordOccurrences(v)
    value match {
      case Some(lst) => t updated (wordOccurrences(v), v :: lst)
      case None => t updated (wordOccurrences(v), List(v))
    }
  }                                               //> res1: Map[forcomp.Anagrams.Occurrences,List[String]] = Map(List((a,1), (e,1)
                                                  //| , (t,1)) -> List(tea, ate, eat))
  val myList = List(('a', 2), ('b', 2))           //> myList  : List[(Char, Int)] = List((a,2), (b,2))

  myList map (p => p match {
    case (c, n) => for (x <- 1 to n) yield (c, x)
  })                                              //> res2: List[scala.collection.immutable.IndexedSeq[(Char, Int)]] = List(Vector
                                                  //| ((a,1), (a,2)), Vector((b,1), (b,2)))
	combinations(myList)                      //> res3: List[forcomp.Anagrams.Occurrences] = List(List((a,2), (b,2)), List((a,
                                                  //| 2), (b,1)), List((a,2)), List((a,1), (b,2)), List((a,1), (b,1)), List((a,1))
                                                  //| , List((b,2)), List((b,1)), List())
}