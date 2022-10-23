package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15684_사다리조작 {
	
	static int N,M,H;
	static int[][] ladder;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		ladder = new int[H+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			ladder[M][N] = N;
			ladder[M][N+1] = N;
		}
		
		backTrack(1, 1, 1, 0);
		
		System.out.println(answer==Integer.MAX_VALUE ? -1 : answer);

	}
	
	static void backTrack(int depth, int current, int original, int change) {
		
		if(change>3) return;
		
		if(depth==H+1) {
			
			if(current==original) {
				if(current==N) {
					answer = Math.min(change,answer);
					return;
				}
				backTrack(1, current+1, original+1, change);
			}
			return;
		}
		
		if(ladder[depth][current]>0) {
			if(current==0) {
				backTrack(depth+1, current+1, original, change);
			}
			else if(current==N) {
				backTrack(depth+1, current-1, original, change);
			}
			else {
				if( ladder[depth][current-1] == ladder[depth][current]) {
					backTrack(depth+1, current-1, original, change);
				}
				else {
					backTrack(depth+1, current+1, original, change);
				}
			}
		}
		else {
			if(current==1) {
				if(ladder[depth][current+1] == 0) {
					
					ladder[depth][current]= current;
					ladder[depth][current+1]= current;
					
					backTrack(depth+1, current+1, original, change+1);
					
					ladder[depth][current]= 0;
					ladder[depth][current+1]= 0;
				}
			}
			else if(current==N) {
				if(ladder[depth][current-1] == 0) {
					
					ladder[depth][current]= current;
					ladder[depth][current-1]= current;
					
					backTrack(depth+1, current-1, original, change+1);
					
					ladder[depth][current]= 0;
					ladder[depth][current-1]= 0;
				}
			}
			else {
				if(ladder[depth][current-1] == 0) {
					
					ladder[depth][current-1] = current;
					ladder[depth][current] = current;
					
					backTrack(depth+1, current-1, original, change+1);
					
					ladder[depth][current-1] = 0;
					ladder[depth][current] = 0;
				}
				if(ladder[depth][current+1] == 0) {
					
					ladder[depth][current] = current;
					ladder[depth][current+1] = current;
					
					backTrack(depth+1, current+1, original, change+1);
					
					ladder[depth][current] = 0;
					ladder[depth][current+1] = 0;
				}
			}
			
			backTrack(depth+1, current, original, change);
			
		}

	}
	
}