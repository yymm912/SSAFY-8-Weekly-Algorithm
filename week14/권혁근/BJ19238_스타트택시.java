import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] findMan(int sx,int sy) {
		if(mat[sy][sx]>0 && mat[sy][sx]!=500) {
            int n=mat[sy][sx];
			mat[sy][sx]=0;
			return new int[] {sx,sy,n,0};
		}
		boolean[][] visited=new boolean[N][N];
		Deque<Node> que = new ArrayDeque<>();
		que.add(new Node(sx,sy,0));
		visited[sy][sx]=true;
		int fx=N,fy=N,fd=50000,fn=0;
		while(!que.isEmpty()) {
			Node cur = que.pollFirst();
			int cx=cur.x, cy=cur.y, cd=cur.d;
			if(cd+1>fd || cd+1>fuel) break;
			for(int dr=0; dr<4; dr++) {
				int nx=cx+dirs[dr][0];
				int ny=cy+dirs[dr][1];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx]==true || mat[ny][nx]==500)
					continue;
				int n=mat[ny][nx];
				visited[ny][nx]=true;
				if(n>0) {
					//System.out.println(n+" "+(cd+1));
					if(cd+1<fd) {
						fx=nx;fy=ny;fd=cd+1; fn=n;
					}else if(cd+1==fd) {
						if(cy<fy) {
							fx=nx;fy=ny;fd=cd+1; fn=n;
						}else if(cy==fy) {
							if(cx< fx) {
								fx=nx;fy=ny;fd=cd+1; fn=n;
							}
						}
					}
				}else {
					que.add(new Node(nx,ny,cd+1));
				}
				
			}
		}
		if(fd==50000) {
			return new int[] {-1,-1,-1,-1};
		}else {
			fuel-=fd;
			mat[fy][fx]=0;
			//System.out.println("찾아갈 사람 "+fn);
			return new int[] {fx,fy,fn,fd};
		}	
	}
	
	static int[] findDest(int sx,int sy, int sn) {
		boolean[][] visited=new boolean[N][N];
		Deque<Node> que = new ArrayDeque<>();
		int[] end=infos.get(sn);
		int ex=end[0], ey=end[1];
		que.add(new Node(sx,sy,0));
		visited[sy][sx]=true;
		int fx=-1,fy=-1,fd=-1;
		while(!que.isEmpty()) {
			Node cur = que.pollFirst();
			int cx=cur.x, cy=cur.y, cd=cur.d;
			if(cd+1>fuel) {
				break;
			}
			for(int dr=0; dr<4; dr++) {
				int nx=cx+dirs[dr][0];
				int ny=cy+dirs[dr][1];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx]==true || mat[ny][nx]==500)
					continue;
				visited[ny][nx]=true;
				if(nx==ex && ny==ey) {
					fx=nx; fy=ny; fd=cd+1;
					break;
				}else {
					que.add(new Node(nx,ny,cd+1));
				}
				
			}
		}
		if(fd==-1) {
			return new int[] {-1,-1,-1};
		}else {
			fuel+=fd;
			return new int[] {fx,fy,fd};
		}	
	}
	
	static int[][] dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] mat;
	static int N,M, fuel;
	static List<int[]> infos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N=Integer.parseInt(stn.nextToken());
		M=Integer.parseInt(stn.nextToken());
		fuel=Integer.parseInt(stn.nextToken());
		mat=new int[N][N];
		for(int y=0; y<N; y++) {
			stn=new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				if(Integer.parseInt(stn.nextToken())==1) {
					mat[y][x]=500;
				}
			}
		}
		
		stn=new StringTokenizer(br.readLine());
		int sy=Integer.parseInt(stn.nextToken())-1;
		int sx=Integer.parseInt(stn.nextToken())-1;
		infos=new ArrayList<>();
		infos.add(new int[] {0,0});
		for(int i=1; i<=M; i++) {
			stn=new StringTokenizer(br.readLine());
			int y1=Integer.parseInt(stn.nextToken())-1;
			int x1=Integer.parseInt(stn.nextToken())-1;
			int y2=Integer.parseInt(stn.nextToken())-1;
			int x2=Integer.parseInt(stn.nextToken())-1;
			mat[y1][x1]=i;
			infos.add(new int[] {x2,y2});
			
					
		}
		int answer=0;
		//for(int y=0; y<N;y++)
		//	System.out.println(Arrays.toString(mat[y]));
		for(int i=0; i<M; i++) {
			int[] man=findMan(sx,sy);
			if(man[0]==-1) {
				answer=-1;
				break;
			}
			
			sx=man[0]; sy=man[1];
			int[]dest = findDest(sx, sy, man[2]);
			if(dest[0]==-1) {
				answer=-1;
				break;
			}
			
			sx=dest[0]; sy=dest[1];
		}
		if(answer==-1) {
			System.out.println(answer);
		}else {
			answer=fuel;
			System.out.println(answer);
		}
	}
	
	static class Node{
		int x, y, d;

		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}
}
