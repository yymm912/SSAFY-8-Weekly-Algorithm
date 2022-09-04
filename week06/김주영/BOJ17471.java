package samsungA;

/*
 *  #1. 구역을 나누는 comb 함수 내에서 한 구역의 크기가 1이상일 때마다 매번 검사를 한다 
 *  	(나누어진 구역이 연결되어 있는지, 연결되어 있다면 인구수 차를 계산해서 최솟값 갱신)
 *  
 *  #2. 두 개의 나눈 구역 list<Integer> red, blue 내에서 서로 연결되어 있는지 확인
 *  	-> bfs, dfs 둘 다 구현 가능한데 여기서는 dfs 쓰면 너무 머리 아픈듯
 *  
 *  	selected[i]=true 이면 red, false이면 blue 이기 때문에 이걸 활용해서 isConnected 함수 구현
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {

	static int N, ans=Integer.MAX_VALUE;
	static boolean[][] adj;
	static int[] population;
	static boolean[] selected, connected;
	
	static List<Integer>red, blue;
	
	public static void main(String[] args) throws IOException  {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		
		adj=new boolean[N+1][N+1];
		population=new int[N+1];
		selected=new boolean[N+1];
		red=new ArrayList<>();
		blue=new ArrayList<>();
		
		StringTokenizer st=new StringTokenizer (br.readLine());
		for (int i=1; i<=N; i++) {
			population[i]=Integer.parseInt(st.nextToken());
		}
		
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			int M=Integer.parseInt(st.nextToken());
			for (int j=0; j<M; j++) {
				int to=Integer.parseInt(st.nextToken());
				adj[i][to]=true;
				adj[to][i]=true;
			}
		}
		
		comb (0,1,false);
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
	
	//flag true=> comb 에 들어오자마자 선거구가 연결되어 있는지 확인하고, 인구 차 계산해라는 뜻
	static void comb (int tgtIdx, int srcIdx, boolean flag) {
	
		if (tgtIdx==N) return;				// 선택된 수
		if (srcIdx==N+1) return;			// 선택할 선거구 번호
		
		if (flag && tgtIdx!=0) {			// 선택된 수가 1개 이상이어야 계산 가능
			red.clear(); blue.clear();
			
			int redSum=0;
			int blueSum=0;
			for (int i=1; i<=N; i++) {
				if (selected[i]) {
					red.add(i);
					redSum+=population[i];
				}
				else {
					blue.add(i);
					blueSum+=population[i];
				}
			}
					
			if (isConnected(red,true) && isConnected(blue,false)) {
				int diff=Math.abs(redSum-blueSum);
				ans=Math.min(ans, diff);
			}
		}
	
		selected[srcIdx]=true;
		comb (tgtIdx+1, srcIdx+1, true);
		selected[srcIdx]=false;
		comb (tgtIdx, srcIdx+1, false);
	}
	
	//flag= red인지 blue인지 확인
	static boolean isConnected (List<Integer> list, boolean flag) {
		connected=new boolean[N+1];
		int cnt=1;
		
		Queue <Integer> q=new ArrayDeque<>();
		q.add(list.get(0));
		connected[list.get(0)]=true;
		
		while (!q.isEmpty()) {
			int n=q.poll();
			
			for (int i=1; i<=N; i++) {
				if (adj[n][i] && !connected[i] && selected[i]==flag) {
					connected[i]=true;
					cnt++;
					q.add(i);
				}
			}
		}
		
		if (cnt==list.size()) return true;
		else return false;
	}
}
