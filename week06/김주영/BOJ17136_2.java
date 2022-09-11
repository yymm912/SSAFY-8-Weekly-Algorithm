package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17136_2 {

	static int N=10;
	static int ans=Integer.MAX_VALUE;
	static char[][] map=new char[N][N];
	static int[] paper=new int[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));


		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=st.nextToken().charAt(0);
			}
		}
		
		dfs(0,0,0);
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
	
	private static void dfs (int y, int x, int cnt) {
		
		boolean flag=false;
		
		/*
		 * 1이 있는 위치를 찾아냄
		 * 만약 (N-1, N-1)까지 갔다면 탐색이 끝난 것 -> 최솟값 갱신
		 */
		for (int i=y; i<N; i++) {
			for (int j=x; j<N; j++) {
				if (map[i][j]=='1') {
					y=i; x=j;
					flag=true; 
					break;
				}
				if (i==N-1 && j==N-1) {
					ans=Math.min(ans, cnt);
					return ;
				}
			}
			if (flag) break;
		}
		
		for (int s=5; s>0; s--) {
			
			if (paper[s]==5) continue;
			if (canAttach (y,x,s)) {
				updateMap (y,x,s,'0');
				paper[s]++;				
				dfs (y,0,cnt+1);			//이거 맨날 실수함 x좌표는 0으로 보내주기!!!!!
				paper[s]--;
				updateMap (y,x,s,'1');
			}
		}
	}
	
	private static boolean canAttach (int y, int x, int size) {
		for (int i=y; i<y+size; i++) {
			for (int j=x; j<x+size; j++) {
				if (i>=N || j>=N || map[i][j]!='1')
					return false;
			}
		}
		return true;
	}
	
	private static void updateMap (int y, int x, int s, char mark) {
		for (int i=y; i<y+s; i++)
			for (int j=x; j<x+s; j++)
				map[i][j]=mark;
	}
	
	private static void print () {
		System.out.println("==============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
