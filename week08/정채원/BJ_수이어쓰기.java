import java.io.*;
import java.util.*;

public class Main{
	static int N, k;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// 총 9자리까지 주어짐
		// 각 자릿수가 주어졌을때, 몇개 낱개 숫자를 포함하는지 구해둠
//		189
//		2889
//		38889
//		488889
//		5888889
//		68888889
//		788888889
		int[] pos = new int[10];
		pos[0] = 0;
		pos[1] = 9;
		for(int i=2; i<10; i++) {
			pos[i] = pos[i-1] * 10;
		}
		for(int i=2; i<9; i++) {
			pos[i] *= i;
			pos[i] += pos[i-1];
		}
		 
		int idx = 1; // 자릿수
		while(idx < 10) {
			if( k - pos[idx] <= 0) break;
			idx++;
		}
		k -= pos[idx-1];
		
		int basis = 1;
		for(int i=0; i<idx-1; i++) basis *= 10;
		int num = basis + ( (k-1)/idx ); // 해당되는 숫자를 구함
		
		if(N < num) {
			System.out.println(-1);
			return;
		}
		int numIdx = idx-1 - (k-1) % idx; // 해당 숫자의 몇번쨰 자리 숫자인지 구함
		
		while(numIdx-- > 0) num /= 10;
		System.out.println(num % 10);
	}
}