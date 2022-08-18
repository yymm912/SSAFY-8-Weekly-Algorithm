package _4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ18310_안테나 {
	static int N;
	static long [] arr;
	static Stack<Long> f;
	static Queue<Long> b;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		st = new StringTokenizer(br.readLine());
		f = new Stack<>();
		b = new ArrayDeque<>();
		long fs = 0,bs = 0;
		for(int i = 0;i<N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
			bs+=arr[i];
		}
		Arrays.sort(arr);
		for(int i = 0;i<N;i++) {
			b.add(arr[i]);
		}
		long answer = -1;
		long amin = Long.MAX_VALUE;
		while(!b.isEmpty()) {
			long cur = b.poll();
			//현재 위치와 뒤편의 모든 쌍의 거리 합
			long bss = bs - cur * (b.size()+1);
			bs -= cur;
			//현재 위치와 앞편의 모든 쌍의 거리 합
			
			if(!f.isEmpty()) 
				fs += ((cur - f.peek()) * f.size());
			if(amin > bss+fs) {
				answer = cur;
				amin = bss+fs;
			}
			f.add(cur);
		}
		
		System.out.println(answer);
	}

}