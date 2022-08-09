import java.util.*;
import java.io.*;

public class Main {

	public static class Snake{
		int x, y;
		public Snake(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Dir{
		int x;
		char dir;
		public Dir(int x, char dir){
			this.x = x;
			this.dir = dir;
		}
	}
	
	public static int dx[] = {0, 1, 0, -1};
	public static int dy[] = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n, k, l;
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		int [][] arr = new int [101][101];
		
		Queue<Dir> d = new LinkedList<>(); // dir
		Queue<Snake> s = new LinkedList<>(); // snake
		
		for(int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
		}
		
		l = Integer.parseInt(br.readLine());
		for(int i=0; i<l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			Dir tmp = new Dir(a, c);
			d.add(tmp);
		}
		
		s.add(new Snake(1, 1)); // 뱀의 초기 위치
		int idx = 0; // 오른쪽 향해서 최초 출발
		int res = 0;
		int nx = 1, ny = 1;
		arr[1][1] = 2; // 뱀 표시
		
		while(true) {
			res++;
			nx += dx[idx];
			ny += dy[idx];
			// 몸통이나 벽에 부딪히는 경우
			if((nx < 1 || nx > n || ny < 1 || ny > n || arr[nx][ny] == 2)) break;
			
			// 사과가 있는 경우
			if(arr[nx][ny] == 1) arr[nx][ny] = 0;
			else {
				Snake tail = s.poll();
				arr[tail.x][tail.y] = 0;
			}
			
			s.add(new Snake(nx, ny));
			arr[nx][ny] = 2;
			
			// 방향 전환
			if(d.size() > 0 && d.peek().x == res) {
				if(d.peek().dir == 'L') idx = (idx + 3) % 4;
				else idx = (idx + 1) % 4;
				d.poll();
			}
		}
		System.out.println(res);
		br.close();
	}

}