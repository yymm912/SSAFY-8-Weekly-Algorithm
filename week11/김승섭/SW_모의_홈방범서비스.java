import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW_홈방범서비스 {
	
	static int T, N, M, max, total, curCnt;
	static int[][] map;
	static boolean[][] visited;
	static List<Home> list;
	static class Home{
		int y, x;
		public Home(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// map, 도시의 크기
			M = Integer.parseInt(st.nextToken());	// 하나의 집이 지불하는 비용 (1~10)
			max = 0;
			list = new ArrayList<>(); 				/* testcase가 존재할 때는 list를 꼭 static 변수 선언에 하지말고 초기화 해주자...*/
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if(Integer.parseInt(st.nextToken()) != 0) list.add(new Home(i, j));
				}	
			}
			total = list.size();
			find();

			System.out.println("#"+t+" "+max);
		}
	}
	
	static void find() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				curCnt = 0;								// 해당 좌표에서 취할 집의 수 count. 모든 좌표를 전수조사한다.
				countHome(i, j);
			}
		}
	}
	static void countHome(int y, int x) {
		int cur_area = 1;  								// 현재 구역의 규모
		for (int area = 0; area <= N; area++) {			// 모두 포함할 수 있는 최대 크기인 N까지 넓히며 살핀다
			cur_area += area * 4; // 1+(1+4)+(5+12).... // 규모에 따른 서비스 총 cost 1 => 5 => 17.... 순으로 커진다.
			
			for (int list_index = 0; list_index < list.size(); list_index++) // 집 리스트들을 순회하며 탐색한다.
			{
				if(Math.abs(list.get(list_index).y - y) 
						+ Math.abs(list.get(list_index).x - x) == area) curCnt++; // 집과 find()에서 선택된 좌표 거리가 현재 서비스의 거리와 같다면(내부로 들어왔다면)
			}																	  // 현재 좌표에서 서비스 이용가능한 집의 개수를 추가한다.
			if(curCnt > 0) {													  // 셀 수 있는 현재 집의 개수가 좌표 상 max값보다 크고, 
																				  //얻을 수 있는 이득이 크거나 같다면 max값을 갱신해준다.
				if(max < curCnt && curCnt * M - cur_area >= 0) max = curCnt;	
				if(max == total) return;										  // ** (가지치기) 갱신한 최대값이 집 모두를 포함한다면 해당 지역은 return **
			}
		}
	}

}

// BFS 로 풀이

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
 
/**
 * 운영 비용 K K = K * K + (K - 1) * (K - 1) 지점 간 거리 = 원좌표 - (K - 1)
 */
public class Solution {
    static int N, M, map[][], max, hcnt;
    static boolean visited[][];
    static Queue<Cell> queue = new ArrayDeque<>();
 
    static class Cell {
        int y, x;
 
        public Cell(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
 
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            hcnt = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1)
                        hcnt++;
                }
            }
            max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new boolean[N][N];
                    queue.clear();
                    bfs(i, j);
                }
            }
            System.out.println("#" + tc + " " + max);
        }
    }
 
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
 
    static void bfs(int y, int x) {
        visited[y][x] = true;
        queue.offer(new Cell(y, x)); // K = 1일 때
        int K = 1;
        int size, cnt = 0;
        Cell cur;
        while (!queue.isEmpty()) {
 
            size = queue.size();
            while (--size >= 0) {
                cur = queue.poll();
                if (map[cur.y][cur.x] == 1)
                    cnt++;
 
                for (int d = 0; d < 4; d++) {
                    int curX = cur.x + dx[d];
                    int curY = cur.y + dy[d];
 
                    if (curX < 0 || curX >= N || curY < 0 || curY >= N || visited[curY][curX]) continue;
 
                    queue.offer(new Cell(curY, curX));
                    visited[curY][curX] = true;
 
                }
            }
 
            if (M * cnt >= (K * K + (K - 1) * (K - 1)))
                max = Math.max(max, cnt);
            K++;
        } 
    }
 
 
}
