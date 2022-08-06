package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ12891_비밀번호{
	
	static int n;
	static int m;
	static int[] arr;
	static char[] ori;
	static char[] alp = {'A','C','G','T'};
	static int[] alpCnt;
	static int count = 0;
	static HashMap<Character,Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//들어온 문자열을 문자배열로 바꿈
		ori = br.readLine().toCharArray();
		
		//유전자 알파벳 최소 개수가 담긴 배열
		alpCnt = new int[4];
		
		//유전자 알파벳 최소 개수가 담긴 배열 받기
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			alpCnt[i] = Integer.parseInt(st.nextToken());
		}
		//(문자,문자가 나온 횟수) 형식으로 m-1만큼만 담기
		for(int i=0; i<m; i++) {
			map.put(ori[i], map.getOrDefault(ori[i],0)+1);
		}
		
		//비밀번호로 사용이 가능한지 체크하고 맞다면 횟수를 증가시킴
		if(isRight()) {
			count++;
		}
		
		//현재 맵에는 m-1만큼의 데이터가 들어있음
		//이때 i값은 m부터 시작하여 하나씩 담으며 lt는 0부터 시작하여 하나씩 뺌
		int lt = 0;
		for(int i=m; i<ori.length; i++) {
			map.put(ori[i], map.getOrDefault(ori[i], 0)+1);
			map.put(ori[lt], map.get(ori[lt])-1);
			lt++;
			//오른쪽에서 하나 올리고 왼쪽에서 하나 뺀 값이 사용가능하다면 횟수증가
			if(isRight()) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	
	private static boolean isRight() {
		int arrTmp[] = new int[4];
		//static char[] alp = {'A','C','G','T'};
		//0부터시작하여 각 알파벳에 담긴 map의 값이 주어진 값 보다 작다면 false리턴
		for(int i=0; i<4; i++) {
			if(map.containsKey(alp[i])) {
				arrTmp[i] = map.get(alp[i]);
			}
			else {
				arrTmp[i] =0;
			}
			if(arrTmp[i] < alpCnt[i]) {
				return false;
			}
		}
		//여기까지 왔다는건 비밀번호로 사용가능하다.
		return true;
	}
}
