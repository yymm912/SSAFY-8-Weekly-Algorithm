package baekjoon.듣보잡_1764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		HashSet<String> set = new HashSet<>();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if(set.contains(str)) {
				list.add(str);
			}
		}
		
		System.out.println(list.size());
		Collections.sort(list);
		for (String str : list) {
			System.out.println(str);
		}
	}
}
