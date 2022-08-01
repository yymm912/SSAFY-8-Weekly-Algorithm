package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [입력]
 * N (스위치의 갯수)
 * arr (각 스위치의 상태 배열) 켜져있으면(1) 꺼져있으면(0)
 * K (학생 수)
 * 학생의 성별 | 학생이 받은 수 (M개의 줄) 남학생(1) 여학생(2)
 * */

//8
//0 1 0 1 0 0 0 1
//2
//1 3
//2 3
//-> 1 0 0 0 1 1 0 1
//
//4
//1 0 1 1
//3
//1 2
//2 2
//2 3
//-> 0 1 1 1
//
//4
//1 0 1 1
//1
//2 2
// -> 0 1 0 1
//
//4
//1 0 1 1
//1
//1 2
//-> 1 1 1 0


// 구현, 시뮬레이션 
public class BJ1244_스위치켜고끄기 {
	static int N, K;
	static int[] arr;
	static int[][] stus;


	// 스위치 on/off 함수
	public static int doSwitch(int idx) {
		return arr[idx] == 1 ? 0 : 1;
	}


	// 스위치 대칭 찾는 함수
	public static void isSwitch(int idx) {
		for (int i = 1; i <= N; i++) {
			if (idx - i < 1 || idx + i >= N + 1) continue;

			int prev = arr[idx - i];
			int next = arr[idx + i];
			if (prev == next) {
				arr[idx - i] = doSwitch(idx - i);
				arr[idx + i] = doSwitch(idx + i);
			} else break; // 여기 조심. break 안하면 계속 찾는다.

		}

	}


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 스위치 갯수, 스위치 배열
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];

		// 스위치 배열 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 학생 수, 학생 배열
		K = Integer.parseInt(br.readLine());
		stus = new int[K][2];

		// 학생 배열 입력
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			stus[i][0] = Integer.parseInt(st.nextToken());
			stus[i][1] = Integer.parseInt(st.nextToken());
		}

		// 탐색
		for (int i = 0; i < K; i++) {
			int gender = stus[i][0]; // 학생 성별
			int no = stus[i][1]; // 받은 스위치 번호

			// 남학생 이라면
			if (gender == 1) {
				for (int j = no; j <= N; j += no)
					arr[j] = doSwitch(j);
			} else if (gender == 2) { // 여학생 이라면
				arr[no] = doSwitch(no);
				isSwitch(no);
			}

		}

		// 20개마다 출력해야 한다.
		for (int i = 1; i <= N; i++) {
			System.out.print(arr[i] + " ");
			if (i % 20 == 0) System.out.println();
		}

	} // end main
} // end class
