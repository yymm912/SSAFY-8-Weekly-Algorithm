package week14;

import java.io.*;
import java.util.*;

public class BJ16235_나무재테크 {
	static int N, M, K;
	static int[][] map, A;
	static List<Integer>[][] trees;
	static Queue<Integer>[][] deadTrees;
	
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		A = new int[N+1][N+1];
		trees = new List[N+1][N+1];
		deadTrees = new ArrayDeque[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = 5; // 양분 5로 초기화
				A[i][j] = Integer.parseInt(st.nextToken()); // A 배열
				trees[i][j] = new ArrayList<>();
				deadTrees[i][j] = new ArrayDeque<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			trees[x][y].add(height);
		}

		// K년이 지난 후 살아남은 나무의 수 출력
		while (K > 0) {
			Spring();
			Summer();
			Autumn();
			Winter();
			
			K--;
			
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ans += trees[i][j].size();
			}
		}
		System.out.println(ans);

	}
	// 자신의 나이만큼 양분을 먹고 나이 +1, 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분 먹음
	// 양분이 부족한 나무는 죽음
	static void Spring() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 나이가 많은 순서대로 나무 정렬
				Collections.reverse(trees[i][j]);
				
				int size = trees[i][j].size();
				for (int h = size - 1; h >= 0; h--) { // 뒤에서부터 시작 -> out of index 에러 해결
					int height = trees[i][j].get(h);
					if (map[i][j] >= height) { // 나무의 나이만큼 양분이 존재하는 경우
						map[i][j] -= height;
						trees[i][j].remove(h);
						trees[i][j].add(h, height+1);
					} else { // 나무의 나이만큼 양분이 존재하지 않는 경우
						trees[i][j].remove(h); // 죽음
						deadTrees[i][j].add(height/2); // 나무 나이의 반만큼 저장	
					}
				}
				
			}
		}
	}
	// 죽은 나무가 양분으로 변함
	static void Summer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				while(!deadTrees[i][j].isEmpty()) {
					map[i][j] += deadTrees[i][j].poll(); // 양분 추가
				}
			}
		}
	}
	// 나무 번식
	static void Autumn() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int h = 0; h < trees[i][j].size(); h++) {
					if (trees[i][j].get(h)%5 == 0) { // 나무의 나이가 5의 배수일 경우
						// 인접한 8칸에 
						for (int d = 0; d < 8; d++) {
							int x = i;
							int y = j;
							int nx = x + dx[d];
							int ny = y + dy[d];
							if (nx >= 1 && ny >= 1 && nx <= N && ny <= N) {
								trees[nx][ny].add(1); // 나이가 1인 나무 생성
							}
						}
					}
				}
			}
		}
	}
	// 양분 A 추가
	static void Winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
}