package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_톱니바퀴_14891 {
	
	static String[] gear = new String[4];
	static int K, ans;
	
	static StringBuilder sb = new StringBuilder();
	
	static int gearNum, dir;
	
	// 회전 : 1, 비회전 : 0
	// 시계방향 : 1, 반시계방향 : -1
	static int[][] checked;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 4; i++) {
			gear[i] = br.readLine();
		}
		
		K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			checked = new int[4][2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			gearNum = Integer.parseInt(st.nextToken()) - 1;
			dir = Integer.parseInt(st.nextToken());
			
			checked[gearNum][0] = 1;
			checked[gearNum][1] = dir;
			
			int leftDir = dir;
			int rightDir = dir;
			
			// 왼쪽으로 가면서 [회전유무][방향]을 채운다.
			for (int j = gearNum - 1; j >= 0; j--) {
				if (gear[j + 1].charAt(6) != gear[j].charAt(2)) {
					checked[j][0] = 1;
					checked[j][1] = -leftDir;
					leftDir *= -1;
				} else {
					break;
				}
			}
			
			// 오른쪽으로 가면서 [회전유무][방향]을 채운다.
			for (int j = gearNum + 1; j < 4; j++) {
				if (gear[j - 1].charAt(2) != gear[j].charAt(6)) {
					checked[j][0] = 1;
					checked[j][1] = -rightDir;
					rightDir *= -1;
				} else {
					break;
				}
			}
			
			// checked를 순회하며 checked[i][0] == 1(회전하는) 인 경우를 처리한다.
			for (int j = 0; j < 4; j++) {
				if (checked[j][0] == 1) {
					turn(j, gear[j], checked[j][1]);
				}
			}
		}
		
		if (gear[0].charAt(0) == '1') ans += 1;
		if (gear[1].charAt(0) == '1') ans += 2;
		if (gear[2].charAt(0) == '1') ans += 4;
		if (gear[3].charAt(0) == '1') ans += 8;
		
		System.out.println(ans);
	}
	
	static void turn(int index, String gearStr, int dir) {
		if (dir == 1) {
			sb.append(gearStr.charAt(7));
			for (int i = 0; i < 7; i++) {
				sb.append(gearStr.charAt(i));
			}
		} else if (dir == -1) {
			for (int i = 1; i < 8; i++) {
				sb.append(gearStr.charAt(i));
			}
			sb.append(gearStr.charAt(0));
		}
		
		gear[index] = sb.toString();
		sb.setLength(0);
	}
}
