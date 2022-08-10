package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 모험가길드 {
	static int N;
	static List<Integer> people;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			people.add(Integer.parseInt(st.nextToken()));
		}
		
		// 내림차순 정렬
		// 가장 X가 많은 사람 기준으로 그룹핑함.
		// X명 묶은 다음, 그룹의 조건에 맞는지 확인 (그룹에 X명 있는지 확인. 마지막 그룹이 걸림.)
		Collections.sort(people);
//		System.out.println(people);
		
		int groupCnt = 0;
		for(int s=0; s<N; s++) {
			int X = people.get(s);
			if(s+X < N) { // 그룹이 결성되면
				groupCnt++;	// 그룹 수 증가
				s += X; // X명의 그룹원 만큼 건너뜀
			}
		}
		
		System.out.println(groupCnt);
	}
}
