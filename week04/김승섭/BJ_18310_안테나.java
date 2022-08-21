package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_18310_안테나 { 
          /*
          * 기존 방식은 안테나 배치를 정렬한 뒤 탐색을 했으나 테스트 케이스를 통과하지 못했다.
          * 또한 N이 1일 때의 경우와 짝수일 때 중앙값, 홀수일 때 중앙값을 나눠야했다.
					* 정렬된 배열의 중앙값의 성질을 이용해 풀이
          */
	static int N;
	static int[] ant;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ant = new int[N];
		for (int i = 0; i < N; i++) { // 안테나 선언
			ant[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(ant); // 정렬
		
		// 안테나의 개수에 따라 중앙값을 택함. 홀수 ==> 소수점 제거 정수값(중앙값보다 앞) 짝수 ==> 중앙값 그대로
		if(N == 1) System.out.println(ant[0]);
		else if(N % 2 == 0) System.out.println(ant[N/2-1]); // 배열의 인덱스는 0부터 시작하므로 -1을 한 연산 적용
		else System.out.println(ant[N/2]);
		
	}



}
