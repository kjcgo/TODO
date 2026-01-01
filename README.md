Problems I've driven myself insane over and would like to solve one day

10/20/25 - Seesaw - https://open.kattis.com/problems/seesaw?tab=metadata 
Difficulty: 8.5
- Works for many test cases yet exceeds time limit
- Creating a new object for every person and constant updates to that list
- Updating neighbors as groups merge is difficult

11/7/25 - Parking Lot - https://open.kattis.com/contests/gd2v8k/problems/parkinglot?tab=metadata
Difficulty: 8.1
- Attempted to reconstruct a graph based on searching through candidate nodes then run Dijkstra's on that represenatative graph
- Almost certain this uses a supercover version of Bresenham's line algo
- Might need to make a frontier using BFS?

11/12/25 - Tree Racing - https://open.kattis.com/problems/treeracing
Difficulty: 6.6
- First tried rerooting the tree at the exit, then running a bfs from each special node to get all players who will pass it. Too much recalcuating. DP?
- Attempted to use an Euler tour with tout/tin to access subtrees. Functions as expected, yet faulty logic with actually utilizing it
- https://www.youtube.com/live/Taqvfalnym0?si=oGKzwaE4PGUZdVeQ&t=13334 !!!! <- Extremely helpful

12/18/25 - Running Miles - https://codeforces.com/contest/1826/problem/D
Difficulty: NA
- Very close. Greedy is partially correct. Fails because you have to greedily pick the numbers on either end, to minimize the cost.
- Fails for this particular case:
  1
  8
  10 8 5 10 6 2 9 5
  Expected value is 25, getting 23. Optimal triple is 10, 8, 10, NOT 8, 10, 9. Greedily pick the OUTER numbers.
- Could probably solve this if I just put more time but I'm sick of this problem for now.

12/23/25 - Concert Tickets - https://cses.fi/problemset/task/1091/
Difficulty: USACO Gold
- Attempts with binary search in Python (bisect.bisect()) and Java (Collections.binarySearch()) are timing out
- Requires a data structure I am not yet familiar with (multisets)
- I really should learn C++...

12/30/25 - Towers - https://cses.fi/problemset/result/15774917/
Difficulty: USACO Gold
- Original attempts are valid but time out
- Read editorial and corresponding article: https://usaco.guide/problems/cses-1073-towers/solution
- https://usaco.guide/gold/intro-sorted-sets?lang=cpp#problem-cses-1073

12/31/25 - Ten Kinds of People - https://open.kattis.com/problems/tenkindsofpeople?tab=metadata
Difficulty: 3.3
- Find the integer solution (if it exists) to a polynomial equation.
- For example: teH9x eR8LJ\
  $29a^4 + 14a^3 + 43a^2 + 9a + 33 = 14b^4 + 53b^3 + 8b^2 + 47b+ 45$\
  $29a^4 + 14a^3 + 43a^2 + 9a + 33 - 14b^4 - 53b^3 - 8b^2 - 47b - 45 = 0$
- Possibly helpful: https://en.wikipedia.org/wiki/Rational_root_theorem
  https://www.youtube.com/watch?v=jQnIvegVhXg
