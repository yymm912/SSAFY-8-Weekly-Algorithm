package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ24445알고리즘수업너비우선탐색 {

	static int N, M, R;
	static List<Integer>[] node;
	static int result[];
	static Deque<Integer> q = new ArrayDeque<>();
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("BJ24445.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		result = new int[N + 1]; // 결과 순서 저장
		isVisited = new boolean[N + 1]; // 방문한 노드 체크

		// 2차원 리스트 초기화 
		node = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			node[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			node[u].add(v); // 양방향 노드 저장 
			node[v].add(u);

		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(node[i], (a, b) -> b - a); // 내림차순 정렬
		}

		q.add(R); // 시작 정점 R부터
		isVisited[R] = true; // 시작 정점 방문 체크 
		result[R] = 1; // 결과를 출력할 배열 
		int idx = 1;
		
		while (!q.isEmpty()) {
			int top = q.peek();
			q.pop();
			
			for (int i = 0; i < node[top].size(); i++) {
				if (!isVisited[node[top].get(i)]) {
					
					q.add(node[top].get(i));
					isVisited[node[top].get(i)] = true;
					
					idx++; // 방문 순서 증가 
					result[node[top].get(i)] = idx; // top의 노드 인덱스에 방문 순서를 저장 
					
				}

			}

		}

		for(int i=1;i<=N;i++) System.out.println(result[i]);
	}

}