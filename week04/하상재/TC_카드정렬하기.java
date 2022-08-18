package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class TC_카드정렬하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pque = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			pque.add(Integer.parseInt(br.readLine()));
		}
		
		int sum = 0, tmp=0;
		
		while(pque.size()>1) {
			tmp = pque.poll() + pque.poll();
			sum += tmp;
			pque.add(tmp);
		}
		
		System.out.println(sum);
		
	}
}
