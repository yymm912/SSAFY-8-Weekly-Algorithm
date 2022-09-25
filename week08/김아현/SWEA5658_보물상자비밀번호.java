package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA5658_보물상자비밀번호 {

	static int T, N, K;
	static List<Character> list;
	static HashSet<Integer> resultSet;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 초기화
			list = new ArrayList<>();
			resultSet = new HashSet<>();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			char[] tmp = br.readLine().toCharArray();
			for(int i=0; i<N; i++) {
				list.add(tmp[i]);
			}
			
			int splitSize = N / 4; // 16진수로 만들 문자의 갯수
			// 회전 수 : 한 변에 위치한 숫자 갯수만큼 = splitSize
			// 한 회전이 끝날 때마다 맨 뒤의 문자를 맨 앞으로 가져온 뒤 다시 연산 수행
			int rotateCnt = splitSize;
			
			while(rotateCnt-- > 0) { // 회전 수 동안 연산 수행
				// List의 문자열 가져오기
				for(int i=0; i < N; i += splitSize) {
					String str = "0x";
					for(int j = i; j < i + splitSize; j++) {
						str += list.get(j);
					}
					
					resultSet.add(Integer.decode(str));
				}
				
				list.add(0, list.remove(N-1));
			}
			
			ArrayList<Integer> resultArr = new ArrayList<>(resultSet);
			Collections.sort(resultArr, Collections.reverseOrder());
			
			System.out.println("#" + t + " " + resultArr.get(K-1));
		}
	}

}
