package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3020 {
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int h=Integer.parseInt(st.nextToken());
		int []a=new int[h+2];
		int []b=new int[h+2];
		
		for(int i=0;i<n/2;i++) {
			a[Integer.parseInt(br.readLine())]++;
			b[h-Integer.parseInt(br.readLine())+1]++;
		}
		for(int i=1;i<=h;i++) {
			a[i]+=a[i-1];
		}
		for(int i=h;i>=1;i--)b[i]+=b[i+1];
		
		int min=n;
		int cnt=0;
		
		for(int i=1;i<=h;i++) {
			int ans=(a[h]-a[i-1])+(b[1]-b[i+1]);
			
			if(ans<min) {
				min=ans;
				cnt=1;
			}else if(ans==min)cnt++;
		}
		System.out.println(min+" "+cnt);
	}
}
