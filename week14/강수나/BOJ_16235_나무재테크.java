package a22_11_06;
import java.io.*;
import java.util.*;

public class BOJ_16235_나무재테크 {
	
	public static int N, M, K;
	public static int[][] A, nut;
	
	static int[] dy = {-1,-1,-1, 0, 0,  1, 1, 1};
	static int[] dx = {-1, 0, 1, -1,1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N + 1][N + 1];
		nut = new int[N + 1][N + 1];
		PriorityQueue<Tree> pq = new PriorityQueue<Tree>();
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
				nut[i][j] = 5;
			}
		}
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			pq.offer(new Tree(y, x, age));
		}
		
		for (int year = 0; year < K; ++year) {
			Queue<Tree> q = new ArrayDeque<Tree>();
			Queue<Tree> die = new ArrayDeque<Tree>();
			Queue<Tree> spread = new ArrayDeque<Tree>();
			
			// 봄 - 양분먹음
			while (!pq.isEmpty()) {
				Tree cur = pq.poll();
				
				if (nut[cur.y][cur.x] < cur.age) {
					die.add(new Tree(cur.y, cur.x, cur.age));
				}
				else {
					nut[cur.y][cur.x] -= cur.age;
					q.offer(new Tree(cur.y, cur.x, cur.age + 1));
					
					if ((cur.age + 1) % 5 == 0) {
						spread.add(new Tree(cur.y, cur.x, cur.age + 1));
					}
				}
			}
			
			// 여름 - 나무죽고 양분
			while (!die.isEmpty()) {
				Tree cur = die.poll();
				nut[cur.y][cur.x] += (cur.age/2);
			}
			
			// 가을 - 나무추가
			while (!spread.isEmpty()) {
				Tree cur = spread.poll();
				
				for (int i = 0; i < 8; ++i) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if (ny <= 0 || nx <= 0 || ny > N || nx > N) {
						continue;
					}
					q.offer(new Tree(ny, nx, 1));
				}
			}
			
			// 겨울 - 양분추가
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					nut[i][j] += A[i][j];
				}
			}
			
			// 살아남은 나무 pq에 넣어줌
			while (!q.isEmpty()) {
				Tree cur = q.poll();
				pq.add(new Tree( cur.y, cur.x, cur.age));
			}
		}
		
		System.out.println(pq.size());
	}

	public static class Tree implements Comparable<Tree> {
		int x, y, age;

		public Tree(int y, int x, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
}