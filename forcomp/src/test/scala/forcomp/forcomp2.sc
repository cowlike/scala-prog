package forcomp

object forcomp2 {
  import Anagrams._

  val dic = List("eat", "ate", "tea")             //> dic  : List[String] = List(eat, ate, tea)
  
  dic.foldLeft (Map(): Map[Occurrences, List[String]]) { (t, v) =>
  	val value = t get wordOccurrences(v)
  	value match {
  		case Some(lst) => t updated (wordOccurrences(v), v :: lst)
  		case None => t updated (wordOccurrences(v), List(v))
  	}
  }                                               //> res0: Map[forcomp.Anagrams.Occurrences,List[String]] = Map(List((a,1), (e,1)
                                                  //| , (t,1)) -> List(tea, ate, eat))
  
  //dic foldLeft
  
  Map('a'->1, 'b'->2)                             //> res1: scala.collection.immutable.Map[Char,Int] = Map(a -> 1, b -> 2)
}