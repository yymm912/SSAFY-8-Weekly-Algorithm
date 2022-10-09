import java.io.*;
import java.util.*;

class Main{
	static int N, L;
	static int[][] map, slope;
	static int roadCnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가로 먼저 서칭
		slope = new int[N][N];
		int sum=0;
		sum +=searchRow();
		sum +=searchCol();
		System.out.println(sum);
//
//		slope = new int[N][N];
//		int sum1=0;
//		sum1 +=searchCol();
//		sum1 +=searchRow();
//		System.out.println(sum1);
//		System.out.println(Math.max(sum, sum1));
	}
	/** 해당 길을 갈 수 없으면, 있던 오르막길 없애야함*/
	static int searchRow() {
		int cnt=0;
		for(int y=0; y<N; y++) {
			int[] tmpSlope = slope[y].clone();
			boolean enable = true;
			/**
			 * 경사로 안되는 경우
			 * 1. map을 벗어나는 경우
			 * 2. 층 차이가 1보다 큰 경우
			 * 3. 연속이 아닌 경우
			 * 4. 경사로가 겹치는 경우
			 * */
			for(int x=1; x<N; x++) {
				if(Math.abs(map[y][x-1] - map[y][x]) > 1) {enable=false; break;} // 층 차이가 1보다 큰 경우
				if(map[y][x-1] > map[y][x]) { // (1) j 이후에 slope이 세워지는 경우
					if(x+L-1 >= N) {enable=false; break;} // map 범위 넘는 경우
					for(int l=x; l<x+L; l++) {
						if(map[y][x] != map[y][l] || tmpSlope[l] == L) { // 연속이 아니거나  slope가 겹쳐지는 경우
							enable = false;
							break;
						}
						tmpSlope[l] = L;
					}
				}
				else if(map[y][x-1] < map[y][x]) { // (2) j-1 이전에 slope이 세워지는 경우
					if(x-L < 0) {enable=false;break;} // map 범위 넘는 경우
					for(int l=x-1; l>x-1-L; l--) {
						if(map[y][x-1] != map[y][l] || tmpSlope[l] == L) { // 연속이 아니거나 slope가 겹쳐지는 경우
							enable = false;
							break;
						}
						tmpSlope[l] = L;
					}	
				}
				else if(map[y][x-1] == map[y][x]) continue;
				if(!enable) break;
			}
			if(enable) {
//				slope[y] = tmpSlope; // 맞으면 경사로 적용
				cnt++;
//				System.out.println(y);
			}
//			System.out.println(Arrays.toString(slope[y]));
		}
		return cnt;
	}
	static int searchCol() {
		int cnt=0;
		for(int x=0; x<N; x++) {
			int[] tmpSlope = new int[N];
			for(int i=0; i<N; i++) {tmpSlope[i] = slope[i][x];}
			boolean enable = true;
			/**
			 * 경사로 안되는 경우
			 * 1. map을 벗어나는 경우
			 * 2. 층 차이가 1보다 큰 경우
			 * 3. 연속이 아닌 경우
			 * 4. 경사로가 겹치는 경우
			 * */
			for(int y=1; y<N; y++) {
				if(Math.abs(map[y-1][x] - map[y][x]) > 1) {enable=false; break;} // 층 차이가 1보다 큰 경우
				if(map[y-1][x] > map[y][x]) { // (1) j 이후에 slope이 세워지는 경우
					if(y+L-1 >= N) {enable=false; break;} // map 범위 넘는 경우
					for(int l=y; l<y+L; l++) {
						if(map[y][x] != map[l][x] || tmpSlope[l] == L) { // 연속이 아니거나  slope가 겹쳐지는 경우
							enable = false;
							break;
						}
						tmpSlope[l] = L;
					}
				}
				else if(map[y-1][x] < map[y][x]) { // (2) j-1 이전에 slope이 세워지는 경우
					if(y-L < 0) {enable=false;break;} // map 범위 넘는 경우
					for(int l=y-1; l>y-1-L; l--) {
						if(map[y-1][x] != map[l][x] || tmpSlope[l] == L) { // 연속이 아니거나 slope가 겹쳐지는 경우
							enable = false;
							break;
						}
						tmpSlope[l] = L;
					}	
				}
				else if(map[y-1][x] == map[y][x]) continue;
				if(!enable) break;
			}
			if(enable) {
//				for(int i=0; i<N; i++) slope[i][x] = tmpSlope[i];
				cnt++;
//				System.out.println(x);
			}
		}
//		for(int i=0; i<N; i++)
//			System.out.println(Arrays.toString(slope[i]));
		return cnt;
	}
}