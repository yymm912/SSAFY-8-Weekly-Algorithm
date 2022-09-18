package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1654_랜선자르기 {

	static int K, N;
	static int[] line;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		long lo = 0, hi = 0;
		
		line = new int[K];
		for (int i = 0; i < K; i++) {
			line[i] = Integer.parseInt(br.readLine());
			hi = Math.max(hi, line[i]);
		}
		
		// mid의 값이 0이 되지 않게 상한값 보정
		hi += 1;
		
		while(lo < hi) {
			long mid = (lo + hi) / 2;
			long cnt = 0;
			
			for (int i = 0; i < K; i++) {
				cnt += line[i] / mid;
			}
			
			// 만들어낸 랜선의 갯수가 N개보다 많다면
			// 랜선의 길이를 최대로 한 것이 아니므로 하한값을 올린다.
			// 최대 랜선의 길이를 구해야 하기 때문에 같은 갯수일 때도 하한을 올려준다.
			if(cnt < N) hi = mid;
			else lo = mid + 1;
		}
		
		System.out.println(lo-1);
	}

}
