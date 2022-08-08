package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//()(((()())(())()))(())
//-> 17
//(((()(()()))(())()))(()())
//-> 24


public class BJ10799_쇠막대기 {
	static int ans;
	static Deque<Character> stack = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char prev = '(';
		for (char ch : br.readLine().toCharArray()) {
			// 여는 괄호 (
			if (ch == '(') stack.push(ch);
			// 닫는 괄호 )
			else {
				stack.pop(); // 여는 괄호 빼기 (짝 맞추기)

				if (prev == '(') ans += stack.size(); // 레이저인 부분 ()
				else ans++; // ))
			}

			prev = ch;
		}

		System.out.println(ans);

	} // end main
}
