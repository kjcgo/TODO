package intro_to_graphs;
import java.io.*;


public class BillboardII {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		
		String[] line = br.readLine().split(" ");
		int[] bl_A = new int[] {Integer.parseInt(line[0]), Integer.parseInt(line[1])};
		int[] tr_A = new int[] {Integer.parseInt(line[2]), Integer.parseInt(line[3])};
		
		line = br.readLine().split(" ");
		int[] bl_B = new int[] {Integer.parseInt(line[0]), Integer.parseInt(line[1])};
		int[] tr_B = new int[] {Integer.parseInt(line[2]), Integer.parseInt(line[3])};
		
		br.close();
		
		//whole area of board A
		int width = (tr_A[0] - bl_A[0]);
		int height = (tr_A[1] - bl_A[1]);
		
		int ans = height * width;
		System.out.println(ans);
		
		//SPAGHETTI CODE SCRAP
		 
		//if there's complete horizontal overlap remove it
		if(bl_B[0] <= bl_A[0] && tr_B[0] >= tr_A[0] && (( bl_B[1] <= bl_A[1] && tr_B[1] >= tr_A[1]) || ( bl_B[1] >= bl_A[1] && tr_B[1] <= tr_A[1]) )) {
			System.out.println("horiz");
			System.out.println(horiz(bl_A[0], tr_A[0], bl_B[0], tr_B[0]));
			ans -= horiz(bl_A[0], tr_A[0], bl_B[0], tr_B[0]) * width;
		}
		
		//if there's complete vertical overlap
		else if(bl_B[1] <= bl_A[1] && tr_B[1] >= tr_A[1] && ((bl_A[0] <= bl_B[0] && tr_A[0] >= tr_B[0]) || (bl_A[0] >= bl_B[0] && tr_A[0] <= tr_B[0]))) {
			System.out.println("vert");
			ans -= vert(tr_A[1], tr_B[1], bl_A[1], bl_B[1]) * height;
		}
		
		System.out.println(ans);
		BufferedWriter bw = new BufferedWriter(new FileWriter("billboard.out"));
		bw.write(String.valueOf(ans));
		bw.close();
		
	}
	//courtesy of USACO Guide
	static int horiz(int bl_a_x, int tr_a_x, int bl_b_x, int tr_b_x) {
		int res = Math.min(tr_a_x, tr_b_x) - Math.max(bl_a_x, bl_b_x);
		if(res > 0)
			return(res);
		return 0;
	}
	
	static int vert(int tr_a_y, int tr_b_y, int bl_a_y, int bl_b_y) {
		int res = Math.min(tr_a_y, tr_b_y) - Math.max(bl_a_y, bl_b_y);
		if(res > 0)
			return(res);
		return 0;
	}

}
