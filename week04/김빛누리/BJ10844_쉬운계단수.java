package week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 안쉬움!!!!!!!!!!!!!!
 * 
 * 메모리:	14188 KB
 * 시간:		124 ms
 * 
 * 풀이시간: 3시간
 * 참고: x
 * 
 * <풀이방식>
 * - 길이가 1인 계단수부터 시작, 0~9로 끝나는 숫자 개수 기록
 * - 길이가 2일 경우
 * - 숫자가 0으로 끝나려면  바로 앞의 수가 1이어야만 함 => dst[0] = src[1] (9인 경우도 src[8])
 * - 숫자가 0, 9 외의 다른 숫자로 끝나는 경우는 -1, +1 숫자가 바로 앞에 오면 된다! ( 3인 경우 2 또는 4)
 * - (src[i-1] + src[i+1]) % 1_000_000_000;
 * 
 * - 현재 dst가 다음 src가 되므로 배열 swap
 * - 마지막까지 구하면 src배열이 결과임
 * - src배열의 총합을 잘 나눠서 구해준다~~~~
 *  
 * 
 * <삽질목록>
 * - 복잡하고 이상한? 규칙을 찾으려고 노력함, 그리고 못찾음 
 * 		(수 뒤에 숫자를 한 종류만 붙일 수 있는 수(끝이 0 또는 9) 의 갯수...무언가 순열이지 않을까 하는 생각함..)
 * */
public class BJ10844_쉬운계단수 {
	static int[] src =  {0, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	static int[] dst;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		dst = new int[10];
		for(int i = 2; i <= N; i++) {
			setDst();
			switchSrcDst();
		}
		if(N == 1) System.out.println(9);
		else System.out.println(sumSrc());
		
		
	}
	
	static void setDst() {
		for(int i = 0; i <= 9; i++) {
			if(i == 0) {
				dst[0] = src[1];
			}else if(i==9) {
				dst[9] = src[8];
			}else {
				dst[i] = (src[i-1] + src[i+1]) % 1_000_000_000;
			}
		}
	}
	static void switchSrcDst() {
		int[] tmp = src;
		src = dst;
		dst = tmp;
	}
	static int sumSrc() {
		int sum = src[0];
		for(int i = 1; i <=9; i++) {
			sum = (sum + src[i]) % 1_000_000_000;
		}
		return sum;
	}
}
