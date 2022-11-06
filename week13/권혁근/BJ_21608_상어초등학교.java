
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean contain(int n, int n2) {
		for(int i=0; i<4; i++) {
			if(infos[n][i]==n2) return true;
		}
		return false;
	}
	
	static void seat(int n) {
		int c0=-1;
		int cf=-1;
		int ix=-1, iy=-1;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(mat[y][x]!=0)continue;
				int cnt0=0;
				int cntf=0;
				for(int dr=0; dr<4; dr++) {
					int nx=x+dirs[dr][0];
					int ny=y+dirs[dr][1];
					if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
					if(mat[ny][nx]==0) {
						cnt0++;
					}else if(contain(n, mat[ny][nx])) {
						cntf++;
					}
				}
				
				if(cf<cntf) {
					cf=cntf; c0=cnt0;
					ix=x; iy=y;
				}else if(cf==cntf) {
					if(c0<cnt0) {
						cf=cntf; c0=cnt0;
						ix=x; iy=y;
					}
				}
				
				
			}
		}
		mat[iy][ix]=n;
		//System.out.println(n+" : "+ix+", "+iy);
	}
	
	static int cal() {
		int sum=0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				int n=mat[y][x];
				int cntf=0;
				for(int dr=0; dr<4; dr++) {
					int nx=x+dirs[dr][0];
					int ny=y+dirs[dr][1];
					if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
					if(contain(n, mat[ny][nx])) cntf++;
				}
				if(cntf==0);
				else if(cntf==1) sum+=1;
				else if(cntf==2)sum+=10;
				else if(cntf==3)sum+=100;
				else sum+=1000;
			}
		}
		
		return sum;
	}
	
	static int N, N2;
	static int[][] infos;
	static int[][] mat;
	static int[][] dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		N=Integer.parseInt(br.readLine());
		N2= N*N;
		infos=new int[N2+1][4];
		mat=new int[N][N];
		int[] order=new int[N2];
		for(int i=0; i<N2; i++) {
			stn=new StringTokenizer(br.readLine());
			int idx=Integer.parseInt(stn.nextToken());
			order[i]=idx;
			for(int j=0; j<4; j++) {
				int n=Integer.parseInt(stn.nextToken());
				infos[idx][j]=n;
			}
		}
		
		for(int n: order) {
			seat(n);
		}
		int answer=cal();
		System.out.println(answer);
		
	}
}
