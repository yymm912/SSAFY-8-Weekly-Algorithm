package baekjoon.도서관_1461;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N,M,ans;
	static PriorityQueue<Integer> plusBook = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순으로 정렬
	static PriorityQueue<Integer> minusBook = new PriorityQueue<>(); // 오름차순으로 정렬
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <N; i++) {
			int book = Integer.parseInt(st.nextToken());
			if(book > 0) plusBook.add(book);
			else minusBook.add(book);
		}
		
		// 둘중 가장 거리가 먼 책 하나는 미리  빼두자. 제일 마지막에 꼽을 책
		int MaxP = plusBook.isEmpty() ? 0 : plusBook.poll(); // 만약 비었으면 0
		int MaxM = minusBook.isEmpty() ? 0 : Math.abs(minusBook.poll()); // 만약 비었으면 0
		if(MaxP > MaxM) { // 양수 쪽이 더 크다면
			ans += MaxP; // 마지막에 갓다온 걸로 저장해두기
			for (int i = 0; i < M-1; i++) { // 겸사겸사 그 주변 책들 가져다 놓기
				if(plusBook.isEmpty()) break;
				plusBook.poll();
			}
			minusBook.add(-MaxM); // 나머지 다시 집어 넣기
		}else {
			ans += MaxM;
			for (int i = 0; i < M-1; i++) { // 겸사겸사 그 주변 책들 가져다 놓기
				if(minusBook.isEmpty()) break;
				minusBook.poll();
			}
			plusBook.add(MaxP); // 위와 동일
		}
		
		// 왼쪽 털기
		while(!minusBook.isEmpty()) {
			int move = 0; // 현재 기준 이동해야하는 거리
			int cnt = 0; // 현재 소지한 책의 권수
			while(cnt != M) { // 책이 한도초과거나
				if(minusBook.isEmpty()) break; // queue가 비었으면 중지
				move = Math.max(Math.abs(minusBook.poll()), move);
				cnt++;
			}
			ans += move*2;
		}
		
		// 오른쪽 털기
		while(!plusBook.isEmpty()) {
			int move = 0; // 현재 기준 이동해야하는 거리
			int cnt = 0; // 현재 소지한 책의 권수
			while(cnt != M) { // 책이 한도초과
				if(plusBook.isEmpty()) break;
				move = Math.max(plusBook.poll(), move);
				cnt++;
			}
			ans += move*2;
		}
		
		System.out.println(ans);
	}

}
