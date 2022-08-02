import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main2{
	static int N;
	static int searchN;			// 찾아야 할 숫자
	static int[] shellMinInfo; 	// 각 껍질의 최소 숫자
	static int[] shellMaxInfo; 	// 각 껍질의 최대 숫자
	public static void main(String[] args) throws Exception {
		/**
		 * 규칙 찾기
		 * 1. 표의 총 숫자 개수 : N^2
		 * 2. 가장 바깥 껍질의 최대, 최소 숫자 구하기 --> shellInfo
		 * 3. 좌표에 따른 껍질 번호 = min(x, y, N-1-x, N-1-y)
		 * 4. 껍질에 따른 숫자
		 * 		4-1. 첫번째 행 (s == y)
		 * 			- 첫번째 열: 역순
		 * 			- 나머지 열: 정순
		 * 		4-2. 끝 행 (N-1-s == y)
		 * 			- 전체 열 : 역순
		 * 		4-3. 나머지 행
		 * 			- 첫번쨰 열: 역순
		 * 			- 끝 열: 정순
		 * */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		searchN = Integer.parseInt(br.readLine());
		
		int shellNum = (N+1)/2; // 총 껍질 개수
		/**16 5 6 7
		 * 15 4 1 8
		 * 14 3 2 9
		 * 13 12 11 10 */
		shellMinInfo = new int[shellNum];	// 각 껍질의 최소 숫자: N=4일 때 [5, 1]  (N=홀수일 때 마지막 이상한 숫자 들어가는데, 무시해도 됨)
		shellMaxInfo = new int[shellNum];	// 각 껍질의 최대 숫자: N=4일 때 [16, 4]
		shellMaxInfo[0] = N*N;
		shellMinInfo[0] = shellMaxInfo[0] - 4*(N-1) + 1; 
		// 각 껍질에 들어가는 숫자 개수(num) : 4 * (N-1-2*s)
		for(int s=1; s<shellNum; s++) {
			int num = 4*(N-1-2*s);
			shellMaxInfo[s] = shellMinInfo[s-1] - 1;
			shellMinInfo[s] = shellMaxInfo[s] - num + 1;
		}
		
		// 출력 성능 때문에 BufferedWriter를 써야 함.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int sx=0, sy=0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				int shell = Math.min(Math.min(x, y), Math.min(N-1-x, N-1-y));
				int curNum = 0;
				if(shell == y) { // 껍질의 첫번째 행
					if(x == shell) { // 껍질의 첫번째 열
						curNum = shellMaxInfo[shell];
						bw.write(shellMaxInfo[shell]-- + " "); // 역순인 경우 : 각 껍질의 Max값에서 차감하면서 진행
					}
					else {
						curNum = shellMinInfo[shell];
						bw.write(shellMinInfo[shell]++ + " "); // 정순인 경우 : 각 껍질의 Min값에서 증가하면서 진행
					}					
				}
				else if(N-1-shell == y) { // 껍질의 마지막 행
					curNum = shellMaxInfo[shell];			
					bw.write(shellMaxInfo[shell]-- + " ");								
				}
				else { // 껍질의 나머지 행
					if(x == shell) { // 껍질의 첫번째 열
						curNum = shellMaxInfo[shell];
						bw.write(shellMaxInfo[shell]-- + " ");												
					}
					else {
						curNum = shellMinInfo[shell];						
						bw.write(shellMinInfo[shell]++ + " ");						
					}
				}
				if(curNum == searchN) { // 찾는 숫자이면 저장
					sx = x;
					sy = y;
				}
			}
			bw.write("\n");
		}
		bw.write((sy+1) + " " + (sx+1)+"\n");
		bw.flush();
	}
}