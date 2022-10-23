import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ16637_괄호추가하기 {

	static int N, res;
	static List<Integer> nums;
	static List<Character> ops;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		res = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		
		nums = new ArrayList<>();
		ops = new ArrayList<>();
		
		char[] ch = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			if(i%2 == 0) nums.add(ch[i] - '0');
			else ops.add(ch[i]);
		}
		
		dfs(0, nums.get(0));
		
		System.out.println(res);
	}

	static void dfs(int idx, int sum) {
		if(idx == ops.size()) {
			res = Math.max(res, sum);
			return;
		}
		
		// 괄호 치기 : 현재 연산자 선택
		dfs(idx + 1, calc(sum, nums.get(idx+1), ops.get(idx)));
		
		// 괄호 안치고 넘어가기 : 다음 연산자 선택
		if(idx + 2 < ops.size() + 1)
			dfs(idx + 2, calc(sum, calc(nums.get(idx+1), nums.get(idx+2), ops.get(idx+1)), ops.get(idx)));
	}
	
	static int calc(int n1, int n2, int op) {
		if(op == '+') return n1 + n2;
		else if(op == '-') return n1 - n2;
		return n1 * n2;
	}
}
