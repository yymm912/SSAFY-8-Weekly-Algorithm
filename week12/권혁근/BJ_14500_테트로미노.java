import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	 
	 static void dfs(int sx, int sy) {
		 Deque<Item> que=new ArrayDeque<>();
		 List<XY> xys = new ArrayList<>();
		 xys.add(new XY(sx,sy)); 
		 que.add(new Item(mat[sy][sx], xys));
		 
		 while(!que.isEmpty()) {
			 Item ci= que.pollLast();
			 int len=ci.lst.size();
			 if(len==4) {
				 answer=Math.max(answer, ci.sum);
				 continue;
			 }
			 int cx=ci.lst.get(len-1).x;
			 int cy=ci.lst.get(len-1).y;
			 for(int dr=0; dr<3; dr++) { //상우하 방향만
				 int nx=cx+dirs[dr][0];
				 int ny=cy+dirs[dr][1];
				 if(nx<0 || ny<0 || nx>=w || ny>=h) continue;
				 XY nxy=new XY(nx,ny);
				 if(ci.lst.contains(nxy)) continue;
				 List<XY> nlst = new ArrayList<>();
				 nlst.addAll(ci.lst);
				 nlst.add(nxy);
				 que.add(new Item(ci.sum+mat[ny][nx],nlst)); //전진
			 }
			 
			 if(len==3) { //빠큐모양 만들기
				 cx=ci.lst.get(1).x;
				 cy=ci.lst.get(1).y;
				 for(int dr=0; dr<3; dr++) {
					 int nx=cx+dirs[dr][0];
					 int ny=cy+dirs[dr][1];
					 if(nx<0 || ny<0 || nx>=w || ny>=h) continue;
					 XY nxy=new XY(nx,ny);
					 if(ci.lst.contains(nxy)) continue;
					 answer=Math.max(answer, ci.sum+mat[ny][nx]);
				 }
			 }
		 }
	 }
	

	static int h,w, answer;
	static int[][]mat;
	static boolean[][] visited;
	static int[][] dirs= {{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn = new StringTokenizer(br.readLine());
		h=Integer.parseInt(stn.nextToken());
		w=Integer.parseInt(stn.nextToken());
		mat=new int[h][w];
		for(int y=0; y<h; y++) {
			stn = new StringTokenizer(br.readLine());
			for(int x=0; x<w; x++) {
				mat[y][x]=Integer.parseInt(stn.nextToken());
			}
		}
		answer=0;
		for(int y=0; y<h; y++) {
			for(int x=0; x<w; x++) {
				dfs(x,y);
			}
		}
		System.out.println(answer);
	}
	
	static class Item{
		int sum;
		List<XY> lst;
		public Item(int sum, List<XY> lst) {
			this.sum=sum;
			this.lst=lst;
		}
	}
	static class XY{
		int x,y;
		public XY(int x, int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public boolean equals(Object obj) {
			XY o=(XY)obj;
			return o.x==this.x && o.y==this.y;
		}
	}

}

