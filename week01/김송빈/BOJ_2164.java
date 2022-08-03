package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2164 {
	public static void main(String[] args) throws Exception {
		//인풋 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());//자연수 받음
		
		if(n==1)System.out.println(1);//1이면 2번 poll못함 무조건 1
		else {
			Queue<Integer> q = new LinkedList<>();//큐 생성
			for (int i = 1; i <= n; i++) {//1~n까지 큐에 넣어줌
				q.offer(i);
	
			}
			int x;
			while (true) {
				q.poll();//한 번 뺴주고
				x = q.poll();//또 빼주고
				if (q.isEmpty())
					break;
				q.offer(x);//x를 맨 뒤에 넣어줌
	
			}
			System.out.println(x);//마지막으로 poll된 게 답
		}
	}
}
