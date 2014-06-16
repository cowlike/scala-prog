package forcomp

object forcomp3 {
  import Anagrams._

  val s = List("Yes", "man")                      //> s  : List[String] = List(Yes, man)
  val sOcc = sentenceOccurrences(s)               //> sOcc  : forcomp.Anagrams.Occurrences = List((a,1), (e,1), (m,1), (n,1), (s,1
                                                  //| ), (y,1))

  //combinations
  def c(occurrences: Occurrences): List[Occurrences] = {
    def expand(p: (Char, Int)): List[List[(Char, Int)]] =
      p match {
        case (_, 0) => List(List())
        case (c, n) => List((c, n)) :: expand(c, n - 1)
      }
    def combine(ls: List[List[List[(Char, Int)]]]): List[List[(Char, Int)]] =
      ls match {
        case List() => List(List())
        case x :: xs => x flatMap (e => for (v <- combine(xs)) yield List(e ::: v).flatten)
      }
    combine(occurrences map expand)
  }                                               //> c: (occurrences: forcomp.Anagrams.Occurrences)List[forcomp.Anagrams.Occurren
                                                  //| ces]

	c(sentenceOccurrences(List("abb")))       //> res0: List[forcomp.Anagrams.Occurrences] = List(List((a,1), (b,2)), List((a,
                                                  //| 1), (b,1)), List((a,1)), List((b,2)), List((b,1)), List())
	
	def combos(c: List[Occurrences]): List[List[Occurrences]] = {
		def foo(arg: List[List[Occurrences]]): List[List[Occurrences]] = {
			arg match {
				case List() => arg
				case _ => arg
				//case x :: xs => for (o <- x) yield o :: c(subtract(List(o), x))
			}
		}
		
		foo(List(c))
	}                                         //> combos: (c: List[forcomp.Anagrams.Occurrences])List[List[forcomp.Anagrams.Oc
                                                  //| currences]]
  
  /*
  
	foo(sOcc)

def sentenceAnagrams__(sentence: Sentence): List[Sentence] = {
    def getOcc(o: Occurrences): List[Word] =
      dictionaryByOccurrences get o match {
        case Some(lst) => lst
        case None => List("")
      }
    val sOcc = sentenceOccurrences(sentence)
    //(for (x <- combinations(sOcc)) yield List(x, subtract(sOcc, x)) map (x => dictionaryByOccurrences(x))).filterNot (x => x.contains("")).flatten
    //(for (x <- combinations(sOcc)) yield List(x, subtract(sOcc, x)) map getOcc _).filterNot (x => x.contains("")).map(_.flatten)
    //(for (x <- combinations(sOcc)) yield List(x, subtract(sOcc, x)) flatMap getOcc _).filterNot (x => x.contains(""))
    (for (x <- combinations(sOcc)) yield List(x, subtract(sOcc, x)) flatMap getOcc _) map (_.filterNot(_ == ""))
  }

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    def getOcc(o: Occurrences): List[Word] =
      dictionaryByOccurrences get o match {
        case Some(lst) => lst
        case None => List()
      }
    val sOcc = sentenceOccurrences(sentence)
    for (x <- combinations(sOcc)) yield List(x, subtract(sOcc, x)) flatMap getOcc _
  }
  

  def combine(list: List[Int], by: Int): List[List[Int]] =
    list match {
      case List() => List()
      case x :: xs => List(x :: xs.take(by - 1)) ::: combine(xs.drop(by - 1), by)
    }

  combine(myList, 2)
  */

  /*
	def combo[T <: Any](list: List[T], by: Int): List[List[T]] =
		list match {
			case List() => List()
			case lst => split(lst, by) ::: combo(lst.tail, by)
		}*/

  //combo(myList, 2)

  /*
	split(sOcc, 2)

	def combo[T <: Any](list: List[T], by: Int): List[List[T]] =
		list match {
			case List() => List()
			case lst => split(lst, by) ::: combo(lst.tail, by)
		}
		
	combo(sOcc, 2) map foo

  def nSplit[T <: Any](list: List[T], by: Int): List[List[T]] =
    list match {
      case List() => List()
      case x :: xs => for (el <- xs) yield
    }
    
  nSplit(List(1, 2, 3, 4, 5), 2)
  def combo[T <: Any](list: List[T], by: Int): List[List[T]] =
    list match {
      case List() => List()
      case lst => nSplit(lst, by) ::: combo(lst.tail, by)
    }
  */
}