/*
    풀이 참고 : https://loosie.tistory.com/586
    크루스칼로 풀이 (Union Find + 우선순위 큐)
    1. 간선에 대한 비용이 적은 순으로 싸이클이 형성되기 전 까지 탐색
    2. 루트 노드가 일치하지 않으면 비용을 더함
*/
package boj;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, total;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		List<int[]> arr = new ArrayList<>();
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st =  new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			arr.add(new int[] {i, x, y, z});
		}
		
		Queue<Node> q = new PriorityQueue<>();
		for(int idx=1; idx<=3; idx++) {
			int v = idx;
			Collections.sort(arr, (o1,o2) -> (o1[v] - o2[v]));
			for(int i=1; i<N; i++) {
				int[] p1 = arr.get(i-1);
				int[] p2 = arr.get(i);
				int dis = Math.abs(p1[idx]-p2[idx]);
			
				q.add(new Node(p1[0], p2[0], dis));
			}
		}
		
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int rx = find(node.to);
			int ry = find(node.from);
			
			if(rx != ry) {
				total += node.value;
				union(node.to, node.from);
			}
		}
		System.out.println(total);
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			int tmp = x;
			x = y;
			y = tmp;
		}
		parent[y] = x;
	}
	
	static class Node implements Comparable<Node>{
		int to;
		int from;
		int value;
		
		public Node(int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}

		// pq에 넣기 위한 compare 함수 오버라이드
		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}
}