package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_감시_15683 {
	
	static int N, M, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] checked;
	// cctv의 방향을 저장할 list
	static List<CCTV> list = new ArrayList<>();
	
	// cctv의 위치를 저장한 list
	static List<Node> camera = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// cctv의 위치를 저장한다.
				if (map[i][j] > 0 && map[i][j] < 6) camera.add(new Node(i, j));
			}
		}
		
		// dfs를 사용하여 모든 경우를 탐색한다.
		dfs(0);
		
		System.out.println(ans);
	}
	
	static void dfs(int index) {		
		if (index == camera.size()) {
			// complete code
			// list를 바탕으로 checked를 그린다.
			checked = new boolean[N][M];
			for (int i = 0; i < list.size(); i++) {
				CCTV c = list.get(i);
				
				int cx = c.x;
				int cy = c.y;
				
				// 1번 cctv
				if (c.index == 1) {
					// 상 : 0, 우 : 1, 하 : 2, 좌 : 3
					if (c.dir == 0) {
						while (true) {
							if (cx < 0) break;
							
							if (map[cx][cy] == 6) {
								checked[cx][cy] = true;
								break;
							}
							
							checked[cx][cy] = true;
							cx--;
						}
					} else if (c.dir == 1){
						while (true) {
							if (cy >= M) break;
							
							if (map[cx][cy] == 6) {
								checked[cx][cy] = true;
								break;
							}
							
							checked[cx][cy] = true;
							cy++;
						}
					} else if (c.dir == 2) {
						while (true) {
							if (cx >= N) break;
							
							if (map[cx][cy] == 6) {
								checked[cx][cy] = true;
								break;
							}
							
							checked[cx][cy] = true;
							cx++;
						}
					} else {
						while (true) {
							if (cy < 0) break;
							
							if (map[cx][cy] == 6) {
								checked[cx][cy] = true;
								break;
							}
							
							checked[cx][cy] = true;
							cy--;
						}
					}
				} else if (c.index == 2) {
					// 2번 cctv
					// 가로 : 0, 세로 : 1
					
					int ccx = cx;
					int ccy = cy;
					
					if (c.dir == 0) {
						// 우측
						while (true) {
							if (ccy >= M) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy++;
						}
						ccy = cy;
						// 좌측
						while (true) {
							if (ccy < 0) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy--;
						}
					} else {
						// 상
						while (true) {
							if (ccx < 0) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx--;
						}
						ccx = cx;
						// 하
						while (true) {
							if (ccx >= N) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx++;
						}
					}
				} else if (c.index == 3) {
					// 3번 cctv
					// 상우 : 0, 우하 : 1, 하좌 : 2, 좌상 : 3
					
					int ccx = cx;
					int ccy = cy;
										
					if (c.dir == 0) {
						// 상
						while (true) {
							if (ccx < 0) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx--;
						}
						
						ccx = cx;
						
						// 우
						while (true) {
							if (ccy >= M) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy++;
						}
					} else if (c.dir == 1) {
						// 우
						while (true) {
							if (ccy >= M) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy++;
						}
						ccy = cy;
						// 하
						while (true) {
							if (ccx >= N) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx++;
						}
					} else if (c.dir == 2) {
						// 하
						while (true) {
							if (ccx >= N) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx++;
						}
						
						ccx = cx;
						// 좌
						while (true) {
							if (ccy < 0) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy--;
						}
					} else {
						// 좌
						while (true) {
							if (ccy < 0) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy--;
						}
						ccy = cy;
						// 상
						while (true) {
							if (ccx < 0) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx--;
						}
					}
				} else if (c.index == 4) {
					// 4번 cctv
					// 좌상우 : 0, 상우하 : 1, 우하좌 : 2, 하좌상 : 3
					
					int ccx = cx;
					int ccy = cy;
					
					if (c.dir == 0) {
						// 좌
						while (true) {
							if (ccy < 0) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy--;
						}
						ccy = cy;
						// 상
						while (true) {
							if (ccx < 0) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx--;
						}
						ccx = cx;
						// 우
						while (true) {
							if (ccy >= M) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy++;
						}
					} else if (c.dir == 1) {
						// 상
						while (true) {
							if (ccx < 0) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx--;
						}
						ccx = cx;
						
						// 우
						while (true) {
							if (ccy >= M) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy++;
						}
						
						ccy = cy;
						
						// 하
						while (true) {
							if (ccx >= N) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx++;
						}
					} else if (c.dir == 2) {
						// 우
						while (true) {
							if (ccy >= M) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy++;
						}
						ccy = cy;
						// 하
						while (true) {
							if (ccx >= N) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx++;
						}
						
						ccx = cx;
						
						// 좌
						while (true) {
							if (ccy < 0) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy--;
						}
					} else {
						// 하
						while (true) {
							if (ccx >= N) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx++;
						}
						ccx = cx;
						// 좌
						while (true) {
							if (ccy < 0) break;
							
							if (map[cx][ccy] == 6) {
								checked[cx][ccy] = true;
								break;
							}
							
							checked[cx][ccy] = true;
							ccy--;
						}
						
						ccy = cy;
						
						// 상
						while (true) {
							if (ccx < 0) break;
							
							if (map[ccx][cy] == 6) {
								checked[ccx][cy] = true;
								break;
							}
							
							checked[ccx][cy] = true;
							ccx--;
						}
					}
				} else {
					// 5번 cctv
					// 사방 : 0
					
					int ccx = cx;
					int ccy = cy;
					
					// 상
					while (true) {
						if (ccx < 0) break;
						
						if (map[ccx][cy] == 6) {
							checked[ccx][cy] = true;
							break;
						}
						
						checked[ccx][cy] = true;
						ccx--;
					}
					ccx = cx;
					// 우
					while (true) {
						if (ccy >= M) break;
						
						if (map[cx][ccy] == 6) {
							checked[cx][ccy] = true;
							break;
						}
						
						checked[cx][ccy] = true;
						ccy++;
					}
					ccy = cy;
					// 하
					while (true) {
						if (ccx >= N) break;
						
						if (map[ccx][cy] == 6) {
							checked[ccx][cy] = true;
							break;
						}
						
						checked[ccx][cy] = true;
						ccx++;
					}
					ccx = cx;
					// 좌
					while (true) {
						if (ccy < 0) break;
						
						if (map[cx][ccy] == 6) {
							checked[cx][ccy] = true;
							break;
						}
						
						checked[cx][ccy] = true;
						ccy--;
					}
				}
			}
			
			// 사각 지대의 크기를 구한다.
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 6) continue;
					if (!checked[i][j]) sum++;
				}
			}
			// 구한 사각지대의 크기와 ans의 값중 작은 값을 ans에 넣는다.
			ans = Math.min(ans, sum);
			return;
		}
		
		if (map[camera.get(index).x][camera.get(index).y] == 1) {
		// 1. 카메라 번호가 1인 경우(4 방향을 탐색)
			// 위 : 0, 우 : 1, 하 : 2, 좌 : 3
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 0));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 1));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 2));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 3));
			dfs(index + 1);
			list.remove(list.size() - 1);
		} else if (map[camera.get(index).x][camera.get(index).y] == 2) {
		// 2. 카메라 번호가 2인 경우(2 방향을 탐색)
			// 가로 : 0, 세로 : 1
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 0));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 1));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
		} else if (map[camera.get(index).x][camera.get(index).y] == 3) {
		// 3. 카메라 번호가 3인 경우(4 방향을 탐색)
			// 상우 : 0, 우하 : 1, 하좌 : 2, 좌상 : 3
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 0));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 1));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 2));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 3));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
		} else if (map[camera.get(index).x][camera.get(index).y] == 4) {
		// 4. 카메라 번호가 4인 경우(4 방향을 탐색)
			// 좌상우 : 0, 상우하 : 1, 우하좌 : 2, 하좌상 : 3
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 0));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 1));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 2));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 3));
			dfs(index + 1);
			list.remove(list.size() - 1);
			
		} else {
		// 5. 카메라 번호가 5인 경우(1 방향을 탐색)
			// 사방 : 0
			list.add(new CCTV(camera.get(index).x, camera.get(index).y, map[camera.get(index).x][camera.get(index).y], 0));
			dfs(index + 1);
			list.remove(list.size() - 1);
		}
	}
	
	static class CCTV {
		int x;
		int y;
		
		int index;
		int dir;
		
		public CCTV(int x, int y, int index, int dir) {
			this.x = x;
			this.y = y;
			this.index = index;
			this.dir = dir;
		}
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
