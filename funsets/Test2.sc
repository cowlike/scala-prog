object Test2 {
  def foo(x: Int)(y: Int) = { x + y }             //> foo: (x: Int)(y: Int)Int
  foo(1)_                                         //> res0: Int => Int = <function1>
  
  val bar = (x: Int) => (y: Int) => x + y         //> bar  : Int => (Int => Int) = <function1>
	bar(3)                                    //> res1: Int => Int = <function1>
	
	def t(x: Int) = true                      //> t: (x: Int)Boolean
	t(1)                                      //> res2: Boolean = true
	def f(x: Int) = !t(x)                     //> f: (x: Int)Boolean
	f(1)                                      //> res3: Boolean = false
	
	import funsets.FunSets._
	
	val s1 = union(singletonSet(1), singletonSet(2))
                                                  //> s1  : Int => Boolean = <function1>
	forall(s1, x => x < 2)                    //> res4: Boolean = false
	exists(s1, x => x < 2)                    //> res5: Boolean = true
	val s2 = map(s1, x => x * 9)              //> s2  : Int => Boolean = <function1>
	contains(s1, 1)                           //> res6: Boolean = true
	contains(s2,  3)                          //> res7: Boolean = false
	
	val f1 = (x: Int) => x * x                //> f1  : Int => Int = <function1>
	val f2 = (x: Int) => x == 2               //> f2  : Int => Boolean = <function1>
	f1(2)                                     //> res8: Int = 4
}