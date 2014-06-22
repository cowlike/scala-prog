package streams

object t1 {
  trait LevelTest {
    val level = """------
        |--ST--
        |--oo--
        |--oo--
        |------""".stripMargin

    lazy val vector: Vector[Vector[Char]] =
      Vector(level.split("\n").map(str => Vector(str: _*)): _*)
  }

  object LevelTest extends LevelTest
  LevelTest.vector //> res0: Vector[Vector[Char]] = Vector(Vector(-, -, -, -, -, -), Vector(-, -, S
  //| , T, -, -), Vector(-, -, o, o, -, -), Vector(-, -, o, o, -, -), Vector(-, -,
  //|  -, -, -, -))

  case class Pos(x: Int, y: Int) {}

  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
    p => p.x < levelVector.length &&
      p.y < levelVector(0).length &&
      levelVector(p.x)(p.y) != '-' //> terrainFunction: (levelVector: Vector[Vector[Char]])streams.t1.Pos => Boolea
  //| n

  val f = terrainFunction(LevelTest.vector) //> f  : streams.t1.Pos => Boolean = <function1>
  f(new Pos(3, 2)) //> res1: Boolean = true

  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos =
    (for {
      x <- 0 until levelVector.length
      y <- 0 until levelVector(0).length
      if levelVector(x)(y) == c
    } yield new Pos(x, y)).head //> findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.t1.Pos

  findChar('T', LevelTest.vector) //> res2: streams.t1.Pos = Pos(1,3)
}