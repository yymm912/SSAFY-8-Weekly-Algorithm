package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ16637괄호추가하기 {

	static int N;
	static char oper[];
	static int num[];
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		// 0 ~ 9
		// 짝수번째는 모두 숫자
		// 홀수번째는 모두 연산자
		num = new int[N/2 + 1];
		oper = new char[N/2];			
	
		String line = br.readLine();
		int numIdx = 0;
		int operIdx = 0;
		for (int i = 0; i < N; i++) {
			if(i%2 == 0) num[numIdx++] = line.charAt(i) - '0';
			else if(i%2 == 1) oper[operIdx++] = line.charAt(i);
		}

		dfs(num[0], 0); // 합, 계산하는 인덱스 위치 
		
		System.out.println(ans);
	}
	
	static void dfs(int sum, int idx) {
		
		if(idx == oper.length ) {
			ans = Math.max(ans, sum);
			return;
		}
		
		// 괄호 없을 때  
		int sum1 = 0;
		if( oper[idx] == '+' ) sum1 = sum + num[idx+1];
		else if(oper[idx] == '-') sum1 = sum - num[idx+1];
		else if(oper[idx] == '*') sum1 = sum * num[idx+1];
		dfs(sum1, idx + 1);
		
		
		// 괄호 있을 때 
		if( idx + 1 == oper.length )return; // 범위 벗어나면 계산하지 않음 
		
		// 오른쪽 값 연산 
		int temp = 0;
		if( oper[idx+1] == '+' ) temp = num[idx+1] + num[idx+2];
		else if(oper[idx+1] == '-') temp = num[idx+1] - num[idx+2];
		else if(oper[idx+1] == '*') temp = num[idx+1] * num[idx+2];
			
		// 위 결과 값(temp)에 현재 sum을 더해야함 
		int sum2 = 0;
		if( oper[idx] == '+' ) sum2 =  sum + temp;
		else if(oper[idx] == '-') sum2 = sum - temp;
		else if(oper[idx] == '*') sum2 = sum * temp;
		
		dfs(sum2, idx + 2);
	
		
		
	}

}
