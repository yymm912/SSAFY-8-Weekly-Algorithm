package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1461 {

	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		// 마이너스 값과 플러스 값의 우선순위 큐 생성 (둘 다 먼 순서부터 우선 순위 높임)
		PriorityQueue<Integer>minus=new PriorityQueue<Integer>((i1,i2)->i2-i1);
		PriorityQueue<Integer>plus=new PriorityQueue<Integer>((i1,i2)->i2-i1);
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int x=Integer.parseInt(st.nextToken());
			if(x<0)minus.add(Math.abs(x));
			else plus.add(x);
		}
		
		int cnt=0;
		if(minus.isEmpty()) {//플러스 큐 없으면 
			cnt=plus.peek();
			for(int i=0;i<m;i++) {
				plus.poll();
				if(plus.isEmpty())break;
			}
		}
		else if(plus.isEmpty()) {//마이너스큐 없으면
			cnt=minus.peek();
			for(int i=0;i<m;i++) {
				minus.poll();
				if(minus.isEmpty())break;
			}
		}else {//둘 다 있으면 
			//제일 먼 거 골라서 미리 빼줌
			if(minus.peek()>plus.peek()) {
				cnt=minus.peek();
				for(int i=0;i<m;i++) {
					minus.poll();
					if(minus.isEmpty())break;
				}
			}
			else {
				cnt=plus.peek();
				for(int i=0;i<m;i++) {
					plus.poll();
					if(plus.isEmpty())break;
				}
			}
		}
		
		while(!minus.isEmpty()) {//마이너스 큐 다 뺄때까지
			int x=minus.peek();
			cnt+=(x*2);//왔다갔다
			for(int i=0;i<m;i++) {
				minus.poll();
				if(minus.isEmpty())break;
			}
		}
		
		while(!plus.isEmpty()) {
			int x=plus.peek();
			cnt+=(x*2);
			for(int i=0;i<m;i++) {
				plus.poll();
				if(plus.isEmpty())break;
			}
		}
		System.out.println(cnt);
	}
}
