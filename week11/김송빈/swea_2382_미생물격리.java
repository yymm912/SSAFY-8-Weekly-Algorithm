package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_2382_미생물격리 {
	static int n,m,k;
	static int []dy= {0,-1,1,0,0},dx= {0,0,0,-1,1};
	static mnode [][]map;
	static Queue<node>q;
	
	static class node{
		int y,x;
		long num;
		int d;
		public node(int y,int x,long num, int d) {	
			this.y=y;
			this.x=x;
			this.num = num;
			this.d = d;
		}	
	}
	static class mnode{
		long num;
		int d;

		public mnode(long num, int d) {
			this.num = num;
			this.d = d;
		}
	}
	
	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/swea_2382_input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<=tc;t++) {
			q=new ArrayDeque<>();
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			map=new mnode[n][n];
			for(int i=0;i<k;i++) {
				st=new StringTokenizer(br.readLine());
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				int num=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				q.offer(new node(y,x,num,d));		
			}
			
			for(int i=0;i<m;i++) {
				for(int j=0;j<n;j++) {
					for(int k=0;k<n;k++) {
						map[j][k]=new mnode(0,0);
					}
				}
				cal();
				
			}
			long ans=0;
			for(node nq:q) {
				//System.out.println(nq.num);
				ans+=nq.num;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	static void cal() {
		while(!q.isEmpty()) {
			node nd=q.poll();
			int ny=nd.y+dy[nd.d];
			int nx=nd.x+dx[nd.d];
			if(ny==0||nx==0||ny==n-1||nx==n-1) {
				int d=dirchange(nd.d);
				long num=nd.num/2;
				if(num==0)continue;
				if(map[ny][nx].num!=0) {
					if(map[ny][nx].num<num) {
						map[ny][nx].d=d;
					}
					map[ny][nx].num+=num;
				}
				else {
					map[ny][nx]=new mnode(num,d);
				}
			}else {
				if(map[ny][nx].num!=0) {
					if(map[ny][nx].num<nd.num) {
						map[ny][nx].d=nd.d;
						//지금 갱신잘못하고 있
					}map[ny][nx].num+=nd.num;
				}else {
					map[ny][nx]=new mnode(nd.num,nd.d);
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j].num!=0) {
					q.offer(new node(i, j, map[i][j].num, map[i][j].d));
				}
			}
		}
		
	}
	static int dirchange(int d) {
		int dir=0;
		switch(d) {
		case 1:
			dir= 2;
			break;
		case 2:
			dir=1;
			break;
		case 3:
			dir=4;
			break;
		case 4:
			dir=3;
			break;
			
		}
		return dir;
	}
}
