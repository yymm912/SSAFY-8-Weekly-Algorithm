import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean spread() {
		boolean have0=false;
		List<int[]> added = new ArrayList<>();
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(mat[y][x]!=0) continue;
				int minv=K+1;
				for(int dr=0; dr<4; dr++) {
					int nx=x+dirs[dr][0];
					int ny=y+dirs[dr][1];
					if(nx<0 || ny<0 || nx>=N || ny>=N ||mat[ny][nx]==0) continue;
					minv=Math.min(minv, mat[ny][nx]);
				}
				if(minv==K+1) {
					have0=true;
				}else {
					added.add(new int[] {x,y,minv});
				}
			}
		}
		
		for( int[] add:added) {
			mat[add[1]][add[0]]=add[2];
		}
		
		return have0;
		
	}
	
	static int N,K;
	static int[][] mat;
	static int[][] dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		N=Integer.parseInt(stn.nextToken());
		K=Integer.parseInt(stn.nextToken());
		mat=new int[N][N];
		for(int y=0; y<N; y++) {
			stn= new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		stn= new StringTokenizer(br.readLine());
		int S=Integer.parseInt(stn.nextToken());
		int ty=Integer.parseInt(stn.nextToken())-1;
		int tx=Integer.parseInt(stn.nextToken())-1;
		
		for(int t=0; t<S; t++) {
			
			if(!spread()|| mat[ty][tx]!=0) {
				break;
			}
			
		}
		System.out.println(mat[ty][tx]);
	}
	
}
