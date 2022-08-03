package hwalgo03_부울경_3반_이재혁;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo03_부울경_3반_이재혁 {
	
	static int n,m;
	static int[][] map;
	  public static void main(String[] args) throws Exception {
		  
		  BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	      int t = Integer.parseInt(br.readLine());
	      for(int test_case = 1; test_case<=t; test_case++) { 
	    	  StringTokenizer st = new StringTokenizer(br.readLine());
	        	n = Integer.parseInt(st.nextToken());
	        	m = Integer.parseInt(st.nextToken());
	        	map = new int[n][n];
	      
		        for(int i=0; i<n; i++) {
		        	st = new StringTokenizer(br.readLine());
		            for(int j=0; j<n; j++) {
		                map[i][j] = Integer.parseInt(st.nextToken());
		            }
		        }
		        
		        int max = 0;
		        
		        for(int i=0; i<n; i++) {
		            for(int j=0; j<n; j++) {
		                int sum = 0;
		                for(int x=0; x<m; x++) {
		                    for(int y=0; y<m; y++) {
		                        int nx = i+x;
		                        int ny = j+y;
		                        if(nx >=n || ny>=n) {
		                            break;
		                        }
		                        sum += map[i+x][j+y];
		                    }
		                }
		                if(max < sum) {
		                    max = sum;
		                }
		            }
		        }
		        System.out.println("#"+test_case+" "+ max);
		        }       
		    }
	}