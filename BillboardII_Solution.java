package rectangle_geometry;
//import java.util.*;
import java.io.*;

public class BillboardsII {
	static int x1, x2, x3, x4, y1, y2, y3, y4;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("billboard.in"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("billboard.out"));

		String[] line = br.readLine().split(" ");
		int x1 = Integer.parseInt(line[0]); int y1 = Integer.parseInt(line[1]);
		int x2 = Integer.parseInt(line[2]); int y2 = Integer.parseInt(line[3]);
		line = br.readLine().split(" ");
		int x3 = Integer.parseInt(line[0]); int y3 = Integer.parseInt(line[1]);
		int x4 = Integer.parseInt(line[2]); int y4 = Integer.parseInt(line[3]);
		br.close();
		int boardArea = (x2-x1) * (y2-y1);
		if(intersect(new int[] {x1, y1, x2, y2}, new int[] {x3, y3, x4, y4})) {
			int interSection =  interArea(new int[] {x1, y1, x2, y2}, new int[] {x3, y3, x4, y4});
			
			if(interSection == boardArea) {
				System.out.println(0);
				bw.write(String.valueOf(0));
				bw.close();
				return;
			}
			if((x3 <= x1 && y3 <= y1 && y4 >= y2 && x4 <= x2) ||
					   (x4 >= x2 && y4 >= y2 && y3 <= y1 && x4 >= x2) ||
					   (x3 <= x1 && x4 >= x2 && y4 >= y2 && y1 <= y3) ||
					   (x3 <= x1 && x4 > x2 && y4 <= y2 && y1 > y3) ) {

				
				System.out.println(boardArea - interSection);
				bw.write(String.valueOf(boardArea - interSection));
				bw.close();
				return;
			}
		}
		System.out.println(boardArea);
		bw.write(String.valueOf(boardArea));
		bw.close();
		
	}
	
	static boolean intersect(int[] s1, int[] s2) {
		int bl_a_x = s1[0], bl_a_y = s1[1], tr_a_x = s1[2], tr_a_y = s1[3];
		int bl_b_x = s2[0], bl_b_y = s2[1], tr_b_x = s2[2], tr_b_y = s2[3];

		// no overlap
		if (bl_a_x >= tr_b_x || tr_a_x <= bl_b_x || bl_a_y >= tr_b_y || tr_a_y <= bl_b_y) {
			return false;
		} else {
			return true;
		}
	}
	static int interArea(int[] s1, int[] s2) {
		int bl_a_x = s1[0], bl_a_y = s1[1], tr_a_x = s1[2], tr_a_y = s1[3];
		int bl_b_x = s2[0], bl_b_y = s2[1], tr_b_x = s2[2], tr_b_y = s2[3];
		return ((Math.min(tr_a_x, tr_b_x) - Math.max(bl_a_x, bl_b_x)) *
		        (Math.min(tr_a_y, tr_b_y) - Math.max(bl_a_y, bl_b_y)));
	}
	
}
