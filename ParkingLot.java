package mockContest1;

import java.util.*;
import java.io.*;

public class ParkingLot {
	
	
	//nodes of my graph, contains node int[] "at" and arraylist of pairs of neighbors and weights
	static ArrayList<N> nodes = new ArrayList<>();
	
	//corner representation
	//static char[][] corners;
	
	//grid representation
	static char[][] lot;
	
	//rows, columns
	static int r;
	static int c;
	
	
	//nodes that are have a car to their sw
	static boolean[][] blocked;
	
	
	//SW corner
	static int[] end;
	
	//for creating graph
	static Queue<int[]> q = new LinkedList<>();
	
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] line = br.readLine().split(" ");
		 r = Integer.parseInt(line[0]);
		 c = Integer.parseInt(line[1]);
		 blocked = new boolean[r+1][c+1];
		 lot = new char[r][c];
		 
		 //lot = new char[r][c];
		 //corners = new char[r+1][c+1];
		 end = new int[] {r, c};
		 
		 String seg;
		 for(int i = 0; i < r; i++) {
			 seg = br.readLine();
			 
			 for(int j = 0; j < c; j++) {
				 lot[i][j] = seg.charAt(j);

				 //keep track of which nodes are blocked
				 if(lot[i][j] == '#') {
					 blocked[i][j] = true;
				 }
			 }
		 }
		 
		 for(int i = 0; i < c; i++) {
			 blocked[i][r] = true;
		 }
		 for(int i = 0; i < r; i++) {
			 blocked[c][i] = true;
		 }
		 
		 //consider the exit to be "blocked" to make loop work
		 blocked[r][c] = true;
		 
		 for(int i = 0; i < r+1; i++) {
			 for(int j = 0; j < c+1; j++) {
				 System.out.print(blocked[i][j] + "  ");
			 }
			 System.out.println();
		 }
		 
		//displayLot();
		makeGraph();
		
		for(N n : nodes) {
			System.out.println(n.toString());
		}
		
		dijkstras();
		
		
	
	}
	
	/*
	public static void displayLot() {
		 for(int i = 0; i < r; i++) {
			 for(int j = 0; j < c; j++) {
				 System.out.print(lot[i][j]);
			 }
			 System.out.println();
		 }
	}
	*/
	
	public static void makeGraph() {
		
		//start at the top left
		int[] NW = new int[] {0, 0};
		
		//add to queue
		q.add(NW);
		int[] curr;
		int x;
		int y;
	
		//TODO some columns are not worth checking
		
		//neighbors of curr
		ArrayList<Pair> nbs = new ArrayList<>();
	
		//build graph while there are still nodes to add
		while(!q.isEmpty()) {
			
			/*
			for(int[] p : q) {
				System.out.println(p[0] +" "+ p[1]);
			}
			*/
			//clean out neighbors for current
			nbs.clear();
			
			//get new coords
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			
			//System.out.println("curr[0] is " + x + " curr[1] is " + y);
			//if no car
			System.out.println("BLOCKED " + blocked[x][y]);
			if(!blocked[x][y]) {
				System.out.println("in here");
				//check for diagonals, traverse L->R, T->B
				//TODO fix this logic!! only visit nodes that have a car next to them or are the exit
				//TODO create a represenation of nodes that are blocked
				//iterates through rows
				
				for(int i = x; i < c; i++) {
					//iterates through columns
					for(int j = y; j < r; j++) {
						//don't bother checking points that aren't blocked off
						if(!blocked[i][j]) {
							System.out.println("IN HERE");
							continue;
						}
						//potential next node in graph
						int[] ptl = new int[] {i, j};						
						
						//System.out.println("ptl is " + ptl[0] + " " + ptl[1]);
						
						//if the next potential node is not in sight, stop searching row
						if(!inSight(curr, ptl)) {
							System.out.println("IN HERE1");
							break;
						}				
				
						//if it is in sight, at the boundary of a car, add to nodes
						else {
							System.out.println("IN ELSE");
							nbs.add(new Pair(ptl, getWeight(curr, ptl)));
							
							if(!Arrays.equals(ptl, end)) {
								//add to queue of new nodes to search
								q.add(ptl);
								System.out.println("ptl is " + ptl[0] + " " + ptl[1]);
							}
							
							//if the end is reached, only add one neighbor
							else {
								//ONLY add the exit as a neighbor
								//loop will naturally end since we have reached the exit
								nbs.clear();
								nbs.add(new Pair(ptl, getWeight(curr, ptl)));
								
							}
						}
					}
				}
			}
			
			//if has car
			else {
				System.out.println("There's a car");
				//TODO make sure these don't index out of bounds
				//TODO make sure these are the right directions
				//add the right and left nodes to nbs
				//x represents r
				//y represents c
				
				
				if(x < c+1 && x > 0 && y > 0) {
					int[] down = new int[] {x-1,y-1};
					nbs.add(new Pair(down, 1));
					q.add(down);
					System.out.println("adding to queue D" + down[0] + " " + down[1]);
				}
				if(y < r+1 && y > 0) {
					int[] right = new int[] {x, y-1};
					nbs.add(new Pair(right, 1));
					q.add(right);
					System.out.println("adding to queue R " + right[0] + " " + right[1]);
				}
				
				
				
				//add to queue of new nodes to search
				
				
			}
			N toadd = new N(curr, nbs);
			
			System.out.println(toadd.toString());
			//add the new nodes
			nodes.add(toadd);
			
			
		}
	}
	
	 //returns if end is in sight of start
	//supercover bresenham's line algo
	// Global grid of obstacles

	static boolean inSight(int[] start, int[] end) {
	    int x0 = start[0];
	    int y0 = start[1];
	    int x1 = end[0];
	    int y1 = end[1];

	    int dx = Math.abs(x1 - x0);
	    int dy = Math.abs(y1 - y0);
	    int sx = (x0 < x1) ? 1 : -1;
	    int sy = (y0 < y1) ? 1 : -1;

	    int err = dx - dy;
	    int e2, e2_ = err;

	    int w = blocked.length;
	    int h = blocked[0].length;

	    // Visit every cell the line passes through
	    while (true) {
	        // Out of bounds check
	        if (x0 < 0 || y0 < 0 || x0 >= w || y0 >= h)
	            return false;

	        // Blocked cell → line of sight blocked
	        if (blocked[x0][y0])
	            return false;

	        // Reached target cell → clear line of sight
	        if (x0 == x1 && y0 == y1)
	            return true;

	        e2 = err;
	        e2_ = err;

	        if (2 * e2 > -dy) {
	            err -= dy;
	            x0 += sx;
	        }
	        if (2 * e2_ < dx) {
	            err += dx;
	            y0 += sy;
	        }
	    }

	    // Should never reach this
	}

	
	//returns weight of the edge between start and end
	public static double getWeight(int[] start, int[] end) {
		return Math.pow((Math.pow(Math.abs(start[0] - end[0]), 2) + Math.pow(Math.abs(start[1] - end[1]), 2)), .5);
	}
	
	//run dijkstras on our modified graph
	public static void dijkstras() {
		
	}
}


