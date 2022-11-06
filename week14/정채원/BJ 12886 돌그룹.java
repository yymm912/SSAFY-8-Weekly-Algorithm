import java.io.*;
import java.util.*;

class Main{
	static int A, B, C;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		boolean result = bfs();
		if(result) System.out.println(1);
		else System.out.println(0);
	}
	static boolean bfs() {
		Map<String, Boolean> visit = new HashMap<>();
		Queue<int[]> q = new ArrayDeque<>();
		int[] s = new int[] {A, B, C};
		Arrays.sort(s);
		q.add(s);
		
		visit.put(Arrays.toString(s), true);
		while(!q.isEmpty()) {
			int[] cur = q.remove();
			if(cur[0] == cur[1] && cur[1] == cur[2]) {
				return true;
			}
			int a = cur[0], b = cur[1], c = cur[2];
			if(b-a > 0) {
				int[] r1 = new int[] {a+a, b-a, c};
				Arrays.sort(r1);
				if(visit.get(Arrays.toString(r1)) == null) {
					visit.put(Arrays.toString(r1), true);
					q.add(r1);
				}
			}
			if(c-a > 0) {
				int[] r2 = new int[] {a+a, b, c-a};
				Arrays.sort(r2);
				if(visit.get(Arrays.toString(r2)) == null) {
					visit.put(Arrays.toString(r2), true);
					q.add(r2);
				}
			}
			if(c-b > 0) {
				int[] r3 = new int[] {a, b+b, c-b};
				Arrays.sort(r3);
				if(visit.get(Arrays.toString(r3)) == null) {
					visit.put(Arrays.toString(r3), true);
					q.add(r3);
				}
			}
		}
		return false;
	}
}