package recfun
import common._

/***
def pascal(c, r) {
    def val = 0
    def prev = []
    (0..r).each { row ->
        def cur = []
        for (col = 0; col <= row; col++) {
            if (col == 0 || col == row) {
                val = 1
            }
            else {
                val = prev[col] + prev[col-1]
            }
            if (c == col && r == row) {
                break
            }
            cur << val
        }
        prev = cur
    }
    val
}

pascal (18,35)
***/

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def sumBalance(chars: List[Char], sum: Int): Boolean = {
      if (chars.isEmpty || sum < 0)
        sum == 0
      else {
        chars.head match {
          case '(' => sumBalance(chars.tail, sum + 1)
          case ')' => sumBalance(chars.tail, sum - 1)
          case _ => sumBalance(chars.tail, sum)
        }
      }
    }
    sumBalance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty || money < 0)
      0
    else if (money == 0)
      1
    else
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
