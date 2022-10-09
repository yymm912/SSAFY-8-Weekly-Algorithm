package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_3190 {
	static int n,k,l;
	static int [][]map;
	static List<node>list=new ArrayList<>();
	static int[]dx= {1,0,-1,0},dy= {0,1,0,-1};//D
	
	static class node{//cmd
		int x;
		char c;
		public node(int x, char c) {
			this.x = x;
			this.c = c;
		}	
	}
	
	static class snackhead{//뱀 대갈
		int y,x,t;
		public snackhead(int y, int x,int t) {
			this.y = y;
			this.x = x;
			this.t=t;
		}
		
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		k=Integer.parseInt(br.readLine());
		
		map=new int[n+1][n+1];
		
		for(int i=0;i<k;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			map[y][x]=1;
		}
		l=Integer.parseInt(br.readLine());
		
		for(int i=0;i<l;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			char c=st.nextToken().charAt(0);
			list.add(new node(x,c));
		}
		simul(1,1,0,0);//y,x,t,d 
	}
	
	static void simul(int y,int x,int t,int d ) {
		List<int[]>snack=new ArrayList<>();//뱀 전체
		snack.add(new int[] {y,x});
		snackhead sh=new snackhead(y, x,0);//뱀 대갈
		
		while(true) {
			int time=sh.t;
			for(int i=0;i<list.size();i++) {
				if(time==list.get(i).x) {
					if(list.get(i).c=='D') {
						d++;
						if(d==4)d=0;
						list.remove(i);
						break;
					}else if(list.get(i).c=='L') {
						d--;
						if(d<0)d=3;
						list.remove(i);
						break;
					}
				}
			}
			int ny=sh.y+dy[d];
			int nx=sh.x+dx[d];
			if(ny>n||nx>n||ny<=0||nx<=0) {
				System.out.println(sh.t+1);
				return ;
			}
			for(int i=0;i<snack.size();i++) {
				if(snack.get(i)[0]==ny&&snack.get(i)[1]==nx) {
					System.out.println(sh.t+1);
					return ;
				}
			}
			if(map[ny][nx]==1) {
				sh=new snackhead(ny, nx, time+1);
				snack.add(new int[] {ny,nx});
				map[ny][nx]=0;
			}else {
				sh=new snackhead(ny, nx, time+1);
				snack.add(new int[] {ny,nx});
				snack.remove(0);
			}
		}		
	}
}
