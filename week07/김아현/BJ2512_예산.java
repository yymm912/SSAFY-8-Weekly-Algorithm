package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파라메트릭 서치
public class BJ2512_예산 {

	static int N, M;
	static int[] req;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		int lo = 0, hi = -1; // 최저, 최고 요청 예산액
		
		req = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			req[i] = Integer.parseInt(st.nextToken());
			hi = Math.max(hi, req[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		while(lo <= hi) {
			int mid = (lo + hi) / 2; // 상한액
			long tBudget = 0L; // 임시 요청예산 총합
			
			for (int i = 0; i < N; i++) {
				if(mid < req[i]) tBudget += mid;
				else tBudget += req[i];
			}
			
			if(M < tBudget) hi = mid - 1;
			else lo = mid + 1;
		}
		
		System.out.println(hi); // 상한액 출력

	}

}
