package forStudy.week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1225_암호생성기 {

	static Queue<Integer> nums = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			br.readLine();
			nums.clear();
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<8; i++) {
				nums.offer(Integer.parseInt(st.nextToken()));
			}
			
			int cnt = 1;
			while(true) {
				int tmp = nums.poll();
				int tmpRes = tmp - cnt;
				// 첫번째 수의 결과가 0이되면 마지막에 넣고 종료
				if(tmpRes <= 0) {
					nums.offer(0);
					break;
				}
				
				// 아니면 계산 값 맨 뒤에 삽입
				nums.offer(tmpRes);
				
				// cnt가 5라면 한 사이클 종료
				if(cnt == 5) {
					cnt = 1;
				} else {
					cnt++;
				}
			}
			
			System.out.print("#" + t + " ");
			for(int i=0; i<8; i++) {
				System.out.print(nums.poll() + " ");
			}
			System.out.println();
		}
	}

}
