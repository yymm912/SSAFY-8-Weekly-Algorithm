package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 풀이시간: 20분
 * 참고: X
 * 
 * 50040KB	464ms
 * 
 * <풀이방식>
 * - 모든 노드에 대해 집합의 대표자 배열을 만들어 본인으로 초기화(원소가 하나인 집합 n+1개)
 * - 다음 입력 m동안 합집합, 같은 연산인지 확인 수행
 * 
 * */
public class boj1717_집합의표현 {
	static int n, m;	// n+1: 집합 개수, m: 연산 개수 
	static int[] parent;	// 집합 대표 배열
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();;
		
		n = Integer.parseInt(st.nextToken());	// 집합 개수 (0~n), n+1개
		m = Integer.parseInt(st.nextToken());	// 연산 개수
		
		// 집합 대표 본인으로 초기화
		parent = new int[n+1];
		for(int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		
		// 입력받으면서 처리
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int oper = Integer.parseInt(st.nextToken()); 
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			
			if(oper==0) {	// 합집합 연산
				union(a, b);
			}else {		// 같은 집합이면 YES, 다른 집합이면 NO
				if(check(a, b)) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}	
		}
		
		// 출력
		System.out.print(sb);
		
	}
	// 집합의 대표자 찾기
	private static int find(int a) {
		if(parent[a] == a) return a;	// 본인이 대표
		
		// 본인이 대표 아니면 다시 함수 호출, 해당 값을 본인 부모로 적고 리턴
		return parent[a] = find(parent[a]);
	}
	
	// 합집합
	// 집합 대표자 찾고 같으면 그만둠 (이미 같은 집합이라 할 일 X)
	// 다르면 한쪽으로 합치기
	static void union(int a, int b) {
		if(a==b) return;	// 가지치기
		
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA==rootB) return;
		
		parent[rootB] = rootA;
		
		
	}
	
	// 같은 집합인지 확인
	static boolean check(int a, int b) {
		if(a==b) return true;	// 가지치기
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA==rootB) return true;
		
		return false;
	}

}
