package _10주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SW5658_보물상자비밀번호 {
	static int T,N,K;
	static String s;
	static SortedSet<Long> set;
	static int num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1;t<=T;t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			s = br.readLine();
			set = new TreeSet<>();
			num = N/4;
			char [] hex = new char[num];
			
			for(int i = 0;i<N;i++) {
				int idx = 0;
				for(int j = i;j<N+i;j++) {
					hex[idx++] = s.charAt(j%N);
					if(idx == num) {
						
						set.add(toDem(hex));
						idx = 0;
					}
				}
			}
			
			Object [] ans = set.toArray();
			System.out.println("#"+t+" "+ans[ans.length - K]);
			
		}
	}
	
	static long toDem(char[] hex) {
		long ret = 0;
		long c = 1;
		
		for(int i = num-1; i>=0;i--) {
			long cur;
			if('A' <= hex[i] && hex[i] <= 'Z') {
				cur = hex[i] - 'A' + 10;
			}else {
				cur = hex[i] - '0';
			}
			ret += cur*c;
			c*=16;
		}
		return ret;
	}

}
