package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
	
	static int N,C;
	static int[] home;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		home=new int[N];
		for (int i=0; i<N; i++) 
			home[i]=Integer.parseInt(br.readLine());
		
		Arrays.sort(home);	//이분 탐색을 위한 정렬
		
		int low=1;
		int high=home[N-1]-home[0]+1; 
		int mid=(low+high)/2;
		
		while (low<high) {
			mid=(low+high)/2;
			
			if (canInstall(mid)<C) {
				high=mid;
			}
			else
				low=mid+1;
		}
		
		//System.out.println(high);
		System.out.println(low-1);
	}
	
	// 두 집 간 거리가 dist일 때, 몇 개의 공유기를 설치할 수 있는지
	private static int canInstall (int dist) {
		int count=1;
		int prev=home[0];
		
		for (int i=1; i<home.length; i++) {
			int now=home[i];
			
			if (now-prev>=dist) {
				count++;
				prev=now;
			}
		}
		return count;
	}
}
