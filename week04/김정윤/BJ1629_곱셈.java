package week4.김정윤;

import java.io.*;
import java.util.*;

public class BJ1629_곱셈 {
static long A, B, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(pow(A,B));
		
	}
	
	// 10^11 = 10^5 * 10^5 * 10 = (((10*10)*(10*10)*10) * ((10*10)*(10*10)*10) * 10)
	// (10*11)%12 = (10%12 * 10%12) % 12
	static long pow(long A, long B) {
		if (B == 1) {
			return A % C;
		}
		
		// 지수의 절반에 해당하는 값만 확인
		long tmp = pow(A, B/2);
		
		// 지수가 홀수면 한번 더 곱해줘야함
		if (B%2 == 1) {
			// (tmp * tmp) * tmp
			return (tmp * tmp % C) * A % C;
		}
		return tmp * tmp % C;
	}

}