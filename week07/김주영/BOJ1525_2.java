package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1525_2 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static final String ANS="123456780";
	
	static Map<String, Integer> map=new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();
		
		for (int i=0; i<3; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<3; j++) {
				sb.append(st.nextToken());
			}
		}

		System.out.println(bfs(sb.toString()));
	}
	
	private static int bfs (String init) {
		Queue<String> q=new ArrayDeque<>();
		q.add(init);
		map.put(init, 0);
		
		while (!q.isEmpty()) {
			String pos=q.poll();
			int cnt=map.get(pos);
			int zero=pos.indexOf('0');
			int y=zero/3;
			int x=zero%3;
			
			if (pos.equals(ANS))
				return cnt;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=3 || nx>=3) continue;
				int nPos=ny*3+nx;
				char ch=pos.charAt(nPos);
				
				String next=pos.replace(ch, 'x');
				next=next.replace('0', ch);
				next=next.replace('x', '0');
				
				if (!map.containsKey(next)) {
					q.add(next);
					map.put(next, cnt+1);
				}
			}
		}
		return -1;
	}

}
