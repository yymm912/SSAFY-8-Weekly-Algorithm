import java.io.*;

// 1. 연산자 우선순위는 모두 동일 = 왼쪽에서부터 순서대로 계산
// 2. 괄호안에는 연산자가 하나만 존재
// 3. 중첩된 괄호 사용불가
// 4. 입력되는 문자 : 0 ~ 9, +, -, *
// https://www.acmicpc.net/problem/16637

public class BJ16637_괄호추가 {
	
	static int answer;
	static int[] nums;
	static char[] oper;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		oper = new char[N / 2 + 1];
		nums = new int[N / 2 + 1];
		
		char[] input = br.readLine().toCharArray();
		nums[0] = input[0];
        // 홀수번째 문자는 숫자, 짝수번째 문자는 연산자
		for (int i = 0, j = 0; i < N; i += 2, j++) nums[j] = input[i]-'0';
		for (int i = 1, j = 1; i < N; i += 2, j++) oper[j] = input[i];

        // -2^31 ~ 2^31 이기 때문에 가장 작은수로 초기화해줘야함
		answer = Integer.MIN_VALUE;
		recur(1, nums[0]);      // 첫번째 수 넣고 재귀함수호출
		
		System.out.println(answer);
		br.close();
	}
	
	static void recur(int idx, int sum) {
        // 모든 수를 한번씩 다 연산했으면 결과값 갱신
		if (idx == oper.length) {
			answer = Math.max(answer, sum);
			return;
		}
		
        // 바로 뒤에 한자리와 연산자를 계산     ex) 2 * 3 -> 2 바로 뒤에 있는 3을 곱하여 재귀함수호출
		recur(idx + 1, calc(sum, nums[idx], oper[idx]));
		if (idx + 1 >= oper.length) return;     // 배열의 범위를 벗어나지 않으면
        // 뒤에 두자리를 계산하여 앞자리와 계산 ex) 2 * (3 + 4) -> 3와 4을 먼저 더하여 앞에 2과 곱하여 재귀함수호출
		recur(idx + 2, calc(sum, calc(nums[idx], nums[idx + 1], oper[idx + 1]), oper[idx]));
	}
	
    // 숫자와 연산자를 받아서 두 수의 연산결과를 반환하는 함수
	static int calc (int a, int b, char oper) {
		int result = 0;
		switch(oper) {
			case '*': result = a * b; break;
			case '+': result = a + b; break;
			case '-': result = a - b; break;
		}
		return result;
	}
}