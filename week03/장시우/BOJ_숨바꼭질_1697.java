package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_숨바꼭질_1697 {
	
	static int subin, sister, ans;
	static Queue<Integer> q;
	static boolean[] checked = new boolean[100001];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		subin = sc.nextInt();
		sister = sc.nextInt();
		
		q = new ArrayDeque<>();
		ans = 0;
		if (subin == sister) {
			System.out.println(0);
		} else {
			bfs();
			System.out.println(ans);
		}
	}
	
	static void bfs() {
		q.add(subin);
		checked[subin] = true;
		
		while (!q.isEmpty()) {
			int currentSize = q.size();
			
			for (int i = 0; i < currentSize; i++) {
				int current = q.poll();
				
				if (current + 1 == sister || current - 1 == sister || 2 * current == sister) {
					q.clear();
					break;
				}
				
				if (current + 1 <= 100000 && !checked[current + 1]) {
					checked[current + 1] = true;
					q.add(current + 1);
				}
				if (current - 1 >= 0 && !checked[current - 1]) {
					checked[current - 1] = true;
					q.add(current - 1);
				}
				if (2 * current >= 0 && 2 * current <= 100000 && !checked[2 * current]) {
					checked[2 * current] = true;
					q.add(2 * current);
				}
			}
			
			ans++;
		}
	}
}