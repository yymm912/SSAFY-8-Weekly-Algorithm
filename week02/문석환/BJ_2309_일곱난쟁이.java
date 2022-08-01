import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준 2309번 일곱 난쟁이
//조건
//일곱 난쟁이의 키 합은 100
//아홉 난쟁이 중 진짜 일곱 난쟁이를 찾기
//입력
//아홉개의 줄에 걸쳐 난쟁이들의 키가 주어짐
//완전 탐색 -> 중복 불가능
public class BJ_2309_일곱난쟁이 {
	static FastReader sc = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int[] S,selected;
	static int sum;
	static boolean[] visit;
	static void input() {
		S = new int[9];
		for(int i=0;i<9;i++) {
			S[i] = sc.nextInt();
		}
		selected = new int[7];
		visit = new boolean[9];
	}

	static void pro(int k) {
		if(k == 7) {
			sum = 0;
			for(int i=0;i<7;i++) {
				sum += selected[i];
			}

			if(sum == 100) {
				Arrays.sort(selected);
				for(int i=0;i<7;i++) {
					System.out.println(selected[i]);
				}
				System.exit(0);
			}
		}else {
			for(int i=0;i<9;i++) {
				if(visit[i]) continue;
				selected[k] = S[i];
				visit[i] = true;
				pro(k+1);
				selected[k] = 0;
				visit[i] = false;
			}
		}


	}
	public static void main(String[] args) {
		input();
		pro(0);
	}

	static class FastReader{
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

