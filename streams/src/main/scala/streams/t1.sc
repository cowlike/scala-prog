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
  
  object LT extends LevelTest
  LT.vector                                       //> res0: Vector[Vector[Char]] = Vector(Vector(-, -, -, -, -, -), Vector(-, -, S
                                                  //| , T, -, -), Vector(-, -, o, o, -, -), Vector(-, -, o, o, -, -), Vector(-, -,
                                                  //|  -, -, -, -))
}