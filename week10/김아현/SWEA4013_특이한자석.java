package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 규칙
 * 1. 붙어있는 자석은 붙어있는 날의 자성과 다를 경우에만 반대방향으로 1칸 회전
 * 2. 모든 회전 끝난 후, 각 자석에 대해 빨간색 화살표 위치에 있는 날이 S극일 때 {1, 2, 4, 8}점을 획득
 * 3. 시계방향 회전을 1로, 반시계방향 회전을 -1로 / 날의 자성은 N극을 0으로, S극은 1로 주어진다.
 * 4. 각 자성의 날 자성정보는 시계방향으로 주어진다.
 * 
 * 고려할 점
 * 1. 화살표가 있는 날의 번호는 0, 양옆과 붙어있는 날의 번호는 왼쪽 6 오른쪽 2
 * 2. 회전처리 => 시계방향일 경우 맨 뒤의 값이 맨 앞으로, 반시계방향일 경우 맨 앞의 값이 맨 뒤로
 * 3. 회전의 연쇄는 동시적으로 일어남 => 맞닿은 날의 자성정보와 회전방향을 바탕으로, 각 자석의 회전방향 담아 한번에 돌리기
 * */

public class SWEA4013_특이한자석 {
	
	static int T, K, res;
	static List<List<Integer>> mags;	// 자석 정보를 담을 리스트
	static int[] rotate;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			
			mags = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				mags.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					mags.get(i).add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				
				// 자석 회전
				rotate = new int[4];
				rotate[idx] = d;
				setLeft(idx - 1);
				setRight(idx + 1);
				rotateMag();
			}
			
			res = 0;
			for (int i = 0; i < 4; i++) {
				if(mags.get(i).get(0) == 1)
					res += Math.pow(2, i);
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void setLeft(int idx) {
		if(idx < 0) return;
		
		int prev = mags.get(idx+1).get(6);
		int cur = mags.get(idx).get(2);
		
		if (rotate[idx+1] == 0)
			rotate[idx] = 0;
		else if (prev == cur)
			rotate[idx] = 0;
		else
			rotate[idx] = rotate[idx+1] == 1 ? -1 : 1;
		
		setLeft(idx-1);
	}
	
	static void setRight(int idx) {
		if(idx > 3) return;
		
		int prev = mags.get(idx-1).get(2);
		int cur = mags.get(idx).get(6);
		
		if (rotate[idx-1] == 0)
			rotate[idx] = 0;
		else if (prev == cur)
			rotate[idx] = 0;
		else
			rotate[idx] = rotate[idx-1] == 1 ? -1 : 1;
		
		setRight(idx+1);
	}
	
	static void rotateMag() {
		for (int i = 0; i < 4; i++) {
			if(rotate[i] == 0) continue;
			
			List<Integer> mag = mags.get(i);
			if(rotate[i] == 1) {
				int last = mag.remove(7);
				mag.add(0, last);
			} else if(rotate[i] == -1) {
				int first = mag.remove(0);
				mag.add(first);
			}
		}
	}
}
