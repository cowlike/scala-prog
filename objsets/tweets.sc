import objsets._

object tweets {
  val set1 = new objsets.Empty
  val set2 = set1.incl(new Tweet("a", "a body", 20))
  val set3 = set2.incl(new Tweet("b", "b body", 20))
  val c = new Tweet("c", "c body", 7)
  val d = new Tweet("d", "d body", 9)
  val set4c = set3.incl(c)
  val set4d = set3.incl(d)
  val set5 = set4c.incl(d)
  new Tweet("a", "a body", 20)
  set3 union set5
 
 	//def show(in: TweetList): String =
  set5.descendingByRetweet.foreach(t => println(t.retweets + " "))
	val l = new Cons(new Tweet("a", "a body", 20), Nil)
	"abc".contains("ab")
	!GoogleVsApple.google.forall(str => !"android".contains(str))
  
	//TweetReader.sites
	val sites = List("gizmodo", "TechCrunch", "engadget", "amazondeals", "CNET", "gadgetlab", "mashable")
	GoogleVsApple.googleTweets.elem
}