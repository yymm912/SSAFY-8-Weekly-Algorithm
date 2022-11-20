package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1486_장훈이의높은선반 {
	static int n, h;
	static int[] nh;
	static int []tgt;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./res/swea_1486_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			nh = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nh[i] = Integer.parseInt(st.nextToken());
			}
			
			min=Integer.MAX_VALUE;
			for(int i=1;i<=n;i++) {
				tgt=new int[i];
				powerset(0,0,i);
			}
			int ans=min-h;
			System.out.println("#"+t+" "+ans);
		}
		
	
	}

	static void powerset(int tgtidx, int srcidx, int x) {
		if (tgtidx == x) {
			int cnt=0;
			for(int i=0;i<x;i++) {
				if(cnt>=min)return ;
				cnt+=nh[tgt[i]];
			}
			if(cnt>=h)min=Math.min(min, cnt);
			return;
		}
		if(srcidx>=n)return;
		
		tgt[tgtidx]=srcidx;
		powerset(tgtidx+1,srcidx+1,x);
		powerset(tgtidx,srcidx+1,x);
	}
}
