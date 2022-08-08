package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1527_금민수2 {
	
	static int result = 0;
	static int A,B;
	
	public static void find47(int num){
		
		// 재귀 종료조건
		if(num > B) return; // B를 초과하면 재귀 종료
		if(num >= A && num <= B) result++; // A와 B 사이 금민수 개수 출력
		
		find47(num * 10 + 4); // 4 => 44, 47 => 444, 447, 474, 477 => 4444...
		find47(num * 10 + 7); // 7 => 74, 77 => 744, 747, 774, 777 => 7777...
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		find47(0);
		System.out.println(result);
			

}
}
