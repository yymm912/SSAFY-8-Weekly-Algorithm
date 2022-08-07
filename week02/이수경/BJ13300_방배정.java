package im;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13300_방배정 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("BJ13300.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학생 수 
		int K = Integer.parseInt(st.nextToken()); // 한 방 최대 인원 수 
		
		int student[][] = new int[2][1001]; // [0]:성별 [1]:학년  /  학생 인덱스
		int sum[][] = new int[2][1001];
		int result = 0;

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());

			student[0][i] = Integer.parseInt(st.nextToken()); // 성별 
			student[1][i] = Integer.parseInt(st.nextToken()); // 학년 
		}
		//입력 완료
		
		
		for(int i=0;i<N;i++) {
			sum[student[0][i] ][student[1][i] ]++;  // sum[ 성별 ][ 학년 ]++
		}
		
		for(int i=1;i<=6;i++) {
			for(int j=0;j<2;j++) {
				
				if( sum[j][i] == 0) continue;
				
				result++;
				
				while(sum[j][i] > K) {
						sum[j][i] -= K;
						result++;
					}
			}
		}
		
		System.out.print(result);
		
	}

}
