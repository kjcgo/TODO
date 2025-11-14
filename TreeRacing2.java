package icpcProblems2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeRacing2 {
	//all racers, (id, distance to end, speed)
	
    //undirected graph
    static ArrayList<Integer>[] adj;

    static int[][] tempRacer;
    //checkpoints, total racers, # allowed at special cps
    static int n;
    static int m;
    static int k;

    //finish line node
    static int finish;

    //number of special checkpoints
    static int scp;

    //special checkpoints
    static int[] special;
    
    //TODO might need to remove this
    //distance of some node from the finish
    static int[] dist;
    static boolean[] finalists;
    
    static int[] start;
    static int[] end;
    static int timer = 0;
    
    static boolean vis[];
    static int eu[];
    
    static HashMap<Integer, int[]> racers = new HashMap<>();


    public static void main(String[] args) throws IOException {
    	long startTime = System.currentTimeMillis();
    	//parse data, populate static globals
        getData();
        
        //start an euler tour from the finishing node, get tin/tout, track distance from finish line
        euler(finish, -1, 0);
        
        for (int[] values : racers.values()) {
        	values[2] = dist[values[3]] * values[1];
        }
      //additional print statements
        //debug();
        
        //eliminate players who will not finish, print results
        results();
        long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime) + " ms");
        
       
    }
    
    @SuppressWarnings("unchecked")
    public static void getData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");

        //n - checkpoints
        //m - racers
        //k - allowed at special checkpoints
        n = Integer.parseInt(info[0]);
        m = Integer.parseInt(info[1]);
        k = Integer.parseInt(info[2]);
        
        vis = new boolean[n+1];
        eu = new int[n+1];
        
        //for euler later 
        start = new int[n+1];
    	end = new int[n+1];
    	dist = new int[n+1];
        //create adjacency list
        adj = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++) {
            adj[i] = new ArrayList<>();   
        }

        String[] line;
        int a;
        int b;
        for(int i = 0; i < n-1; i++) {

            //scan checkpoints
            line = br.readLine().split(" ");

            //avoid off by one since cpts start at 0
            a = Integer.parseInt(line[0]);
            b = Integer.parseInt(line[1]);

            //add to adjacency
            adj[a].add(b);
            adj[b].add(a);

        }
        
        //position and speed
        tempRacer = new int[m][2];
        //get racer information
        for(int i = 0; i < m; i++) {
            line = br.readLine().split(" ");
          
            //key is their position, contains their id, speed, dist, and location
            racers.put(Integer.parseInt(line[0]), new int[] {i+1, Integer.parseInt(line[1]), -1 , Integer.parseInt(line[0])});
        }

        //get finish line
        finish = Integer.parseInt(br.readLine());

        //get count of special checkpoints
        scp = Integer.parseInt(br.readLine());
        special = new int[scp];

        //get special checkpoints
        for(int i = 0; i < scp; i++) {
            special[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void euler(int at, int prev, int ds) {
    	//eu[timer] = at
    	dist[at] = ds;
    	start[at] = ++timer-1;
    	if(racers.containsKey(at)) {
    		
    		//store the id of the player at index at
    		eu[at] = racers.get(at)[0];
    	}
    	
    	
    	
    	for(int n : adj[at]) {
    		
    		if(n != prev) {
     			euler(n, at, ds+1);
    		}
    	}
    	end[at] = timer-1;
    }
    
    public static void results() {
        //1. eliminate players who won't finish, by checking the subtrees for every special checkpoint + seeing who will and won't pass
        //IMPORTANT - check checkpoints in order from farthest to shortest distance to exit!!
        
        //use PQ min heap by first element
        PriorityQueue<int[]> sPQ = new PriorityQueue<>((x, y) -> y[0] - x[0]);

        for(int i = 0; i < scp; i++) {
            sPQ.add(new int[] {dist[special[i]], special[i]});
        }
        
        //organize by smallest seconds and break ties with largest speeds
        PriorityQueue<int[]> fPQ = new PriorityQueue<>((x, y) -> {
            if (x[2] != y[2]) return x[2] - y[2];  // smaller c first
            return x[1] - y[1];                    // smaller b first if tie
        });


        int st = 0;
        int ed = 0;
        
        Set<Integer> set = new HashSet<>();
        int a;
        
        while(!sPQ.isEmpty()) {
            fPQ.clear();
            set.clear();
            
            a = sPQ.poll()[1];
            st = start[a]+1;
            ed = end[a]+1;
            // System.out.println(a);
            // System.out.println("start is " + st + " end is " + ed);
            
            for(int i = st; i < ed+1; i++) {
                if(eu[i] != 0) {
                    // System.out.println("i is " + i + " eu[i] is " + eu[i]);
                    // System.out.println(racers.get(i)[0] +" "+ racers.get(i)[1] +" "+ racers.get(i)[2]);
                    fPQ.add(racers.get(i));
                }
            }
            
            for(int i = 0; i < k; i++) {
                if(!fPQ.isEmpty()) {
                    // System.out.println("the player at node " + ((fPQ.peek()[3]))+ " is added to passing players");
                    set.add((fPQ.poll()[3]));
                }
            }
            
            // System.out.println("going from st " + st + " to ed " + ed);
            for(int i = st; i < ed+1; i++) {
                // System.out.println("i is " + i);
                if(!set.contains(i) && eu[i] != 0) {
                    // System.out.println("setting index " + i + " to zero");
                    eu[i] = 0;
                }
            }
        }

        //id of the winners
        HashMap<Integer, Integer> winners = new HashMap<>();
        
        for(int i = 1; i < n+1; i++) {
            // System.out.println(eu[i]);
            if(eu[i] != 0) {
                // System.out.println("the racer with ID " + eu[i] + " wins");
                winners.put(racers.get(i)[0], racers.get(i)[2]);
            }
        }
        
        //TODO fix this: only print this final loop
        for(int i = 1; i < m+1; i++) {
            if(winners.containsKey(i)) {
                System.out.println(winners.get(i));
            }
            else {
                System.out.println(-1);
            }
        }
    }

    public static void debug() {
    	 
    	System.out.println("--TIME IN TIME OUT--");
        for(int i = 0; i < n+1; i++) {
        	System.out.println("Time in at " + i + ": " + start[i]);
        }
        System.out.println();
        for(int i = 0; i < n+1; i++) {
        	System.out.println("Time out at " + i + ": " + end[i]);
        }
        
        System.out.println("\n--DISTANCES TO FINISH AT NODE N--");
        for(int i = 0; i < n+1; i++) {
        	System.out.println(i + "'s dist from start: " + dist[i]);
        }
        
        System.out.println("\n--EULER TOUR RESULTS--");
        for(int i = 0; i < n+1; i++) {
        	System.out.println("Player at " + i + ": " + eu[i]);
        	
        }
        System.out.println("\n--RACER INFO--");
        for (Map.Entry<Integer, int[]> entry : racers.entrySet()) {
            int key = entry.getKey();
            int[] arr = entry.getValue();

            System.out.print("Racer " + key + ": ");
            System.out.println(Arrays.toString(arr));
        }

        
    }
}
