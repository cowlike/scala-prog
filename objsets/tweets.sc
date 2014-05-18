import objsets._

object tweets {
  val set1 = new objsets.Empty                    //> set1  : objsets.Empty = _
  val set2 = set1.incl(new Tweet("a", "a body", 20))
                                                  //> set2  : objsets.TweetSet = {_a:20_}
  val set3 = set2.incl(new Tweet("b", "b body", 77))
                                                  //> set3  : objsets.TweetSet = {_a:20{_b:77_}}
  val c = new Tweet("c", "c body", 7)             //> c  : objsets.Tweet = c:7
  val d = new Tweet("d", "d body", 99)            //> d  : objsets.Tweet = d:99
  val set4c = set3.incl(c)                        //> set4c  : objsets.TweetSet = {_a:20{_b:77{_c:7_}}}
  val set4d = set3.incl(d)                        //> set4d  : objsets.TweetSet = {_a:20{_b:77{_d:99_}}}
  val set5 = set4c.incl(d)                        //> set5  : objsets.TweetSet = {_a:20{_b:77{_c:7{_d:99_}}}}
  new Tweet("a", "a body", 20)                    //> res0: objsets.Tweet = a:20
  set3 union set5                                 //> res1: objsets.TweetSet = {_a:20{_b:77{_c:7{_d:99_}}}}
  set5.mostRetweeted                              //> res2: objsets.Tweet = d:99
 
 	//def show(in: TweetList): String =
  //set5.descendingByRetweet.foreach(t => println(t.retweets + " "))
	val l = new Cons(new Tweet("a", "a body", 20), Nil)
                                                  //> l  : objsets.Cons = objsets.Cons@84fa6a
	"abc".contains("ab")                      //> res3: Boolean = true
	!GoogleVsApple.google.forall(str => !"android".contains(str))
                                                  //> res4: Boolean = true
  
	//TweetReader.sites
	val sites = List("gizmodo", "TechCrunch", "engadget", "amazondeals", "CNET", "gadgetlab", "mashable")
                                                  //> sites  : List[String] = List(gizmodo, TechCrunch, engadget, amazondeals, CNE
                                                  //| T, gadgetlab, mashable)
	GoogleVsApple.googleTweets.descendingByRetweet.foreach(println)
                                                  //> gizmodo:290
                                                  //| CNET:131
                                                  //| gizmodo:120
                                                  //| gizmodo:108
                                                  //| gizmodo:101
                                                  //| mashable:85
                                                  //| gadgetlab:79
                                                  //| mashable:78
                                                  //| mashable:49
                                                  //| CNET:49
                                                  //| CNET:48
                                                  //| mashable:42
                                                  //| CNET:39
                                                  //| CNET:36
                                                  //| gizmodo:33
                                                  //| engadget:32
                                                  //| CNET:25
                                                  //| engadget:25
                                                  //| CNET:25
                                                  //| TechCrunch:24
                                                  //| TechCrunch:24
                                                  //| engadget:23
                                                  //| gizmodo:22
                                                  //| gadgetlab:22
                                                  //| engadget:21
                                                  //| engadget:20
                                                  //| CNET:19
                                                  //| TechCrunch:18
                                                  //| CNET:15
                                                  //| CNET:14
                                                  //| engadget:13
                                                  //| engadget:12
                                                  //| CNET:12
                                                  //| engadget:12
                                                  //| gadgetlab:11
                                                  //| CNET:10
                                                  //| engadget:9
                                                  //| engadget:8
	
	val g = GoogleVsApple.googleTweets.mostRetweeted
                                                  //> g  : objsets.Tweet = gizmodo:290
	GoogleVsApple.googleTweets.remove(g).mostRetweeted
                                                  //> res5: objsets.Tweet = CNET:131

}