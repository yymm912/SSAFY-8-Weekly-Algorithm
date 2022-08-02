package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [입력] 
 * N(수학여행에 참가하는 학생 수) K (한 방에 배정할 수 있는 최대 인원 수)
 * 학생의 성별 (0: 여자, 1:남자), 학생의 학년 
 * return 필요한 방의 최소 갯수
 * 
 * [주의]
 * - 1학년부터 6학년 
 * - 남 - 남, 여 - 여 
 * - 한방에 같은 학년 
 * - 한 방에 한명도 가능 
 * */

//16 2
//1 1
//0 1
//1 1
//0 2
//1 2
//0 2
//0 3
//1 3
//1 4
//1 3
//1 3
//0 6
//1 5
//0 5
//1 5
//1 6
//-> 12

//4 2
//1 1
//1 1
//1 1
//1 1
//-> 2

//6 4
//1 1
//1 1
//1 1
//0 1
//0 1
//1 1
//-> 2


public class BJ13300_방배정 {
	static int N, K, ans;
	static int[][] map = new int[2][7];


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());

			map[gender][year]++;
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= 6; j++) {
				int temp = map[i][j];

				if (temp != 0) {
					// 만약 딱 나누어 떨어지면 밑에 코드 실행안하고 넘어간다.
					if (temp % K == 0) {
						ans += (temp / K);
						continue;
					} else { // 그게 아니라면 추가적으로 더하고
						ans += (temp / K);
					}

					ans++;
				}

			}

			// System.out.println(Arrays.toString(map[i]));

		}

		System.out.println(ans);

	} // end main
}
