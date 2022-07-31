package BOJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Main_4949 {
	
	static void solve(String s) {
		//스택 생성
		Stack<Character> stack = new Stack<>();
		
		//state: 오류 확인->왜? 중간에 stack empty인 상태로 오류 날 수 있으니깐
		boolean state=true;
		for(int i = 0; i < s.length(); i++) {
			char c= s.charAt(i);
			
			if(c == '(' || c == '[') {
				stack.push(c);//열린 괄호면 무조건 stack으로 넣음
			}
			
			else if(c == ')') {
				if(stack.empty() || stack.peek() != '(') {//짝이 안 맞거나 비어있으면 오류
					state=false;//오류 확인
					break;//굳이 더 돌 필요가 없음
				}
				else stack.pop();//아니면 빼줌
			}
			
			else if(c == ']') {
				if(stack.empty() || stack.peek() != '[') {//짝이 안 맞거나 비어있으면 오류
					state=false;//오류 확인
					break;//굳이 더 돌 필요가 없음
				
				}
				else stack.pop();//아니면 빼줌
			} 
		}
		
		if(stack.empty()&&state) {//state가 true여도 empty가 아니면 오류, empty여도 state==false며 오류
			System.out.println("yes");
		}
		else {
			System.out.println("no");
		}
	} 
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		while(true) {			
			s = br.readLine();//한 줄 단위로 조건 충족할 때까지 계속 input
			if(s.equals("."))break;//문자열이 .이면 break
			solve(s);//문자열 단위로 위의 메소드 호출
		}
	}
}