

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2001파리퇴치 {
	static int[][] arr;
	static int max;
	static int sum;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 1; i <= T; i++) {
			st= new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			arr = new int[N+1][N+1];
			
			for (int y = 1; y <=N; y++) {
				st= new StringTokenizer(br.readLine());
				for (int x = 1; x <=N; x++) {
					arr[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 0;
			for (int y = 1; y <=N; y++) {
				for (int x = 1; x <=N; x++) {
					sum = 0;
					for (int leny = 0; leny < M; leny++) {
						for (int lenx = 0; lenx < M; lenx++) {
							if (y+leny <= N && x+lenx <= N)
								sum+=arr[y+leny][x+lenx];
						}
					}
					if (sum > max)
						max = sum;
				}
			}
			System.out.println("#"+i+" "+max);
		}

	}

}
