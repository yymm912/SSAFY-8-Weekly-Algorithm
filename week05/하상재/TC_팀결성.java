package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_팀결성 {
	
	static int n, m;
	static int[] size, set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		make();
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			
			if(command==0) {
				union(tmp1,tmp2);
			}
			else {
				if(find(tmp1)==find(tmp2)) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void make() {    // 크기가 1인 서로소 집합 생성
		set = new int[n+1];
        for(int i = 0; i < n+1; i++) {    // 모든 노드가 자신을 부모로 하는(대표자)
        	set[i] = i;
        }
    }
	
	static int find(int a) {
        if(set[a] == a) return a;
        
        return set[a] = find(set[a]);    // 우리의 대표자를 나의 부모로
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        
        set[bRoot] = aRoot;
        return true;
    }
	
}