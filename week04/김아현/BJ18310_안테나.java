package forStudy.week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 집들의 위치가 주어질 때, 안테나를 설치했을 때 모든 집까지 거리의 총합이
 * 최소가 되는 집의 위치 구하기. (안테나는 집이 있는 위치에 설치할 수 있다)
 * => 모든 집들의 가운데에 위치해야 한다.
 */

public class BJ18310_안테나 {
	static int N;
	static int[] street;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		street = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			street[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(street);
		
		System.out.println(street[(N-1)/2]);
	}

}
