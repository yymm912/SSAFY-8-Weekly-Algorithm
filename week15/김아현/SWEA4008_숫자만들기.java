import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4008_숫자만들기 {

	static int T, N, min, max;
	static int[] nums;
	static int[] ops;
	static int[] tgt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			tgt = new int[N-1];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			ops = new int[4];			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			} // 입력 끝
			
			solve(0);
			
			System.out.println("#" + t + " " + (max - min));
		}
	}

	static void solve(int cnt) {
		if(cnt == N-1) {
			int res = calc();
			min = Math.min(min, res);
			max = Math.max(max, res);
			return;
		}
		
		// 순열 -> 숫자 모두 다 쓰기
		for (int i = 0; i < 4; i++) {
			if(ops[i] == 0) continue;
			
			tgt[cnt] = i;
			ops[i]--;
			solve(cnt + 1);
			ops[i]++;
		}
	}
	
	static int calc() {
		int res = nums[0];
		
		for (int i = 0; i < N-1; i++) {
			
			switch(tgt[i]) {
			case 0: res += nums[i+1]; break;
			case 1: res -= nums[i+1]; break;
			case 2: res *= nums[i+1]; break;
			case 3: res /= nums[i+1]; break;
			}
		}
		
		return res;
	}
}
