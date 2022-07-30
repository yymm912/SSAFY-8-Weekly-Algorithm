package al.jul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class card2 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		//queue add는 오류. offer는 false값제공
		
		while(true) {
			if(q.size()==1)break; //q.size가 1이면 break;
			q.remove();
			int temp = q.poll();
			q.offer(temp);
		}
		
		System.out.println(q.peek());
	}
}
