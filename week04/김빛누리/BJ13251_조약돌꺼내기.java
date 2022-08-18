package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리:	14280 KB
 * 시간:		128 ms
 * 
 * 풀이시간: 30분
 * 참고: X
 * 
 * <풀이방식>
 * - factorial로 분자, 분모를 각각 계산해 나누면 오버플로가 발생하므로
 * - 분자와 분모를 바로바로 계산해서 다음 계산함
 * 
 * - 색별로 계산, 모든 색에 대해 반복한다 
 * - 색 개수 / 전체 개수 * calc(색 갯수-1,전체-1, 반복횟수-1 )
 * - 이렇게 순차적으로 곱해주며 계산한다.
 * 
 * <삽질목록>
 * - factorial에서 오버플로가 발생한 줄 모르고 색별로 분자, 분모를 따로 계산함
 * 
 * */
public class BJ13251_조약돌꺼내기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] color = new int[N];
		int total = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			color[i] = Integer.parseInt(st.nextToken());
			total += color[i];
		}
		
		int K = Integer.parseInt(br.readLine());
		
		
		double result = 0;
		for(int c : color) {	// 색 별로 계산
			if(c >= K) {
				result += calc(c, total, K);
			}
		}	
		System.out.println(result);
	}
	
	// top, bottom, 반복횟수를 줄여가며 순차적으로 계산하여 결과값을 리턴
	static double calc(int top, int bottom, int repeat) {
		if(repeat == 0) {
			return 1;
		}
		return (double)top/bottom *calc(top-1, bottom-1, repeat-1); 
		
	}
}
