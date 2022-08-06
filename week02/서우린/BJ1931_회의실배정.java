package _2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1931_회의실배정 {
	public static class C implements Comparable<C>{
		int s,e;
		public C(int s, int e) {
			this.s = s;
			this.e = e;
		}
		@Override
		public int compareTo(C o) {
			if(this.e == o.e)
				return this.s - o.s;
			return this.e - o.e;
		}
	}
	static int N;
	static List<C> c = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			c.add(new C(s,e));
		}
		
		Collections.sort(c);
		
		int answer = 0;
		int t = -1;
		for(int i = 0;i<N;i++) {
			C cur = c.get(i);
			if(t <= cur.s) {
				answer++;
				t = cur.e;
			}
		}
		
		System.out.println(answer);

	}

}