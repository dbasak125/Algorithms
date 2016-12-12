/**
 * Program to calculate the Longest Common Subsequence
 * 
 * @author dbasak
 * @email  dbasak@buffalo.edu
 * @date   08-Dec-2016
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class LCS {
	static int[][] cost;
	static int[][] map;
	static char[] A;
	static char[] B;
	
	public static void main(String args[]) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "UTF-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "UTF-8"));
			Scanner sc = new Scanner(br);
			
			//initialize variables
			String a = sc.nextLine();
			String b = sc.nextLine();
			cost = new int[a.length()+1][b.length()+1];
			map = new int[a.length()+1][b.length()+1];
			A = a.toCharArray();
			B = b.toCharArray();
			
			//initialize starting cost to 0 for dimension 1
			for(int j=1; j<b.length()+1; j++) {
				cost[0][j] = 0;
			}
			//calculating optimal cost functions and length of LCS
			for(int i=1; i<a.length()+1; i++) {
				cost[i][0] = 0;
				for(int j=1; j<b.length()+1; j++) {
					if(A[i-1] == B[j-1]) {
						cost[i][j] = cost[i-1][j-1] + 1;
						map[i][j] = 1;
					} else
						if(cost[i][j-1] >= cost[i-1][j]) {
							cost[i][j] = cost[i][j-1];
							map[i][j] = 2;
						} else {
							cost[i][j] = cost[i-1][j];
							map[i][j] = 3;
						}
				}
			}
			
			//reconstruction of LCS
			int i = a.length();
			int j = b.length();
			bw.write(cost[i][j]+"\n"+reconst(i,j));
			bw.flush();
			
		} catch(Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
		}
	}
	
	static String reconst(int i, int j) {
		if(i>=1 && j>=1) {
			if(map[i][j] == 1)
				return reconst(i-1,j-1) + A[i-1];
			else if(map[i][j] == 2)
				return reconst(i,j-1);
			else
				return reconst(i-1,j);
		} else
			return "";
			
	}
}
