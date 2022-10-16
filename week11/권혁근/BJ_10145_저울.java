import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;



public class Main {
	
	static int N,M;
	static List<Integer>[] bigs,smalls;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		bigs=new List[N+1];
		smalls=new List[N+1];
		for(int i=1; i<=N; i++) {
			bigs[i]=new ArrayList<>();
			smalls[i]=new ArrayList<>();
			
		}
		int[] counts=new int[N+1];
		for(int i=0; i<M; i++) {
			stn=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(stn.nextToken());
			int n2=Integer.parseInt(stn.nextToken());
			bigs[n1].add(n2);
			smalls[n2].add(n1);
		}
		
		for(int sn=1; sn<=N; sn++) { //측정할 노드
            //큰 수들 갯수세기
			Deque<Integer> que=new ArrayDeque<>();
			que.add(sn);
			boolean[] visited=new boolean[N+1];
			visited[sn]=true;
			while(!que.isEmpty()) {
				int cn=que.pollFirst();
				for(int nn:bigs[cn]) {
					if(visited[nn]) continue;
					visited[nn]=true;
					counts[sn]++;
					que.add(nn);
				}
			}
			
            //작은 수들 갯수세기
			que=new ArrayDeque<>();
			que.add(sn);
			visited=new boolean[N+1];
			visited[sn]=true;
			while(!que.isEmpty()) {
				int cn=que.pollFirst();
				for(int nn:smalls[cn]) {
					if(visited[nn]) continue;
					visited[nn]=true;
					counts[sn]++;
					que.add(nn);
				}
			}
		}
		//System.out.println(Arrays.toString(counts));
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(N-counts[i]-1).append("\n");
		}
		System.out.println(sb);
	}


}
