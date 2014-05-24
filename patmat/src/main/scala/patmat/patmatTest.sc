package patmat

object patmatTest {
	import Huffman._
	
	val str = "Now is the time for all good men to come to the aid of their country"
                                                  //> str  : String = Now is the time for all good men to come to the aid of their
                                                  //|  country
	val leaves = makeOrderedLeafList(times(string2Chars(str)))
                                                  //> leaves  : List[patmat.Huffman.Leaf] = List(Leaf(y,1), Leaf(u,1), Leaf(g,1), 
                                                  //| Leaf(s,1), Leaf(w,1), Leaf(N,1), Leaf(c,2), Leaf(n,2), Leaf(d,2), Leaf(l,2),
                                                  //|  Leaf(a,2), Leaf(f,2), Leaf(r,3), Leaf(m,3), Leaf(h,3), Leaf(i,4), Leaf(e,6)
                                                  //| , Leaf(t,7), Leaf(o,9), Leaf( ,15))

	val x1 = until(singleton, combine)(leaves)//> x1  : List[patmat.Huffman.CodeTree] = List(Fork(Fork(Fork(Leaf(t,7),Fork(For
                                                  //| k(Fork(Leaf(y,1),Leaf(u,1),List(y, u),2),Leaf(c,2),List(y, u, c),4),Fork(For
                                                  //| k(Leaf(w,1),Leaf(N,1),List(w, N),2),Fork(Leaf(g,1),Leaf(s,1),List(g, s),2),L
                                                  //| ist(w, N, g, s),4),List(y, u, c, w, N, g, s),8),List(t, y, u, c, w, N, g, s)
                                                  //| ,15),Leaf( ,15),List(t, y, u, c, w, N, g, s,  ),30),Fork(Fork(Fork(Fork(Leaf
                                                  //| (l,2),Leaf(a,2),List(l, a),4),Fork(Leaf(n,2),Leaf(d,2),List(n, d),4),List(l,
                                                  //|  a, n, d),8),Fork(Leaf(i,4),Fork(Leaf(f,2),Leaf(r,3),List(f, r),5),List(i, f
                                                  //| , r),9),List(l, a, n, d, i, f, r),17),Fork(Leaf(o,9),Fork(Fork(Leaf(m,3),Lea
                                                  //| f(h,3),List(m, h),6),Leaf(e,6),List(m, h, e),12),List(o, m, h, e),21),List(l
                                                  //| , a, n, d, i, f, r, o, m, h, e),38),List(t, y, u, c, w, N, g, s,  , l, a, n,
                                                  //|  d, i, f, r, o, m, h, e),68))
	val x2 = createCodeTree(string2Chars(str))//> x2  : patmat.Huffman.CodeTree = Fork(Fork(Fork(Leaf(t,7),Fork(Fork(Fork(Leaf
                                                  //| (y,1),Leaf(u,1),List(y, u),2),Leaf(c,2),List(y, u, c),4),Fork(Fork(Leaf(w,1)
                                                  //| ,Leaf(N,1),List(w, N),2),Fork(Leaf(g,1),Leaf(s,1),List(g, s),2),List(w, N, g
                                                  //| , s),4),List(y, u, c, w, N, g, s),8),List(t, y, u, c, w, N, g, s),15),Leaf( 
                                                  //| ,15),List(t, y, u, c, w, N, g, s,  ),30),Fork(Fork(Fork(Fork(Leaf(l,2),Leaf(
                                                  //| a,2),List(l, a),4),Fork(Leaf(n,2),Leaf(d,2),List(n, d),4),List(l, a, n, d),8
                                                  //| ),Fork(Leaf(i,4),Fork(Leaf(f,2),Leaf(r,3),List(f, r),5),List(i, f, r),9),Lis
                                                  //| t(l, a, n, d, i, f, r),17),Fork(Leaf(o,9),Fork(Fork(Leaf(m,3),Leaf(h,3),List
                                                  //| (m, h),6),Leaf(e,6),List(m, h, e),12),List(o, m, h, e),21),List(l, a, n, d, 
                                                  //| i, f, r, o, m, h, e),38),List(t, y, u, c, w, N, g, s,  , l, a, n, d, i, f, r
                                                  //| , o, m, h, e),68)
	x1.head == x2                             //> res0: Boolean = true

  def contains(char: Char, list: List[(Char, Int)]): Boolean =
    if (list.isEmpty) false
    else list.head match {
      case (ch, _) => if (char == ch) true else contains(char, list.tail)
    }                                             //> contains: (char: Char, list: List[(Char, Int)])Boolean

  val l1 = List('x', 'a', 'b', 'a', 'b', 'c', 'b')//> l1  : List[Char] = List(x, a, b, a, b, c, b)
  val t = Huffman.times(l1)                       //> t  : List[(Char, Int)] = List((x,1), (a,2), (b,3), (c,1))
  makeOrderedLeafList(t)                          //> res1: List[patmat.Huffman.Leaf] = List(Leaf(c,1), Leaf(x,1), Leaf(a,2), Leaf
                                                  //| (b,3))
  
  val l2 = List(1, 5, 10, 15)                     //> l2  : List[Int] = List(1, 5, 10, 15)
  def insert(i: Int, acc: List[Int]): List[Int] =
  	if (acc.isEmpty) List(i)
  	else acc.head match {
  		case n => if (i <= n) i :: acc else acc.head :: insert(i, acc.tail)
  	}                                         //> insert: (i: Int, acc: List[Int])List[Int]
	
	insert(12, insert(1, insert(8, l2)))      //> res2: List[Int] = List(1, 1, 5, 8, 10, 12, 15)
	1 :: 2 :: List()                          //> res3: List[Int] = List(1, 2)
	
	def single[T](lst: List[T]): Boolean = !lst.isEmpty && lst.tail.isEmpty
                                                  //> single: [T](lst: List[T])Boolean
	single[Int](1 :: 2 :: List())             //> res4: Boolean = false
}