import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution{
	static int T;
	static int N;
	/** N = 3인 경우
	 * 1 2 3 
	 * 8 9 4
	 * 7 6 5 */
	static int   shellNum;	// 껍질의 개수
	static int[] shellInfo; //각 껍질의 최소 숫자, 최대 숫자 순으로 저장됨.
							// ex) N=3인 경우 {1, 8, 9, 9};
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			shellNum = (N+1)/2;
			shellInfo = new int[shellNum*2];
			
			// shellInfo 계산
			// N=5일 때
			//  ** shell 정보 = {1, 16,/ 17, 24,/ 25, 24 (25여야 하지만, 구현 때문에 24)};
			shellInfo[0] = 1;
			shellInfo[1] = 4*(N-1);
			for(int shell=1; shell<shellNum; shell++) {
				int idx = 2*shell;
				shellInfo[idx] = shellInfo[idx-1] +1;
				shellInfo[idx+1] = shellInfo[idx] + 4*(N-shell*2 -1) - 1;
				// 홀수는 어쩔 수 없이 마지막 최대 숫자가 -1 됨
			}			
			
			System.out.println("#" + t + " ");
			
			// 껍질 출력하기
			// 1. 가장 바깥 껍질 : 4*(N-1)개 숫자 => 16까지 들어감
			// 2. 껍질 번호 (s): min(y, x)
			// 3. [y][x]의 숫자 :
			//		(역순이란, 껍질의 최대 숫자에서부터 감소하는 것)
			//		(정순이란, 껍질의 최소 숫자에서부터 증가하는 것)
			//    3-1. 껍질의 첫번째 행 (s == y)인 경우 --->  정순
			//    3-2. 껍질의 마지막 행(N-1-s == y)인 경우 --->  역순
			//    3-3. 그 외 중간 행(s+1 < y < N-2-s)인 경우
			//		3-3-1. 껍질의 첫번째 열(x == s)인 경우 ---> 역순
			//		3-3-2. 껍질의 마지막 열(x == N-2-s)인 경우 ---> 정순
			// 4. 정순인 경우, 껍질의 최소 숫자++;
			//    역순인 경우, 껍질의 최대 숫자--;
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					int shell = Math.min(Math.min(x, y), Math.min(N-1-x, N-1-y)); // 현재 shell 번호
					// #3-1
					if(shell == y) { // 정순
						System.out.print(shellInfo[2*shell]);
						shellInfo[2*shell]++;
					} 
					// #3-2
					else if(y == N-1-shell) { // 역순
						System.out.print(shellInfo[2*shell+1]);
						shellInfo[2*shell+1]--;
					} 
					// #3-3
					else {
						if(x == shell) { // 역순
							System.out.print(shellInfo[2*shell+1]);
							shellInfo[2*shell+1]--;
						} else { // 정순
							System.out.print(shellInfo[2*shell]);
							shellInfo[2*shell]++;							
						}
					}
					System.out.print(" ");
				}
				System.out.println();
			}			
		}
	}		
}