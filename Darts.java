//package challenge;
import java.util.*;
import java.io.*;


public class Darts {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input;
		
		while ((input = br.readLine()) != null && !input.trim().isEmpty()) {

			
			//stores by smallest polar angle
			//PriorityQueue<Point> pq = new PriorityQueue<>();
			
			String[] line = input.split(" ");
			if(line.length == 2) {
				System.out.println(100);
				continue;
			}
			else if(line.length == 4) {
				double x1 = Double.parseDouble(line[0]); double x2 = Double.parseDouble(line[1]);
				double y1 = Double.parseDouble(line[2]); double y2 = Double.parseDouble(line[3]);
				
				double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
				System.out.println(200 / (1+dist));
				continue;
			}
			ArrayList<Point> sortedPts = new ArrayList<>();
			
			//store dart coordinates
			//make custom object?
			int darts = line.length;
			double[][] points = new double[darts][2];
			int at = 0;
			
			double[] start = new double[] {Integer.MIN_VALUE, Integer.MAX_VALUE};
			
			//TODO see if starting with objects and updating is faster 
			for(int i = 0; i < darts; i+=2) {
				points[at][0] = Double.parseDouble(line[i]);
				points[at][1] = Double.parseDouble(line[i+1]);
		
				//System.out.println(points[at][1] + " " + start[1]);
				//identify the point with the lowest y coordinate, with max x coordinate as a tie breaker
				if(points[at][1] < start[1] || (points[at][1] == start[1] && points[at][0] > start[0])) {
					start[0] = points[at][0];
					start[1] = points[at][1];
				}
				at++;
			}
			
			//insert into min heap
			double x, y;
			for(int i = 0; i < darts / 2; i++) {
				x = points[i][0];
				y = points[i][1];
				
				//use inverse negative slope as metric
				//System.out.println(x + " " + y + " " + -((x - start[0] / (y-start[1]))));
				//TODO is this line necessary?
				if(x != start[0] && y != start[1]) {
					sortedPts.add(new Point(x, y, -(((x - start[0]) / (y-start[1])))));
					
					//pq.add(new Point(x, y, -((x - start[0] / (y-start[1])))));
				}
			}
			
			//TODO review lambda and custom comparators
			sortedPts.sort((p1, p2) -> Double.compare(p1.r, p2.r));
			//System.out.println(start[0] + " " + start[1]);
			//TODO should I make a stack?
			Stack<Point> chPts = new Stack<>();
			chPts.add(new Point(start[0], start[1], -1));
			chPts.add(sortedPts.get(0));
			chPts.add(sortedPts.get(1));
			/*
			for(Point pt : sortedPts) {
				System.out.println(pt.x + " " + pt.y);
			}
			*/
			for(int i = 2; i < darts/2 - 1; i++) {
				
				Point curr = sortedPts.get(i);
				//System.out.println("POINT " + curr.x + " " + curr.y);
				while(isLeftTurn(chPts.get(chPts.size()-2), chPts.get(chPts.size()-1), curr) < 0 ) {
					//System.out.println("IS RIGHT TURN, popping " + chPts.peek().x + " " + chPts.peek().y);
					chPts.pop();
				}
				chPts.add(curr);
			}
			/*
			for(Point pt : chPts) {
				System.out.println(pt.x + " " + pt.y);
			}
			*/
			
			double ans = 0;
			int limit = chPts.size();
			//System.out.println("SIZE " + chPts.size());
			Point ref = chPts.peek();
			Point prev = chPts.pop();
			Point curr;
			//System.out.println("STARTING AT " + start[0] + " " + start[1]);
			
			for(int i = 0; i < limit-1; i++) {
				//System.out.println("I IS " + i);
				curr = chPts.pop();
				//System.out.println(prev.x + " " + prev.y + " and " + curr.x + " " + curr.y);
				ans += Math.sqrt(Math.pow(prev.x - curr.x, 2) + Math.pow(prev.y - curr.y, 2));
				
				prev = curr;
			}
			ans += Math.sqrt(Math.pow(prev.x - ref.x, 2) + Math.pow(prev.y - ref.y, 2));
			//System.out.println(ans);
			
			System.out.println(100 * (darts/2) / (1+ans));
			/*
			for(Point pt : sortedPts) {
				System.out.print(pt.r + " ");
			}
			System.out.println();
			System.out.println(start[0] + " " + start[1]);
			*/
			
		}
	}
	
	//p is the start, q is the current point, r is the next candidate point
	public static double isLeftTurn(Point p, Point q, Point r) {
		double a = p.x, b = q.x, c = r.x, d = p.y, e = q.y, f = r.y, g = 1.0, h = 1.0, i = 1.0;
		return a * (e*i - f*h) - b * (d*1 - f*g) + c * (d*h - e*g);
	}
	
}
class Point{
	
	double x;
	double y; 
	double r;
	
	public Point(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	/*
	@Override 
	public int compareTo(Point point) {
		return Double.compare(this.r, point.r);
	}
	*/
	
	
}
