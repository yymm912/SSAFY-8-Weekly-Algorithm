package week9.김정윤;

import java.io.*;
import java.util.*;

public class BJ11657_타임머신 {
	static int N, M;
	static final int INF = 999999999;
	static long[] d;
	static ArrayList<Bus> bus = new ArrayList<>();
	
	public static class Bus {
		int start;
		int end;
		int cnt;
		
		public Bus(int start, int end, int cnt) {
			this.start = start;
			this.end = end;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		d = new long[N+1]; // 0 dummy
		Arrays.fill(d, INF);
		
		// 버스 정보 저장
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			bus.add(new Bus(s, e, c));
		}
		if (busRoute(1)) { // 무한
			System.out.println(-1);
		} else {
			// 도착지점 최단경로 확인
			for (int i = 2; i <= N; i++) {
				if (d[i] == INF) // 최단거리 경로에 무한값
					System.out.println(-1); 
				else
					System.out.println(d[i]);
			}
		}
		
	}
	static boolean busRoute(int start) {
		boolean isInf = false;
		d[start] = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				int s = bus.get(j).start;
				int e = bus.get(j).end;
				int c = bus.get(j).cnt;
				
				if (d[s] != INF && d[s] + c < d[e]) {
					d[e] = d[s] + c; // 도착지점 = 시작지점 + 이동시간
					if (i == N) isInf = true;
					System.out.println(i + ", " + j);
					System.out.println(d[s] + "->" + d[e] + ":" + isInf);
				}
			}
		}
		return isInf;
	}
}
