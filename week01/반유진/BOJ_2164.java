
// 2164번 카드 2

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Queue<Integer> que = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++) {
			que.add(i);
		}

		while (que.size() > 1) { // 카드가 한장만 남을때까지 반복
			que.poll(); // 제일 위의 카드 버리기
			int first = que.poll(); // 제일 위의 카드를
			que.add(first); // 제일 아래로 옮기기
		}
		System.out.println(que.poll());
	}

}
