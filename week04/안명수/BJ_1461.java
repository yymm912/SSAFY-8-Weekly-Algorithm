package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1461 {
	static int N, M, ans;
	static List<Integer> pos, neg;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		
		// 입력
		// 양수와 음수를 따로받음
		// 음수의 경우 양수로 바꿔서 저장
		pos = new ArrayList<>();
		neg = new ArrayList<>();
		stk = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(stk.nextToken());
			if(num > 0) pos.add(num);
			else neg.add(-num);
		}
		
		
		
		// 내림차순으로 정렬
		Collections.sort(neg, (a, b)-> b-a);
		Collections.sort(pos, (a, b)-> b-a);
		
		
		int neg_pos = 0, pos_pos = 0;
		int posSize = pos.size();
		int negSize = neg.size();
		while(true) {
			if(neg_pos >= negSize && pos_pos >= posSize) break;
		
			// 양수와 음수의 집합에서 가장 큰 값을 가지는 숫자를 가져옴
			int A = negSize > neg_pos ? neg.get(neg_pos) : 0;
			int B = posSize > pos_pos ? pos.get(pos_pos) : 0;
			
			// 더 큰값을 먼저 방문
			if(A > B) {
				if(ans == 0) ans += A;
				else ans += A*2;
				neg_pos += M;
			}else {
				if(ans == 0) ans += B;
				else ans += B*2;
				pos_pos += M;
			}
			
		}
		
		System.out.println(ans);
	}
}
