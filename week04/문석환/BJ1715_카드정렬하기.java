package BOJ.그리디.카드정렬하기_1715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static int sum;
	static PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			PQ.add(Integer.parseInt(br.readLine()));
		}

		if(N == 1) {
			sum = 0;
		}else {
			while(PQ.size() > 1) {
				int num1 = PQ.poll();
				int num2 = PQ.poll();
				sum += num1 + num2;
				PQ.add(num1+num2);
			}
		}

		System.out.println(sum);

	}
}
