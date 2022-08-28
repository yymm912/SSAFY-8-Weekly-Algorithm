package _5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW5656_벽돌깨기 {
	static int T,N,W,H;
	static int [][] board;
	static int [][] board_og;
	static Queue<block> q;
	static Stack<Integer> stack;
	static boolean [] sel;
	static int [] order;
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			board = new int[H+1][W+1];
			board_og = new int[H+1][W+1];
			sel = new boolean[W+1];
			order = new int[N];
			q = new ArrayDeque<>();
			stack = new Stack<>();
			ans = Integer.MAX_VALUE;
			for(int i = 1;i<=H;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1;j<=W;j++) {
					board_og[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve(0,0);
			System.out.println("#"+t+" "+ans);
		}

	}
	
	static void solve(int idx,int m) {
		if(m == N) {
			for(int i = 1;i<=H;i++)
				for(int j = 1;j<=W;j++)
					board[i][j] = board_og[i][j];
			
			for(int i = 0;i < N;i++) {
				//벽돌 떨어뜨리기
				shoot(order[i]);
				
				//게임판 정리하기
				org();
			}
			int a = 0;
			for(int i = 1;i<=H;i++)
				for(int j = 1;j<=W;j++)
					if(board[i][j] != 0) a++;
			ans = Math.min(ans,a);
			return;
		}
		
		for(int i = 1;i<=W;i++) {
			order[idx] = i;
			solve(idx+1,m+1);
		}
		
	}
	
	static void shoot(int c) {
		q.clear();
		int r = 0;
		while(r <= H) {
			if(board[r][c] != 0) {
				q.add(new block(r,c,board[r][c]));
				board[r][c] = 0;
				break;
			}
			r++;
		}
		
		while(!q.isEmpty()) {
			block cur = q.poll();
			
			//상
			for(int i = 0;i<cur.range;i++) {
				if(cur.r - i <= 0) break;
				if(board[cur.r-i][cur.c] != 0) q.add(new block(cur.r - i,cur.c,board[cur.r-i][cur.c]));
				board[cur.r - i][cur.c] = 0;
			}
			//하
			for(int i = 0;i<cur.range;i++) {
				if(cur.r + i > H) break;
				if(board[cur.r+i][cur.c] != 0) q.add(new block(cur.r + i,cur.c,board[cur.r+i][cur.c]));
				board[cur.r + i][cur.c] = 0;
			}
			//좌
			for(int i = 0;i<cur.range;i++) {
				if(cur.c - i <= 0) break;
				if(board[cur.r][cur.c-i] != 0) q.add(new block(cur.r,cur.c - i,board[cur.r][cur.c-i]));
				board[cur.r][cur.c - i] = 0;
			}
			//우
			for(int i = 0;i<cur.range;i++) {
				if(cur.c + i > W) break;
				if(board[cur.r][cur.c+i] != 0) q.add(new block(cur.r,cur.c + i,board[cur.r][cur.c+i]));
				board[cur.r][cur.c + i] = 0;
			}
		}
		
	}
	
	static void org() {
		stack.clear();
		
		for(int j = 1; j <= W;j++) {
			//해당 열에 모든 블록들을 가져간다.
			for(int i = 1;i<=H;i++) {
				if(board[i][j] != 0) {
					stack.add(board[i][j]);
					board[i][j] = 0;
				}
			}
			//다시 쌓는다.
			for(int i = H;i>=1;i--) {
				if(stack.isEmpty()) break;
				board[i][j] = stack.pop();
			}
		}
	}
	
	static class block{
		int r, c, range;
		block(int r,int c,int range){
			this.r = r;
			this.c = c;
			this.range = range;
		}
	}

}
