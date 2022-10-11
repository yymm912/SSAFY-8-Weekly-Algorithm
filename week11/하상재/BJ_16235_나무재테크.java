package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16235_나무재테크 {

	static int N, M, K;
	static int[][] arr;
	static int[][] map;
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	static List<Tree> list;
	static Queue<Tree> deadQue;
	static Queue<Tree> aliveQue;
	static PriorityQueue<Tree> tmpQue;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken()); 
		
		map = new int[N+1][N+1];
		arr = new int[N+1][N+1];
		list = new ArrayList<>();
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = 5;
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Tree(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()) ));
		}
		
		process();
		System.out.println(list.size());
	}
	
	static void process() {
		
		for(int i=0; i<K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
	}
	
	static void spring() {
		
		deadQue = new ArrayDeque<Tree>();
		aliveQue = new ArrayDeque<Tree>();
		
		for(int i=0; i<list.size(); i++) {
			int r = list.get(i).row;
			int c = list.get(i).col;
			
			if(map[r][c]-list.get(i).age >=0 ) {
				map[r][c] -= list.get(i).age;
				list.get(i).age++;
				aliveQue.add(list.get(i));
			}
			else {
				deadQue.add(list.get(i));
			}
			
		}
		
		list.clear();
		
		
	}
	
	static void summer() {
		
		while(!deadQue.isEmpty()) {
			Tree tree = deadQue.poll();
			map[tree.row][tree.col] += tree.age/2;
		}
		
	}
	
	static void fall() {
		tmpQue =  new PriorityQueue<Tree>((e1,e2)-> e1.age-e2.age);
		
		while(!aliveQue.isEmpty()) {
			Tree tree = aliveQue.poll();
			
			if(tree.age%5==0) {
				born(tree.row, tree.col);
			}
			
			tmpQue.add(tree);
		}
		
		while(!tmpQue.isEmpty()) {
			list.add(tmpQue.poll());
		}
	}
	
	static void winter() {
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] += arr[i][j];
			}
		}
		
	}
	
	static void born(int row, int col) {
		
		for(int i=0; i<8; i++) {
			int nr = row+dr[i];
			int nc = col+dc[i];
			if(nr<1 || nc<1|| nr > N || nc >N) continue;
			tmpQue.add(new Tree(nr,nc,1));
		}
		
	}
	
	static class Tree{
		int row, col, age;
		public Tree(int row, int col, int age) {
			this.row = row;
			this.col = col;
			this.age = age;
		}
	}
}