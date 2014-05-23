package patmat

object patmatTest {
  def times(chars: List[Char]): List[(Char, Int)] = {

    def insert(char: Char, list: List[(Char, Int)]): List[(Char, Int)] =
      if (list.isEmpty) (char, 1) :: list
      else list.head match {
        case (ch, n) =>
          if (char == ch) (ch, n + 1) :: list.tail
          else list.head :: insert(char, list.tail)
      }

    def part(chars: List[Char], acc: List[(Char, Int)]): List[(Char, Int)] =
      if (chars.isEmpty) acc
      else part(chars.tail, insert(chars.head, acc))

    part(chars, List())
  }                                               //> times: (chars: List[Char])List[(Char, Int)]

  def contains(char: Char, list: List[(Char, Int)]): Boolean =
    if (list.isEmpty) false
    else list.head match {
      case (ch, _) => if (char == ch) true else contains(char, list.tail)
    }                                             //> contains: (char: Char, list: List[(Char, Int)])Boolean

  val l1 = List('x', 'a', 'b', 'a', 'b', 'c', 'b')//> l1  : List[Char] = List(x, a, b, a, b, c, b)
  val t = times(l1)                               //> t  : List[(Char, Int)] = List((x,1), (a,2), (b,3), (c,1))
  Huffman.makeOrderedLeafList(t)                  //> res0: List[patmat.Huffman.Leaf] = List(Leaf(c,1), Leaf(x,1), Leaf(a,2), Leaf
                                                  //| (b,3))
  
  val l2 = List(1, 5, 10, 15)                     //> l2  : List[Int] = List(1, 5, 10, 15)
  def insert(i: Int, acc: List[Int]): List[Int] =
  	if (acc.isEmpty) List(i)
  	else acc.head match {
  		case n => if (i <= n) i :: acc else acc.head :: insert(i, acc.tail)
  	}                                         //> insert: (i: Int, acc: List[Int])List[Int]
	
	insert(12, insert(1, insert(8, l2)))      //> res1: List[Int] = List(1, 1, 5, 8, 10, 12, 15)
	1 :: 2 :: List()                          //> res2: List[Int] = List(1, 2)
	
	def single[T](lst: List[T]): Boolean = !lst.isEmpty && lst.tail.isEmpty
                                                  //> single: [T](lst: List[T])Boolean
	single[Int](1 :: 2 :: List())             //> res3: Boolean = false
}