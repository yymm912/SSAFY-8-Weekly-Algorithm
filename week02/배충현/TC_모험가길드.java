import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TC_모험가길드 {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(arr);
		
		int group = 0;
		int cnt = 0;	// 구성원 수
		int max = 0;	// 구성원 내 최고 공포도
		
		for(int m : arr) {
			max = m;
			cnt++;
			
			if (max == cnt) {
				group++;
				cnt = 0;
			}
		}
		
		System.out.println(group);
	}

}
