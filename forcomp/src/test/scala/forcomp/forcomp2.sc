package forcomp

object forcomp2 {
  import Anagrams._

  val dic = List("eat", "ate", "tea")             //> dic  : List[String] = List(eat, ate, tea)

  dic.foldLeft(Map(): Map[Occurrences, List[String]]) { (t, v) =>
    val value = t get wordOccurrences(v)
    value match {
      case Some(lst) => t updated (wordOccurrences(v), v :: lst)
      case None => t updated (wordOccurrences(v), List(v))
    }
  }                                               //> res0: Map[forcomp.Anagrams.Occurrences,List[String]] = Map(List((a,1), (e,1)
                                                  //| , (t,1)) -> List(tea, ate, eat))
  val myList = List(('a', 2), ('b', 2))           //> myList  : List[(Char, Int)] = List((a,2), (b,2))

  myList map (p => p match {
    case (c, n) => for (x <- 1 to n) yield (c, x)
  })                                              //> res1: List[scala.collection.immutable.IndexedSeq[(Char, Int)]] = List(Vector
                                                  //| ((a,1), (a,2)), Vector((b,1), (b,2)))
	combinations(myList)                      //> res2: List[forcomp.Anagrams.Occurrences] = List(List((a,2), (b,2)), List((a,
                                                  //| 2), (b,1)), List((a,2)), List((a,1), (b,2)), List((a,1), (b,1)), List((a,1))
                                                  //| , List((b,2)), List((b,1)), List())
}