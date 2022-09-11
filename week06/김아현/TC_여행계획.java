package union_find;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
<TC1>
5 4
0 1 0 1 1
1 0 1 1 0
0 1 0 0 0
1 1 0 0 0
1 0 0 0 0
2 3 4 3

<TC2>
5 4
0 1 0 1 1
1 0 0 1 0
0 0 0 0 0
1 1 0 0 0
1 0 0 0 0
2 3 4 3

<TC3>
1 1
1
1

*/

public class TC_여행계획 {
	
	static int N, M, res;
	static int[] parents;
	static int[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) union(i, j);
			}
		}
		
		list = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		res = 1;
		for (int i = 0; i < M-1; i++) {
			if(findSet(list[i]) != findSet(list[i+1])) {
				res = 0;
				break;
			}
		}
		
		System.out.println(res == 0 ? "NO" : "YES");
	}
	
	static int findSet(int x) {
		if(parents[x] == x) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static void union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		if(x != y) {
			if(x < y) parents[y] = x;
			else parents[x] = y;
		}
	}

}
