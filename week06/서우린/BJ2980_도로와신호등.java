package _6주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2980_도로와신호등 {
	static int N,L;
	static int [] D,R,G;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		D = new int[N];
		R = new int[N];
		G = new int[N];
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			D[i] = Integer.parseInt(st.nextToken());
			R[i] = Integer.parseInt(st.nextToken());
			G[i] = Integer.parseInt(st.nextToken());
		}
		
		int dist = 0 , ans = 0;
		
		for(int i = 0;i<N;i++) {
			ans += D[i] - dist;
			dist = D[i];
			
			int cycle = R[i] + G[i];
			if(ans % cycle < R[i]) ans += R[i] - (ans % cycle);
		}
		
		ans += L-dist;
		
		System.out.println(ans);
	}

}
