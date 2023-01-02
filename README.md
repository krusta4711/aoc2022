**Advent of Code 2022**

My solutions for the great `Advent of Code` 2022 exercises (https://adventofcode.com/2022) in Java. The code is not always pefectly clean as I often try out things just to try them out (e.g. single liners with streams).

---

**Day 1**

The task itself was as easy as it gets. But part 2 put me into an internal brain controversy whether I create a clean solution with managing just the actual three highest calories or whether I use the dirty version an put everything in a list and sort it. The dirty version has won. ;-)

---

**Day 2**

German: Stein, Schere, Papier. I implemented this one with enums.

---

**Day 3**

I wasn't pleased with my solution. It was neither a nice design nor short. On reddit I found a crazy oneliner which challenged me to use a single stream too on day 4. Let's see. ;-)

---

**Day 4**

As intended, I used just a single stream one-liner. Was tough as I'm not a fan of hard to read single line code (it's much more important to read than to write code). But was fun too.

---

**Day 5**

The solution was easy but the parsing led to ugly code. Nevertheless, a good one to use the `Deque` interface and a little bit of regex training.

---

**Day 6**

This was the easiest puzzle ever. Felt more like day a day 1 puzzle. Especially for part 2 it was just changing the chunks from 4 to 14. At the beginning I used a `Set` for finding out the distinct chars but then found out that a int-stream provides the method `distinct`.

---

**Day 7**

Nice one. I was thinking of just maintaining the directories and their sizes but then decided that a full tree with recursion is easier.

---

**Day 8**

A good old grid/matrix exercise by Advent of Code. I don't like to use hundreds of loops in loops. Additionally accessing a grid/matrix in a loop can drive one crazy when using `i` and `j` as loop indeces and `+` and `-` to navigate and borders have to be taken into account. So I tried a different solution this time. I didn't fill the grid with integers but with instances of my own class `Tree` which all know their siblings. In that class I used recursion to calculate the solutions. So I didn't have to mess around with indices and borders. My Tree class can maybe still be slimed down a bit. Using a Non-Tree instance instead of `null` might be cool too. But time was over. Edit: I implemented the Non-Tree version later. Makes the code better to read and I got rid of the nulls.

---

**Day 9**

Arrgh. I totally over-engineered part 1 and then got lost in part 2 with a problem in my hash-method. Needed way to much time. Bad day.

---

**Day 10**

Easy one with funny ASCII art as result of part 2. My code is ok but the main loop is doubled which I do not like. EDIT: I uploaded a shorter version where I used lambda expression to use the methods as parameter for the main loop. The code is now way shorter but still not clean as it does not feel good that both methods use the same variables. But I decided to fetch me a cold beer instead of refactoring. :-P
Even shorter is the version, where the calculation for part 1 and 2 are done in one method.

---

**Day 11**

Part 1 was great. Some parsing and a few calculation. Part 2 was the hell, because the numbers got too big (which was intended by AoC). I couldn't solve it with BigInteger, because it was too slow. I didn't know the math how to reduce the items by using a super modulo. So I needed help in reddit to find out the math behind it.

---

**Day 12**

I do not like the days where it is just about mathematics and nothing else. I used the `BFS (Breadth-first search)` this year, as I used the `Dijkstra` algorithm last year. I tried to solved it without using a grid but Node-classes that know their siblings instead. It would have worked but I had to remember the last row for setting the siblings which didn't lead to less or cleaner code than using a grid directly.

---

**Day 13**

Ouch. I tried to find an error in my compare method for three hours just to find out that the parsing was the problem: numbers might be bigger than one digit which my parsing code did not reflect. At least it worked after that... but my code got worse and worse while trying to find the problem and using recursion was the wrong idea, as there were too many exceptions in the rules. In real life I would have to throw it aways and start over for having it clean. ;-)
Part 2 was easy. I just introduced a `Comparator`, threw everything in a list and sort it. Took 5 minutes.

---

**Day 14**

Nice one. I decided to neither use `array` nor `recursion` the next time AoC presents a grid exercise (which was today). So I put all blocked coordinates into a `HashSet` to try out what happens. It worked but I do not think that I will use this approach again in the future. ;-)
Edit: I didn't like my code as there where too many while loops and bad use of null return values. So I did a refactoring and use `recursion` after all. The code is a lot more intuitive now.

---

**Day 15**

It was fun to get the logic behind it. But I had no time left for part 2.

---

**Day 17**

Programming `Tetris` was real fun. I worked with a clean Object structure to keep the overview. But creating the objects and handle them in a Map not fast enough for part 2. I would have to wait 3 days for the result. ;-)

---

**Day 18**

This was fun. I thought of a clean object oriented structure first but then decided to just throw the coordinates in a list. It worked fine.
For part 2 it took me a while to understand that it is not enough to search for strait lines to the border but to also take winding tunnels into account. I flooded the outer box with water so that all tunnels get filled and then add the remaining air to the droplet and use part 1 calculation.

---
 
**Day 20**

Nice one. I decided to just work with the indices to not get caught again by a clean but too slow design for part 2. I had a one-off problem in my modulo for negative indices so I needed a little bit help by other code examples. Part 2 was easy because my code was fast enough this time.

---
 
**Day 21**

I just a full-blown `observer pattern` as solution. It was fun as it is kind of rare that patterns are helpful in AoC. Mostly algorithms are needed. But obserever pattern is still a pain in the ass in Java. The class `Observable` was  - mercifully - deprecated in Java 9. I used the `PropertyChangeListener` which is not good either as it does not support generics, so you are forced to cast. Next time i would try `java.util.concurrent.Flow`.
For part 2 I was too lazy implementing cutting in halves. So I searched for an working start value manually and let a loop do the rest. :-P

---

**Day 22**

Nice one. I didn't use a grid but Objects which know the position next to them and recursion. Would have worked great with the second part too. But my brain was too lazy to find out which sites of the cube do touch each other.

---
 
**Day 23**

Nice one again. My code is a little bit lengthy and should be refactored a little bit. But it perfectly fitted for part 2 which took me just a few seconds.

---
 
**Day 24**

Sounds too time-consuming to implement on Christmas. Some kind of graph operation (e.g. `BFS`) but in a dynamically changing graph. 

---
 
**Day 25**

I enjoyed this one. But as I didn't find the math behind it, I have a lot of if then else in my code. ;-) 

---