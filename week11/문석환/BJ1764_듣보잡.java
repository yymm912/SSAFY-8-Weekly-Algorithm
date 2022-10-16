package BOJ.문자열.듣보잡_1764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 제목 : 듣보잡
// 번호 : 1764
// 난이도 : 실버 4
// https://www.acmicpc.net/problem/1764
/*
 * INFO
 * 듣 X 명단
 * 보 X 명단
 * 듣 X & 보 X 인 사람 명단 구하는 프로그램 작성
 * 단 듣 X , 보 X 명단에는 중복 되는 이름 존재 X
 */

/*
 * INPUT
 * N M (N : 듣 X 사람 수 , M : 보 X 사람 수)
 * N개의 줄에 걸쳐 듣 X 이름
 * 이후 보 X 이름 순서대로 입력
 */
public class Main {
	static int N,M;
	static String[] D,B;
	static String[] ans;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 듣 X 사람 수
		M = Integer.parseInt(st.nextToken()); // 보 X 사람 수
		D = new String[N];
		B = new String[M];
		for(int i=0;i<N;i++) {
			D[i] = br.readLine();
		}

		for(int i=0;i<M;i++) {
			B[i] = br.readLine();
		}

		if(N>=M) pro(D,B,M);
		else pro(B,D,N);
	}

	static void pro(String[] search,String[] result,int size) {
		int cnt = 0;
		Arrays.sort(search,0,N); // search 문자열 배열 정렬 -> 이분탐색을 위해
		ans = new String[size]; // 제일 많아야 size개 이므로

		for(int i=0;i<size;i++) {
			if(isRepeated(search,0,search.length-1,result[i])) {
				ans[cnt++] = result[i];
			}
		}

		sb.append(cnt).append("\n");
		Arrays.sort(ans,0,cnt);
		for(int i=0;i<cnt;i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.println(sb);
	}

	static boolean isRepeated(String[] S,int L,int R,String X) {
		// S 문자열 배열의 아이템에서 X의 아이템을 찾아보자
		// 단 시간 초과를 해결하기 위해 이분탐색 사용
		while(L<=R) {
			int mid = (L+R)/2;

			if(S[mid].compareTo(X) == 0) return true; // S[mid]에 X가 존재하는 경우

			if(S[mid].compareTo(X) > 0) {
				R = mid - 1;
			}else {
				L = mid + 1;
			}
		}
		return false;
	}
}
