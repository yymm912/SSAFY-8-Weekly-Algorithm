import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	static void print() {
		for(int y=0;y<N;y++)
			System.out.println(Arrays.toString(mat[y]));
		System.out.println("==============");
	}
	
	static void go() {
		int cx,cy,nx,ny;
		que.add(new Item(0,0));
		mat[0][0]=2;
		if(orders.containsKey(0)) {
			dr=(dr+orders.get(0))%4;
		}
		
		while(true) {
			//System.out.println(que+" "+dr);
			//print();
			
			t++;
			Item ci=que.peekLast();
			cx=ci.x; cy=ci.y;
			nx=cx+dirs[dr][0];
			ny=cy+dirs[dr][1];
			
			if(nx<0 || ny<0 || nx>=N || ny>=N) return;
			if(mat[ny][nx]==2) return; //다음이 뱀이면
			
			if(mat[ny][nx]==0) { //사과 없으면
				Item pi=que.pollFirst();
				try {
				mat[pi.y][pi.x]=0;
				}catch(Exception e) {};
			}
			mat[ny][nx]=2;
			que.add(new Item(nx,ny));
			
			
			if(orders.containsKey(t)) { //방향전환
				dr=Math.floorMod(dr+orders.get(t),4);
			}
			
		}
	}
	
	static int N,K,L; //크기, 사과, 방향전환수
	static int[][] mat;
	static Map<Integer, Integer> orders;
	static int[][] dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	static Deque<Item> que;
	static int dr;
	static int t;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		N=Integer.parseInt(br.readLine());
		K=Integer.parseInt(br.readLine());
		mat=new int[N][N];
		
		for(int i=0; i<K;i++) {
			stn=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(stn.nextToken())-1;
			int x=Integer.parseInt(stn.nextToken())-1;
			
			mat[y][x]=1;
		}
		L=Integer.parseInt(br.readLine());
		orders=new HashMap<>();
		for(int i=0; i<L; i++) {
			stn=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(stn.nextToken());
			char c=stn.nextToken().charAt(0);
			if(c=='D') orders.put(n, 1);
			else orders.put(n, -1);
		}
		que= new ArrayDeque<>();
		dr=0;
		t=0;
		go();
		System.out.println(t);
		
		
	}
	static class Item{
		int x,y;
		public Item(int x, int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public boolean equals(Object obj) {
			Item o=(Item)obj;
			return (this.x==o.x) && (this.y==o.y);
		}
		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
	}
}
