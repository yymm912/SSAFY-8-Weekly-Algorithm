package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_1764_듣보잡 {
	static int N, M;
	static TreeSet<String> ts;
	static List<String> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ts = new TreeSet<>();
		list = new ArrayList<String>();

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			ts.add(str);
		}

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if (ts.contains(str)) {
				list.add(str);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(list.size()).append("\n");
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("\n");
		}

		System.out.println(sb);
	}
}
