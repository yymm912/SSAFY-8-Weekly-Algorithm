package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_5014 {
	static int f,s,g,u,d;

	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		f=Integer.parseInt(st.nextToken());
		s=Integer.parseInt(st.nextToken());
		g=Integer.parseInt(st.nextToken());
		u=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		int []visited=new int[f+1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		Queue<Integer>q=new ArrayDeque<Integer>();
		q.offer(s);
		visited[s]=0;

		while(!q.isEmpty()) {
			int x=q.poll();
			if(x==g)break;
			if(x-d>=1&&visited[x-d]==Integer.MAX_VALUE) {
				visited[x-d]=visited[x]+1;
				q.offer(x-d);
			}
			if(x+u<=f&&visited[x+u]==Integer.MAX_VALUE) {
				visited[x+u]=visited[x]+1;
				q.offer(x+u);
			}
		}
		System.out.println(visited[g]==Integer.MAX_VALUE?"use the stairs":visited[g]);
		
	}
	
}
