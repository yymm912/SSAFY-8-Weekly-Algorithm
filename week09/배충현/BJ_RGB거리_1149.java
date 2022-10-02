import java.util.*;
import java.io.*;

// 각 집을 빨 초 파로 색칠
// 옆의 집이랑 색이 달라야한다.
// 모든 집을 칠하는 비용의 최솟값을 출력한다.
public class BJ_RGB거리_1149 {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N+1][3];
		for(int i=1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				input[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for(int i=2; i<=N; i++) {
			input[i][0] += Math.min(input[i-1][1], input[i-1][2]);
			input[i][1] += Math.min(input[i-1][0], input[i-1][2]);
			input[i][2] += Math.min(input[i-1][0], input[i-1][1]);
		}
		
		System.out.println(Math.min(Math.min(input[N][0], input[N][1]), input[N][2]));
		
	}
}
