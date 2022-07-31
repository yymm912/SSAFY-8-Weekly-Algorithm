
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Balanced_BJ_4949 {
	
	public static String checkString(String s) {
		// 입력받은 String을 검사하는 메서드
		
		// 괄호 기호를 담을 임시 캐릭터 배열 생성, 배열 인덱스 선언-초기화
		char[] c = new char[s.length()];
		int cnt = 0;
		
		// 입력받은 String을 한글자씩 검토
		for (int i = 0; i < s.length(); i++) {
			
			// i번째 문자가 여는 괄호이면 임시 배열에 문자를 담고 인덱스를 증가.
			if(s.charAt(i) == '(' || s.charAt(i) == '[') c[cnt++] = s.charAt(i);
			
			// i번쨰 문자가 닫는 괄호이나, 여는 괄호없이 시작하거나 임시 배열의 이전 인덱스가 짝이 안맞으면 "no"문자열 리턴
			else if(s.charAt(i) == ')')
			{
				if(cnt == 0)
					return "no";
				else if(c[cnt-1] != '(' ) return "no";
				else cnt--;
			}
			
			else if(s.charAt(i) == ']')
			{
				if(cnt == 0)
					return "no";
				else if(c[cnt-1] != '[' ) return "no";
				else cnt--;
			}
		}
		
		// 반복문을 마치고 괄호 짝이 맞아 count가 0이면 yes 출력
		if(cnt == 0) return "yes";
		else return "no";
			
		}
		

	public static void main(String[] args) throws IOException {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String str;
	

		while(true)
		{
			str = br.readLine();
			
			if(str.equals(".")) break;
			
			System.out.println(checkString(str));
			
		}	
		
	}
}
	
