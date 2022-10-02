package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_드래곤커브_15685 {
	
	static int N;
	static int x, y, d, g;
	
	static List<Node> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			int stage = 0;
			
			// 다음 과정을 통해 g세대를 그린다.
			
			
			while (stage++ < g) {
				for (int k = 0; k < list.size(); k++) {
					
				}
			}
		}
	}
	
	static class Node {
		int x;
		int y;
		char dir;
		
		public Node(int x, int y, char dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}
