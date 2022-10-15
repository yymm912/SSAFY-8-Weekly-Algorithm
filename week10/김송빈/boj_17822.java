package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class boj_17822 {

	static int N,M,T;
	static int[][]map;
	static int x,d,k;
	
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		
		map=new int[N+1][M+1];//0 dummy
		for(int i=1;i<=N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<T;i++) {
			st=new StringTokenizer(br.readLine());
			x=Integer.parseInt(st.nextToken());
			d=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			
			for(int j=0;j<k;j++) {
				for(int l=x;l<=N;l++) {
					if(l%x==0) {
						simul(l,d);
					}
				}
			}
			remove();
		}
		int ans=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				//System.out.print(map[i][j]);
				ans+=map[i][j];
			}
			//System.out.println();
		}
		System.out.println(ans);
		
	}
	static void simul(int x,int d) {
		if(d==1) {
			int tmp=map[x][1];
			for(int i=1;i<M;i++) {
				map[x][i]=map[x][i+1];
			}
			map[x][M]=tmp;
		}
		else if(d==0) {
			int tmp=map[x][M];
			for(int i=M;i>=2;i--) {
				map[x][i]=map[x][i-1];
			}
			map[x][1]=tmp;
		}
	}
	
	static void remove() {
		HashSet<int[]>hs=new HashSet<>();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				int by=i-1;
				if(by==0)by=0;
				int ay=i+1;
				if(ay>N)ay=0;
				
				int bx=j-1;
				if(bx==0)bx=M;
				int ax=j+1;
				if(ax>M)ax=1;
				
				if(map[i][j]==map[by][j]&&map[i][j]!=0) {
					hs.add(new int[] {i,j});
					hs.add(new int[] {by,j});
				}
				if(map[i][j]==map[ay][j]&&map[i][j]!=0) {
					hs.add(new int[] {i,j});
					hs.add(new int[] {ay,j});
				}
				if(map[i][j]==map[i][bx]&&map[i][j]!=0) {
					hs.add(new int[] {i,j});
					hs.add(new int[] {i,bx});
				}
				if(map[i][j]==map[i][ax]&&map[i][j]!=0) {
					hs.add(new int[] {i,j});
					hs.add(new int[] {i,ax});
				}
			}
		}
	
		if(hs.size()==0) {
			mipl();
		}else {
			for(int []a:hs) {
				//System.out.println(a[0]+" "+a[1]);
				map[a[0]][a[1]]=0;
			}
		}
	}
	static void mipl() {
		int sum=0;
		int cnt=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(map[i][j]!=0) {
					cnt++;
					sum+=map[i][j];
				}
			}
		}
		double avg=(double)sum/cnt;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				if(map[i][j]>avg)map[i][j]-=1;
				else if(map[i][j]<avg&&map[i][j]!=0)map[i][j]+=1;
			}
		}
	}
}
