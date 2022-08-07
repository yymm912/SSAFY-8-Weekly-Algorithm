package week02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 1.3346481323242188 MB
 * 0.0012859 s
 * 
 * 현재 코드에서 BufferedReader 사용시 
 * 3.779E-4 s
*/
public class TC_모험가길드 {

	public static int N;
	public static ArrayList<Integer> arrList = new ArrayList<>();
	 
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		// 시간 측정
		long start = System.nanoTime();
		
		N = sc.nextInt();
	
		for(int i = 0; i < N; i++) {
			arrList.add(sc.nextInt());
		}
		
		// 공포도 X인 모험가는 반드시 X명 이상으로 구성한 모험가 그룹..
		// 여행 떠날 수 있는 그룹 수의 최댓값
		
		// sort
		Collections.sort(arrList);
		
		int cnt = 0;	// 멤버 count
		int gNum = 0;	// 그룹 수
		
		for(int i = 0; i < N; i++) {
			cnt++;
			if(cnt == arrList.get(i)) {	// 현재까지 멤버수랑 현재 모험가의 공포도가 같으면 그룹 추가, 멤버수 초기화
				gNum++;
				cnt = 0;
			}
		}
		
		System.out.println(cnt);
		
		
		/* 메모리 및 시간 확인 */
//		long end = System.nanoTime();
//		Runtime.getRuntime().gc();
//		long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//		System.out.println((double)usedMemory / 1024 / 1024 + " MB");
//		System.out.println((double)(end-start) / 1_000_000_000 + " s");
	}

}
