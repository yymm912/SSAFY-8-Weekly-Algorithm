package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14891톱니바퀴 {

	static int N;
	static int num;
	static int d;
	static int direction[] = new int[4];
	static int gear[][];
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = line.charAt(j) - '0';
			}
		}
		// 입력 완
		N = Integer.parseInt(br.readLine());
		
		
		
		for (int t = 0; t < N; t++) { // 회전 횟수만큼 반복
			
			direction = new int[4];
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 회전시킬 톱니바퀴의 번호
			num = Integer.parseInt(st.nextToken()) - 1;
			// 방향 
			d = Integer.parseInt(st.nextToken());
			
			// 방향 저장 
			check();
			
			// 자기자신은 항상 회전
			int start = gear[num][0];
			int end = gear[num][7];
			if( d == -1  ) { // 반시계 방향 
				for (int j = 0; j < 7; j++) {
					gear[num][j] = gear[num][j+1]; // 오른쪽 <- 왼쪽
				}
				gear[num][7] = start;
				
			}
			else if (d == 1 ) { // 시계 방향
				
				for (int j = 6; j >= 0; j--) {
					gear[num][j+1] = gear[num][j]; // 왼쪽 <- 오른쪽
				}
				gear[num][0] = end;
			}
			
			// 현재 톱니바퀴 ~ 오른쪽 회전 
			for (int i = num + 1; i < 4; i++) {
				
				start = gear[i][0];
				end = gear[i][7];
				
					if( direction[i] == -1  ) { // 반시계 방향 
						
						for (int j = 0; j < 7; j++) {
							gear[i][j] = gear[i][j+1]; // 오른쪽 <- 왼쪽
						}
						gear[i][7] = start;
						
						
					}
					else if (direction[i] == 1 ) { // 시계 방향
						
						for (int j = 6; j >= 0; j--) {
							gear[i][j+1] = gear[i][j]; // 왼쪽 <- 오른쪽
						}
						gear[i][0] = end;
						
					}
					
				
				
			}	
			
			// 현재 톱니바퀴 ~ 처음 톱니 까지 회전
			for (int i = num-1; i >= 0; i--) {
				 
				 start = gear[i][0];
				 end = gear[i][7];
				
					if( direction[i] == -1  ) { // 반시계 방향 
						
						for (int j = 0; j < 7; j++) {
							gear[i][j] = gear[i][j+1]; // 오른쪽 <- 왼쪽
						}
						gear[i][7] = start;
						
						
					}
					else if (direction[i] == 1 ) { // 시계 방향
						
						for (int j = 6; j >= 0; j--) {
							gear[i][j+1] = gear[i][j]; // 왼쪽 <- 오른쪽
						}
						gear[i][0] = end;
						
					}
			
			}
			
		
		} // for: N
		
	
		// 0번째에 따라 점수 합 출력
		
		if(gear[0][0] == 1) ans += 1;  
		if(gear[1][0] == 1) ans += 2;
		if(gear[2][0] == 1) ans += 4;
		if(gear[3][0] == 1) ans += 8;
		
		System.out.println(ans);
		
	}
	
	static void check() {
		direction = new int[4];
		direction[num] = d;
		
		// 현재 자석보다 왼쪽 자석들
		for (int i = num-1; i >= 0; i--) {
			if( gear[i][2] != gear[i+1][6] ) {
				direction[i] = direction[i+1] * (-1); // 반대방향으로 저장
			}
		}
		
		// 현재 자석보다 오른쪽 자석들
		for (int i = num+1; i < 4; i++) {
			if( gear[i-1][2] != gear[i][6] ) {
				direction[i] = direction[i-1] * (-1); // 반대 방향으로 저장 
			}
		}
	
		
	}

}
