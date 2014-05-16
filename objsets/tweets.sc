import objsets._

object tweets {
  val set1 = new objsets.Empty                    //> set1  : objsets.Empty = objsets.Empty@1127c4d
  val set2 = set1.incl(new Tweet("a", "a body", 20))
                                                  //> set2  : objsets.TweetSet = objsets.NonEmpty@e4d7f7
  val set3 = set2.incl(new Tweet("b", "b body", 20))
                                                  //> set3  : objsets.TweetSet = objsets.NonEmpty@1c5b2de
  val c = new Tweet("c", "c body", 7)             //> c  : objsets.Tweet = User: c
                                                  //| Text: c body [7]
  val d = new Tweet("d", "d body", 9)             //> d  : objsets.Tweet = User: d
                                                  //| Text: d body [9]
  val set4c = set3.incl(c)                        //> set4c  : objsets.TweetSet = objsets.NonEmpty@624a40
  val set4d = set3.incl(d)                        //> set4d  : objsets.TweetSet = objsets.NonEmpty@f8db08
  val set5 = set4c.incl(d)                        //> set5  : objsets.TweetSet = objsets.NonEmpty@1f3eab7
 
 	//def show(in: TweetList): String =
  set5.descendingByRetweet.head//foreach(t => println(t.retweets + " "))
                                                  //> res0: objsets.Tweet = User: d
                                                  //| Text: d body [9]
	val l = new Cons(new Tweet("a", "a body", 20), Nil)
                                                  //> l  : objsets.Cons = objsets.Cons@16d8a64
}