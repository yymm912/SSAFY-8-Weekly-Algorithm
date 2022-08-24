package forStudy.week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// #1. 궁수의 자리를 조합으로 구한다 mC3
// #2. 게임을 진행한다.
// 	 - 모든 궁수는 동시에 적을 공격한다.
//   - 궁수가 공격하는 적은 거리가 D이하인 적 중 가장 가까운 적이고 여럿일 경우 왼쪽을 공격
//   - 공격받은 적은 게임에서 제외된다.
//   - 한번의 공격이 끝나면 적은 한칸 아래로 이동한다.(턴+1)
// #3. 모든 적이 N행을 벗어나면 게임이 종료된다.

public class BJ17135_캐슬디펜스 {

	static int N, M, D, max, res;
	static int[][] map; 	// 좌표를 담을 배열
	static int[][] tmp; 	// 원본 맵의 카피본(깊은 복사)을 담을 배열
	static int[] src;		// 궁수가 배정받을 수 있는 인덱스 (0~M-1)
	static int[] arIdx; 	// 궁수가 N행에 위치하는 인덱스를 담을 배열
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		src = new int[M];
		for(int i=0; i<M; i++) {
			src[i] = i;
		}
	
		res = 0;
		arIdx = new int[3];
		comb(0, 0);
		System.out.println(res);
	}
	
	// 궁수 자리 선정
	static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx == 3) {
			init();  // 초기화
			play();  // 게임 시작
			res = Math.max(res, max);
			return;
		}
		
		for(int i=srcIdx; i<M; i++) {
			arIdx[tgtIdx] = src[i];
			comb(i + 1, tgtIdx+1);
		}
	}
	
	static void init() {
		// max값 초기화
		max = 0;
		
		// 기존 map 복사
		tmp = new int[N+1][M];
		for(int i=0; i<N+1; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
		}
		// 궁수 자리배치
		for(int i=0; i<3; i++) { 
			tmp[N][arIdx[i]] = 2;
		}
	}
	
	// 게임 진행
	static void play() {
		// 1.궁수 다 같이 적 공격
		// 궁수가 같은 적을 공격할 수 있음
		boolean[][] attack = new boolean[N][M];
		for(int a=0; a<3; a++) {
			// 제일 가까운 적 구하기
			int minDist = Integer.MAX_VALUE;
			int minR = Integer.MAX_VALUE;
			int minC = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(tmp[i][j] == 1) {
						int tmpDist = dist(N, arIdx[a], i, j);
						if(minDist > tmpDist) {
							minDist = tmpDist;
							minR = i;
							minC = j;
						} else if(minDist == tmpDist) {
							if(minC > j) {
								minR = i;
								minC = j;
							}
						}
					}
				}
			}
			if(minDist <= D) {
				// 각 궁수의 제일 가까운 적 공격 체크
				attack[minR][minC] = true;
			}
		}
		// 2.한 턴 끝나면 공격받은 적 제거 및 수 누적 
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(attack[i][j]) {
					tmp[i][j] = 0;
					max++;
				}
			}
		}
		
		// 3.적 위치 한칸 내리기
		for(int i=N-1; i>0; i--) {
			System.arraycopy(tmp[i-1], 0, tmp[i], 0, M);
		}
		Arrays.fill(tmp[0], 0);
		
		// 4.맵에 더이상 적이 없으면 게임 종료
		if(!isEmpty()) {
			play();
		} else {
			return;
		}
	}
	
	static boolean isEmpty() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tmp[i][j] == 1) return false;
			}
		}
		return true;
	}
	
	static int dist(int ax, int ay, int ex, int ey) {
		return Math.abs(ax-ex) + Math.abs(ay-ey);
	}
}