package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 
 * Solution () : 솔루션 함수
 * 
 * bfs (i,j) : i,j 위치에서 합쳐질 수 있는 영역이 자신을 포함하여 2개 이상인지 확인
 * 				이동할 수 없다면 false 를 return
 * 				이동할 수 있다면 이동할 수 있는 위치의 좌표와 모든 합을 저장한 정보를 calc함수에 넘기고 true 리턴
 * 
 * calc (List<Pos> union, int sum) : 
 * 
 */

public class 인구이동 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N,L,R;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
	}
	
	private static void solution () {
		
		boolean flag=true;
		int Time=0;
		
		while (true) {
			
			flag=false;
			visited=new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (!visited[i][j]) {
						boolean result=bfs (i,j);
						if (result) flag=true;
					}
				}
			}
			
			//print();
			
			if (!flag) break;
			Time++;
		}
		System.out.println(Time);
	}
	
	// Union이 생성 되었다면 true를, 아니면 false를 return
	private static boolean bfs (int i, int j) {
		
		visited[i][j]=true;
		
		Queue <Pos> q=new ArrayDeque<>();
		List <Pos> union=new ArrayList<>(); 
		
		q.add(new Pos (i,j));
		union.add(new Pos(i,j));
		
		
		int sum=map[i][j];
		
		while (!q.isEmpty()) {
			Pos cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (!isInRange (ny, nx) || visited[ny][nx]) continue;
				
				int diff=Math.abs(map[ny][nx]-map[y][x]);
				if (diff >=L && diff<=R) {
					visited[ny][nx]=true;
					q.add(new Pos (ny,nx));
					union.add(new Pos (ny,nx));
					sum+=map[ny][nx];
				}
			}
		}
		
		if (union.size()==1) return false;
		calc (union, sum); return true;
	}
	
	private static void calc (List<Pos> union, int sum) {
		double avg=(double)sum/(double)union.size();
		
		for (int i=0; i<union.size(); i++) {
			Pos p=union.get(i);
			map[p.y][p.x]=(int)avg;
		}
	}
	
	private static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}
	
	static class Pos {
		int y,x;
		Pos (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
	
	private static void print () {
		System.out.println("==========================================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.printf("%4d", map[i][j]);
			}
			System.out.println();
		}
	}
}
