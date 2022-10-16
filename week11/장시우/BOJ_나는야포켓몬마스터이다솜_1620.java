package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_나는야포켓몬마스터이다솜_1620 {
	
	static int N, M;
	static String[] poketmon;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		poketmon = new String[N];
		
		for (int i = 0; i < N; i++) {
			poketmon[i] = br.readLine();
		}
		
		for (int i = 0; i < M; i++) {
			String question = br.readLine();
			if (question.matches("-?\\d+")) {
				System.out.println(poketmon[Integer.parseInt(question) - 1]);
			} else {
				for (int j = 0; j < N; j++) {
					if (poketmon[j].equals(question)) {
						System.out.println(j + 1);
						break;
					}
				}
			}
		}
	}
}
