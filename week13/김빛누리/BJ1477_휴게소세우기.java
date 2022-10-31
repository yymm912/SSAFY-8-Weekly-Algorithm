package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 참고: 솔루션
 * */
public class BJ1477_휴게소세우기 {
	static int N, M, L;
	static ArrayList<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		list.add(0);
		list.add(L);
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		
		int left = 0;
		int right = L;
		while(left<=right) {
			int mid = (left+right)/2;
			if(canMake(mid)) left = mid+1;
			else right = mid-1;
		}
		System.out.println(left);
		
	}

	private static boolean canMake(int mid) {
		int cnt = 0;
		for(int i = 0; i < list.size()-1; i++) {
			cnt += (list.get(i+1)-list.get(i)-1)/mid;
		}
		if(cnt > M) return true;
		return false;
	}
}
