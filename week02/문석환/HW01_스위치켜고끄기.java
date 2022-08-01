package boj.p1244;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1244번 스위치 켜고 끄기
public class HW01_스위치켜고끄기 {
	static FastReader sc = new FastReader("src/boj/p1244/input.txt");
	static StringBuilder sb = new StringBuilder();
	static int switchCnt,studentCnt,S,num;
	// switchCnt : 스위치개수
	// studentCnt : 학생 수
	// S : 성별 (1 : 남 , 2 : 여)
	// num : 학생이 받은 수
	static int[] switchs; // 스위치 상태

	static void input() {
		switchCnt = sc.nextInt();
		switchs = new int[switchCnt+1];
		for(int i=1;i<=switchCnt;i++) {
			switchs[i] = sc.nextInt();
		}
		studentCnt = sc.nextInt();
	}


	static void pro(int sex,int num) {
		// 학생의 성별과 숫자를 입력 받으면 pro메서드 호출
		if(sex == 1) {
			// 남자인 경우
			for(int i=1;i<=switchCnt;i++) {
				if(i % num == 0) {
					switchs[i] = switchs[i] == 0 ? 1 : 0;
				}
			}
		}else if(sex == 2){
			// 여자인 경우
			int cnt = 1;
			while(true) {
				// num-cnt가 범위를 벗어나면 break
				if(num-cnt < 1 || num+cnt > switchCnt)break;
				// num-cnt의 값과 num+cnt의 값이 같지 않으면 break
				if(switchs[num-cnt] != switchs[num+cnt])break;
				cnt++;
			}

			// num-cnt+1 ~ num+cnt-1 까지의 범위의 스위치 값 변경
			for(int i=num-(cnt-1);i<=num+cnt-1;i++) {
				switchs[i] = switchs[i] == 0 ? 1 : 0;
			}
		}
	}

	public static void main(String[] args) {
		input();
		for(int i=0;i<studentCnt;i++) {
			S = sc.nextInt();
			num = sc.nextInt();
			pro(S,num);
		}

		for(int i=1;i<=switchCnt;i++) {
			System.out.printf("%d ",switchs[i]);
			if(i % 20 == 0) System.out.println();
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(new File(s)));
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String next() {
			if(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		Integer nextInt() {
			return Integer.parseInt(next());
		}
	}
}
