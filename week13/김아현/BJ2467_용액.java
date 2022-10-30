package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2467_용액 {

	static int N;
	static long[] num;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		num = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Long.parseLong(st.nextToken());
		}
		
		int left = 0;
		int right = N-1;
		long LEFT=0, RIGHT=0;
		long min = Long.MAX_VALUE;
		
		while (left < right) { // 두 개의 용액을 선택해야 함
			long sum = num[left] + num[right];
			
			if(Math.abs(sum) <= min) {
				min = Math.abs(sum);
				LEFT = num[left];
				RIGHT = num[right];
			}
			
			// 두 수를 합한 값이 0보다 크면 오른쪽 인덱스 한칸 아래로
			//                 작으면 왼쪽 인덱스 한칸 위로
			if(sum >= 0) right--;
			else left++;
		}
		
		System.out.println(LEFT + " " + RIGHT);
	}
}
