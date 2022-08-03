
// 4949번 균형잡힌 세상 

import java.util.Scanner;
import java.util.Stack;

public class BOJ_4949 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String input;

		while (true) {
			input = sc.nextLine();
			if (input.compareTo(".") == 0) {	// . 이 들어오면 종료 
				break;
			}

			Stack<Character> stack = new Stack<>();

			for (int i = 0; i < input.length(); i++) {
				char iChar = input.charAt(i);
				if (iChar == '(' || iChar == '[') {	// 괄호가 들어오면 push
					stack.push(iChar);
				} else if (iChar == ')') {	
					// 닫는 소괄호이며, 현재 스택이 비어있거나 여는 소괄호가 없으면 
					if (stack.empty() || stack.peek() != '(') {	
						stack.push(iChar);
						break;
					} else {
						// 맞으면 스택의 여는 괄호를 pop 해준다. 
						stack.pop();
					}
				} else if (iChar == ']') {
					// 닫는 대괄호이며, 현재 스택이 비어있거나 여는 대괄호가 없으면 
					if (stack.empty() || stack.peek() != '[') {
						stack.push(iChar);
						break;
					} else {
						// 맞으면 스택의 여는 괄호를 pop 해준다. 
						stack.pop();
					}
				}
			}

			if (stack.empty()) {
				// 스택이 비어있으면 (모든 괄호가 짝을 이루면) yes
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}

}
