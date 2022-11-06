package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ13904과제 {

	static int N;
	static Item work[];
	static int sum;
	static int score[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		work = new Item[N];
		score = new int[1001];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			work[i] = new Item(d, w);
		}
		
		Arrays.sort(work, (a,b)-> b.w - a.w); // 과제의 가치 높은 순서대로 정렬 
		
		
		for (int i = 0; i < N; i++) {
			
			if(score[ work[i].d ] == 0) {
				score[ work[i].d ] = work[i].w;
			}
			else { // 비어있지 않다면 앞에서부터 빈칸 찾아서 채우기 
				
				int idx = work[i].d;
				while( idx > 0 && score[idx] != 0) idx--;
				if(idx == 0) continue;
				
				score[idx] = work[i].w;
				
			}
			
		}
		
		for (int i = 0; i < score.length; i++) {
			sum += score[i];
		}
		System.out.println(sum);
	}

	static class Item {
		int d, w;

		public Item(int d, int w) {
			super();
			this.d = d;
			this.w = w;
		}
		
	}
}
