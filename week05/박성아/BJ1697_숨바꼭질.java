package boj;

import java.util.*;
import java.io.*;

public class Main{
	
	static int N, K;
	static int[] arr = new int[100001];
	static int ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs(N);
		
		System.out.println(arr[K]);
	}
	
	public static void bfs(int start)
	{
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty())
		{
			int now = q.poll();
			if(now == K) break;
			int moves[] = {now-1, now+1, now*2};
			
			for(int i=0;i<3;i++)
			{
				int next = moves[i];
				if(next>=0 && next<=100000 && arr[next] == 0)
				{
					q.add(next);
					arr[next] = arr[now] + 1;
				}
			}
		}
	}
}