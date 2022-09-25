package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_상어초등학교_21608 {
	
	static int N, ans;
	static int[] sdtNum;
	static int[][] like;
	
	// 교실
	static int[][] map;
	// 좋아하는 사람이 몇명 주변에 있는지를 확인하기 위한 배열
	static int[][] temp;
	
	// 들어갈 위치의 좌표를 저장해놓을 List
	static List<Node> list = new ArrayList<>();
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sdtNum = new int[N * N];
		map = new int[N][N];
		like = new int[N * N][4];
		
		StringTokenizer st = null;
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			sdtNum[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				like[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N * N; i++) {
			list.clear();
			temp = new int[N][N];
						
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸이 가장 많은 칸으로 자리를 정한다.
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (map[j][k] == like[i][0] || map[j][k] == like[i][1] || map[j][k] == like[i][2] || map[j][k] == like[i][3]) {
						for (int d = 0; d < 4; d++) {
							int nj = j + dx[d];
							int nk = k + dy[d];
							
							if (nj >= 0 && nj < N && nk >= 0 && nk < N && map[nj][nk] == 0) {
								temp[nj][nk]++;
							}
						}
					}
				}
			}
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (temp[j][k] > 0 && list.isEmpty()) list.add(new Node(j, k));
					else if (temp[j][k] > 0 && !list.isEmpty()) {
						if (temp[j][k] == temp[list.get(0).x][list.get(0).y]) list.add(new Node(j, k));
						else if (temp[j][k] > temp[list.get(0).x][list.get(0).y]) {
							list.clear();
							list.add(new Node(j, k));
						}
					}
				}
			}
			
			// list 안 원소의 갯수가 1이라면 그 좌표에 넣는다.
			if (list.size() == 1) {
				Node n = list.get(0);

				map[n.x][n.y] = sdtNum[i];
				continue;
			}
			
			// (1을 만족하는 칸이 여러 개 => list의 size가 1보다 큼) 2. 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
			if (list.size() > 1) {
				int ax = 0;
				int ay = 0;
				int max = -1;
				
				for (int j = 0; j < list.size(); j++) {
					int localMax = 0;
					for (int d = 0; d < 4; d++) {
						int nx = list.get(j).x + dx[d];
						int ny = list.get(j).y + dy[d];
						
						if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
							localMax++;
						}
					}
					
					if (localMax > max) {
						max = localMax;
						ax = list.get(j).x;
						ay = list.get(j).y;
					} else if (localMax == max) {
						// 행 번호가 작은 것 우선
						if (ax > list.get(j).x) {
							ax = list.get(j).x;
							ay = list.get(j).y;
							max = localMax;
						} else if (ax == list.get(j).x) {
							// 행 번호가 같다면 열번호 작은 것 우선
							if (ay > list.get(j).y) {
								ax = list.get(j).x;
								ay = list.get(j).y;
								max = localMax;
							} 
						} 
					}
				}
				
				map[ax][ay] = sdtNum[i];
			} else if (list.size() == 0) {
				// list의 size가 0임 2. 모든 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
				int ax = 0;
				int ay = 0;
				int max = -1;
				
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						if (map[j][k] == 0) {
							int localMax = 0;
							for (int d = 0; d < 4; d++) {
								int nx = j + dx[d];
								int ny = k + dy[d];
								if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0) {
									localMax++;
								}
							}
							
							if (localMax > max) {
								max = localMax;
								ax = j;
								ay = k;
							} else if (localMax == max) {
								// 행 번호가 작은 것 우선
								if (ax > j) {
									ax = j;
									ay = k;
									max = localMax;
								} else if (ax == j) {
									// 행 번호가 같다면 열번호 작은 것 우선
									if (ay > k) {
										ax = j;
										ay = k;
										max = localMax;
									} 
								} 
							}
						}
					}
				}
				map[ax][ay] = sdtNum[i];
			}
		}
		
		// 마무리 : 행복도 구하기
		for (int a = 0; a < N * N; a++) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == sdtNum[a]) {
						for (int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							
							if (nx >= 0 && nx < N && ny >= 0 && ny < N && (map[nx][ny] == like[a][0] || map[nx][ny] == like[a][1] || map[nx][ny] == like[a][2] || map[nx][ny] == like[a][3])) {
								cnt++;
							}
						}
						
						break;
					}
				}
			}
			
			if (cnt == 0) {
				// 만족도 0
				ans += 0;
			} else if (cnt == 1) {
				// 만족도 1
				ans += 1;
			} else if (cnt == 2) {
				// 만족도 2
				ans += 10;
			} else if (cnt == 3) {
				// 만족도 3
				ans += 100;
			} else {
				// 만족도 4
				ans += 1000;
			}
		}
		
		System.out.println(ans);
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

