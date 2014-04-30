package recfun
import common._

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
      sum >= 0 &&
        (chars match {
          case Nil => sum == 0
          case x :: xs => x match {
            case '(' => sumBalance(xs, sum + 1)
            case ')' => sumBalance(xs, sum - 1)
            case _ => sumBalance(xs, sum)
          }
        })
    }
    sumBalance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def makeChange(money: Int, coins: List[Int], count: Int): Int = {
      if (coins == Nil) {
        count
      }
      else {
        val remainder = money - coins.head
        if (remainder < 0) {
          println(remainder + " < 0, " + coins.tail + ", " + count)
          makeChange(money, coins.tail, count)
        }
        else if (remainder == 0) {
          println(remainder + " = 0, " + coins.tail + ", " + (count + 1))
          makeChange(remainder, coins.tail, count + 1)
        }
        else {
          println(remainder + " > 0, " + coins + ", " + count)
          makeChange(remainder, coins, count)
        }
      }
    }
    makeChange(money, coins, 0)
  }
}
