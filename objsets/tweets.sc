import objsets._

object tweets {
  val set1 = new objsets.Empty                    //> set1  : objsets.Empty = _
  val set2 = set1.incl(new Tweet("a", "a body", 20))
                                                  //> set2  : objsets.TweetSet = {_a:20_}
  val set3 = set2.incl(new Tweet("b", "b body", 20))
                                                  //> set3  : objsets.TweetSet = {_a:20{_b:20_}}
  val c = new Tweet("c", "c body", 7)             //> c  : objsets.Tweet = c:7
  val d = new Tweet("d", "d body", 9)             //> d  : objsets.Tweet = d:9
  val set4c = set3.incl(c)                        //> set4c  : objsets.TweetSet = {_a:20{_b:20{_c:7_}}}
  val set4d = set3.incl(d)                        //> set4d  : objsets.TweetSet = {_a:20{_b:20{_d:9_}}}
  val set5 = set4c.incl(d)                        //> set5  : objsets.TweetSet = {_a:20{_b:20{_c:7{_d:9_}}}}
  new Tweet("a", "a body", 20)                    //> res0: objsets.Tweet = a:20
  set3 union set5                                 //> res1: objsets.TweetSet = {_a:20{_b:20{_c:7{_d:9_}}}}
 
 	//def show(in: TweetList): String =
  set5.descendingByRetweet.foreach(t => println(t.retweets + " "))
                                                  //> 20 
                                                  //| 20 
                                                  //| 7 
                                                  //| 9 
	val l = new Cons(new Tweet("a", "a body", 20), Nil)
                                                  //> l  : objsets.Cons = objsets.Cons@1d46259
	"abc".contains("ab")                      //> res2: Boolean = true
	!GoogleVsApple.google.forall(str => !"android".contains(str))
                                                  //> res3: Boolean = true
  
	//TweetReader.sites
	val sites = List("gizmodo", "TechCrunch", "engadget", "amazondeals", "CNET", "gadgetlab", "mashable")
                                                  //> sites  : List[String] = List(gizmodo, TechCrunch, engadget, amazondeals, CNE
                                                  //| T, gadgetlab, mashable)
}