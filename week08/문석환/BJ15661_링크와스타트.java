import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 번호 : 15661
// 난이도 : 실버2
// 제목 : 링크와 스타트
// https://www.acmicpc.net/problem/15661
// N/2개를 순서없이 중복없이 뽑는다. -> 조합
// N/2개를 뽑고 사용한
public class Main {
	static int N,ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N];

		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// read end

		comb(0,0);

		System.out.println(ans);
	}

	static void comb(int k,int start) {
		if(k == N/2) {
			int teamA = 0;
			int teamB = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visit[i] && visit[j]) {
						teamA += map[i][j];
					}else if(!visit[i] && !visit[j]) {
						teamB += map[i][j];
					}
				}
			}

			ans = Math.min(ans, Math.abs(teamA-teamB));
			return;
		}

		for(int i=start;i<N;i++) {
			visit[i] = true;
			comb(k+1,i+1);
			visit[i] = false;
		}
	}
}
