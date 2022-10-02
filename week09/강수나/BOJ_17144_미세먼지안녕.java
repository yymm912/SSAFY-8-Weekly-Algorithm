package a22_09_28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지안녕 {

	static int R, C, T;
	static int[][] arr = new int[50][50];
	static List<Integer> airCleanerRows = new ArrayList<> ();
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					airCleanerRows.add(i);
				}
			}
		}//입력끝
		
		solution();
	}
	
	static void solution() {
		while (T-- > 0) {
			//1. 먼지 확산
			arr = spreadDust();
			//2. 공기청정기 가동
			executeAirCleaner();
		}
		System.out.println(calculateSum());
	}
	
	static int [][] spreadDust() {
		int[][] temp = new int[50][50];
		for (int y = 0; y < R; y++) {
			for (int x = 0; x < C; x++) {
				if (arr[y][x] == -1) {
					temp[y][x] = -1;
					continue;
				}
				temp[y][x] += arr[y][x];
				
				for (int i = 0; i < 4; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					
					if (nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
					if (arr[ny][nx] == -1) continue;
					
					temp[ny][nx] += (arr[y][x] / 5);
					temp[y][x] -= (arr[y][x] / 5);
				}
			}
		}
		return temp;
	}
	
	static void executeAirCleaner() {
		int top = airCleanerRows.get(0);
		
		for (int y = top-1; y > 0; y--) {
			arr[y][0] = arr[y-1][0];
		}
		for (int x = 0; x < C-1; x++) {
			arr[0][x] = arr[0][x+1];
		}
		for (int y = 0; y < top; y++) {
			arr[y][C-1] = arr[y+1][C-1];
		}
		for (int y = C-1; y > 1; y--) {
			arr[top][y] = arr[top][y-1];
		}
		arr[top][1] = 0;
		
		int bottom = airCleanerRows.get(1);
		
		for (int y = bottom+1; y < R-1; y++) {
			arr[y][0] = arr[y+1][0];
		}
		for (int x = 0; x < C-1; x++) {
			arr[R-1][x] = arr[R-1][x+1];
		}
		for (int y = R-1; y > bottom; y--) {
			arr[y][C-1] = arr[y-1][C-1];
		}
		for (int x = C-1; x > 1; x--) {
			arr[bottom][x] = arr[bottom][x-1];
		}
		
		arr[bottom][1] = 0;
	}
	
	
	static int calculateSum() {
		int sum = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += arr[i][j];
			}
		}
		return sum;
	}
	
}
