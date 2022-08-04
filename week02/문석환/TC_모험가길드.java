package tc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// N명의 모험가
// N명의 모험가 대상으로 공포도 측정
// 공포도 높으면 쉽게 공포 느낌 (BAD)
// 공포도가 X인 모험가는 반드시 X명 이상으로 구성한 그룹에 참여
// EX) 공포도 5 모험가 5명 이상의 그룹 참여
// 입력
// int N : 모험가 수
// int[] F : 각 모험가의 공포도
// 출력
// 여행을 떠날 수 있는 그룹 수의 최대값

public class TC_모험가길드{
	static int N,ans,cnt;
	static int[] F;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		F = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			F[i] = st.nextToken().charAt(0) - '0';
		}

		Arrays.sort(F);

		ans = 0;
		cnt = 0;

		for(int i=0;i<N;i++) {
			cnt++;
			if(F[i] == cnt) {
				ans++;
				cnt=0;
			}
		}

		System.out.println(ans);

	}
}
