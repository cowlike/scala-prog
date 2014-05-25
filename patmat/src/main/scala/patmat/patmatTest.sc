package patmat

object patmatTest {
	import Huffman._
	
  def contains(char: Char, list: List[(Char, Int)]): Boolean =
    if (list.isEmpty) false
    else list.head match {
      case (ch, _) => if (char == ch) true else contains(char, list.tail)
    }                                             //> contains: (char: Char, list: List[(Char, Int)])Boolean

  val l1 = List('x', 'a', 'b', 'a', 'b', 'c', 'b')//> l1  : List[Char] = List(x, a, b, a, b, c, b)
  val t = Huffman.times(l1)                       //> t  : List[(Char, Int)] = List((x,1), (a,2), (b,3), (c,1))
  makeOrderedLeafList(t)                          //> res0: List[patmat.Huffman.Leaf] = List(Leaf(c,1), Leaf(x,1), Leaf(a,2), Leaf
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
	
	List(1,2) ::: List(3,4)                   //> res4: List[Int] = List(1, 2, 3, 4)
}