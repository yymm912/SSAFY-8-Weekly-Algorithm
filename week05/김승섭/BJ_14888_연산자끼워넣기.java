import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14888_연산자끼워넣기 {
	static int N, max, min;
	static int[] calc = new int[4]; 
	/*
	 * calc[0] : 덧셈 
	 * calc[1] : 뺄셈 
	 * calc[2] : 곱셈 
	 * calc[3] : 나눗셈
	 */
	static int[] nums;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) { // 연산될 정수들을 저장
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < 4; i++) { // 연산자의 수를 각각의 연산자 배열에 누적합. 
			calc[i] += Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		dfs(nums[0], 1); 
		System.out.println(max);
		System.out.println(min);
		
	}
	static void dfs(int num, int cnt) {
		if(cnt == N) { // 선택된 깊이가 N에 도달하면(끝까지 도착하면) 최대값과 최소값을 갱신
			max = Math.max(max, num);
			min = Math.min(min, num);		
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(calc[i] == 0) continue; // 연산배열을 선입된 요소부터 사용하여, dfs 재귀에 들어감
			calc[i]--;
			if(i == 0) { // 각각의 연산에 맞게 연산치를 누적시킴
				dfs(num + nums[cnt], cnt+1);
			}
			else if(i == 1) {
				dfs(num - nums[cnt], cnt+1);
			}
			else if(i == 2) {
				dfs(num * nums[cnt], cnt+1);		
			}
			else {
				dfs(num / nums[cnt], cnt+1);
			}
			calc[i]++; 
		}
		
	}

}
