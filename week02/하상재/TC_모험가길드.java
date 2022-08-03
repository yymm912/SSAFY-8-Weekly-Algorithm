package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 배열 크기 변수
		int numOfGroup=0,answer=0; // maxFear = 그룹원 중 최대 공포치, numOfGroup = 그룹 수, answer = 답
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}
		
		Arrays.sort(arr); // Arrays.sort로 정렬해줌 
		
		for(int maxFear : arr) {
			numOfGroup++; // 그룹원 수를 증가 시킴
			if(numOfGroup>=maxFear) { // 만약 그룹원 수가 빼왔던 공포치 보다 크거나 같다면 그룹이 성립된거임
				answer++; // 정답 수 하나 늘려줌
				numOfGroup = 0; // 그룹원을 0명으로 초기화 해줌
			}
		}
		
		System.out.println(answer);
		
	}

}
