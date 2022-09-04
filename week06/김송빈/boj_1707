package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1707 {
	static int k,v,e;
	static int []visited;
	static List<List<Integer>>list;
	static Queue<Integer>q;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		k=Integer.parseInt(br.readLine());
		
		for(int i=0;i<k;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			v=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			visited=new int[v+1];
			list=new ArrayList<>();
			
			for(int j=0;j<v+1;j++) {
				list.add(new ArrayList<>());
			}
			
			for(int j=0;j<e;j++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				list.get(x).add(y);
				list.get(y).add(x);
			}
			
			bfs();
		}
	}
	static void bfs() {
		q=new ArrayDeque<Integer>();
		for(int i = 1; i <= v; i++) {
			if(visited[i] == 0) {
				q.add(i);
				visited[i] = 1;
			}

			while(!q.isEmpty()) {
				int x = q.poll();
				
				for(int j = 0; j < list.get(x).size(); j++) {
					if(visited[list.get(x).get(j)] == 0) {
						q.add(list.get(x).get(j));
					}
					
					if(visited[list.get(x).get(j)] == visited[x]) {
						System.out.println("NO");
						return;
					}

					if(visited[x] == 1 && visited[list.get(x).get(j)] == 0)
						visited[list.get(x).get(j)] = 2;
					else if(visited[x] == 2 && visited[list.get(x).get(j)] == 0)
						visited[list.get(x).get(j)] = 1;
				}
			}
		}

		System.out.println("YES");
	}

}
