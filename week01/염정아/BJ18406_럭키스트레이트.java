package problem.BJ;


import java.io.FileInputStream;
import java.util.Scanner;

//123402
//-> LUCKY
//
//7755
//-> READY


public class BJ18406_럭키스트레이트 {
	static String N;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.next();
		// System.out.println(N);

		int sum1 = 0, sum2 = 0;
		for (int i = 0, idx = 0; i < N.length(); i++, idx++) {
			if (idx < N.length() / 2) sum1 += N.charAt(i) - '0';
			else sum2 += N.charAt(i) - '0';
		}

		if (sum1 == sum2) System.out.println("LUCKY");
		else System.out.println("READY");

	}
}
