package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 문제에서 한 그룹 안에 몇 명이 있는가와는 상관없이
 * 여행을 떠날 수 있는 그룹의 최댓값을 구해야하므로
 * 작은 그룹 여러 개를 만드는 것이 더 효과적 -> 오름차순으로 sorting 한 후 찾기
 */

public class 모험가길드 {

	static int N;
	static int ret,cnt;
	static ArrayList<Integer> fear=new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		st=new StringTokenizer (br.readLine(), " ");
		
		for (int i=0; i<N; i++)
			fear.add(Integer.parseInt(st.nextToken()));
		
		Collections.sort(fear);
		
		
		// ret: 총 그룹 수, cnt: 한 그룹에 들어가 있는 모험가의 수
		for (int i=0; i<N; i++) {
			cnt+=1;
			
			if (cnt>=fear.get(i)) {
				ret+=1;
				cnt=0;
			}
		}
		System.out.println(ret);
	}
}
