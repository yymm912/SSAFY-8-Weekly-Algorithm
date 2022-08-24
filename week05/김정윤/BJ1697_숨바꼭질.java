package BJ;

import java.io.*;
import java.util.*;

public class BJ_숨바꼭질_1697 {
	static int N, K;
	static int[] road = new int[100001]; // 이동가능 범위
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		// N - 수빈이 위치, K - 동생 위치
		N = sc.nextInt();
		K = sc.nextInt();
		
		System.out.println(bfs(N));
		
		sc.close();
	}
	static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		road[start] = 0; // 0초로 시작. road의 각 지점에 방문까지 걸린 시간 저장. 
		
		while (!q.isEmpty()) {
			int current = q.poll(); // 수빈이의 현재 위치
			
			// 수빈이가 동생을 잡은 경우
			if (current == K)
				return road[current]; // 해당 위치에서의 시간 리턴
			
			// 한칸 뒤로
			if (current-1 >= 0 && road[current-1] == 0) { // 해당 지점에 방문하지 않은 경우
				road[current-1] = road[current]+1; // 한칸 뒤 위치에 현재 시간 + 1
				q.add(current-1);
			}
			// 한칸 앞으로
			if (current+1 <= 100000 && road[current+1] == 0) {
				road[current+1] = road[current]+1; // 한칸 앞 위치에 현재 시간 + 1
				q.add(current+1);
			}
			// 순간이동
			if (current*2 <= 100000 && road[current*2] == 0) {
				road[current*2] = road[current]+1; // 순간이동 위치에 현재 시간 + 1
				q.add(current*2);
			}
		}
		return -1;
	}

}
