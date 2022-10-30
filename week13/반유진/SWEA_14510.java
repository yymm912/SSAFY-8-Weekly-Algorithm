// SWEA 14510번 나무 높이 

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_14510 {

	static int T, N;
	static int[] arr, diff;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int max = 0;
			arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
			}

			diff = new int[N];
			int equal = 0;
			for (int i = 0; i < N; i++) {
				diff[i] = max - arr[i];
				if (max == arr[i])
					equal++;
			}
			if (equal == N) {
				System.out.println("#" + t + " " + 0);
				continue;
			}

			Arrays.sort(diff);
			// System.out.println(Arrays.toString(diff));

			int day = 1;
			outer: while (true) {
				if(check_success()) {
					System.out.println("여기");
					break;
				}

				int zeroCnt = 0;

				for (int i = 0; i < N; i++) {
					
					if (diff[i] == 0)
						zeroCnt++;

					if (diff[i] != 0) {
						if (day % 2 == 0 && diff[i] != 1) {
							diff[i] -= 2;
							if (diff[i] == 0) {
								zeroCnt++;
								if (zeroCnt == N) {
									break outer;
								}
							}
							//System.out.println(day + " " + Arrays.toString(diff));
							
							if(check_success()) {
								break outer;
							}
							
							day++;
							continue outer;
						} else if (day % 2 == 0 && diff[i] == 1) {
							continue;
						} else if (day % 2 == 1) {
							if (check_two()) {
								day++;
								continue outer;
							}

							diff[i] -= 1;
							if (diff[i] == 0) {
								zeroCnt++;
								if (zeroCnt == N) {
									break outer;
								}
							}
							
							if(check_success()) {
								break outer;
							}
							
							//System.out.println(day + " " + Arrays.toString(diff));
							day++;
							continue outer;
						}
					}
				}
				if(check_success()) {
					break;
				}
				
				day++;
			}

			System.out.println("#" + t + " " + day);
		}
	}

	static boolean check_success() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (diff[i] == 0)
				cnt++;
		}
		if (cnt == N) {
			return true;
		}
		return false;
	}

	static boolean check_two() {
		int cnt_zero = 0;
		int cnt_two = 0;
		for (int i = 0; i < N; i++) {
			if (diff[i] == 2)
				cnt_two++;
			else if (diff[i] == 0)
				cnt_zero++;
		}
		if (cnt_two == 1 && cnt_zero == N - 1) {
			return true;
		}
		return false;
	}

}
