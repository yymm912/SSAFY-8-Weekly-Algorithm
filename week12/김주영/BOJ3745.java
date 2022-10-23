package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3745 {

	static int T,N, max;
	static int[] p, LIS;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		String line=null;
		
		while ((line=br.readLine())!=null) {
			
			line=line.trim();
			N=Integer.parseInt(line);
			p=new int[N];
			LIS=new int[N];
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++)
				p[i]=Integer.parseInt(st.nextToken());
			
			int size=0;
			
			for (int i=0; i<N; i++) {
				int pos=Arrays.binarySearch(LIS, 0, size, p[i]);
				if (pos>=0) continue;
				
				int insertPos=Math.abs(pos)-1; // 맨뒤 or 기존 원소 대체 자리
				LIS[insertPos]=p[i];
				
				if (insertPos==size) size++;	//맨 마지막에 추가 되었다는 얘기
			}
			
			System.out.println(size);
		}
	}

}
