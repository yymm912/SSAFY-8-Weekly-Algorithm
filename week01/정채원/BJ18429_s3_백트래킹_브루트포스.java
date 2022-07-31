import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N, K;
	static int[] kit;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// input 
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		kit = new int[N];
		visit = new boolean[N];
		for(int i=0; i<N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		
		//solution
		int result = 0;
		for(int i=0; i<N; i++) {
			visit[i] = true;
			result += sol(i, 500, 1);
			visit[i] = false;
		}
		System.out.println(result);		
	}
	public static int sol(int idx, int curW, int visitCnt) {
		curW = curW - K + kit[idx];
		if(visitCnt == N) {
			if (curW >= 500) return 1;
			else return 0;
		}
		if (curW < 500) return 0;
		
		int cnt=0;
		visit[idx] = true;
		for(int i=0; i<N; i++) {
			if(visit[i]) continue;
			cnt += sol(i, curW, visitCnt+1);
		}
		visit[idx] = false;
		return cnt;
	}
}