package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_10775_공항 {
	static int G, P;
	static int[] gate;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		gate = new int[G + 1]; // 0 dummy;

		MakeSet();

		// 비행기를 최대한 태우려면 1~입렵숫자 게이트 중 하나인 조건에서 입력숫자와 가장 가까운 곳에 차곡차곡 넣어야함
		// 시간 1초를 고려해보았을 때 완전탐색은 오래걸림
		// Find 로, 자신보다 앞서 최선의 선택인 자리를 부모로 가지고 있으면 시간초과 해결

		int ans = 0;

		for (int i = 0; i < P; i++) {
			int input = Integer.parseInt(br.readLine());

			int parent = Find(input);

			if (parent == 0)
				break;
			gate[parent] = Find(parent - 1);
			ans++;
		}

		System.out.println(ans);
	}

	static void MakeSet() {
		for (int i = 1; i <= G; i++) {
			gate[i] = i;
		}
	}
	
	static int Find(int n) {
		if (gate[n] == n)
			return n;
		return gate[n] = Find(gate[n]);
	}
}
