package patmat

object patmatTest {
	import Huffman._
	
	val str = "ABCDEFGH"                      //> str  : String = ABCDEFGH
	val codeTree = createCodeTree(string2Chars(str))
                                                  //> codeTree  : patmat.Huffman.CodeTree = Fork(Fork(Fork(Leaf(F,1),Leaf(E,1),Lis
                                                  //| t(F, E),2),Fork(Leaf(H,1),Leaf(G,1),List(H, G),2),List(F, E, H, G),4),Fork(F
                                                  //| ork(Leaf(B,1),Leaf(A,1),List(B, A),2),Fork(Leaf(D,1),Leaf(C,1),List(D, C),2)
                                                  //| ,List(B, A, D, C),4),List(F, E, H, G, B, A, D, C),8)

	decode(codeTree, List(1,0,0,0,1,0,1,0))   //> res0: List[Char] = List(B, H)
	decode(codeTree, List(1,0,1,1))           //> res1: List[Char] = List(A)
	decodedSecret                             //> res2: List[Char] = List(h, u, f, f, m, a, n, e, s, t, c, o, o, l)
	
	
	
	def la = Leaf('A',8)                      //> la: => patmat.Huffman.Leaf
  def lb = Leaf('B',3)                            //> lb: => patmat.Huffman.Leaf
  def lc = Leaf('C',1)                            //> lc: => patmat.Huffman.Leaf
  def ld = Leaf('D',1)                            //> ld: => patmat.Huffman.Leaf
  def le = Leaf('E',1)                            //> le: => patmat.Huffman.Leaf
  def lf = Leaf('F',1)                            //> lf: => patmat.Huffman.Leaf
  def lg = Leaf('G',1)                            //> lg: => patmat.Huffman.Leaf
  def lh = Leaf('H',1)                            //> lh: => patmat.Huffman.Leaf
  val s2c = string2Chars _                        //> s2c  : String => List[Char] = <function1>
  val myTree = Fork(la, Fork(Fork(lb, Fork(lc,ld,s2c("CD"),2), s2c("BCD"),5), Fork(Fork(le,lf,s2c("EF"),2), Fork(lg,lh,s2c("GH"),2), s2c("EFGH"),4), s2c("BCDEFGH"),9), s2c("ABCDEFGH"), 17)
                                                  //> myTree  : patmat.Huffman.Fork = Fork(Leaf(A,8),Fork(Fork(Leaf(B,3),Fork(Leaf
                                                  //| (C,1),Leaf(D,1),List(C, D),2),List(B, C, D),5),Fork(Fork(Leaf(E,1),Leaf(F,1)
                                                  //| ,List(E, F),2),Fork(Leaf(G,1),Leaf(H,1),List(G, H),2),List(E, F, G, H),4),Li
                                                  //| st(B, C, D, E, F, G, H),9),List(A, B, C, D, E, F, G, H),17)
	decode(myTree, List(1,0,0,0,1,0,1,0))     //> res3: List[Char] = List(B, A, C)
	decode(myTree, List(1,0,1,1))             //> res4: List[Char] = List(D)
		
  def contains(char: Char, list: List[(Char, Int)]): Boolean =
    if (list.isEmpty) false
    else list.head match {
      case (ch, _) => if (char == ch) true else contains(char, list.tail)
    }                                             //> contains: (char: Char, list: List[(Char, Int)])Boolean

  val l1 = List('x', 'a', 'b', 'a', 'b', 'c', 'b')//> l1  : List[Char] = List(x, a, b, a, b, c, b)
  val t = Huffman.times(l1)                       //> t  : List[(Char, Int)] = List((x,1), (a,2), (b,3), (c,1))
  makeOrderedLeafList(t)                          //> res5: List[patmat.Huffman.Leaf] = List(Leaf(c,1), Leaf(x,1), Leaf(a,2), Lea
                                                  //| f(b,3))
  
  val l2 = List(1, 5, 10, 15)                     //> l2  : List[Int] = List(1, 5, 10, 15)
  def insert(i: Int, acc: List[Int]): List[Int] =
  	if (acc.isEmpty) List(i)
  	else acc.head match {
  		case n => if (i <= n) i :: acc else acc.head :: insert(i, acc.tail)
  	}                                         //> insert: (i: Int, acc: List[Int])List[Int]
	
	insert(12, insert(1, insert(8, l2)))      //> res6: List[Int] = List(1, 1, 5, 8, 10, 12, 15)
	1 :: 2 :: List()                          //> res7: List[Int] = List(1, 2)
	
	def single[T](lst: List[T]): Boolean = !lst.isEmpty && lst.tail.isEmpty
                                                  //> single: [T](lst: List[T])Boolean
	single[Int](1 :: 2 :: List())             //> res8: Boolean = false
}