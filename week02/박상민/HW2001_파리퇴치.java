import java.io.*;
import java.util.*;

public class HW2001_파리퇴치 {

	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N + 1][N + 1];
			
			for (int y = 1; y <= N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 1; x <= N; x++) {
					arr[y][x] = arr[y - 1][x] + arr[y][x - 1] - arr[y - 1][x - 1] + Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			for (int sy = 1, ey = M; ey <= N; sy++, ey++) {
				for (int sx = 1, ex = M; ex <= N; sx++, ex++) {
					answer = Math.max(answer, arr[ey][ex] - arr[sy-1][ex] - arr[ey][sx-1] + arr[sy-1][sx-1]);
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}