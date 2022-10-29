package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_21608_상어초등학교 {
	
	static int N, many, like_many, r, c, answer;
	static List<Integer> list;
	static boolean[][] like;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		answer = 0;
		list = new ArrayList<>();
		like = new boolean [N*N+1][N*N+1];
		map = new int[N][N];
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			list.add(val);
			
			for(int j=0; j<4; j++) {
				like[val][Integer.parseInt(st.nextToken())] = true;
			}
			
		}
		
		
		for(int k=0; k<N*N; k++) {
			like_many = 0;
			many = 0;
			r = 401;
			c = 401;
			int val = list.get(k);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]>0)continue;
					positioning(i, j, val);
					
				}
			}
			map[r][c] = val; 
		}
		
		// 점수 계산
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				int val = map[i][j];
				int cnt = 0;
				
				for(int k=0; k<4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
					
					if( like[val][map[nr][nc]]) cnt++; 
					
				}
				
				if(cnt==0) continue;
				answer += Math.pow(10, cnt-1);
				
			}
		}
		
		System.out.println(answer);

	}
	
	static void positioning(int row, int col, int val) {
		int like_cnt = 0;
		int many_cnt = 0;
		
		for(int i=0; i<4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			
			if( like[val][map[nr][nc]]) like_cnt++;
			else if(map[nr][nc]==0) many_cnt++;
			
		}
		
		// 가장 최우선은 좋아하는 사람이 주변에 있어야함
		if(like_cnt>like_many) {
			r = row;
			c = col;
			like_many = like_cnt;
			many = many_cnt;
		}
		else if(like_cnt == like_many) { // 만약 같다면 
			if(many_cnt> many) { // 빈 칸이 많은 곳을 골라야함
				r = row;
				c = col;
				many = many_cnt;
			}
			else if(many_cnt==many) { // 만약 좋아하는 사람 수 빈칸도 같다면
				if(r>row) { // 행을 먼저 고려함
					r = row;
					c = col;
				}
			}
		}
		
	}
	
}