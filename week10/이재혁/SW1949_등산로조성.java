package studygroup.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW1949_등산로조성 {
	static int T,N,K,ans;
	static int[][] map;
	static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	static ArrayList<Node> list;
	static boolean[][] visited;
	static int dx[] = {0,0,-1,1};
	static int dy[]	= {1,-1,0,0};
	static int[][] tmp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T ;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			list = new ArrayList<>();
			int max = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					//가장 높은 산봉우리가 누군가
					max = Math.max(max, map[i][j]);
				}
			}
			
			// 그 산봉우리 인 값들 리스트에 저장
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==max) {
						list.add(new Node(i,j));
					}
				}
			}
			simul(max);
			System.out.println("#"+t+" "+ans);
			ans = 0;
		}
	}
	private static void simul(int max) {
		for(int i=0; i<list.size(); i++) {
			//리스트를 순회하며 값을 꺼낸다
			visited= new boolean[N][N];
			int x = list.get(i).x;
			int y = list.get(i).y;
			
			//맵 복사한다.
			tmp = new int[N][N];
			for(int j=0; j<N; j++) {
				tmp[j] = map[j].clone();
			}
			visited[x][y]=true;
			dfs(x,y,1,1,tmp);
		}
		
	}
	private static void dfs(int x,int y,int dis, int count,int[][] tmp) {
		//ans를 계속 dis값과 비교하며 저장하고
		ans = Math.max(dis, ans);
		
		for(int d=0; d<4; d++) {
			int nx = x+dx[d];
			int ny = y+dy[d];
			//4방 탐색을 하면서 현재 값보다 작으면 가보자
			if(nx<0||ny<0||nx>=N||ny>=N||visited[nx][ny]) continue;
			if(tmp[nx][ny] < tmp[x][y]) {
				visited[nx][ny]=true;
				dfs(nx,ny,dis+1,count,tmp);
				visited[nx][ny] = false;
			}
			// 현재 값보다 크면 
			else {
				for(int j=1; j<=K; j++) {
					//1~K까지 깎아보면서 기회가 있다면 가보자
					if(count==1 && tmp[nx][ny]-j < tmp[x][y]) {
						tmp[nx][ny] -= j;
						visited[nx][ny]=true;
						dfs(nx,ny,dis+1,count-1,tmp);
						tmp[nx][ny] += j;
						visited[nx][ny]=false;
					}
				}
			}
		}
	}
}
