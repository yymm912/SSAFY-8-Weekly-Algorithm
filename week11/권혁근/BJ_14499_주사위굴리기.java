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
	
	static void go() {
		
		for(int i=0; i<M; i++) {
			int dr=orders[i];
			int nx=cx+dirs[dr][0];
			int ny=cy+dirs[dr][1];
			if(nx<0 || ny<0 || nx>=w || ny>=h) 
				continue;
			
			moveDice(dr); //주사위 굴리기
			
			if(mat[ny][nx]==0) {
				mat[ny][nx]=dicec[3];
			}else {
				dicec[3]=mat[ny][nx];
				mat[ny][nx]=0;
			}
			sb.append(dicer[1]+"\n");
			cx=nx;cy=ny;
		}
	}
	
	static void moveDice(int dr) {
		if (dr==1) {//동
			int temp=dicer[2];
			dicer[2]=dicer[1];
			dicer[1]=dicer[0];
			dicer[0]=dicec[3];
			dicec[3]=temp;
			dicec[1]=dicer[1];
		}else if(dr==4) { //남
			int temp=dicec[3];
			dicec[3]=dicec[2];
			dicec[2]=dicec[1];
			dicec[1]=dicec[0];
			dicec[0]=temp;
			dicer[1]=dicec[1];
		}else if(dr==2) { //서
			int temp=dicer[0];
			dicer[0]=dicer[1];
			dicer[1]=dicer[2];
			dicer[2]=dicec[3];
			dicec[3]=temp;
			dicec[1]=dicer[1];
		}else { //북
			int temp=dicec[0];
			dicec[0]=dicec[1];
			dicec[1]=dicec[2];
			dicec[2]=dicec[3];
			dicec[3]=temp;
			dicer[1]=dicec[1];
		}
		/*
		System.out.println("r :"+Arrays.toString(dicer));
		System.out.println("c :"+Arrays.toString(dicec));
		System.out.println();*/
	}
	
	
	static int [][]dirs= {{},{1,0},{-1,0},{0,-1},{0,1}};
	static int h,w,M;
	static int[] orders;
	static int[][] mat;
	static int[] dicec;
	static int[] dicer;
	static int cx,cy;
	static StringBuffer sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		h=Integer.parseInt(stn.nextToken());
		w=Integer.parseInt(stn.nextToken());
		cy=Integer.parseInt(stn.nextToken());
		cx=Integer.parseInt(stn.nextToken());
		M=Integer.parseInt(stn.nextToken());
		mat=new int[h][w];
		
		for(int y=0; y<h; y++) {
			stn = new StringTokenizer(br.readLine());
			for(int x=0; x<w; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		orders=new int[M];
		stn = new StringTokenizer(br.readLine());
		
		for(int i=0; i<M; i++)
			orders[i]=Integer.parseInt(stn.nextToken());
		
		dicer=new int[]{0,0,0};
		dicec=new int[]{0,0,0,0};
		
		sb = new StringBuffer();
		go();
	
		System.out.println(sb);
		
	}

	
}
