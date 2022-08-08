package forStudy.week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <문제>
 * 규칙 : 공포도가 X인 모험가는 반드시 X명 이상으로 구성한 모험가 그룹에 참여해야 한다.
 * 할일 : 모험가 그룹의 최대 갯수를 구해야 하는 문제. (모든 모험가가 포함될 필요x)
 * => 공포도가 1인 모험가는 1명 이상, 2인 모험가는 2명이상, .. 이므로
 * 	   공포도가 작은 모험가부터 그룹을 지으면 최대한 많은 모험가 그룹을 만들 수 있다.
 * 입력 : 첫째줄 모험가의 수 N (1<=N<=100,000), 둘째줄 각 모험가의 공포도 공백구분
 * 출력 : 여행을 떠날 수 있는 그룹 수의 최댓값
 **/

public class TC_모험가길드 {

	static int N;		// 모험가의 수
	static int cnt, res;	// 그룹인원수, 그룹의 총 갯수
	static int[] fear;		// 모험가들의 공포도를 담을 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		fear = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			fear[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fear);
		
		cnt=0;
		res=0;
		
		for (int f : fear) {
			// 그룹에 들어간다
			cnt++;
			// 그룹인원수가 자신의 공포도와 일치하면 그룹이 완성된다
			if(cnt == f) {
				res++;
				cnt=0;
			}
		}
		
		System.out.println(res);
	}

}
