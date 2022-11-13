import java.io.*;
import java.util.*;

class Main{
	static int N, K, S;
	static Deque<int[]> left, right;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		left = new ArrayDeque<>();
		right = new ArrayDeque<>();
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int ap = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			arr[i] = new int[] {ap, p}; // 아파트 위치, 사람 수
		}
		Arrays.sort(arr, (a, b)->a[0]-b[0]); //위치 정렬
		for(int i=0; i<N; i++) {
			if(arr[i][0] < S) {
				arr[i][0] = S - arr[i][0]; // 거리로 넣음
				left.add(arr[i]);
			} else {
				arr[i][0] = arr[i][0] - S;
				right.add(arr[i]);
			}
		}
		
		int dist = 0;
		while(!left.isEmpty()) {
			int[] cur = left.removeFirst();
//			System.out.println(Arrays.toString(cur));
			if(cur[1] > K) {
				int moveCnt = cur[1] / K;
				int remainPerson = cur[1] % K;
				dist += moveCnt * cur[0] * 2;
				if(remainPerson > 0) {
					cur[1] = remainPerson;
					left.addFirst(cur);
				}
			} else {			
				left.addFirst(cur);
				int remain = K;
	
				int maxDist = 0;
				while(remain > 0 && !left.isEmpty()) {
					int[] apt = left.removeFirst(); // 거리, 사람 수
					if(apt[1] <= remain) {
						remain -= apt[1];					
					} else {
						apt[1] -= remain;
						remain = 0;
						left.addFirst(apt);
					}
					maxDist = Math.max(maxDist, apt[0]);
				}
				dist += maxDist<<1;
			}
		}
//		System.out.println(dist);
		
		while(!right.isEmpty()) {
			
			int[] cur = right.removeLast();
			if(cur[1] > K) {
				int moveCnt = cur[1] / K;
				int remainPerson = cur[1] % K;
				dist += moveCnt * cur[0] * 2;
				if(remainPerson > 0) {
					cur[1] = remainPerson;
					right.addLast(cur);
				}
			} else {		
				right.addLast(cur);
				int remain = K;
				
				int maxDist = 0;
				while(remain > 0 && !right.isEmpty()) {
					int[] apt = right.removeLast(); // 거리, 사람 수
					if(apt[1] <= remain) {
						remain -= apt[1];					
					} else {
						apt[1] -= remain;
						remain = 0;
						right.addLast(apt);
					}
					maxDist = Math.max(maxDist, apt[0]);
				}
				dist += maxDist<<1;
			}
		}System.out.println(dist);
	}
}