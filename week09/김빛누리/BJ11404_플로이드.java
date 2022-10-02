
import java.io.*;
import java.util.*;

/*
 * 풀이시간: 1시간+
 * 참고: 플로이드 와샬 코드:(
 * 
 * <풀이방식>
 * - 기본 플로이드 와샬
 * 
 * - 모든 도시의 쌍에 대해서 최소비용을 구하는 문제
 * - 처음 입력받을 때 버스 비용 최소로 입력받는다 (기존 값보다 더 작은 값 나올 경우 갱신).
 * - 입력에서 city 숫자는 1부터 시작하므로 하나씩 빼서 입력받기
 * 
 * - 모든 출발지와 도착지에 대해 모든 노드를 하나씩 거쳐가는 방법을 확인
 * - 최소비용 갱신한다.
 * 
 * */
public class BJ11404_플로이드 {
	
	static int n, m;
	
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		map = new int[n][n];

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken())-1;
			int city2 = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			if(map[city1][city2] == 0 || map[city1][city2] > cost)
				map[city1][city2] = cost;		
		}
		
//		print();
//		System.out.println("*********************");
		
		// 거쳐가는 노드 k
		for (int k = 0; k < n; k++) {
			
			// 출발 노드 i
			for (int i = 0; i < n; i++) {
				
				// 도착 노드 j
				for (int j = 0; j < n; j++) {
					
					// 출발지, 도착지 같은 경우 또는 아직 갈 수 없는 경우 (0)
					if( i==j || map[i][k] == 0 || map[k][j] == 0) continue;
					
					// 이전까지 갈 수 있는 경로 없었던 경우 (첫방문)
					if(map[i][j] == 0) {
						map[i][j] = map[i][k] + map[k][j];
						continue;
					}
					
					// 일반적인 경우
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					
				}
			}
		}

		print();

		
	}
	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}


}
