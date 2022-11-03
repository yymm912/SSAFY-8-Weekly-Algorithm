package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1477_휴게소세우기 {

	static int N, M, L;
	static int[] rest;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		rest = new int[N+2];
		
		// 휴게소를 세울 때 계산의 기준이 되는
		// 도로의 시작과 끝을 넣어주기
		rest[0] = 0;
		rest[N+1] = L;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			rest[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색을 위한 정렬
		Arrays.sort(rest);
		
		// 휴게소 간 거리 중 최소값과 최대값
		int min = 1;
		int max = L-1;
		
		while(min <= max) {
			int mid = (min + max) / 2;
			int cnt = 0;
			int prev = rest[0];
			
			for (int i = 1; i <= N+1; i++) {
				// 두 휴게소 사이의 거리가 기준 거리보다 클 때 휴게소 세우기 가능
				if(rest[i] - prev > mid) {
					// 지어진 휴게소를 제외하고 세울 수 있는 휴게소 갯수 카운트
					cnt += (rest[i] - prev - 1) / mid;
				}
				
				// 이전 휴게소 갱신
				prev = rest[i];
			}
			
			// 세울 휴게소보다 많이 세웠다면 하한값 올리기
			// 세울 휴게소보다 적게 세웠다면 상한값 내리기
			// 같을 때는 최소값을 찾아야 하기 때문에 상한값을 내려준다
			if(M < cnt) min = mid + 1;
			else max = mid - 1;
		}
		
		System.out.println(min);
	}
}
