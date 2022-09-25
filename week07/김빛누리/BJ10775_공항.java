package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 풀이시간: 40분
 * 참고: boj 알고리즘 분류
 * 
 * 23192KB	268ms
 * 
 * <풀이>
 * - 집합 대표자가 0인 경우 빈 게이트
 * - 0이 아닌 경우 연속적으로 게이트가 찬 곳을 집합설정
 * 
 * */
public class BJ10775_공항 {
	static int G, P, result = 0;
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		parent = new int[G+1];
	
		for (int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			
			if(parent[g] == 0) {	// 가장 높은숫자 게이트가 비어있음
				result++;
				parent[g] = g;
				if(g-1 > 0 && parent[g-1] != 0) union(g-1, g);
				if(g+1 < G && parent[g+1] != 0) union(g, g+1);
			} else {
				// 가장 높은 숫자 게이트가 비어있지 않다면
				// 동일 집합 내 가장 작은 숫자(parent) 찾고 그 이전 숫자 union
				
				int index = find(g)-1;
				if(index > 0) {
					result++;
					parent[index] = index;
					union(index, g);
					if(index-1 > 0 && parent[index-1] != 0) union(index-1, index);
					if(index+1 < G && parent[index+1] != 0) union(index, index+1);
				} else break;
			}
			
		}
		
		System.out.println(result);
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
	
	
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 집합의 대표자는 집합 원소 중 가장 작은 원소로
		if(aRoot < bRoot) parent[bRoot] = parent[aRoot];
		else parent[aRoot] = parent[bRoot];
	}

}
