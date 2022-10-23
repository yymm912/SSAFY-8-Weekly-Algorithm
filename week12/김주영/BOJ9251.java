package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251 {

	static String a,b;
	static int[][] cache;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		a=br.readLine();
		b=br.readLine();
		
		cache=new int [a.length()+1][b.length()+1];
		
		//cache[][0], cache[0][] --> dummy 
		for (int i=0, aSize=a.length(); i<aSize; i++) {
			for (int j=0, bSize=b.length(); j<bSize; j++) {
				
				if (a.charAt(i)==b.charAt(j)) {
					
					cache[i+1][j+1]=cache[i][j]+1;
					
				} else {
					
					cache[i+1][j+1]=Math.max(cache[i][j+1], cache[i+1][j]);
					
				}
			}
		}
		System.out.println();
		for (int i=0; i<=a.length(); i++) {
			for (int j=0; j<=b.length(); j++) {
				System.out.print(cache[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println(cache[a.length()][b.length()]);
		
	}
	
	

}
