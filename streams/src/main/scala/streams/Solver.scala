package streams

import common._

/**
 * This component implements the solver for the Bloxorz game
 */
trait Solver extends GameDef {

  /**
   * Returns `true` if the block `b` is at the final position
   */
  def done(b: Block): Boolean = b.isStanding && b.b1 == goal

  /**
   * This function takes two arguments: the current block `b` and
   * a list of moves `history` that was required to reach the
   * position of `b`.
   *
   * The `head` element of the `history` list is the latest move
   * that was executed, i.e. the last move that was performed for
   * the block to end up at position `b`.
   *
   * The function returns a stream of pairs: the first element of
   * the each pair is a neighboring block, and the second element
   * is the augmented history of moves required to reach this block.
   *
   * It should only return valid neighbors, i.e. block positions
   * that are inside the terrain.
   */
  def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] =
    for ((block, move) <- b.legalNeighbors.toStream) yield (block, move :: history)

  /**
   * This function returns the list of neighbors without the block
   * positions that have already been explored. We will use it to
   * make sure that we don't explore circular paths.
   */
  def newNeighborsOnly(neighbors: Stream[(Block, List[Move])],
    explored: Set[Block]): Stream[(Block, List[Move])] =
    neighbors.filter(n => !(explored contains n._1))

  /**
   * The function `from` returns the stream of all possible paths
   * that can be followed, starting at the `head` of the `initial`
   * stream.
   *
   * The blocks in the stream `initial` are sorted by ascending path
   * length: the block positions with the shortest paths (length of
   * move list) are at the head of the stream.
   *
   * The parameter `explored` is a set of block positions that have
   * been visited before, on the path to any of the blocks in the
   * stream `initial`. When search reaches a block that has already
   * been explored before, that position should not be included a
   * second time to avoid cycles.
   *
   * The resulting stream should be sorted by ascending path length,
   * i.e. the block positions that can be reached with the fewest
   * amount of moves should appear first in the stream.
   *
   * Note: the solution should not look at or compare the lengths
   * of different paths - the implementation should naturally
   * construct the correctly sorted stream.
   */
  def _from(initial: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] = {
    val nh = neighborsWithHistory(initial.head._1, initial.head._2)
    nh match {
      case Stream.Empty => initial
      case _ => {
        val neighbors = newNeighborsOnly(nh, explored)
        if (neighbors.isEmpty)
          initial
        else {
          val newExplored = neighbors.foldLeft(explored)((t, bh) => t + bh._1)
          from(neighbors, newExplored) #::: initial
        }
      }
    }
  }

  def __from(initial: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] = {
    val nh = neighborsWithHistory(initial.head._1, initial.head._2)
    nh match {
      case Stream.Empty => initial
      case _ => {
        val neighbors = newNeighborsOnly(nh, explored)
        if (neighbors.isEmpty)
          initial
        else {
          (for (n <- neighbors) yield from((n._1, n._2) #:: initial, explored + n._1)).flatten

          //neighbors flatMap (bh => from((bh._1, bh._2) #:: initial, explored + bh._1))

          //val newExplored = neighbors.foldLeft(explored)((t, bh) => t + bh._1)
          //from(neighbors, newExplored) #::: initial
        }
      }
    }
  }

  def from(initial: Stream[(Block, List[Move])], explored: Set[Block]): Stream[(Block, List[Move])] =
    initial match {
      case Stream.Empty => initial
      case x #:: xs => { println("find new neighbors for " + x) /*hack*/
        val neighbors = neighborsWithHistory(x._1, x._2)
        val newNeighbors = newNeighborsOnly(neighbors, explored)
        if (newNeighbors.isEmpty)
          newNeighbors
        else {
          val newlyExplored = (newNeighbors map (bh => bh._1)).toSet
          from(newNeighbors, newlyExplored ++ explored) #::: initial
        }
      }
    }

  /**
   * The stream of all paths that begin at the starting block.
   */
  lazy val pathsFromStart: Stream[(Block, List[Move])] = {
    val neighbors = neighborsWithHistory(startBlock, Nil)
    from(neighbors, Set(startBlock))
  }

  /**
   * Returns a stream of all possible pairs of the goal block along
   * with the history how it was reached.
   */
  lazy val pathsToGoal: Stream[(Block, List[Move])] =
    pathsFromStart.foldLeft(Stream.empty: Stream[(Block, List[Move])])((t, bh) => if (done(bh._1)) bh #:: t else t)

  /**
   * The (or one of the) shortest sequence(s) of moves to reach the
   * goal. If the goal cannot be reached, the empty list is returned.
   *
   * Note: the `head` element of the returned list should represent
   * the first move that the player should perform from the starting
   * position.
   */
  lazy val solution: List[Move] =
    pathsToGoal match {
      case Stream.Empty => Nil
      case h #:: t => h._2
    }
}
