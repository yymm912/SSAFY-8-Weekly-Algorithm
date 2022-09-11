package study;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질 {
	public static void main(String[] args) {
		int N, K, time;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 수빈이 위치
		K = sc.nextInt(); // 동생 위치
		boolean[] visit = new boolean[100001];
		Queue<Integer> queue = new ArrayDeque<>();
		
		if (N == K) {
			System.out.println(0);
			return;
		}

		time = 0;
		queue.offer(N);
		int len = queue.size();
		
		while (true) {
			time++;
			len = queue.size();
			
			for(int i = 0; i < len;i++) { // 여기서 len을 따로 선언하지 않고 queue.size()를 넣고 돌렸더니 offer될 때마다 유동적으로 변해서
										 // 값이 요동쳤다... 첫 bfs 큐의 크기를 지정하고 고정시킨 다음 그 횟수만큼만 돌려야했다..
				int num = queue.poll();
				visit[num] = true;
				if (num - 1 == K || num + 1 == K || num * 2 == K) { // 해당 위치라면 누적된 시간을 출력하고 종료.
					System.out.println(time);
					return;
				}
				if (num - 1 >= 0 && !visit[num - 1]) { // 걸어갈 수 있는 위치로의 경우의 분기점을 추가하고 그 지점을 방문 표시.
					visit[num - 1] = true;
					queue.offer(num - 1);
				}
				if (num + 1 <= 100000 && !visit[num + 1]) {
					visit[num + 1] = true;
					queue.offer(num + 1);
				}
				if (num * 2 <= 100000 && !visit[num * 2]) { // 걷는 위치와 마찬가지로 순간이동을 할 수 있는 위치도 방문표시 후 이동(큐 추가)
					visit[num * 2] = true;
					queue.offer(num * 2);
				}
			}
		}

	}

}
