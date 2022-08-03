package algorithm.TC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Guild {
	static int N;
	static int[] fear;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		fear = new int[N];
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
			fear[i] = Integer.parseInt(stk.nextToken());
		
		Arrays.sort(fear);
		
		int ans = 0, cnt = 0;
		for(int i = 0; i < N; i++) {
			cnt++;
			if(fear[i] == cnt) {
				ans++;
				cnt= 0;
			}
		}
		
		System.out.println(ans);
	}
}
