import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 13300번 방배정 문제
// 조건
// 1. 한 방에는 같은 학년의 학생들을 배정
// 2. 한 방에는 같은 성별의 학생들을 배정
// 3. 한 방에 1명만 배정하는 것도 가능
// 입력
// N : 학생 수
// K : 한 방에 배정할 수 있는 최대 인원 수
// S : 성별 (여 : 0 , 남 : 1)
// Y : 학년 (1 <= Y <= 6)
// 출력
// 학생들을 모두 배정하기 위해 필요한 최소한의 방의 수 : ans
public class BJ_13300_방배정 {
	static FastReader sc = new FastReader("src/boj/p13300/input/input.txt");
	static final int GRADE = 6;
	static int N,K,S,Y,ans=0;
	static ArrayList<Integer>[] a;

	static void input() {
		N = sc.nextInt();
		K = sc.nextInt();

		a = new ArrayList[GRADE+1];
		for(int i=1;i<=GRADE;i++) {
			a[i] = new ArrayList<>();
		}

		for(int i=0;i<N;i++) {
			S = sc.nextInt();
			Y = sc.nextInt();
			a[Y].add(S);
		}
	}
	static void pro() {
		// 학년마다 ArrayList를 가지고 학생의 성별을 담아줬다.
		for(int i=1;i<=GRADE;i++) {
			int len = a[i].size(),m = 0,fm =0;
			for(int j=0;j<len;j++) {
				if(a[i].get(j) == 0) fm++;
				else m++;
			}
			if(fm % K == 0) {
				ans+= fm / K;
			}else {
				ans+= fm / K + 1;
			}

			if(m % K == 0) {
				ans+= m / K;
			}else {
				ans+= m / K +1;
			}
		}
	}
	public static void main(String[] args) {
		input();
		pro();
		System.out.println(ans);
	}

	static class FastReader{
		StringTokenizer st;
		BufferedReader br;

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
