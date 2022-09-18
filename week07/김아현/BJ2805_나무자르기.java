package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2805_나무자르기 {

	static int N, M;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int lo = 0, hi = -1;
		
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			hi = Math.max(hi, tree[i]);
		}
		
		while(lo < hi) {
			int mid = (lo + hi) / 2;
			long sum = 0L;
			
			for (int i = 0; i < N; i++) {
				if(tree[i] > mid) sum += tree[i]-mid;
			}
			
			// 최소한으로 가져가고 싶기 때문에, 같을 경우에도 하한값을 올려준다.
			if(sum >= M) lo = mid + 1;
			else hi = mid;
		}
		
		// M의 크기를 초과하는 값의 바로 이전 값을 출력
		System.out.println(lo-1);
	}

}
