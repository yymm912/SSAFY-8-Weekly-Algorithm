package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Card2_BJ_2164 {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);

		
		int N = sc.nextInt();
		
		Queue<Integer> card = new LinkedList<>(); // 큐 스택을 선언
		
		
		for(int i = 1; i <= N; i++) {
			 card.add(i); //각 카드에 순차적으로 번호를 부여
		}

		while(card.size() > 1) { //카드가 한 장 이상인 동안, 맨 앞 카드를 한장 제거하고,
			 card.remove(); //  이후의 제일 앞 쪽 값을 마지막에 추가한 후 중복되는 맨 앞 카드를 제거한다.
			 card.add(card.peek());
			 card.remove();
		}
		
		System.out.println(card.peek()); // 최종적으로 남은 마지막 카드 한 장을 출력.
		
		
//		{ // ### 같은 방식으로, 정수형 1차원 배열을 이용해 풀이한 방법 ###
		//
//		int[] card = new int[N+1]; // 선언한 카드 수 보다 하나 더 많은 배열을 선언
//		
//		for (int i = 0; i < N; i++) 
//		{
//			card[i] = i+1; //순차적으로 카드에 정수를 부여하고
//		}
//		
//		while(N > 1) // 카드가 한 장보다 많은 동안,
//		{
//			int tmp = card[0]; //제일 앞쪽 카드를 맨 뒤 + 1번째와 바꾼다.
//			card[0] = card[N];
//			card[N] = tmp; 
//			
//			for (int i = 1; i < N; i++) { 카드를 한 장 씩 앞으로 밀면서 자리 배치를 변경한다.
//				int tmp2 = card[i];
//				card[i] = card[i-1];
//				card[i-1] = tmp2;
//			}
//			
//			N--; // 카드의 마지막 장 인덱스를 하나씩 앞으로 당기다.
//			
//			
//		}
//		System.out.println(card[N-1]); 최종적으로 남은 카드의 배열에 담긴 값을 출력
//		
//		
//		sc.close();
//		}
	}
}

