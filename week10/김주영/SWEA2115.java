package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA2115 {

	static int T,N,M,C, ans, total;
	static int [][] map;
	static boolean[][] selected;
	static int [] tgt;
	
	static Stack<int[]>s=new Stack<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			selected=new boolean[N][N];
			tgt=new int[M*2];
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			ans=Integer.MIN_VALUE;
			dfs (0,0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void dfs (int tgtIdx, int y) {
		if (tgtIdx==2) {		
			check();
			return ;
		}
		
		for (int i=y; i<N; i++) {
			LOOP: for (int j=0; j<N; j++) {
				if (selected[i][j]) continue;
				
				if (j+M-1>=N) break;
				
				
				for (int k=0; k<M; k++) {
					if (selected[i][j+k])
						continue LOOP;
				}
				
				
				for (int k=0; k<M; k++) {
					s.push(new int[] {i, j+k});	
					selected[i][j+k]=true;
					
					if (tgtIdx==0) tgt[k]=map[i][j+k];
					else tgt[M+k]=map[i][j+k];
				}
				
				dfs (tgtIdx+1, i);
				for (int m=0; m<M; m++)
					selected[s.peek()[0]][s.pop()[1]]=false;
				
			}
		}
	}
	
	static void subSum (int [] arr, int idx, int sum, int cSum) {
		if (idx==M) {
			total=Math.max(total, sum);
			return ;
		}
		
		if (cSum+arr[idx]<=C) 
			subSum(arr, idx+1, sum+(arr[idx]*arr[idx]), cSum+arr[idx]);
		subSum (arr, idx+1, sum, cSum);
		
	}
	
	static void check () {
		int[] a=new int[M];
		int[] b=new int[M];
		

		
		for (int i=0; i<M; i++)
			a[i]=tgt[i];
		for (int i=M; i<2*M; i++)
			b[i-M]=tgt[i];
		
		subSum(a, 0,0,0);
		int aSum=total;
		total=0;
		subSum(b, 0,0,0);
		int bSum=total;
		total=0;
		ans=Math.max(ans, aSum+bSum);	
	}

}
