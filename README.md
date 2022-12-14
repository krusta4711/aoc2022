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

I wasn't pleased  with my solution. It was neither a nice design nor short. On reddit I found a crazy oneliner which challenged me to use a single stream too on day 4. Let's see. ;-)

---

**Day 4**

As intended, I used just a single stream one-liner. Was tough as I'm not a fan of hard to read single line code (it's much more important to readthan to write code). But was fun too.

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

A good old grid/matrix exercise by Advent of Code. I don't like to use hundreds of loops in loops. Additionally accessing a grid/matrix in a loop can drive one crazy when using `i` and `j` as loop indeces and `+` and `-` to navigate and borders have to be taken into account. So I tried a different solution this time. I didn't fill the grid with integers but with instances of my own class `Tree` which all know their siblings. In that class I used recursion to calculate the solutions. So I didn't have to mess around with indices and borders. My Tree class can maybe still be slimed down a bit. Using a Non-Tree instance instead of `null` might be cool too. But time was over.

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

Ouch. I tried to find an error in my compare  method for three hours just to find out that the parsing was the problem: numbers might be bigger than one digit which my parsing code did not reflect. At least it worked after that... but my code got worse and worse while trying to find the problem and using recursion was the wrong idea, as there were too many exceptions in the rules. In real life I would have to throw it aways and start over for having it clean. ;-)
Part 2 was easy. I just introduced a `Comparator`, threw everything in a list and sort it. Took 5 minutes.

---

**Day 14**

Nice one. I decided to neither use `array` nor `recursion` the next time AoC presents a grid exercise (which was today). So I put all blocked coordinates into a `HashSet` to try out what happens. It worked but I do not think that I will use this approach again in the future. ;-)
Edit: I didn't like my code as there where too many while loops and bad use of null return values. So I did a refactoring and use `recursion` after all. The code is a lot more intuitive now.

---