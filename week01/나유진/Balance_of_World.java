package al.jul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Balance_of_World {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			boolean flag = true; // 균형인지 아닌지 확인
			Stack<Character> stack = new Stack<>(); // 괄호를 담을 stack
			String s = br.readLine();

			// 받은 문자열이 .이면 종료
			if (s.equals("."))
				break;

			for (int i = 0; i < s.length(); i++) {
				// i번쨰 문자
				char c = s.charAt(i);

				// 열린 괄호라면 push
				if (c == '(' || c == '[') {
					stack.push(c);
				} else if (c == ')' || c == ']') { // 닫힌 괄호라면 체크

					// stack이 비었다면 균형잡히지 않은 경우 표시하고 나가기
					if (stack.empty()) {
						flag = false;
						break;
					}

					// stack의 맨 위 값 가져와서 균형인지 확인
					char top = stack.peek();
					stack.pop();

					if ((c == ')' && top == '(') || (c == ']' && top == '[')) {
						continue; // 균형인 경우
					} else {
						// 균형 아닌 경우 표시하고 나가기
						flag = false;
						break;
					}

				} else
					continue;
			}
			
			// 균형 X 판별이 낫거나, 스택에 여전히 문자가 남아있으면
			// stack.empty() 비어있으면 --> true 남아있으면 --> false
			if (flag == false || !(stack.empty()))
				System.out.println("no");
			else {
				System.out.println("yes");
			}
				
		}
	}
}
