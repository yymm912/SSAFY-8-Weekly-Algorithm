package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2110_공유기설치 {
	
	static int N, M;
	static int[] house;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		int low = 1;
		int max = house[N-1] - house[0]+1;
		
		while(low<max) {
			int mid = (max+low)/2;
			
			if(canInstall(mid)<M) {
				max = mid;
			}
			else low = mid+1;
			
			
		}
		
		System.out.println(low-1);
		
	}
	
	public static int canInstall(int distance) {

		int count = 1;
		int lastLocate = house[0];

		for (int i = 1; i < house.length; i++) {
			int locate = house[i];

			if (locate - lastLocate >= distance) {
				count++;
				lastLocate = locate;
			}
		}
		return count;

	}

}
