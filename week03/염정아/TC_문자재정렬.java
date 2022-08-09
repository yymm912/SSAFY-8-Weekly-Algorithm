package problem.TC;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


// K1KA5CB7
//-> ABCKK13
//AJKDLSI412K4JSJ9D
//-> ADDIJJJKKLSS20
public class TC_문자재정렬 {
	static char[] str;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자열 입력 받기
		str = br.readLine().toCharArray();

		// 정렬한다. 숫자 -> 알파벳 순으로 정렬 된다.
		Arrays.sort(str);

		int sum = 0; // 숫자들의 합을 저장할 변수
		for (int i = 0; i < str.length; i++) {
			// 숫자인지 확인한다.
			if (Character.isDigit(str[i])) sum += str[i] - '0';
			else sb.append(str[i]);

		}

		System.out.println(sb.toString() + sum);

	}
}
