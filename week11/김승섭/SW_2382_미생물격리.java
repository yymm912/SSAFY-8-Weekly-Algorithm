import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SW_2382_미생물격리 {
	static int T, N, M, K;
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };

	static class Micro implements Comparable<Micro>{
		int idx, y, x, num, dir;
		/**
		 * @param idx: 셀 바깥 좌표를 이용한 군집의 고유 위치 인덱스
		 * @param y  : 군집의 y좌표
		 * @param x  : 군집의 x좌표
		 * @param num: 군집의 규모
		 * @param dir: 군집의 진행 방향
		 */
		public Micro(int idx, int y, int x, int num, int dir) {
			this.idx = idx;
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}
		// list를 sort하여 곂친 군집들을 내림차순으로 정렬시키고,
		// 규모가 큰 군집만 비교하여 거를수 있도록 만든다.
		
		// sort를 사용하기 위한 overriding.
		// 두 군집 객체의 index값이 같다(위치가 같다)면 크기의 차이로써 정렬한다.
		// 다르다면 index 순으로 정렬한다.
		@Override
		public int compareTo(Micro o) {
			if(this.idx == o.idx) return o.num - this.num;
			return this.idx - o.idx;
		}
	}
	// ArrayList를 활용해 동적인 size를 다룰 수 있도록 한다.
	static List<Micro> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 셀의 개수
			M = Integer.parseInt(st.nextToken()); // 격리시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수
			
			list = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list.add(new Micro(y * N + x, y, x, num, dir));
				/*
				 * idx param은 범위 밖 좌표로 idx값을 지정하여 미생물끼리 겹치는지 비교하기 위한 용도이다.
				 */

			}

			for (int i = 0; i < M; i++) {
				/**********한 시간 동안의 진행**********/
				for (int m = 0; m < list.size(); m++) {
					Micro micro = list.get(m);
					micro.y += dy[micro.dir];
					micro.x += dx[micro.dir];
					micro.idx = (micro.y * N) + micro.x;
					
					// 만약 군집이 가장자리의 약품에 도달한다면,
					if (micro.y == 0 || micro.x == 0 || micro.y == N - 1 || micro.x == N - 1) 
					{
						// 군집의 규모를 2로 나누고 방향을 반대로 바꾼다.
						micro.num /= 2;
						micro.dir = changeDir(micro.dir);
						
						// 군집이 소멸한다면 list에서 제거한다.
						if (micro.num == 0) 
						{
							list.remove(m);
							m--;
						}
					}
				}
				// 겹치는 군집을 정리하기 위해 index 순으로 내림차순으로 정렬한다.
				Collections.sort(list);
				
				// 오름차순으로 정렬된 index순의 군집 객체들을 비교한다.
				// 현재 군집과 그 다음 군집을 비교하여 index가 같다면 위치가 같으므로,
				// 더 큰 규모인 현재 군집을 기준으로 두 군집을 합친다.
				// 이후 다음 군집들을 확인한다.
				for (int now = 0; now < list.size()-1; now++) {
					Micro cur = list.get(now);
					Micro next = list.get(now+1);
					
					if(cur.idx == next.idx) {
						cur.num += next.num;
						list.remove(now+1);
						now--;
					}
					
				}
				
				/**********한 시간 동안의 진행**********/
			}
			
			int ans = 0;
			for (int i = 0; i < list.size(); i++) {
				ans += list.get(i).num;
			}
			
			System.out.println("#"+t+" "+ans);

		}
	}

	private static int changeDir(int dir) {
		switch(dir) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		}
		return -1;
	}

}
