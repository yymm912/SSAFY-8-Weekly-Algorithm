package baekjoon.개똥벌레_3020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, H, min, destroy, cnt;
	static int[] up, down;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		min = N+1;
		// 어짜피 무조건 짝수
		up = new int[N/2]; // 위에서 ㄴ ㅐ려오는 애 
		down = new int[N/2]; // 밑에서 올라오는 애
		
		for (int i = 0; i < N/2; i++) {
			down[i] = Integer.parseInt(br.readLine());
			up[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(up);
		Arrays.sort(down);
		
		cnt = 0;
		for (int i = 1; i <= H; i++) {
			destroy = search(i);
			if(min == destroy) {
				cnt++;
				continue;
			}
			if( min > destroy) {
				min = destroy;
				cnt = 1;
			}
		}
		
		System.out.println(min + " " + cnt);
	}

	static int search(int i) {
		int count_up = 0;
		int count_down = 0;
		int l = 0;
		int r = N/2;
		int mid = 0;
		// up에 관해
		while(l < r) {
			mid = (l + r) / 2 ;
			if(up[mid] >= i) r = mid;
			else l = mid+1;
		}
		count_up = N/2 - r; // 총길이에서 r만큼을 뺀 것이 i보다 큰 것의 개수
		
		// 초기화
		l = 0;
		r = N/2;
		mid = 0;
		// down에 관해
		// down은 반대방향이니까
		// 1번째라인의 경우 down에서는 H의 값을 가져야만 파괴 가능
		while(l < r) {
			mid = (l + r) / 2 ;
			if(down[mid] >= H-i+1) r = mid;
			else l = mid+1;
		}
		count_down = N/2 - r;
		
		return count_up + count_down;
		
	}
}
