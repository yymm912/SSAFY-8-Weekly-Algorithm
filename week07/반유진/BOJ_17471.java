// BOJ 17471번 게리맨더링

package BOJ.Mathematics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_17471 {

	static int N, min;
	static int[] population;
	static List<Integer> arrA, arrB;
	static List<Integer>[] list;

	static boolean[] select, visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		population = new int[N + 1];
		list = new List[N + 1];
		select = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= num; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 입력 끝
		min = Integer.MAX_VALUE;
		arrA = new ArrayList<>();
		arrB = new ArrayList<>();

		// 부분집합으로 구역을 나누기
		// 나눈 구역에서 서로 인접한지 check
		// 인접하다면 ? -> 두 선거구의 인구 수 구해서 차이가 최소인 것 답으로 출력
		
		subSet(1);
		
		if (min == Integer.MAX_VALUE) {
			// 두 선거구로 나눌 수 없는 경우 
			min = -1;
		}
		System.out.println(min);
	}

	static void subSet(int srcIdx) {
		if (srcIdx == N + 1) {

			arrA.clear();
			arrB.clear();

			int sumA = 0, sumB = 0;
			for (int i = 1; i <= N; i++) {
				if (select[i]) {// 구역 A
					arrA.add(i);
					sumA += population[i];
				} else { // 구역 B
					arrB.add(i);
					sumB += population[i];
				}
			}

			if (arrA.size() == 0 || arrB.size() == 0)
				return;

			// 나눈 구역이 서로 인접한지 check하는 함수 호출
			visited = new boolean[N + 1];
			bfs(arrA.get(0), 0);

			// 방문 안된 곳이 있다면(인접 X) return
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && arrA.contains(i))
					return;
			}

			bfs(arrB.get(0), 1);
			// 방문 안된 곳이 있다면(인접 X) return
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && arrB.contains(i))
					return;
			}

			// 여기까지 왔다면 나눠진 그룹들이 각각 모두 인접하다는 것
			// -> 두 선거구의 인구 수 차이 구하기
			min = Math.min(min, Math.abs(sumA - sumB));

			return;
		}

		select[srcIdx] = true;
		subSet(srcIdx + 1);

		select[srcIdx] = false;
		subSet(srcIdx + 1);
	}

	static void bfs(int v, int checkAB) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(v);
		visited[v] = true;

		while (!que.isEmpty()) {
			int x = que.poll();

			for (int i = 0; i < list[x].size(); i++) {
				int y = list[x].get(i);
				if (checkAB == 0) {	// A구역 체크할 때 호출한 경우 
					if (!visited[y] && select[y]) {
						que.offer(y);
						visited[y] = true;
					}
				} else {	// B구역 체크할 때 호출한 경우 
					if (!visited[y] && !select[y]) {
						que.offer(y);
						visited[y] = true;
					}
				}
			}
		}
	}
}
