object TestSheet {
  def sum1(chars: List[Char], sum: Int): Boolean = {
    sum >= 0 &&
      (chars match {
        case Nil => sum == 0
        case x :: xs => x match {
          case '(' => sum1(xs, sum + 1)
          case ')' => sum1(xs, sum - 1)
          case _ => sum1(xs, sum)
        }
      })
  }                                               //> sum1: (chars: List[Char], sum: Int)Boolean

  def sum2(chars: List[Char], sum: Int): Boolean = {
    if (chars.isEmpty || sum < 0)
      sum == 0
    else if (chars.head == '(')
      sum2(chars.tail, sum + 1)
    else if (chars.head == ')')
      sum2(chars.tail, sum - 1)
    else
      sum2(chars.tail, sum)
  }                                               //> sum2: (chars: List[Char], sum: Int)Boolean

  def sum3(chars: List[Char], sum: Int): Boolean = {
    if (chars.isEmpty || sum < 0)
      sum == 0
    else {
      chars.head match {
        case '(' => sum3(chars.tail, sum + 1)
        case ')' => sum3(chars.tail, sum - 1)
        case _ => sum3(chars.tail, sum)
      }
    }
  }                                               //> sum3: (chars: List[Char], sum: Int)Boolean

  sum1(")(((()))".toList, 0)                      //> res0: Boolean = false
  sum2("((()))".toList, 0)                        //> res1: Boolean = true
  def x = sum1 _                                  //> x: => (List[Char], Int) => Boolean
}