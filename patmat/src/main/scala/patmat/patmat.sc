package patmat

object patmat {
  def times(chars: List[Char]): List[(Char, Int)] = {
  
    def insert(char: Char, list: List[(Char, Int)]): List[(Char, Int)] = {
      //println("inserting char: " + char)
      if (list.isEmpty) (char, 1) :: list
      else list.head match {
      	
        case (ch, n) => if (char == ch) (ch, n + 1) :: list.tail else list.head :: insert(char, list.tail)
      }
    }

    def part(chars: List[Char], acc: List[(Char, Int)]): List[(Char, Int)] = {
      //println("part: " + chars + ", acc: " + acc)
      if (chars.isEmpty) acc
      else {
        part(chars.tail, insert(chars.head, acc))
      }
    }
    part(chars, List())
  }                                               //> times: (chars: List[Char])List[(Char, Int)]

  def contains(char: Char, list: List[(Char, Int)]): Boolean =
    if (list.isEmpty) false
    else list.head match {
      case (ch, _) => if (char == ch) true else contains(char, list.tail)
    }                                             //> contains: (char: Char, list: List[(Char, Int)])Boolean

	val l1 = List('b', 'a', 'a', 'b', 'c', 'b')
                                                  //> l1  : List[Char] = List(b, a, a, b, c, b)
  val t = times(l1)                               //> t  : List[(Char, Int)] = List((b,3), (a,2), (c,1))
  
}