package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * [입력]
 * arr (난쟁이들의 키 배열, 9개)
 * return 오름차순으로 출력 
 * 
 * [주의]
 * - 난쟁이들의 키의 합은 100 
 * */

//20
//7
//23
//19
//10
//15
//25
//8
//13
//-> 
//7
//8
//10
//13
//19
//20
//23


// 그리디, 브루트 포스 
public class BJ2309_일곱난쟁이 {
	static int[] arr = new int[9]; // 난쟁이 키 배열
	static int n1, n2;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int sum = 0;

		// 배열 입력
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}

		// 배열 정렬
		Arrays.sort(arr);

		// 빼야하는 두 명의 난쟁이를 골라낸다.
		// 두 명을 빼서 만약 100 이라면 해당 idx 를 저장
		for (int i = 0; i < 9; i++) {
			sum -= arr[i];
			for (int j = i + 1; j < 9; j++) {
				sum -= arr[j];
				if (sum == 100) {
					n1 = i;
					n2 = j;
					break;
				}

				sum += arr[j];
			}

			sum += arr[i];
		}

		for (int i = 0; i < 9; i++) {
			if (i == n1 || i == n2) continue;
			System.out.println(arr[i]);
		}

	} // end main
} // end class
