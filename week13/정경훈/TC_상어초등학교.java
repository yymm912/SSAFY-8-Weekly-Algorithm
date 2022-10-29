package baekjoon.상어초등학교_21608;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, ans;
	static Student[][] map;
	static Student[] students;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = 0;
		
		map = new Student[N][N];
		students = new Student[(N*N)];
		
		for (int i = 0; i < N*N; i++) {
			students[i] = new Student(br.readLine());
 		}
		
		assign();
		
		invest();
		
		System.out.println(ans);
		
	}
	
	static void invest() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
					for (int k : map[i][j].fav) {
						if(map[ny][nx].n == k) {
							cnt++;
							break;
						}
					}
					
				}
				ans += Math.pow(10, cnt-1);
			}
		}
	}
	
	static void assign() {
//		비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
//		1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
//		2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
		
		for (int i = 0; i < N*N; i++) {
			int[] fav = students[i].fav;
			int maxCnt = 0; // 좋아하는 학생 수 맥스
			int maxBlank = 0; // 빈칸 수 맥스
			int sy = N;
			int sx = N;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(map[j][k] != null) continue;
					// 좋아하는 학생 수
					int cnt = 0;
					// 빈칸 수
					int blank = 0;
					for (int d = 0; d < 4; d++) {
						int ny = j + dy[d];
						int nx = k + dx[d];
						if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
						if(map[ny][nx] == null) {
							blank++;
							continue;
						}
						for (int l : fav) {
							if(map[ny][nx].n == l) {
								cnt++;
								break;
							}
						}
					}
					if(cnt > maxCnt) {
						maxCnt = cnt;
						maxBlank = blank;
						sy = j;
						sx = k;
					}
					else if(cnt == maxCnt) {
						if(blank > maxBlank) {
							maxBlank = blank;
							sy = j;
							sx = k;
						}// 이후 빈칸 개수가 같으면 어짜피 탐색 순서가 위에서 왼쪽아래로 가는 순서라 조건에 맞게 된다. 하지만 조건따지긴해야겟넹,,..
						else if(blank == maxBlank) {
							if(j < sy) {
								sy = j;
								sx = k;
							}else if( j == sy) {
								if(k < sx) {
									sy = j;
									sx = k;
								}
							}
						}
					}
				}
			}
			map[sy][sx] = students[i];
		}
	}
	
	static class Student{
		int n;
		int[] fav = new int[4];
		Student(String students) {
			StringTokenizer st = new StringTokenizer(students);
			this.n = Integer.parseInt(st.nextToken());
			for (int i = 0; i < 4; i++) {
				fav[i] = Integer.parseInt(st.nextToken());
			}
			
		}
	}
}
