package algorithm.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sudoku {
	
	static List<Pos> target;
	
	// 가로, 세모, 네모 숫자현황 저장
	static boolean[][] row = new boolean[9][10];
	static boolean[][] col = new boolean[9][10];
	static boolean[][] sqr = new boolean[9][10];
	
	// 스도쿠 맵
	static int[][] sudoku;
	
	public static class Pos{
		int y;
		int x;
		
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		// 찾아야하는 위치 저장
		target = new ArrayList<>();
		
		// 스도쿠 맵 받기
		sudoku = new int[9][9];
		for(int y = 0; y < 9; y++){
			stk = new StringTokenizer(br.readLine());
			for(int x = 0; x < 9; x++) {
				sudoku[y][x] = Integer.parseInt(stk.nextToken());
				
				if(sudoku[y][x] == 0) {
					// 번호가 입력되지 않은 위치 저장
					target.add(new Pos(y,x));
					continue;
				}
				
				// 가로, 세로, 네모 숫자현황 갱신
				row[y][sudoku[y][x]] = true;
				col[x][sudoku[y][x]] = true;
				sqr[y/3*3 + x/3][sudoku[y][x]] = true;
			}
		}
		
		
		fill(0, target.size());
		
		// 출력
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				System.out.print(sudoku[y][x] + " ");
			}
			System.out.println();
		}
	}
	
	// true = 이상없이 성공
	// false = 문제발생
	public static boolean fill(int idx, int size) {
		// 끝까지 탐색했으면 성공이라고 리턴
		if(idx == size) return true;
		
		Pos item = target.get(idx);
		for(int num = 1; num <= 9; num++) {
			boolean r = row[item.y][num];
			boolean c = col[item.x][num];
			boolean s = sqr[item.y/3*3 + item.x/3][num];
			
			// 가로, 세로, 네모 모든 곳에 없는 숫자일 경우 갱신
			if(!r && !c && !s) {
				row[item.y][num] = col[item.x][num] = sqr[item.y/3*3 + item.x/3][num] = true;
				sudoku[item.y][item.x] = num;
				
				// 성공했으면 리턴
				if(fill(idx+1, size)) return true;
				
				// 다시 복구
				row[item.y][num] = col[item.x][num] = sqr[item.y/3*3 + item.x/3][num] = false;
				sudoku[item.y][item.x] = 0;
			}
		}
		
		return false;
	}
}
