//package prefixSums;
//https://codeforces.com/contest/1826/problem/D

import java.util.*;
import java.io.*;

public class RunningMiles {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t = Long.parseLong(br.readLine());
		
		Queue<Long> q = new LinkedList<>();
		
		PriorityQueue<Beauty> pq = new PriorityQueue<>();
		//HashMap<Integer, Integer> hm  = new HashMap<>();
		
		for(int i = 0; i < t; i++) {
			q.clear();
			pq.clear();
			long n = Long.parseLong(br.readLine());
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < n; j++) {
				//hm.put(Long.parseLong(input[j]), j+1);
				pq.add(new Beauty(Long.parseLong(input[j]), j+1));
			}
			
			//store all beauties in order
			Beauty[] beauties = new Beauty[(int) n];
			long[] prefix = new long[(int) (n+1)];
			prefix[0] = 0;
			
			//make prefix sums of all beauties and make sliding window
			//calculate distances... more efficient way?
			
			/*
			System.out.println(a + " " + b  + " " + c);
			int min = Math.min(Math.min(a, b), c);
			int max = Math.max(Math.max(a, b), c);
			a = 0;
			b = 0;
			c = 0;
			dist[k-2] = max - min;
			*/
			
			//TODO find a way to store distances efficiently
			//0,0,4,2,3 for first test case.
			
			long dist[] = new long[(int) n];
			long far = -1;
			long close = Long.MAX_VALUE;
			
			for(int k = 0; k < n; k++) {
				beauties[k] = pq.poll();
				//System.out.println(beauties[k].value + " " + beauties[k].loc);
				prefix[k+1] = (prefix[k] + beauties[k].value);
				if(k < 2) {
					q.add(beauties[k].loc);
				}
				else {
					q.add(beauties[k].loc);
					for(Long x : q) {
						//System.out.print("x: " + x + " ");
						if(x > far) {
							far = x;
						}
						if(x < close) {
							close = x;
						}
					}
					 
					//System.out.println("FAR AND CLOSE " + far + " " + close);
					dist[k] = far - close; 
					q.remove();
					far = -1;
					close = Integer.MAX_VALUE; 
				}

			}
			/*
			System.out.println("PREFIX");
			for(int x : prefix) {
				System.out.println(x);
			}
			System.out.println("DISTANCES");
			for(int x : dist) {
				System.out.println(x);
			}
			System.out.println("RESULTS");
			*/
			int r = 3;
			long res = 0;
			long max = 0;
			for(int l = 0; l < n-2; l++) {
				res = prefix[r] - prefix[l] - dist[r-1];
				//System.out.println(prefix[r] + " - " + prefix[l] + " - " + dist[r-1] + " = " + res);
				max = Math.max(res, max);
				r++;
			}
			
			System.out.println(max);
		}
		
		
		
	}

}
//package prefixSums;

//review how this works again
class Beauty implements Comparable<Beauty>{
	long value;
	long loc;
	public Beauty(long value ,long loc) {
		this.value = value;
		this.loc = loc;
	}
	
	@Override
	public int compareTo(Beauty x) {
	    if (this.value != x.value) {
	        // higher value first
	        return Long.compare(x.value, this.value);
	    }
	    // tie: smaller loc first
	    return Long.compare(this.loc, x.loc);
	}

}
