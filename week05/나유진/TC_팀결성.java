package TC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_팀결성 {
	static int n, m; // 0~n의 원소, m개의 명령어
	static int[] arr; // 원소의 부모 저장. idx = 원소. arr[idx] = 원소의 부모.
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];

		// arr 초기화. 처음에 원소의 부모는 자기자신임.
		for (int i = 0; i <= n; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char op = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (op == '1') { // 1이면 같은 집합인가 확인
				if (FindSet(a) == FindSet(b))
					sb.append("YES").append('\n');
				else
					sb.append("NO").append('\n');

			} else { // 0 이면 합집합 만들기(Union)
				if (a < b)
					Union(a, b);
				else
					Union(b, a);
			}
		}

		System.out.println(sb);
	}

	static int FindSet(int x) { // 자신이 포함된 집합의 대표(root)반납
		if (arr[x] == x)
			return x;
		return arr[x] = FindSet(arr[x]); // path compression
	}

	static void Union(int a, int b) {
		int ap = FindSet(a); // a parents , b parents
		int bp = FindSet(b);

		arr[ap] = bp; // a집합을 b로 통일 => 집합의 대표가 b의 root가 됨.
	}
}
