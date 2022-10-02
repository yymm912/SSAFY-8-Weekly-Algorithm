package baekjoon.타임머신_11657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<Node> list;
	static long[] dis;
	static long INF = Long.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dis = new long[N+1];
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Node(a,b,c));
		}
		
		for (int i = 0; i <= N; i++) {
			dis[i] = INF;
		}
		
		bell();
		
		System.out.println(sb.toString());

	}
	
	static void bell() {
		dis[1] = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Node n = list.get(j);
				if(dis[n.s] == INF) continue;
				if(dis[n.e] > dis[n.s] + n.w) dis[n.e] = dis[n.s] + n.w;
			}
		}
		for (int i = 0; i < M; i++) {
			Node n = list.get(i);
			
			if(dis[n.s] == INF) continue;
			if(dis[n.e] > dis[n.s] + n.w) {
				sb.append(-1);
				return;
			}
		}
		
		for (int i = 2; i <= N; i++) {
			if(dis[i] == INF) sb.append(-1).append('\n');
			else sb.append(dis[i]).append('\n');
		}
	}
	
	static class Node{
		int s,e,w;

		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
	}

}
