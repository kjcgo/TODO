//package twoPointers;
import java.util.*;
import java.io.*;

public class DiamondCollection {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int k = Integer.parseInt(line[1]);
		
		long[] nums = new long[n];
		for(int i = 0; i < n; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(nums);
		for(int i = 0; i < n; i++) {
			System.out.print(nums[i] + " ");
			
		}
		System.out.println();
		int r = 0;
		PriorityQueue<Long> pq
        = new PriorityQueue<Long>(
            Collections.reverseOrder());
		pq.add((long)0);
		//int ans = Integer.MIN_VALUE;
		for(int l = 0; l < n; l++) {
			long max = 1;
			r = l;
			/*
			System.out.println("l: " + l + " r: " + r);
			System.out.println("N IS " + n + " R+1 is " + (r+1));
			
			if(r+1<n)
				System.out.println("DIFF " + (nums[r+1] - nums[l]));
			//System.out.println("NEW ITERATION");
			*/
			
			while(r + 1 < n && nums[r+1] - nums[l] <= k) {
				r++;
				max++;
				System.out.println("l: " + l + " r: " + r);
				//System.out.println(max);
				
				
			}
			//System.out.println("R " + r);
			System.out.println(max);
			pq.add(max);
		}
		
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("diamond.out"));
		long ans = pq.poll() + pq.poll();
		bw.write(String.valueOf(ans));
		bw.close();
		System.out.println("ANS " + ans);
	}
}
