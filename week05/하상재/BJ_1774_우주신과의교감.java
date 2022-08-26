package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1774_우주신과의교감 {
	
	static int n,m;
	static int[] parent;
	static int [][] xy;
	static ArrayList<Edge> edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		xy = new int [n][2];
		
		int cnt = 0;
		double ans = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			xy[i][0] = Integer.parseInt(st.nextToken());
			xy[i][1] = Integer.parseInt(st.nextToken());
		}
		
		makeSet();
		edges = new ArrayList<>();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			union(x,y);
			cnt++;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(find(i)==find(j)) continue;
				edges.add(new Edge(i, j, Math.sqrt( Math.pow((xy[i][0]-xy[j][0]), 2)+Math.pow((xy[i][1]-xy[j][1]), 2))));
			}
		}
		
		Collections.sort(edges, (o1,o2)->{
			if(o1.weight < o2.weight) return -1;
			return 1;
		});
		
		for(int i=0; i<edges.size(); i++) {
			if(union(edges.get(i).n1, edges.get(i).n2)) {
				ans+=edges.get(i).weight;
				if(++cnt == n-1) break;
			}
		}
		
		System.out.printf("%.2f",ans);
	}
	
	static class Edge{
		int n1, n2;
		double weight;
		public Edge(int n1, int n2, double weight) {
			this.n1 = n1;
			this.n2 = n2;
			this.weight = weight;
		}
	}
	
	static void makeSet() {
		parent = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int x) {
		if(parent[x]==x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px==py) return false;
		
		parent[py] = px;
		
		return true;
	}
	
}