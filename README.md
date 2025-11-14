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
