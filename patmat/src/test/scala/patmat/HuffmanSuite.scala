package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
    val t3 = createCodeTree(string2Chars("hello, world"))
    val t4 = createCodeTree(string2Chars("ABCDEFGH"))

    def la = Leaf('A', 8)
    def lb = Leaf('B', 3)
    def lc = Leaf('C', 1)
    def ld = Leaf('D', 1)
    def le = Leaf('E', 1)
    def lf = Leaf('F', 1)
    def lg = Leaf('G', 1)
    def lh = Leaf('H', 1)
    val s = string2Chars _
    val exampleCodeTree = Fork(la, Fork(Fork(lb, Fork(lc, ld, s("CD"), 2), s("BCD"), 5), Fork(Fork(le, lf, s("EF"), 2), Fork(lg, lh, s("GH"), 2), s("EFGH"), 4), s("BCDEFGH"), 9), s("ABCDEFGH"), 17)

    val tbl1 = List(('a', List(0, 1, 0)), ('b', List(0, 1, 1))) //sample code table
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a a bit longer text should be identity") {
    new TestTrees {
      assert(decode(t3, encode(t3)("hello, world".toList)) === "hello, world".toList)
    }
  }

  test("quickEncode and encode are same") {
    new TestTrees {
      assert(encode(t3)("hello, world".toList) === quickEncode(t3)("hello, world".toList))
    }
  }

  test("codeBits for character") {
    new TestTrees {
      assert(codeBits(tbl1)('a') === List(0, 1, 0))
    }
  }

  test("codeBits for character not in table throws exception") {
    new TestTrees {
      intercept[NoSuchElementException] {
        codeBits(tbl1)('c')
      }
    }
  }

  test("encode and quickEncode should give same result") {
    new TestTrees {
      val cc = string2Chars("ACGDE")
      assert(encode(t4)(cc) === quickEncode(t4)(cc))
    }
  }

  test("test results of decode from assignment example code tree") {
    new TestTrees {
      assert(decode(exampleCodeTree, List(1, 0, 0, 0, 1, 0, 1, 0)) === List('B', 'A', 'C'))
      assert(decode(exampleCodeTree, List(1, 0, 1, 1)) === List('D'))
    }
  }
  
  test("test decodedSecret") {
    assert(decodedSecret === string2Chars("huffmanestcool"))
  }
}
