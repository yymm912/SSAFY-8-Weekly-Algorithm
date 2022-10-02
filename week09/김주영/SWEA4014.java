package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4014 {

	static int T,N,X;
	static int[][] map1, map2;
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			X=Integer.parseInt(st.nextToken());
			
			map1=new int[N][N];
			map2=new int[N][N];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map1[i][j]=Integer.parseInt(st.nextToken());
					map2[j][i]=map1[i][j];
				}
			}
			int ret=check(map1)+check(map2);
			System.out.println("#"+tc+" "+ret);
		}
	}
	
	static int check (int[][] map) {
		
		int cnt=0;
		
		for (int i=0; i<N; i++) {
			
			int prev=map[i][0];
			int dist=1;
			
			for (int j=1; j<N; j++) {
				
				if (prev==map[i][j]) {
					dist++;
				} else if (Math.abs(map[i][j]-prev)>1) {
					break;
				} else if (prev<map[i][j]) {
					if (dist>=X) {
						prev=map[i][j];
						dist=0;
						j--;
					} else break;
				} else if (prev>map[i][j]) {
					
					boolean flag=false;
					int standard=map[i][j];
					for (int k=j; k<=j+X-1; k++) {
						// 범위를넘거나 다리를 짓는 곳의 높이가 각각 다를 때
						if (k>=N || map[i][k]!=standard) {
							flag=true; break;
						}
					}
					if (flag) break;
					
					dist=0;
					j=j+X-1;
					prev=map[i][j];
				}
				
				if (j==N-1) {
					//System.out.println(i+"행"+j+"열");
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
