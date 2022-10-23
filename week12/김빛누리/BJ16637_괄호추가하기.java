package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 풀이시간: 1시간반~2시간
 * 참고: Integer.toString(최종 코드에서는 사용X)
 * 
 * <풀이>
 * - 수식의 길이가 5 이상일 경우
 * - dfs로 선택, 비선택 반복
 * - 연산자 기준으로 양 옆 묶음
 * - 수식은 List<String> 으로 표현
 * 
 * <삽질목록>
 * - calcAllExpression에 길이가 3 이하인 수식 리스트가 들어가면 (IndexOutOfBounds) 뜨는데 예외처리 안해줬음(저번에도 못함)
 * - list clone 안하고 재사용하려고 함
 * */

public class BJ16637_괄호추가하기 {
	static int N, max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(input.substring(i, i+1));
		}
		// 예외적인 경우 처리
		if(N==1) System.out.println(list.get(0));
		else if(N==3) System.out.println(calcExpression(list.get(1).charAt(0),Integer.parseInt(list.get(0)), Integer.parseInt(list.get(2))));
		else {	// 일반적인 경우 ( N >= 5)
			dfs(N-2,list);
			System.out.println(max);
		}
	}
	// dfs로 선택, 비선택
	// idx는 연산자의 위치
	// list는 원본 또는 수정된 수식이다
	private static void dfs(int idx, ArrayList<String> list) {
		// 종료조건
		// 맨 앞 연산자는 괄호로 묶어도 묶지 않은 것과 같음
		// 시도할 이유 X
		if(idx <= 1){
			int result = calcAllExpression((ArrayList<String>)list.clone());
			max = Math.max(result, max);
			return;
		}
		
		dfs(idx-2, list);	// 비선택, 바로 이전 연산자 선택 가능하므로 idx-2
		
		int bracketResult = calcExpression(list.get(idx).charAt(0),Integer.parseInt(list.get(idx-1)), Integer.parseInt(list.get(idx+1)));
		ArrayList<String> tmpList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(i==idx-1) {
				tmpList.add(Integer.toString(bracketResult));
				continue;
			}
			if(i==idx||i==idx+1) continue;
			
			tmpList.add(list.get(i));
		}
		
		dfs(idx-4, tmpList);	// 선택, 바로 이전 연산자 선택 불가하므로 idx-4
		
	}
	// 전체 수식 (list) 를 받아 계산 결과를 리턴
	private static int calcAllExpression(ArrayList<String> list) {
		// 앞에서부터 계산 시작
		// 처음 길이 3의 수식을 calcExpression으로 계산해 prev로 저장
		int prev = calcExpression(list.get(1).charAt(0),Integer.parseInt(list.get(0)), Integer.parseInt(list.get(2)));
		list.remove(0);
		list.remove(0);
		list.remove(0);
		int size = list.size();
		
		// prev(이전 계산 결과), 다음 연산자, 다음 숫자로 계산
		// 모두 계산할 때까지 반복
		while(size!=0) {
			prev = calcExpression(list.get(0).charAt(0),prev, Integer.parseInt(list.get(1)));
			list.remove(0);
			list.remove(0);
			size -= 2;
		}
		return prev;	// 최종적으로 prev에 모든 계산결과가 들어간다
	}
	// 연산자 하나, 숫자 2개를 받아 계산
	private static int calcExpression(char oper, int n1, int n2) {
		int result = 0;
		switch(oper) {
		case '+':
			result = n1+n2;
			break;
		case '-':
			result = n1-n2;
			break;
		case '*':
			result = n1*n2;
			break;
		case '/':
			result = n1/n2;
			break;
		}
		return result;
	}

}
