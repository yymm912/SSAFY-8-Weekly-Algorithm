// BOJ 14888번 연산자 끼워넣기 

package BOJ.Bruteforcing_Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_14888 {

	static int N, plus, minus, mul, div, min, max;
	static int[] arr, src, tgt;
	static boolean[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		plus = Integer.parseInt(st.nextToken());
		minus = Integer.parseInt(st.nextToken());
		mul = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());

		// 연산자들을 순열로 만들어야 하기 때문에 src 배열에 연산자 넣어주기 
		src = new int[N - 1];
		tgt = new int[N - 1];
		select = new boolean[N - 1];
		for (int i = 0; i < N - 1; i++) {
			if (plus-- > 0) {
				src[i] = 0;
				continue;
			} else if (minus-- > 0) {
				src[i] = 1;
				continue;
			} else if (mul-- > 0) {
				src[i] = 2;
				continue;
			} else if (div-- > 0) {
				src[i] = 3;
				continue;
			}
		}

		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		perm(0);

		System.out.println(max);
		System.out.println(min);
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			Calc();
			return;
		}

		for (int i = 0; i < N - 1; i++) {
			if (select[i])
				continue;

			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

	static void Calc() {
		int result = arr[0];
		for (int i = 0; i < N - 1; i++) {
			if (tgt[i] == 0) { // 더하기
				result += arr[i + 1];
			} else if (tgt[i] == 1) { // 빼기
				result -= arr[i + 1];
			} else if (tgt[i] == 2) { // 곱하기
				result *= arr[i + 1];
			} else if (tgt[i] == 3) { // 나누기
				result /= arr[i + 1];
			}
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);
	}

}
