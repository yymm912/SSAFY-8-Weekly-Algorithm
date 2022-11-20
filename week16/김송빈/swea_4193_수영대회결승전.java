package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_4193_수영대회결승전 {

	static int n;
	static int [][]map;
	static int sy,sx,ey,ex;
	static int []dy= {1,0,-1,0},dx= {0,1,0,-1};
	static int ans=-1;
	
	static class node{
		int y,x,time;

		public node(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}

		@Override
		public String toString() {
			return "node [y=" + y + ", x=" + x + ", time=" + time + "]";
		}
		
		
	}
	
	public static void main(String[] args)throws Exception {
		//System.setIn(new FileInputStream("./res/swea_4193_input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			ans=-1;
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			st=new StringTokenizer(br.readLine());
			sy=Integer.parseInt(st.nextToken());
			sx=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			ey=Integer.parseInt(st.nextToken());
			ex=Integer.parseInt(st.nextToken());
			
			
			bfs(sy,sx);
			System.out.println("#"+t+" "+ans);
		}
	}
	static void bfs(int y,int x) {
		PriorityQueue<node>pq=new PriorityQueue<>((p1,p2)->p1.time-p2.time);
		map[y][x]=-1;
		pq.add(new node(y,x,0));
		boolean success=false;
		
		while(!pq.isEmpty()) {
			
			node nd=pq.poll();
			if(nd.x==ex&&nd.y==ey) {
				ans=nd.time;
				break;
			}
			//System.out.println(nd.toString());
			for(int i=0;i<4;i++) {
				int nx=nd.x+dx[i];
				int ny=nd.y+dy[i];
				
				if(ny<0||nx<0|ny>=n||nx>=n||map[ny][nx]==-1||map[ny][nx]==1)continue;
				if(map[ny][nx]==2) {
					int a=0;
					int ntime=0;
					while(true) {
						if(3*a-1>=nd.time) {
							ntime=3*a-1+1;
							break;
						}
						else a++;
					}
					pq.add(new node(ny,nx,ntime));
					map[ny][nx]=-1;
				}else if(map[ny][nx]==0) {
					pq.add(new node(ny,nx,nd.time+1));
					map[ny][nx]=-1;
				}
			}
		}
		
	}
}
