
package seesaw;


import java.util.Scanner;


public class Seesaw3 {
	public static void main(String[] args){
        
		final double F = 1e10;
        //input
        Scanner scan = new Scanner(System.in);
        final int n = scan.nextInt();
        double[][] people = new double[n][4];
        for(int i = 0; i < n; i++){
        	System.out.println("Please enter the position and weight of person " + i);
            double ind = scan.nextDouble();
            double wght = scan.nextDouble();
            people[i][0] = ind;
            people[i][1] = wght;
            people[i][2] = wght;
            people[i][3] = 1;
        }
        
        if(n == 1) {
        	System.out.println(people[0][0]);
        }
        scan.close();
        
        double dist = 0;
        
        System.out.println("_____");
        //move people until 0 torque
        double trq = torque(people, n);
        System.out.println("Current torque is " + trq);
        
        for(int i = 0; i < n; i++) {
        	System.out.print("[" + people[i][0] + ", " + people[i][1] + ", " + people[i][2]+ ", " +people[i][3] + "]");
        	System.out.print(", ");
        }
        //if not already balanced
        double eps = 1e-12;
        
        while(Math.abs(trq) > eps){
        	
        	//find highest efficiency, ignoring NaN
        	int indEff = -1;  

        	//TODO better structure?
        	for (int i = 0; i < n; i++) {
        	    if (Double.isNaN(people[i][2])) {
        	        // skip NaN elements
        	        continue;
        	    }
        	    if (indEff == -1 || people[i][2] > people[indEff][2]) {
        	        indEff = i;
        	    }
        	}
        	System.out.println("\nindEff is " + indEff);
        	
        	
        	
        
        	//see if someone is in the way
        	
        	//TODO Compress and make cleaner
        	boolean conflict = false;
        	
        	int offset = 0;
        	
        	/*
        	//if we are moving right and someone is in the way
        	if(trq < 0 && indEff != n-1 && people[indEff+1][0] < ideal) {
        		offset = 1;
        		conflict = true;
        	}
        	*/
        	
        	int start = 1;
        	if(trq < 0 && indEff != n-1) {
        		while(people[indEff+start][0] == Double.NaN) {
        			start++;
        		}
        		if(people[indEff+start][0] < ideal) {
        			offset = 1;
            		conflict = true;
        		}
        	}
        	
        	/*
        	if(trq > 0 && indEff != 0 && people[indEff-1][0] > ideal) {
        		offset = -1;
        		conflict = true;
        	}
        	*/
        	
        	if(trq > 0 && indEff != 0) {
        		while(people[indEff-start][0] == Double.NaN) {
        			start++;
        		}
        		if(people[indEff-start][0] > ideal) {
        			offset = 1;
            		conflict = true;
        		}
        	}
        	
        	
        	if(conflict) {
        		System.out.println("THERE IS A CONFLICT");
            		//update weight 
            		people[indEff+offset][1] = people[indEff+offset][1] + people[indEff][1];
            		System.out.println("Weight is now " +  people[indEff+offset][1]);
            		
            		//update occupancy
            		people[indEff+offset][3]++;
            		System.out.println("Occupancy is now " +  people[indEff+offset][3]);
            		
            		//update efficiency
                    people[indEff+offset][2] = people[indEff+offset][1] / people[indEff+offset][3];
            		System.out.println("Efficiency is now " +  people[indEff+offset][2]);
            		
            		//update torque
            		trq = trq - people[indEff][2] * (people[indEff][0] - people[indEff+offset][0]);
            		System.out.println("torque is now" + trq);
            		
            		//update distance 
            		dist += Math.abs(people[indEff+offset][0] - people[indEff][0]);
            				
            		//"delete" moved element
            		for (int j = 0; j < people[indEff].length; j++) {
            		    people[indEff][j] = Double.NaN;
            		}
        		}
        		else {
        			trq = 0;
        			dist += Math.abs(ideal - people[indEff][0]) * people[indEff][3];
        			
        		}
        }
        System.out.println("Distance is " + dist);
        
    }
    
    //return torque
    public static double torque(double people[][], int n){
        double res = 0;
        for(int i = 0; i < n; i++){
            res += people[i][0] * people[i][1];
        }
        return res;
    
    }
}
