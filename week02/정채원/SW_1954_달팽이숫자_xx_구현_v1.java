package hwalgo02_부울경_03반_정채원;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution{
	static int T;
	static int[][] arr;
	static int N;
	static int[] dx = {1, 0, -1, 0};//동, 남, 서, 북
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			int x = 0, y = 0;			
			int delta = 0;
			int maxNum = N*N;
			for(int num=1; num<=maxNum; num++) {
				// 숫자를 채움
				arr[y][x] = num;
				int nx = x + dx[delta];
				int ny = y + dy[delta];
				// 경계선이거나, 이미 갱신된 곳은 -> 방향을 바꿈.
				if(nx < 0 || nx > N-1 || ny < 0 || ny > N-1 || arr[ny][nx] > 0) {
					delta = (delta + 1) % 4;
					nx = x + dx[delta];
					ny = y + dy[delta];
				}
				// 앞으로 나감
				x = nx;
				y = ny;
			}
			System.out.println("#"+t);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {					
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	
	}		
}