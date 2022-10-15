package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 1초마다 주어진 이동궤적에 따라 두 사용자를 이동시키면서
// bc를 사용하는 경우의 수를 모두 따져 계산

public class SWEA5644_무선충전 {

	static int T, M, A, res;
	static int[][] move, user;
	static List<BC> bc;	// BC 좌표를 담을 리스트
	
	static int[] dx = {0, -1, 0, 1, 0}; // x상우하좌
	static int[] dy = {0, 0, 1, 0, -1};
	
	static class BC {
		int x, y, c, p;
		BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			move = new int[2][M+1];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= M; j++) { // 0초도 계산하는 것을 고려 -> dummy
					move[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bc = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				bc.add(new BC(x, y, c, p));
			}
			
			user = new int[2][2];
			user[0][0] = user[0][1] = 1;
			user[1][0] = user[1][1] = 10;
			
			res = 0;
			
			// M초동안 사용자 이동
			for (int i = 0; i <= M; i++) {
				user[0][0] += dx[move[0][i]];
				user[0][1] += dy[move[0][i]];
				user[1][0] += dx[move[1][i]];
				user[1][1] += dy[move[1][i]];
				
				res += charge();
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	static int charge() {
		int max = 0;
		
		BC b1, b2;  // 각 사용자가 선택한 bc
		int d1, d2; // 각 사용자와 bc와의 거리
		int u1, u2; // 각 사용자가 얻는 bc의 파워
		
		// 두 유저가 bc를 사용하는 경우의 수
		for (int i = 0; i < A; i++) { // 유저1
			
			b1 = bc.get(i);
			d1 = Math.abs(user[0][0] - b1.x) + Math.abs(user[0][1] - b1.y);
			u1 = d1 <= b1.c ? b1.p : 0;
			
			for (int j = 0; j < A; j++) { // 유저2
				
				b2 = bc.get(j);
				d2 = Math.abs(user[1][0] - b2.x) + Math.abs(user[1][1] - b2.y);
				u2 = d2 <= b2.c ? b2.p : 0;
				
				int sum = 0;
				if(i != j) sum += u1 + u2;    // 다른 bc면 각자 얻은 충전량만큼 충전가능
				else sum += Math.max(u1, u2); // 같은 bc면 충전량이 큰 bc의 충전량을 나눠 충전
				
				max = Math.max(max, sum);
			}
		}
		
		return max;
	}
}
