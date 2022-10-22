package week12.김정윤;

import java.io.*;
import java.util.*;

public class SWEA2477_차량정비소 {
	static int T, N, M, K, A, B, ans;
	static int[] Ai, Bi, waitTime;
	static int[][] customer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
			M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
			K = Integer.parseInt(st.nextToken()); // 방문 고객 수
			A = Integer.parseInt(st.nextToken())-1; // 지갑 분실고객 방문한 A 창구번호
			B = Integer.parseInt(st.nextToken())-1; // 지갑 분실고객 방문한 B 창구번호
			
			// 접수창구
			Ai = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i =0; i< N; i++) {
				Ai[i] = Integer.parseInt(st.nextToken());
			}
			
			// 정비창구
			Bi = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i< M ; i++) {
				Bi[i] = Integer.parseInt(st.nextToken());
			}
			
			customer = new int[K][3]; // 방문 시간 / 고객 번호 / 이용 창구
			st = new StringTokenizer(br.readLine());
			for(int i =0; i< K ;i++) {
				customer[i][0] = Integer.parseInt(st.nextToken()); // 방문 시간
				customer[i][1] = i+1; // 고객 번호
			}
			
			ans = -1; 
			waitTime = new int[N]; // 대기시간
			
			// 고객 입장 시작
			// 접수 창구 대기시간 설정
			for(int k = 0; k < K ;k++) { 
				int tellr = 0; 
				for(int n = 0; n < N; n++) {
					if(waitTime[n] <= customer[k][0]) {
						tellr = n; 
						break;
					} else if(waitTime[n] < waitTime[tellr]) {
						tellr = n;
					}
				}
				
				customer[k][2] = tellr; // 사용한 창구 번호
				// 고객이 방문한 시간에 대기해야 하는 경우 or 바로 접수할 수 있는 경우
				waitTime[tellr] = (waitTime[tellr] < customer[k][0]) ? customer[k][0] + Ai[tellr] : waitTime[tellr] + Ai[tellr];
				customer[k][0] = waitTime[tellr];
			}
			
			// 방문 시간으로 정렬. 같을 경우 대기시간 순으로 정렬.
			Arrays.sort(customer, (c1, c2) -> ((c1[0] != c2[0]) ? c1[0] - c2[0] : c1[2] - c2[2]));
			
			waitTime = new int[M];
			
			// 수리 창구 대기시간 설정
			for (int i = 0; i < K; i++) {
				int teller = 0; 
				for (int n = 0; n < M; n++) {
					if (waitTime[n] <= customer[i][0]) {
						teller = n;
						break;
					} else if(waitTime[n] < waitTime[teller]) {
						teller = n;
					}
				}
				
				waitTime[teller] = (waitTime[teller] < customer[i][0]) ? customer[i][0] + Bi[teller] : waitTime[teller] + Bi[teller];
				if(customer[i][2] == A && teller == B) { // 지갑 잃어버린 고객이 사용한 창구를 모두 사용한 고객의 경우
					ans += customer[i][1];
				}
			}
			System.out.println("#" + tc + " " + (ans == 0 ? -1 : ans));
		}
	}
}
