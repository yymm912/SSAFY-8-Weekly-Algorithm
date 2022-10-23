package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_5650_2_핀볼게임 {
	
	static int N, answer;
	static int[][] map, wormHole;
	
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/swea_5650_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
			int T = Integer.parseInt(br.readLine());
			map = new int[102][102];
			wormHole = new int[21][2];
			
			for (int testCase = 1; testCase <= T; ++testCase) {
				init();
				
				N = Integer.parseInt(br.readLine());
				for (int i = 1; i <= N; ++i) {
					StringTokenizer st = new StringTokenizer(br.readLine());
					for (int j = 1; j <= N; ++j) {
						map[i][j] = Integer.parseInt(st.nextToken());
						
						if (map[i][j] >= 6) {
							if (wormHole[map[i][j] + 10][0] == -1) {
								wormHole[map[i][j] + 10][0] = i;
								wormHole[map[i][j] + 10][1] = j;
							} else {
								wormHole[map[i][j]][0] = i;
								wormHole[map[i][j]][1] = j;
								map[i][j] += 10;
							}
						}
					}
				}

				for (int i = 1; i <= N; ++i) {
					for (int j = 1; j <= N; ++j) {
						if (map[i][j] == 0) {
							for (int d = 0; d < 4; ++d) {
								cal(i, j, d);
							}
						}
					}
				}
				
				System.out.println("#" + testCase + " " + answer );
			}
	
	}

	public static void init() {
		answer = 0;
		for (int i = 0; i < 21; ++i) {
			wormHole[i][0] = -1;
		}
		for (int i = 0; i < map.length; ++i) {
			Arrays.fill(map[i], 0);
		}
	}
	
	public static void cal(int sy, int sx, int d) {
		int y = sy, x = sx, score = 0;
		
		while (true) {
			int ny = y + dir[d][0];
			int nx = x + dir[d][1];

			//벽을 만날 경우
			if (ny < 1 || nx < 1 || ny > N || nx > N) {
				d = (d + 2) % 4;
				score++;
				y = ny; x = nx;
				continue;
			}

			//블랙홀을 만나거나 시작점에 도달할 경우
			if (map[ny][nx] == -1 || (ny == sy && nx == sx)) {
				answer = Math.max(answer, score);
				break;
			}

			//웜홀을 만날 경우
			if (map[ny][nx] >= 6){
				y = wormHole[map[ny][nx]][0]; x = wormHole[map[ny][nx]][1];
				continue;
			}
			
			y = ny; x = nx;
			
			//빈 공간을 만날 경우
			if (map[y][x] == 0) continue;
			
			//블록을 만날 경우
			score++;
			if (map[y][x] == 1) {
				if (d == 0 || d == 1) {
					d = (d + 2) % 4;
				} else if (d == 2) {
					d = 1;
				} else if (d == 3) {
					d = 0;
				}
			} else if (map[y][x] == 2) {
				if (d == 1 || d == 2) {
					d = (d + 2) % 4;
				} else if (d == 0) {
					d = 1;
				} else if (d == 3) {
					d = 2;
				}
			} else if (map[y][x] == 3) {
				if (d == 2 || d == 3) {
					d = (d + 2) % 4;
				} else if (d == 0) {
					d = 3;
				} else if (d == 1) {
					d = 2;
				}
			} else if (map[y][x] == 4) {
				if (d == 0 || d == 3) {
					d = (d + 2) % 4;
				} else if (d == 1) {
					d = 0;
				} else if (d == 2) {
					d = 3;
				}
			} else if (map[y][x] == 5) {
				d = (d + 2) % 4;
			}
		}
	}
}