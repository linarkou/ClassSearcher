# ClassSearcher
Searcher of last K modified classnames (such as in IDE).
This is a solution of a test task.
<h1> Problem description </h1>
You need to implement the search function of a class by its name. For convenience, You just need to type the first letters of the class name, the IDE offers you a list of K=12 classes that begin with the entered characters. Classes should be sorted by date (last modified at the beginning), if modified at one time - ordered lexicographically. Your task is to implement the selection of class names in the Java language.<br>
It is assumed that data are indexed once, and then searches are fast.<br>
Full description you can find <a href="/test_java.pdf">here</a> (in Russian).
<h1> Constraints </h1>
Count of classes from 1 to 100000.<br>
Class name contains up to 32 characters.<br>
Time of indexing data - few seconds.<br>
Time of query - 300 ms.
<h1> Solution </h1>
I used a <a href="https://en.wikipedia.org/wiki/Trie">Trie</a>, but in each node I store an ordered set of K=12 last modified classes. So searches by prefix are fast.<br>
In the case of 100000 classnames with same prefix of length=25, building a trie takes 2-3 seconds.
