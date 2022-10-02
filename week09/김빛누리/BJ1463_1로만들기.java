
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 풀이시간: 30분
 * 참고: X
 * 
 * <풀이방식>
 * - bfs
 * 
 * - N번째부터 확인
 * - 3이나 2로 나눠지면 나눈 값 큐에 넣음, 1 빼고 큐에 넣음
 * - bfs로 탐색하다 1이 나오면 현재까지 탐색한 거리 리턴 
 * */
public class BJ1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		System.out.println(bfs(N));
		
	}

	static int bfs(int n) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(n);
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int curr = q.poll();
				
				if(curr == 1) return cnt;
			
				if(curr%3 == 0) q.add(curr/3);
				if(curr%2 == 0) q.add(curr/2);
				q.add(curr-1);
			}
			cnt++;
			
		}
		return cnt;
	}

}
