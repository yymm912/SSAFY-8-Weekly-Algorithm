import java.io.*;
import java.util.*;

public class Main{
	/**
	 * 크루스칼 알고리즘
	 * 1. x, y, z 정렬해서 가중치 pq에 넣음
	 * 2. pq에서 하나 꺼내서, 두 정점 union이 아니면, union함 
	 * */
	static class V{
		int x, y, z;
		int idx;
		int root;
		public V(int x, int y, int z, int idx) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
			this.root = idx;
		}	
	}
	static class E{
		V a, b;
		int w;
		public E(V a, V b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
	static int N;
	static V[] vers;
	static List<E> elist = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		vers = new V[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			vers[i] = new V(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						i
					);
		}
		V[] xsort, ysort, zsort;
		xsort = vers.clone();
		ysort = vers.clone();
		zsort = vers.clone();
		Arrays.sort(xsort, (a, b) -> a.x - b.x);
		Arrays.sort(ysort, (a, b) -> a.y - b.y);
		Arrays.sort(zsort, (a, b) -> a.z - b.z);
		
		for(int i=0; i<N-1; i++) {
			elist.add(new E(xsort[i], xsort[i+1], xsort[i+1].x - xsort[i].x));
			elist.add(new E(ysort[i], ysort[i+1], ysort[i+1].y - ysort[i].y));
			elist.add(new E(zsort[i], zsort[i+1], zsort[i+1].z - zsort[i].z));
		}
		// 가중치 순서로 정렬
		elist.sort((a, b) -> a.w - b.w);
		
		int answer = 0;
		int cnt = 0;
		int idx = 0;
		while(cnt < N-1) {
			E e = elist.get(idx++);
			if(!union(e.a.idx, e.b.idx)) continue;
			answer += e.w;
			cnt++;
		}
		
		System.out.println(answer);
	}
	static int getMinDist(V a, V b) {
		int x = Math.abs(a.x-b.x);
		int y = Math.abs(a.y-b.y);
		int z = Math.abs(a.z-b.z);
		return Math.min(x, Math.min(y, z));
	}
	static int find(int a) {
		if(vers[a].root == a) return a;
		return vers[a].root = find(vers[a].root);
	}
	static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot == broot) return false;
		vers[broot].root = aroot;
		return true;
	}
}