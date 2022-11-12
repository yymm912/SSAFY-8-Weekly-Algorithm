import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2112_보호필름 {

	static int T, D, W, K, res;
	static int[][] film; // 원본 필름
	static int[][] filmCopy; // 필름 복사본
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			filmCopy = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
					filmCopy[i][j] = film[i][j];
				}
			} // 입력 끝
			
			res = 14;
			
			if(K == 1) {
				System.out.println("#" + t + " 0");
			} else {
				insert(0, 0);
				System.out.println("#"+ t + " " + res);
			}
		}
	}

	static void insert(int idx, int cnt) {
		if(cnt > res) return;
		
		if(test()) {
			res = Math.min(res, cnt);
			return;
		}
		
		if(idx == D) return;
		
		insert(idx+1, cnt);  // 약품 x
		
		Arrays.fill(filmCopy[idx], 0);  // 약품 A
		insert(idx + 1, cnt + 1);
		
		Arrays.fill(filmCopy[idx], 1);  // 약품 B
		insert(idx + 1, cnt + 1);
		
		copy(idx);  // 바꾼 열 원상복구
	}
	
	static boolean test() { // 검사
		
		boolean ok = true;
		
		for (int j = 0; j < W; j++) {
			int cnt = 1;
			for (int i = 1; i < D; i++) {
				if(filmCopy[i-1][j] == filmCopy[i][j]) {
					cnt++;
				} else {
					cnt = 1;
				}
				if(cnt >= K) break;
			}
			if(cnt < K) {
				ok = false;
				break;
			}
		}
		
		return ok;
	}
	
	static void copy(int i) {
		for (int j = 0; j < W; j++) {
			filmCopy[i][j] = film[i][j];
		}
	}
}
