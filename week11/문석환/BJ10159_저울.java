package BOJ.OTHER.저울_10159;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 제목 : 저울
// 번호 : 10159
// 난이도 : 골드 4
// https://www.acmicpc.net/problem/10159

/*
 * INFO
 * 무게가 서로다른 N개의 물건 (1~N까지 번호가 매겨져 있음)
 * 일부 물건 쌍 (a,b)에 대한 측정값 있음
 * 이 측정값으로 직접 측정하지 않은 물건 쌍의 비교 결과를 알아낼 수도 있고 못 할수도 있다.
 * (a,b) 이면 a가 b보다 무겁다 -> a > b
 */
public class Main {
	static int N,M,ans; // N : 물건의 개수 , M : 측정한 쌍의 개수
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N+1][N+1];

		for(int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 무거운 물건 -> 가벼운 물건
			// a > b 이면 [a][b] = 1 , [b][a] = -1
			map[a][b] = 1;
			map[b][a] = -1;
		}

		for(int i=1;i<=N;i++) {
			makeRelation(i);
		}

		for(int i=1;i<=N;i++) {
			ans = 0;
			for(int j=1;j<=N;j++) {
				if(i == j)continue;
				if(map[i][j] == 0 && map[j][i] == 0)ans++;
			}
			sb.append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void makeRelation(int v) {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(map[i][v] == map[v][j] && map[i][v] != 0) {
					// v점에서 들어오고 나가고 가 존재하고 그 값이 0이아닌 경우
					// 비교 가능
					map[i][j] = map[i][v];
				}
			}
		}
	}
}
