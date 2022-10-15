package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA5656_벽돌깨기 {

	static int T, N, W, H, min;
	static int[][] map, cMap;
	static int[] tgt; 			// 중복순열 결과를 담을 배열, 구슬을 떨어뜨릴 N개의 가로인덱스
	static List<Brick> list;	// 맨 위의 벽돌 정보를 담을 리스트
	
	static class Brick {
		int x, y, n;
		
		public Brick(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			
			// 중복순열 구하기
			tgt = new int[N];
			getNOrder(0);
			
			System.out.println("#" + t + " " + min);
		}
	}

	static void getNOrder(int cnt) {
		if(cnt == N) {
			init();  // 원본 배열 복사
			start(); // 벽돌 깨기
			return;
		}
		
		for (int i = 0; i < W; i++) {
			tgt[cnt] = i;
			getNOrder(cnt+1);
		}
	}
	
	static void init() {
		cMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				cMap[i][j] = map[i][j];
			}
		}
	}
	
	// 한 경우에 대해 벽돌깨기
	static void start() {
		int cnt = 0;
		
		while(cnt < N) {
			// 맨위의 벽돌을 담는 list 만들기
			list = new ArrayList<>();
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H; j++) {
					if(cMap[j][i] != 0) {
						list.add(new Brick(j, i, cMap[j][i]));
						break;
					}
				}
			}
			
			if(tgt[cnt] >= list.size()) break;
			
			// 범위만큼 벽돌 깨기
			Queue<Brick> qu = new ArrayDeque<>();
			qu.offer(list.get(tgt[cnt]));
			
			while(!qu.isEmpty()) {
				Brick b = qu.poll();
				
				cMap[b.x][b.y] = 0;
				
				// 상
				for (int i = 1; i < b.n; i++) {
					int tx = b.x - i;
					if(tx < 0) break;
					if(cMap[tx][b.y] == 0) continue;
					else if(cMap[tx][b.y] > 1)
						qu.offer(new Brick(tx, b.y, cMap[tx][b.y]));
					cMap[tx][b.y] = 0;
				}
				
				// 하
				for (int i = 1; i < b.n; i++) {
					int tx = b.x + i;
					if(tx >= H) break;
					if(cMap[tx][b.y] == 0) continue;
					else if(cMap[tx][b.y] > 1)
						qu.offer(new Brick(tx, b.y, cMap[tx][b.y]));
					cMap[tx][b.y] = 0;
				}
				
				// 좌
				for (int i = 1; i < b.n; i++) {
					int ty = b.y - i;
					if(ty < 0) break;
					if(cMap[b.x][ty] == 0) continue;
					else if(cMap[b.x][ty] > 1)
						qu.offer(new Brick(b.x, ty, cMap[b.x][ty]));
					cMap[b.x][ty] = 0;
				}
				
				// 우
				for (int i = 1; i < b.n; i++) {
					int ty = b.y + i;
					if(ty >= W) break;
					if(cMap[b.x][ty] == 0) continue;
					else if(cMap[b.x][ty] > 1)
						qu.offer(new Brick(b.x, ty, cMap[b.x][ty]));
					cMap[b.x][ty] = 0;
				}
			}
			
			sorting();
			cnt++;
		}
		
		// 남은 벽돌 수 세기
		count();
	}
	
	static void sorting() {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if(cMap[j][i] != 0) {
					stack.add(cMap[j][i]);
					cMap[j][i] = 0;
				}
			}
			
			int idx = H-1;
			while(!stack.isEmpty()) {
				cMap[idx--][i] = stack.pop();
			}
		}
	}
	
	static void count() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(cMap[i][j] != 0) cnt++;
			}
		}
		
		min = Math.min(min, cnt);
	}
}
