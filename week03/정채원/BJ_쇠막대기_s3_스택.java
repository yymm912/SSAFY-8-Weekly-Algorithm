import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{
	public static void main(String[] args) throws Exception{
		/**
		 * 1. 한 막대기 당 토막 개수 : 품고있는 레이저 + 1개
		 * 2. 각 막대기 당 품고있는 레이저 개수 구하기
		 * 		- 레이저(인접한 쌍)를 만나면 레이저 개수 count++
		 * 		- 막대기 시작부분(여는 부분)을 만나면 stack에 이전까지의 레이저 개수 저장
		 * 		- 막대기 마지막 부분(닫는 부분) 을 만나면 (현재 레이저 - 이전 레이저 개수 + 1)만큼 토막 수가 생김
		 * 3. 레이저와 막대기를 구분하는 기준
		 * 		- 닫는 괄호를 만났을 때, 바로 이전 괄호가 '(' 이면 레이저
		 * */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] iron = br.readLine().toCharArray();
		
		int razerCnt = 0;
		int splitCnt = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i=0; i<iron.length; i++) {
			char c = iron[i];
			if(c == '(') {
				stack.addFirst(razerCnt);
			}
			else if(c == ')') {
				if(iron[i-1] == '(') { // 레이저
					stack.removeFirst();
					razerCnt ++;
				} else { // 막대기
					int prevRazerCnt = stack.removeFirst();
					splitCnt += razerCnt - prevRazerCnt + 1;
				}
			}
		}
		System.out.println(splitCnt);
	}
}