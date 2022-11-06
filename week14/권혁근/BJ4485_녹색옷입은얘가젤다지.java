import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static void go() {
		PriorityQueue<Item> hp = new PriorityQueue<>((o1,o2)->o1.d-o2.d);
		hp.add(new Item(0,0,mat[0][0]));  //x,y,비용
		while(!hp.isEmpty()) {
			Item cur=hp.poll();
			int cx=cur.x, cy=cur.y, cd=cur.d;
			if(visited[cy][cx]) continue;
			visited[cy][cx]=true;
			for(int dr=0; dr<4; dr++) {
				int nx=cx+dirs[dr][0];
				int ny=cy+dirs[dr][1];
				if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
				int nd=cd+mat[ny][nx]; //다음 비용
				if(visited[ny][nx]) continue;
				if(ny==N-1 && nx==N-1) { //도착
					answer=nd;
					return;
				}
				hp.add(new Item(nx,ny,nd));
			}
		}
	}

	static int N,answer;
	static int[][] mat;
	static boolean[][] visited;
	static int[][] dirs= {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		StringBuffer sb = new StringBuffer();
		int tc=1;
		while(true) {
			N= Integer.parseInt(br.readLine());
			if(N==0) break;
			mat=new int[N][N];
			for(int y=0; y<N; y++) {
				stn=new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					mat[y][x]=Integer.parseInt(stn.nextToken());
				}
			}
			visited=new boolean[N][N];
			/*for(int y=0; y<N; y++) {
				Arrays.fill(visited[y], Integer.MAX_VALUE);
			}*/
			answer=0;
			go();

			sb.append("Problem "+tc+": ").append(answer+"\n");
			tc++;
		}
		System.out.println(sb);
		
		

	}
	static class Item{
		int x,y,d;
		Item(int x, int y, int d){
			this.x=x;
			this.y=y;
			this.d=d;
		}
	}

}
