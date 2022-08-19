package algo_study._8월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가운데 인덱스
 * */
public class BOJ_18310안테나 {

	static int[] home;
	static int cnt, mid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cnt = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		home = new int[cnt];
		for (int i = 0; i < cnt; i++) {
			home[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(home);
		
		//가운데 인덱스
		if (cnt!= 0) 
			mid = home[(cnt-1)/2];
		else mid = home[0];
		
		System.out.println(mid);
	}

}
