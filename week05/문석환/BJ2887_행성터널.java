package BOJ.최소신장트리.행성터널_2887;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 번호 : 2887
// 난이도 : 플레 5
// 제목 : 행성터널
// https://www.acmicpc.net/problem/2887
// 3번의 for문 -> 1번의 for문 안에서 한꺼번에 처리 -> planets배열의 아이템을 가지는 3가지 리스트 xList,yList,zList 생성
// List에 생성한 간선을 담고 이를 비용을 기준으로 오름차순 정렬
// 시간 : 1412ms
// 이 방법이 가장 빨랐다.
public class Main3 {
	static int N;
	static Planet[] planets;
	static List<Edge> edgeList;
	static List<Planet> xList,yList,zList; // x좌표 기준 정렬, y좌표 기준 정렬, z좌표 기준 정렬
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		edgeList = new ArrayList<>();
		parent = new int[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			planets[i] = new Planet(i,x,y,z);
		} // 입력 완료

		// 현재 행성 배열을 복사
		xList = new ArrayList<>(Arrays.asList(planets));
		yList = new ArrayList<>(Arrays.asList(planets));
		zList = new ArrayList<>(Arrays.asList(planets));
		// 크루스칼 알고리즘

		Collections.sort(xList,(o1,o2)->o1.x - o2.x);
		Collections.sort(yList,(o1,o2)->o1.y - o2.y);
		Collections.sort(zList,(o1,o2)->o1.z - o2.z);

		for(int i=0;i<N-1;i++) {
			// 인접한 두 정점 뽑아냄 -> 비용 계산에 사용
			Planet p1 = xList.get(i);
			Planet p2 = xList.get(i+1);
			edgeList.add(new Edge(p1.no,p2.no,Math.abs(p1.x-p2.x)));
			p1 = yList.get(i);
			p2 = yList.get(i+1);
			edgeList.add(new Edge(p1.no,p2.no,Math.abs(p1.y-p2.y)));
			p1 = zList.get(i);
			p2 = zList.get(i+1);
			edgeList.add(new Edge(p1.no,p2.no,Math.abs(p1.z-p2.z)));
		}

		Collections.sort(edgeList,(o1,o2)->o1.c - o2.c);
		makeSet();
		int sum = 0;
		int cnt = 0;
		for(Edge e : edgeList) {
			if(cnt == N-1)break;
			if(findSet(e.v1) != findSet(e.v2)) {
				// 부모가 다름
				union(e.v1,e.v2);
				cnt++;
				sum+= e.c;
			}
		}

		System.out.println(sum);
	}

	static void makeSet() {
		for(int i=0;i<N;i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if(parent[x] == x)return x;
		else return parent[x] = findSet(parent[x]);
	}

	static void union(int x,int y) {
		int px = findSet(x);
		int py = findSet(y);

		if(px == py)return;
		parent[py] = px;
	}

	static class Edge{
		int v1,v2,c;
		public Edge(int v1,int v2,int c) {
			this.v1 =v1;
			this.v2 = v2;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", c=" + c + "]";
		}


	}

	static class Planet{
		int no,x,y,z;
		public Planet(int no,int x,int y,int z) {
			this.no = no;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
