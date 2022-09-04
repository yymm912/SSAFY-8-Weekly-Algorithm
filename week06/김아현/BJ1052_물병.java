package bitmask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 참고 : https://ddb8036631.github.io/boj/1052_%EB%AC%BC%EB%B3%91/
// 1. '3개의 물병 + 산 물병 1개' 또는 '4개의 물병'으로 1개의 물병을 만드는 것은 동일한 결과가 나온다.
// 2. 이진수 변환한 N의 각 자리는 각 물병에 든 물의 양과 같고, 1의 갯수는 물병의 갯수와 같다. ex) N = 7 = 111(2) -> 4리터, 2리터, 1리터가 든 물병으로 총 3개
// 3. K개의 물병을 만드려면, 이진수로 변환한 N의 1의 갯수와 K가 같거나 작아야 한다.
// 4. 1의 카운트가 K보다 크다가 작아지는 시점은 2^N의 값이 된 거라, 1의 갯수가 한개라도 물병을 K개 만들어 낼 수 있다.
// 5. 물병을 얼마든지 새로 살 수 있어서 못 만드는 경우(-1)는 없다.

public class BJ1052_물병 {

	static int N, K, res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		res = 0;
		
		while(true) {
			int cnt = 0;
			char[] binary = Integer.toBinaryString(N).toCharArray();
			
			for(int i=0; i<binary.length; i++) {
				if(binary[i] == '1') cnt++;
			}
			
			if(cnt <= K) {
				System.out.println(res);
				break;
			}
			
			N++;
			res++;
		}
	}

}
