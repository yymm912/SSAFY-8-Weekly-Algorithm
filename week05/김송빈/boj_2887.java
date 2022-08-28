package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//Kruskal algo
public class boj_2887 {
	static int n,ans=0;
	static List<edge> list = new ArrayList<>();
	static int[] parent;
	static node[] nd;

	static class edge {
		int from, to, d;

		edge(int from, int to, int d) {
			this.from = from;
			this.to = to;
			this.d = d;
		}
	}

	static class node {
		int num;
		int x;
		int y;
		int z;

		node(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nd = new node[n];
		parent=new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			parent[i]=i;//makeset
			nd[i] = new node(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		//x로 봤을 때 가까운 곳
		Arrays.sort(nd, (e1, e2) -> e1.x - e2.x);
		for (int i = 0; i < n - 1; i++) {
			list.add(new edge(nd[i].num, nd[i + 1].num, Math.abs(nd[i].x - nd[i + 1].x)));
		}

		//y로 봤을 때 가까운 곳
		Arrays.sort(nd, (e1, e2) -> e1.y - e2.y);
		for (int i = 0; i < n - 1; i++) {
			list.add(new edge(nd[i].num, nd[i + 1].num, Math.abs(nd[i].y - nd[i + 1].y)));
		}
		
		
		//z로 봤을 때 가까운 곳
		Arrays.sort(nd, (e1, e2) -> e1.z - e2.z);
		for (int i = 0; i < n - 1; i++) {
			list.add(new edge(nd[i].num, nd[i + 1].num, Math.abs(nd[i].z - nd[i + 1].z)));
		}
		
		
		//x,y,z 상관 안쓰고 가까운 걸로 sort
		Collections.sort(list,(e1,e2)->e1.d-e2.d);
		
		int cnt=0;
		for(int i=0;i<list.size();i++) {
			edge eg=list.get(i);
			if(union(eg.from,eg.to)) {//연결돼있으면 안됨 안 들린 곳이면  됨
				cnt++;
				ans+=eg.d;
			}
			if(cnt==n-1)break;//다 들리면 나감
		}
		System.out.println(ans);
	}

	static boolean union(int a, int b) {
		int pa = findset(a);
		int pb = findset(b);

		if (pa == pb)
			return false;

		else if (pa > pb)
			parent[pa] = pb;
		else
			parent[pb] = pa;
		return true;
	}

	static int findset(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = findset(parent[x]);
	}
}
