package problem.BJ;


import java.util.Scanner;


public class BJ1463_1로만들기 {
	static int N;
	static int cnt = 0;
	static int d[];


	public static int go(int N) {
		if (N == 1)
		    return 0;

		if (d[N] > 0)
		    return d[N];

		d[N] = go(N - 1) + 1;

		if (N % 2 == 0) {
			int tmp = go(N / 2) + 1;

			if (d[N] > tmp)
			    d[N] = tmp;
		}

		if (N % 3 == 0) {
			int tmp = go(N / 3) + 1;

			if (d[N] > tmp)
			    d[N] = tmp;
		}

		return d[N];
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		d = new int[N + 1];

		cnt = go(N);

		System.out.println(cnt);

	} // end main

} // end class
