
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.Point;;

public class Main {
	
	static int check() {
		Deque<Point> que = new ArrayDeque<>();
		for(Point v : virus) {
			que.add(v);
		}
		
		int[][] temp = new int[H][];
		for(int y=0; y<H; y++) {
			temp[y]=mat[y].clone();
		}
		int cnt=0;
		while(!que.isEmpty()) {
			Point cur = que.pollFirst();
			int cx=cur.x, cy=cur.y;
			for(int dr=0; dr<4; dr++) {
				int nx=cx+dirs[dr][0];
				int ny=cy+dirs[dr][1];
				if(nx<0 || ny<0||nx>=W || ny>=H || temp[ny][nx]!=0) continue;
				temp[ny][nx]=2;
				cnt++;
				que.add(new Point(nx,ny));
			}
		}
		return cnt0-3-cnt;
	}
	
	static void block() {
		int HW=H*W;
		int xi,yi,xj,yj,xk,yk;
		for(int i=0; i<HW-2; i++) {
			xi=i%W;
			yi=i/W;
			if(mat[yi][xi]!=0) continue;
			mat[yi][xi]=1;
			for(int j=i+1; j<HW-1; j++) {
				xj=j%W;
				yj=j/W;
				if(mat[yj][xj]!=0) continue;
				mat[yj][xj]=1;
				for(int k=j+1; k<HW; k++) {
					xk=k%W;
					yk=k/W;
					if(mat[yk][xk]!=0)continue;
					mat[yk][xk]=1;
					int cnt=check();
					answer=Math.max(cnt, answer);
					mat[yk][xk]=0;
				}
				mat[yj][xj]=0;
			}
			mat[yi][xi]=0;
			
		}
	}
	
	static int H,W;
	static int[][] mat;
	static List<Point> virus;
	static int[][] dirs= {{1,0},{0,1},{-1,0},{0,-1}};
	static int cnt0, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		H=Integer.parseInt(stn.nextToken());
		W=Integer.parseInt(stn.nextToken());
		mat=new int[H][W];
		virus=new ArrayList<>();
		cnt0=0;
		answer=0;
		for(int y=0; y<H; y++) {
			stn=new StringTokenizer(br.readLine());
			for(int x=0; x<W; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
				if(mat[y][x]==2) {
					virus.add(new Point(x,y));
				}else if(mat[y][x]==0) {
					cnt0++;
				}
			}
		}
		block();
		System.out.println(answer);
		
		
	}
}
