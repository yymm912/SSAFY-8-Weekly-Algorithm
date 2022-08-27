import java.io.*;
import java.util.*;
 
public class Main {
 
	public static int N, M;
	public static int[] arr;
	public static boolean[] visit;
	public static StringBuilder sb = new StringBuilder();
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
 
		arr = new int[M];
		visit = new boolean[N];
        
		dfs(0);
		System.out.println(sb);
 
	}
 
	public static void dfs(int x) {
		if (x == M) {
			for (int val : arr) {
				sb.append(val).append(' ');
			}
			sb.append('\n');
			return;
		}
 
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				arr[x] = i + 1;
				dfs(x + 1);
				visit[i] = false;
			}
		}
	}
 
}