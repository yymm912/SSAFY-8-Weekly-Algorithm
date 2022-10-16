import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12015_LIS2 {
	static int N;
	static int[] arr;
	static int answer;
	static int LIS[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		LIS = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		/*
		 * for (int i = 0; i < N; i++) { LIS[i] = 1;
		 * 
		 * for (int j = 0; j < i; j++) { if (arr[j] < arr[i] && LIS[i] <= LIS[j]) {
		 * LIS[i] = LIS[j] + 1; } } }
		 * 
		 * Arrays.sort(LIS); System.out.println(LIS[LIS.length - 1]);
		 */

		LIS[0] = arr[0];
		int size = 1;

		for (int i = 1; i < N; i++) {
			int key = arr[i];

			if (LIS[size - 1] < key) {
				size++;
				LIS[size - 1] = key;
			} else {
				int lo = 0;
				int hi = size;

				while (lo < hi) {
					int mid = (lo + hi) >>> 1;

					if (LIS[mid] < key) {
						lo = mid + 1;
					} else {
						hi = mid;
					}

				}
				/*
				 * lo가 LIS에서 대치 될 원소의 위치가 될 것이고 해당 위치에 key값으로 원소를 바꿔준다.
				 */

				LIS[lo] = key;

			}

		}
		System.out.println(size);

	}

}
