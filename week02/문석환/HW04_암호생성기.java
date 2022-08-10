import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static Queue<Integer> Q = new ArrayDeque<Integer>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t = 1;t<=10;t++) {
			Q.clear();
			// 테스트 케이스 번호 입력 무시
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<8;i++) {
				Q.add(Integer.parseInt(st.nextToken()));
			}
			int cnt = 0;
			while(true) {
				// 1,2,3,4,5가 한 사이클 이므로 5이면 다시 0으로 변
				if(cnt == 5) cnt=0;
				int N = Q.poll();
				N -= cnt += 1;
				// N을 감소 시켰는데 0보다 작아지는 경우 값을 0으로 유
				if(N <= 0) {
					N = 0;
				}
				// N을 다시 Q에 넣어줌
				Q.add(N);

				// 넣어준 N의 값이 0인경우 반복문 멈추고 해당 Q를 출
				if(N == 0)break;
			}
			sb.append("#").append(t).append(" ");
			while(!Q.isEmpty()) {
				sb.append(Q.poll()).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
