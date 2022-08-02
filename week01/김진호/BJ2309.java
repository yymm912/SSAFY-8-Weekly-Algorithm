import java.util.Arrays;
import java.util.Scanner;

/**
  * * 1. 난쟁이의 키의 총 합을 구함
  * * 2. 총 합에서 100을 뺌
  * * 3. 난쟁이 2명의 키의 합이 총 합에서 100을 뺸 것과 같으면 큰 수로 바꿔 정렬 시 제일 뒷부분으로 보냄
  * * 4. 난쟁이들을 정렬하면 2명의 난쟁이는 큰수로 뒤에 보내버려서, 자연스럽게 7명의 난쟁이만 출력이 가능함.

 */

public class BJ2309 {

	static int arr[] = new int[9];
	static int sum = 0;

	static void sol() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == j)
					continue;

				if (arr[i] + arr[j] == sum) {
					arr[i] = 9999;
					arr[j] = 9999;
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
			sc.nextLine();
		}

        // 배열의 합 
		sum = Arrays.stream(arr).sum();

        // 총 키의 합에서 100을 뺌
		sum -= 100;

		sol();
		
		Arrays.sort(arr);

		for (int i = 0; i < 7; i++)
			System.out.println(arr[i]);
	}

}