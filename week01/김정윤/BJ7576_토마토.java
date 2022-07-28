package week1.김정윤;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576_토마토 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] box;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("BJ7576_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = (int)br.readLine().charAt(0) - '0';
        
        for (int testCase = 0; testCase < T; testCase++) {
        	st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            box = new int[N][M];
            
            // 토마토 박스 세팅
            setTomatoBox(br);
            
            // 결과 출력
            System.out.println(printResult());
		}
        
    }

	private static void setTomatoBox(BufferedReader br) throws IOException {
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for (int j = 0; j < M; j++) {
		        box[i][j] = Integer.parseInt(st.nextToken());
		        // 익은 토마토 위치 확인
		        if (box[i][j] == 1) {
		            q.add(new int[]{i, j});
		        }
		    }
		}
	}

    private static int printResult() {
    	// 토마토 숙성
        tomatoRipening();

        int max = 0;
        if (isAnyUnripedTomato()) { // 존재하는 토마토가 모두 익지 못하는 상황인 경우
            return -1;
        } else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    max = Math.max(max, box[i][j]); // 토마토가 모두 익을때 까지의 날짜
                }
            }
            return max - 1;
        }
    }

	private static void tomatoRipening() {
		while (!q.isEmpty()) {
			// 현재 위치 세팅
            int[] current = q.poll();
            int cX = current[0];
            int cY = current[1];
            
            for (int i = 0; i < 4; i++) {
            	// 다음 위치 = 현재 위치 + 상하좌우
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                if (nX >= 0 && nX < N && nY >= 0 && nY < M) { // 박스 사이즈를 넘어가지 않으면서
                	if (box[nX][nY] == 0) { // 위치에 덜익은 토마토가 존재하는 경우
                        box[nX][nY] = box[cX][cY] + 1; // 다음 상태 = 현재 상태 + 1
                        q.add(new int[]{nX, nY});
                    }
                }
            }
            
        }
	}

    private static boolean isAnyUnripedTomato() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0)
                    return true;
            }
        }
        return false;
    }
}
