package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2606_VIRUS {
	static int NETWORK[][];
	static boolean isVisited[];
	static int ans, PAIR, COM_NUM;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		COM_NUM = Integer.parseInt(br.readLine()); // 첫째줄에 주어진 컴퓨터의 수 (100 이하)
		PAIR = Integer.parseInt(br.readLine()); // 네트워크 상에 연결된 컴퓨터 쌍의 수

		NETWORK = new int[COM_NUM + 1][COM_NUM + 1]; // DFS 탐색으로 정점을 지날 네트워크 배열선언
		isVisited = new boolean[COM_NUM + 1]; // 방문 했는지 안했는지 여부 확인
		ans = 0;


		for (int i = 0; i < PAIR; i++) { // 직접 연결되어있는 컴퓨터의 쌍 선언
			StringTokenizer st = new StringTokenizer(br.readLine());
			int COM1 = Integer.parseInt(st.nextToken());
			int COM2 = Integer.parseInt(st.nextToken());

			NETWORK[COM1][COM2] = 1;
			NETWORK[COM2][COM1] = 1;
		}

		dfs(1);
		System.out.println(ans);

	}

	public static void dfs(int num) {

		isVisited[num] = true; // 해당 노드는 방문을 한 상태로 체크
		

		for (int i = 1; i <= COM_NUM; i++) { // 탐색을 실시하며 선언된 노드이고 방문하지 않은 곳을 찾으면 바이러스에 걸릴 컴퓨터로 체크
			if (NETWORK[num][i] == 1 && !isVisited[i]) { 
				ans++;
				dfs(i);
			}
		}
		return;
	}
}
