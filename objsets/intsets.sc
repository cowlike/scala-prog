object intsets {
  val t1 = new NonEmpty(3, new Empty, new Empty)  //> t1  : NonEmpty = {.3.}
  val t2 = t1 incl 4 incl 1                       //> t2  : IntSet = {{.1.}3{.4.}}
  val t3 = t2 incl 8 incl 2 incl 7                //> t3  : IntSet = {{.1{.2.}}3{.4{{.7.}8.}}}
  val t4 = t1 union t2                            //> t4  : IntSet = {{.1.}3{.4.}}
  
  t2.sum                                          //> res0: Int = 8
  t2 filter (x => x % 2 != 0)                     //> res1: IntSet = {{.1.}3.}

  def max: (Int, Int) => Int = (x, y) => if (x > y) x else y
                                                  //> max: => (Int, Int) => Int
  def min: (Int, Int) => Int = (x, y) => if (x > y) y else x
                                                  //> min: => (Int, Int) => Int
  t1 pick (max)                                   //> res2: Int = 3
  t2 pick (max)                                   //> res3: Int = 4
  t3 pick (min)                                   //> res4: Int = 0
  t3.pick(max)                                    //> res5: Int = 8
  
  t2.max(0)                                       //> res6: Int = 4
  t3.max(0)                                       //> res7: Int = 8
}

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def sum: Int
  def union(other: IntSet): IntSet

  def filter(p: Int => Boolean): IntSet
  def filterAcc(p: Int => Boolean, acc: IntSet): IntSet

  def pick(comp: (Int, Int) => Int): Int
  def pickAcc(comp: (Int, Int) => Int, acc: Int): Int

  def max(acc: Int): Int
}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
  def sum = 0
  def union(other: IntSet): IntSet = other

  def filter(p: Int => Boolean) = this
  def filterAcc(p: Int => Boolean, acc: IntSet) = acc
  def pick(comp: (Int, Int) => Int): Int = 0
  def pickAcc(comp: (Int, Int) => Int, acc: Int): Int = 0

  def max(acc: Int) = acc
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
    (left filter (p)) union (right filter (p)) union filterAcc(p, new Empty)

  def filterAcc(p: Int => Boolean, acc: IntSet) =
    if (p(elem)) acc.incl(elem)
    else acc

  def pickAcc(comp: (Int, Int) => Int, acc: Int): Int = comp(elem, acc)

  def pick(comp: (Int, Int) => Int): Int =
    comp(elem, comp(left pick (comp), right pick (comp)))

  def max(acc: Int) = {
    def comp(x: Int, y: Int) = if (x > y) x else y
    comp(elem, left max((right max (acc))))
  }
}