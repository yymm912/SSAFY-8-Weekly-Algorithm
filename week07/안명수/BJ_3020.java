package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_3020 {
	static int N, H;
	static int[] cave, bottom, top;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		
		cave = new int[H];
		bottom = new int[H];
		top = new int[H];
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			
			if(i % 2 == 0) {
				bottom[num - 1]++; // 석순 값
			}else {
				top[H - num]++; // 종유석 값
			}
		}
		
		// 석순의 끝에서 바닥까지 내려오면서 값 갱신
		for(int i = H - 1; i > 0; i--) {
			bottom[i - 1] += bottom[i]; 
		}
		
		// 종유석의 끝에서 천장까지 올라가면서 값 갱신
		for(int i = 0; i < H - 1; i++) {
			top[i + 1] += top[i];
		}
		
		for(int i = 0; i < H; i++) {
			cave[i] = bottom[i] + top[i];
		}
		
		// 오름차순 정렬
		Arrays.sort(cave);
		
		int min = cave[0];
		int cnt = 0;
		for(int i = 0; cave[i] == min; i++) {
			cnt++;
		}
		
		System.out.println(min + " " + cnt);
	}
}	
