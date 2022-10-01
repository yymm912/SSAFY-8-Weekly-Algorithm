package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 치킨집과 집의 좌표를 list에 각각 담는다.
// subset을 통해 선택된 치킨집의 갯수가 M개가 넘으면 탐색을 종료하고 도시의 치킨거리를 구한다.
// 각 집마다 모든 치킨집과의 거리 계산을 통해 얻은 최소 값들을 합한 것이 도시의 치킨거리이다.
// 구한 도시의 치킨거리가 최소값인지 확인한다.

public class BJ15686_치킨거리 {

	static int N, M, C, min;
	static List<Node> chickens = new ArrayList<>();
	static List<Node> houses = new ArrayList<>();
	static List<Node> select = new ArrayList<>();
	
	static class Node {
		int x, y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 1) houses.add(new Node(i, j));
				else if(tmp == 2) chickens.add(new Node(i, j));
			}
		}
		
		C = chickens.size();
		min = Integer.MAX_VALUE;
		
		getCityChicken(0, 0);
		
		System.out.println(min);
	}
	
	static void getCityChicken(int idx, int cnt) {
		if(cnt == M) {
			// 도시의 치킨거리 계산
			int sum = 0;
			for (Node h : houses) {
				int minD = Integer.MAX_VALUE;
				for (Node c : select) {
					int dist = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
					minD = Math.min(minD, dist);
				}
				sum += minD;
			}
			
			min = Math.min(min, sum);
			return;
		}
		
		// 치킨집 선택
		for(int i=idx; i<C; i++) {
			select.add(chickens.get(i));
			getCityChicken(i+1, cnt+1);
			select.remove(chickens.get(i));
		}
	}
}
