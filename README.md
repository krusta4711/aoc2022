**Advent of Code 2022**

My solutions for the great Advent of Code 2022 exercises (https://adventofcode.com/2022) in Java. The code is not always pefectly clean as I often try out things just to try them out (e.g. single liners with streams).

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

As intended, I used just a single stream obe-liner. Was tough as I'm not a fan of hard to read single line code. But was fun too.

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

Arrgh. I totally over-engineered part 1 and then got lost in part 2 with problems in my hash-method. Needed way to much time. Bad day.

---

**Day 10**

Easy one with funny ASCII art as result of part 2. My code is ok. Nevertheless I try to change it to functional interfaces, so that the looping is not doubled in the code.

---
