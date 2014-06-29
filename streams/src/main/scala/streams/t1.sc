package streams

object t1 {
  trait LevelTest {
    var level =
      """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    lazy val vector: Vector[Vector[Char]] =
      Vector(level.split("\n").map(str => Vector(str: _*)): _*)
  }

  object LevelTest extends LevelTest
  LevelTest.vector                                //> res0: Vector[Vector[Char]] = Vector(Vector(o, o, o, -, -, -, -, -, -, -), Ve
                                                  //| ctor(o, S, o, o, o, o, -, -, -, -), Vector(o, o, o, o, o, o, o, o, o, -), Ve
                                                  //| ctor(-, o, o, o, o, o, o, o, o, o), Vector(-, -, -, -, -, o, o, T, o, o), Ve
                                                  //| ctor(-, -, -, -, -, -, o, o, o, -))

	LevelTest.vector map (_.length)           //> res1: scala.collection.immutable.Vector[Int] = Vector(10, 10, 10, 10, 10, 10
                                                  //| )
	
  case class Pos(x: Int, y: Int) {}

  def terrainFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean =
    p => p.x >= 0 && p.y >= 0 &&
      p.x < levelVector.length &&
      p.y < levelVector(p.x).length &&
      levelVector(p.x)(p.y) != '-'                //> terrainFunction: (levelVector: Vector[Vector[Char]])streams.t1.Pos => Boolea
                                                  //| n

  val f = terrainFunction(LevelTest.vector)       //> f  : streams.t1.Pos => Boolean = <function1>
  f(Pos(3, 2))                                    //> res2: Boolean = true
  f(Pos(4,10))                                    //> res3: Boolean = false

  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos =
    (for {
      x <- 0 until levelVector.length
      y <- 0 until levelVector(0).length
      if levelVector(x)(y) == c
    } yield Pos(x, y)).head                       //> findChar: (c: Char, levelVector: Vector[Vector[Char]])streams.t1.Pos

	def findChar2(c: Char, levelVector: Vector[Vector[Char]]): Pos =
    (for (v <- (0 until levelVector.length) if levelVector(v) contains c)
      yield Pos(v, levelVector(v).indexWhere(_ == c))).head
                                                  //> findChar2: (c: Char, levelVector: Vector[Vector[Char]])streams.t1.Pos
      
  assert(findChar('T', LevelTest.vector) == findChar2('T', LevelTest.vector))
  
   findChar2('S', LevelTest.vector)               //> res4: streams.t1.Pos = Pos(1,1)
 }