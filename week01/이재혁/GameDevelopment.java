import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class GameDevelopment {
	
	static class User{
		int row;
		int col;
		int direction;
		int count;
		
		public User(int row,int col,int direction) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.count = 1;
		}
	}
	
	//북동남서 으로 델타 선언
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//지도 배열 선언
		int map[][] =new int[n][m];
		//방문여부 확인 배열 선언
		boolean visited[][] = new boolean[n][m];
		
		st = new StringTokenizer(br.readLine());
		// 행 , 렬 , 초기 방향 입력받기
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		
		User user = new User(row,col,direction);
		
		for(int i=0; i<n; i++) {
			 st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	
		int breakCount = 0; //멈춘횟수		
		int nx = user.row; //row좌표
		int ny = user.col; //col좌표
		visited[user.row][user.col] = true; //현재 시작점 방문 체크
		
		while(true) {
			if(breakCount == 4) {
				//4방향 전부 갈 곳이 없을때 뒤로 갈 수 있다면 위치를 옮김
				if(map[nx-dx[user.direction]][ny-dy[user.direction]] == 0) {
					nx = nx-dx[user.direction];
					ny = ny-dy[user.direction];
					continue;
				}
				//뒤로마저 갈 수 없을때 멈추기
				break;
			}
			//바라보는 방향은 -1이라면 3을 가르키게함 북 -> 서 경우 아니라면 방향을 감소시킴 (델타 참조) 
			user.direction = (user.direction-1==-1)?user.direction=3:user.direction-1;
			// 갈 방향 1칸 전진했을때 좌표 생성
			int tempx = nx + dx[user.direction];
			int tempy = ny + dy[user.direction];
			// 배열의 범위를 벗어나거나 방문했거나 바다이거나 경우의 수를 체크하여 멈춘횟수를 증가 시키고 진행
			if(tempx < 0 || tempx >= n || tempy < 0 || tempy >=m || map[tempx][tempy] == 1 || visited[tempx][tempy] == true) {
				breakCount++;
				continue;
			}
			//갈 수있다면 방문체크,nx,ny좌표 현재있는 곳으로 바꿔주고 한칸 증가, 4방향중 한방향이라도 갈 수 있었을 상황이었으니 멈춘횟수 초기화 시켜줌
			visited[tempx][tempy] = true;
			user.count++;
			nx = tempx;
			ny = tempy;
			breakCount=0;
		}
		System.out.println(user.count);

	}

}
