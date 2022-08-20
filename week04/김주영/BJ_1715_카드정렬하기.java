package greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 
 * 카드의 장수가 적은 카드 묶음끼리 더하는 것이 그 횟수를 줄일 수 있음
 * 카드를 묶는 횟수는 N-1 번
 * => 현재 존재하는 카드 묶음 중에서, 가장 적은 2개의 묶음을 우선적으로 계산
 * 
 * 그 값을 다시 priority queue에 넣고 계산 (참고로 java에서 min heap이 default!!)
 * 
 */


public class BOJ1715 {

	static int N, ans;
	static PriorityQueue <Integer> pq=new PriorityQueue<>();
	
	public static void main(String[] args) {
	
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		
		for (int i=0; i<N; i++) 
			pq.add(sc.nextInt());
		
		for (int i=0; i<N-1; i++) {
			int n1=pq.poll();
			int n2=pq.poll();
			
			ans+=n1+n2;
			pq.add(n1+n2);
		}
		
		System.out.println(ans);
	}

}
