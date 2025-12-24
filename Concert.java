//package cses_sorting_and_searching;
import java.io.*;
import java.util.*;
 
public class Concert {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int n = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		
		ArrayList<Integer> tx = new ArrayList<>();
		
		temp = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			tx.add(Integer.parseInt(temp[i]));
		}
		
		Collections.sort(tx);
		
		temp = br.readLine().split(" ");
		int p;
		int q;
		for(int i = 0; i < m; i++) {
			p = Integer.parseInt(temp[i]);
			
			q = Collections.binarySearch(tx, p);
			//System.out.println(q + " q");
			
			if(q == -1) {
				System.out.println(-1);
				continue;
			}
			
			q = q < 0 ? (q+1) * -1 - 1: q;
			
			System.out.println(tx.get(q));
			tx.remove(q);
			
			
		}
		
	}
}
