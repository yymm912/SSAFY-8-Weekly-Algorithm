package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16235_나무재테크 {
	
	static int N;
	static int[][] val;
	static boolean[][] check;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		val = new int[M][2];
		check = new boolean[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());
			check[tmp2][tmp1] = true;
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(k==i) continue;
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					if(check[i][k] && check[k][j]) check[i][j] = true;
				}	
			}
		}
		
		for(int i=1; i<=N; i++) {
			int cnt = 1;
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				if(check[i][j] || check[j][i]) cnt++;
			}
			sb.append(N-cnt+"\n");
		}
		System.out.println(sb);
	}
	
}