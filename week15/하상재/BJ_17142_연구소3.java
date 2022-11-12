package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17142_연구소3 {
	
	static int N, M, zero_cnt, answer;
	static int[][] original, map;
	static Queue<int[]> queue;
	static List<int[]> list;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new ArrayList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		original = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp==0) zero_cnt++;
				
				if(tmp==2) {
					list.add(new int[] {i,j});
					original[i][j] = -1;
					zero_cnt++;
				}
				else original[i][j] = tmp;
			}
		}
				
		answer = Integer.MAX_VALUE;
		backTrack(0, 0, new int[M]);
		
		if(answer==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);

	}
	
	static void backTrack(int depth, int val, int[] arr) {
		
		if(depth==M) {
			map_maker(arr);
			int tmp = process();
			
			answer = Math.min(answer, tmp);
			return;
		}
		
		if(val>=list.size()) return;
		
		arr[depth] = val;
		
		backTrack(depth+1, val+1, arr);
		backTrack(depth, val+1, arr);
		
	}
	
	static void map_maker(int[] arr) {
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			map[i] = original[i].clone();
		}
		
		queue = new ArrayDeque<>();
		
		for(int i=0; i<M; i++) {
			int[] tmp = list.get(arr[i]);
			map[tmp[0]][tmp[1]] = 1;
			queue.add(list.get(arr[i]));
		}
		
	}
	
	
	static int process() {
		int zero = zero_cnt-M;
		int max = 1; 
		while(!queue.isEmpty()) {
			
			int[] rc = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nr = rc[0] + dr[i];
				int nc = rc[1] + dc[i];
				
				if(nr <0 || nc <0|| nr>=N || nc>=N) continue;
				
				if(map[nr][nc]==-1) {
					map[nr][nc] = map[rc[0]][rc[1]]+1;
					zero--;
					queue.add(new int[] {nr, nc});
				}
				else if(map[nr][nc]==0) {
					map[nr][nc] = map[rc[0]][rc[1]]+1;
					max = Math.max(map[nr][nc], max);
					zero--;
					queue.add(new int[] {nr, nc});					
				}
				
			}	
		}
		
		if(zero>0) return Integer.MAX_VALUE;
		else return max-1;
		
	}
	
}