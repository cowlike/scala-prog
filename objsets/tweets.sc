import objsets._

object tweets {
  val set1 = new objsets.Empty                    //> set1  : objsets.Empty = objsets.Empty@c5575
  val set2 = set1.incl(new Tweet("a", "a body", 20))
                                                  //> set2  : objsets.TweetSet = objsets.NonEmpty@15d9be1
  val set3 = set2.incl(new Tweet("b", "b body", 20))
                                                  //> set3  : objsets.TweetSet = objsets.NonEmpty@9dc8f2
  val c = new Tweet("c", "c body", 7)             //> c  : objsets.Tweet = User: c
                                                  //| Text: c body [7]
  val d = new Tweet("d", "d body", 9)             //> d  : objsets.Tweet = User: d
                                                  //| Text: d body [9]
  val set4c = set3.incl(c)                        //> set4c  : objsets.TweetSet = objsets.NonEmpty@f38b42
  val set4d = set3.incl(d)                        //> set4d  : objsets.TweetSet = objsets.NonEmpty@13bcbc8
  val set5 = set4c.incl(d)                        //> set5  : objsets.TweetSet = objsets.NonEmpty@bfc25c
 
 	//def show(in: TweetList): String =
  set5.descendingByRetweet.foreach(t => println(t.retweets + " "))
                                                  //> 20 
                                                  //| 20 
                                                  //| 7 
                                                  //| 9 
	val l = new Cons(new Tweet("a", "a body", 20), Nil)
                                                  //> l  : objsets.Cons = objsets.Cons@139ebdb
	"abc".contains("ab")                      //> res0: Boolean = true
	!GoogleVsApple.google.forall(str => !"android".contains(str))
                                                  //> res1: Boolean = true
  
	//TweetReader.sites
	val sites = List("gizmodo", "TechCrunch", "engadget", "amazondeals", "CNET", "gadgetlab", "mashable")
                                                  //> sites  : List[String] = List(gizmodo, TechCrunch, engadget, amazondeals, CNE
                                                  //| T, gadgetlab, mashable)
}