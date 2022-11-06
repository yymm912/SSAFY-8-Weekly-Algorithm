package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1976_여행가자 {
	
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		int tmp = 0;
		boolean check = true;
		
		makeSet();
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = 1;
			tmp = 0;
			while(st.hasMoreTokens()) {
				tmp = Integer.parseInt(st.nextToken());
				if(tmp==1) union(idx,i);
				idx++;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		tmp = Integer.parseInt(st.nextToken());
		
		while(st.hasMoreTokens() && check) {
			int tmp2 = Integer.parseInt(st.nextToken());
			if(find(tmp)!=find(tmp2)) check = false;
		}
		if(check) System.out.println("YES");
		else System.out.println("NO"); 
		
	}
	
	static void makeSet() {
		parent = new int[n+1];
		for(int i=1; i<=n; i++) {
			parent[i] = i;
		}
	}
	
	static int find(int x) {
		if(parent[x]==x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		parent[py] = px;
	}
	
}