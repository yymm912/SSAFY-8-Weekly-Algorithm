package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17779_게리맨더링2 {
	static int n;
	static int [][]map;
	static int []sum;
	static int y,x,d1,d2;
	
	static int diff=Integer.MAX_VALUE;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++)map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				x=i;
				y=j;
				selectd(i,j);
			}
		}
		System.out.println(diff);
	}
	
	static void selectd(int x,int y) {
		for(int i=1;i<n;i++) {
			for(int j=1;j<n;j++) {
				if(x+i+j>n||y-i<1||y+j>n||y-i>=y)continue;
				d1=i;
				d2=j;
				cal(x,y,d1,d2);
			}
		}
	}
	
	static void cal(int x,int y,int d1,int d2) {
		System.out.println(x+" "+y+" "+d1+" "+d2);
		int max=0;
		int min=Integer.MAX_VALUE;
		sum=new int[6];
		boolean [][]visited=new boolean[n+1][n+1];
		//1
		for(int i=1;i<x+d1;i++) {
			for(int j=1;j<=y;j++) {
				sum[1]+=map[i][j];
				visited[i][j]=true;
			}
		}
		
		//2
		for(int i=1;i<=x+d2;i++) {
			for(int j=y+1;y<=n;y++) {
				sum[2]+=map[i][j];
				visited[i][j]=true;
			}
		}
		System.out.println(y-d1+d2);
		//3
		for(int i=x+d1;i<=n;i++) {
			for(int j=1;j<y-d1+d2;j++) {
				System.out.println(i+" "+j);
				sum[3]+=map[i][j];
				visited[i][j]=true;
			}
		}
		
		//4
		for(int i=x+d2+1;i<=n;i++) {
			for(int j=y-d1+d2;j<=n;j++) {
				sum[4]+=map[i][j];
				visited[i][j]=true;
			}
		}
		
		//5
		for(int i=x;i<=n;i++) {
			for(int j=y-d1;j<=n;j++) {
				if(!visited[i][j])sum[5]+=map[i][j];
			}
		}
		
		for(int i=1;i<=5;i++) {
			max=Math.max(max, sum[i]);
			min=Math.min(min, sum[i]);
		}
		diff=Math.min(diff, max-min);
	}
}
