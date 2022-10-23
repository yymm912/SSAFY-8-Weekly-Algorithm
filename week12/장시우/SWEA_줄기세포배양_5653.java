package swea;

import java.io.*;
import java.util.*;

// pq사용해서 생명력이 높은 세포를 먼저 꺼내서 배양해주기
// map의 크기는 무한히 크다는 조건에 따라서 k(시간)을 비교해줘서 r+K*2+4
// 몇시간 뒤 활성화하니까 원래 oglife와 curlife를 2배로 줘서 비교해주며 따져주고
// 시간별로 진행을 하니까 temp에 넣어줘서 원래 pq가 다 돌고 temp에 있는거 다시 넣어주기 

public class SWEA_줄기세포배양_5653 {
	static int N,M,K,days;
	static boolean[][] visited;
	static int[][] map;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};

	static Queue<Pos> temp ;
	static PriorityQueue<Pos> pq;
	
	static class Pos implements Comparable <Pos>{
		int i,j,originalLife, curLife;

		Pos(int i, int j, int originalLife, int curLife) {
			super();
			this.i = i;
			this.j = j;
			this.originalLife = originalLife;
			this.curLife = curLife;
		}

		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return -Integer.compare(originalLife, o.originalLife);
		}
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t= Integer.parseInt(br.readLine());
		for(int tc=1 ; tc<=t ; tc++) {

			st = new StringTokenizer(br.readLine(), " ");
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			days=0;
			N = r+K*2+4;
			M = c+K*2+4;
			map = new int[N][M];
			visited =new boolean[N][M];
			
			pq = new PriorityQueue<>();
			temp = new LinkedList<Pos>();

			for(int i=N/2-1 ; i<N/2-1+r ; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=M/2-1 ; j<M/2-1+c ; j++) {
					int num=Integer.parseInt(st.nextToken());
					if(num!=0) {
						map[i][j]=num;
						pq.offer(new Pos(i,j,num,num*2));
						visited[i][j]=true;
						
					}
				}
			}

			bfs();
			
			System.out.println("#"+tc+" "+pq.size());
		}

	}

	private static void bfs() {
		// TODO Auto-generated method stub
		for(int t=1 ; t<=K ; t++) {
			while(!pq.isEmpty()) {
				Pos cur = pq.poll();
				
				cur.curLife=cur.curLife-1;
				// 원래 curlife를 2배로 잡아서 og가 커지면 그때부터 활성화 
				if(cur.originalLife>cur.curLife) {
					for(int i=0; i<4; i++) {
						int nx= cur.i + dx[i];
						int ny= cur.j + dy[i];
						if(0>nx || nx>N || 0>ny || ny> M) continue;
						
						if(visited[nx][ny]==false) {
							visited[nx][ny]=true;
							//시간별로 나누기 위해 temp넣어두고 pq 다돌면 (1시간이 지나) temp에 있는거 다시 pq 넣어주  
							temp.offer(new Pos(nx,ny,cur.originalLife, cur.originalLife*2));
						}
					}
				}
				// 죽지 않은 경우 
				if(cur.curLife != 0) temp.offer(new Pos(cur.i,cur.j,cur.originalLife,cur.curLife));
			}
			
			while(!temp.isEmpty()) {
				pq.offer(temp.poll());
			}
		}

	}
}