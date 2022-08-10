package week3.김정윤;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16928_뱀과사다리게임 {
	static int N, M, ans; // N - 사다리 개수, M - 뱀 개수
	// 0: dummy | 1 ~ 100번 칸
	static int[] LadderOrSnake = new int[101]; // // 사다리 Or 뱀 목적지 정보
	static int[] rst = new int[101]; // 주사위 굴린 횟수
	static boolean[] isVisited = new boolean[101]; // 방문 여부 확인
	static Queue<Integer> q = new LinkedList<>();
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        
        // 사다리 Or 뱀 정보 저장
        for(int i = 0 ; i < N+M ; i++){
            stringTokenizer  = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());

            LadderOrSnake[from] = to;
        }

        
        System.out.println(playGame());
    }

	private static int playGame() {
		q.offer(1);
        rst[1] = 0;
        isVisited[1] = true;
        ans = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            int current = q.poll();
            
            // 목적지 도착
            if(current == 100){
            	ans = Math.min(ans, rst[current]);
                break;
            }

            for(int i = 1; i <= 6; i++){
                int next = current + i;
                
                if (next > 100) break;
                if(isVisited[next])  continue;
                isVisited[next] = true;
                
                if(LadderOrSnake[next] != 0){
                    if(!isVisited[LadderOrSnake[next]]){
                        isVisited[LadderOrSnake[next]] = true;
                        rst[LadderOrSnake[next]] = rst[current] + 1;
                        q.offer(LadderOrSnake[next]);
                    }
                }
                else{
                    isVisited[next] = true;
                    rst[next] = rst[current] + 1;
                    q.offer(next);
                }
            }
        }
        return ans;
	}
}