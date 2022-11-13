import java.util.*;

public class B_boj_13594 {

	static int N, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		if(N>K) System.out.println(N-K);
		else System.out.println(logic());
	}
	
	static int logic() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(N);
        
        int[] visit = new int[100_001];
		visit[N] = 1;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			if(curr == K) return visit[K]-1;
			
			if(curr-1 >= 0 && visit[curr - 1] == 0) {
                visit[curr-1] = visit[curr]+1;
                q.add(curr-1);
            }
			if(curr*2 <= 100000 && visit[curr*2] == 0) {
                visit[curr*2] = visit[curr];
                q.add(curr*2);
            }
			if(curr+1 <= 100000 && visit[curr + 1] == 0) {
                visit[curr+1] = visit[curr]+1;
                q.add(curr+1);
            }
			
		}
		return -1;
	}
}
