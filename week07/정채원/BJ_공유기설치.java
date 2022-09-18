import java.io.*;
import java.util.*;

class Main{
	static int N, C;
	static int[] wifi;
	static boolean cond(int[] home, int dist) {
		int prev = -1_000_000_000; // 처음 집 무조건 포함해야 하니까 작게 둠 
		int homeCnt = 0;
		for(int i=0; i<home.length; i++) {
			if(home[i] - prev >= dist) {
				prev = home[i];
				homeCnt++;
			}
		}
		return homeCnt >= C ? true : false;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		wifi = new int[N];
		for(int i=0; i<N; i++) {
			wifi[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(wifi);
		
		int s = 0, e = wifi[N-1];
		while(s < e) {
			int mid = (s+e+1)>>1;
			if (cond(wifi, mid)) {
				s = mid; 
			} else {
				e = mid-1;
			}
		}
		System.out.println(s);
	}
	
}