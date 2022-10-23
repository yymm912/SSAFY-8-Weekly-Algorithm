package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_원자소멸시뮬레이션_5648 {	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[][] map =new int[4002][4002]; //여기 방문한 수
		
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] Atom = new int[N][4];
			for(int i =0; i<N;i++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1000)*2;
				int y = (Integer.parseInt(st.nextToken())+1000)*2;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				Atom[i] = new int[] {x,y, d,k};
				map[x][y] =1;
			}// 입력 끝 
			int ans =0;// 합 담아둘 거
			boolean[][] col = new boolean[4002][4002]; // 충돌?
			for(int time = 0; time<4002 && N>0; time++) {
				for(int i =N-1; i>=0; i--) {
					int x = Atom[i][0];
					int y = Atom[i][1];
					map[x][y]--;
					//System.out.println(Atom[i]);
					switch(Atom[i][2]) {
					case 0:
						if(y==4000) {
							Atom[i] = Atom[N-1];
							N--;
							break;
						}
						Atom[i][1]++;
						y++;
						map[x][y]++;
						break;
					case 1:
						if(y==0) {
							Atom[i] = Atom[N-1];
							N--;
							break;
						}
						Atom[i][1]--;
						y--;
						map[x][y]++;
						break;
					case 2:
						if(x==0) {
							Atom[i] = Atom[N-1];
							N--;
							break;
						}
						Atom[i][0]--;
						x--;
						map[x][y]++;
						break;
					case 3:
						if(x==4000) {
							Atom[i] = Atom[N-1];
							N--;
							break;
						}
						Atom[i][0]++;
						x++;
						map[x][y]++;
						break;
					}
					if(map[x][y]>1) {
						col[x][y] = true;
					}
					
				}// 충돌하면 map이 개수가 되고
				//System.out.println("없어지는거 ");
				for(int i =N-1; i>=0; i--) {
					int x = Atom[i][0];
					int y = Atom[i][1];
					if(col[x][y]) {
						map[x][y]-=1;
						if(map[x][y]==0) {
							col[x][y] = false;
						}
						
						//System.out.println(Atom[i]);
						ans+=Atom[i][3];
						Atom[i] = Atom[N-1];
						N--;
					}
				}
				//System.out.println(N+"남은거");
			}
			sb.append('#').append(tc).append(" ").append(ans).append('\n');
			
		}
		br.close();
		System.out.println(sb);
	}
}