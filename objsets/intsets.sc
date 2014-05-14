object intsets {
  val t1 = new NonEmpty(3, new Empty, new Empty)  //> t1  : NonEmpty = {.3.}
  val t2 = t1 incl 4 incl 1 incl 7                //> t2  : IntSet = {{.1.}3{.4{.7.}}}
  t2.sum                                          //> res0: Int = 15
  t2.filter(x => x % 2 != 0)                      //> res1: IntSet = {{.1.}3{.7.}}
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def sum: Int
  def union(other: IntSet): IntSet

  def filter(p: Int => Boolean): IntSet
  def filterAcc(p: Int => Boolean, acc: IntSet): IntSet
}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
  def sum = 0
  def union(other: IntSet): IntSet = other

  def filter(p: Int => Boolean) = this
  def filterAcc(p: Int => Boolean, acc: IntSet) = acc

}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString = "{" + left + elem + right + "}"

  def sum = elem + (left sum) + (right sum)

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem

  def filter(p: Int => Boolean) =
    (left filter(p)) union (right filter(p)) union filterAcc(p, new Empty)

  def filterAcc(p: Int => Boolean, acc: IntSet) =
    if (p(elem)) acc.incl(elem)
    else acc
}