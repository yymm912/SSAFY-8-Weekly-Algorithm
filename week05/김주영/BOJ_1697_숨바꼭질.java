package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {

	static int N,K;
	static int[] memoi=new int[100001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		
		if (N==K) 
			System.out.println(0);
		else 
			System.out.println(bfs());

	}
	
	private static int bfs() {
		
		memoi[N]=1;
		Queue <Integer> q=new ArrayDeque<>();
		q.add(N);
		
		while (!q.isEmpty()) {
			int n=q.poll();
			if (n==K) 
				return memoi[n]-1;
			
			if (n-1>=0 && memoi[n-1]==0) {
				memoi[n-1]=memoi[n]+1;
				q.add(n-1);
			}
			if (n+1<=100000 && memoi[n+1]==0) {
				memoi[n+1]=memoi[n]+1;
				q.add(n+1);
			}
			if (n*2 <=100000 && memoi[n*2]==0) {
				memoi[n*2]=memoi[n]+1;
				q.add(n*2);
			}
		}
		return -1;
	}
}
