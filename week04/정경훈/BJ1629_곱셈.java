package baekjoon.곱셈_1629;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int A, B, C;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(pow(A,B)%C);
	}

	static long pow(int a, int b) {
		if(b == 0) return 1; // 0승은 1
		if(b == 1) return a; // 1승은 그 자체
		if(b%2 == 1) return pow(a,b-1)*a; // 홀수번 곱할시 짝수번 곱한거에 자기자신 곱하기
		else { // 짝수번 곱할 시
			long half = pow(a,b/2); // 짝수를 절반으로 나눈 값 구하기
			half = half % C; // 여기서부턴 모듈러의 법칙
			return (half * half)%C;
		}
	}
}
