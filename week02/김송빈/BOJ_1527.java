package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_1527 {

	static long a, b;
	static int cnt = 0;

	static void dfs(long n) {

		if (n > b)//안 찾아도 됨
			return;

		else if (n <= b && n >= a) {
			cnt++;//사이에 몇개 있는지 
		}
		//재귀 호출로 값 찾아줌
		dfs(n * 10 + 4);//4 44 74.....
		dfs(n * 10 + 7);//7 47 77 ....
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		// 10*(4or7)+(4or7)

		dfs(0);//4 or 7 만들어줌 

		System.out.println(cnt);
	}
}
