package study;

import java.util.Scanner;

public class 집합의표현 {
	static int[] parents;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		parents = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			int bool = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(bool == 0) { // a와 b의 집합을 합친다.
				union(a, b);
			}else { // a와 b가 같은 집합에 있는 지 확인한다.
				System.out.println(isUnion(a, b) ? "YES":"NO");
			}
		}
	}
	
	static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		int nx = find(x);
		int ny = find(y);
		
		if(nx == ny) return;
		parents[ny] = nx;
	}
	
	static boolean isUnion(int x, int y) {
		int nx = find(x);
		int ny = find(y);
		
		if(nx == ny) return true;
		return false;
	}

}
