package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9997_2 {
	
	static int N;
	static int ret;
	static int[] words; // 각 단어의 비트마스크가 들어있는 배열
	
	static final int ALPHA = (1<<26)-1;
	// 0000 0011 1111 1111 1111 1111 1111 1111
	// 모든 알파벳을 사용했을 경우 비트마스크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		words=new int[N];

		
		for (int i=0; i<N; i++) { 
			String str=br.readLine();
			
			for (int j=0; j<str.length(); j++) {
				words[i] |= 1<<str.charAt(j)-'a';
			}
		}
		
		dfs (-1, 0);
		System.out.println(ret);
	}
	
	private static void dfs (int idx, int mask) {
		if (idx==N-1) {
			if (mask==ALPHA) 
				ret ++;
			return ;
		}
		
		dfs (idx+1, mask | words[idx+1]);
		dfs (idx+1, mask);
	}
}
