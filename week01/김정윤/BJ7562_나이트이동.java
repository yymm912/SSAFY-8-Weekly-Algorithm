package week1.김정윤;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7562_나이트이동 {
	// 나이트가 이동할 수 있는 위치 - 상하좌우 1칸 + 좌상우상좌하우하 대각선 1칸
	private static int[] dx = {-2, -1, 2, 1, -2, -1, 1, 2};
	private static int[] dy = {1, 2, 1, 2, -1, -2, -2, -1};
	private static int[][] board;
	private static boolean[][] isVisited;
	private static int startX, startY, destX, destY, boardSize;
	private static Queue<int[]> q = new LinkedList<>();
    
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("BJ7562_input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        
    	int T = (int)br.readLine().charAt(0) - '0';
        for(int testCase  = 0; testCase < T; testCase ++){
        	st = new StringTokenizer(br.readLine());
            boardSize = Integer.parseInt(st.nextToken());
            // 보드 초기화
            board = new int[boardSize][boardSize];
            // 방문여부 초기화
            isVisited = new boolean[boardSize][boardSize];
            
            // 시작 위치
            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            
            // 도착 위치
            st = new StringTokenizer(br.readLine());
            destX = Integer.parseInt(st.nextToken());
            destY = Integer.parseInt(st.nextToken());
            
            // 나이트 이동
            moveKnight();
            
            // 목적지 도착 후 몇번의 움직임이 있었는지 확인
            System.out.println("#" + testCase + ": " + board[destX][destY]);
        }
    }

    private static void moveKnight() {
    	// 목적지 도착여부 확인
        arrivedDest();
        
        // 목적지에 도달하지 못했을 때
        q.add(new int[] {startX, startY});
        isVisited[startX][startY] = true;

        while(!q.isEmpty()){
        	// 현재 위치의 x, y 좌표 세팅
        	int[] current = q.poll();
            int cX = current[0];
            int cY = current[1];
            
            // 나이트가 움직일 수 있는 거리만큼 +
            for(int i = 0; i < 8; i++){
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                
                // 보드의 크기를 넘지 않으면서 아직 방문하지 않은 경우
                if(nX >= 0 && nX < boardSize && nY >= 0 && nY < boardSize && !isVisited[nX][nY]){
                    q.add(new int[] {nX, nY});
                    isVisited[nX][nY] = true;
                    
                    // 다음 위치 값 = 현재 위치 값 + 1
                    board[nX][nY] = board[cX][cY] + 1;
                }
            }
        }
    }

	private static void arrivedDest() {
		if(startX == destX && startY == destY){
            return;
        }
	}
}