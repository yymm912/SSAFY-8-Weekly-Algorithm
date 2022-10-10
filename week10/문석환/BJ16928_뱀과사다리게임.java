package BOJ.그래프탐색.뱀과사다리게임_16928;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 번호 : 16928
// 난이도 : 골드 5
// 제목 : 뱀과 사다리 게임
// https://www.acmicpc.net/problem/16928
// 주사위 1~6
// 게임의 크기 10x10 , 총 100개의 칸으로 나누어져 있음
// 보드판에는 1~100까지 수가 순서대로 적혀 있다.
// 100칸 넘어서면 이동 불가능
// 뱀칸에 가면 해당 숫자 칸으로 이동 (떨어짐)
// 사다리칸에 가면 숫자 칸으로 이동 (올라감)
public class Main {
    static int N,M;
    static int[] snakeAndLadders;
    static int[] dist; // 각 칸의 실행횟수를 기록하기 위한 배열 (해당 칸의 최소 횟수 값을 기록)
    static boolean[] visit; // 칸을 중복해서 갈 수 없도록 중복 체크
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/BOJ/뱀과사다리게임_16928/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        snakeAndLadders = new int[100+1];
        dist = new int[100+1];
        visit = new boolean[100+1];

        for(int i=0;i<N+M;i++){
            st= new StringTokenizer(br.readLine());
            snakeAndLadders[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        // 프로세스 시작
        pro();

        // 정답 출력 -> 100번째 칸에 도달 했을 때의 최소 실행 횟수
        System.out.println(dist[100]);
    }

    static void pro() {
        bfs();
    }

    static void bfs(){
        Queue<Integer> Q = new ArrayDeque<>();

        Q.offer(1);
        visit[1] = true;
        dist[1] = 0;

        while(!Q.isEmpty()){
            int cIdx = Q.poll();
            for(int i=1;i<=6;i++){
                int nIdx = cIdx + i;
                if(nIdx > 100) break; // i는 1~6이므로 1에서 이미 100을 넘어 섰다면 그 이후 값도 무조건 100을 넘어서므로 break
                if(visit[nIdx]) continue;
                visit[nIdx] = true;

                if(snakeAndLadders[nIdx] != 0){ // 0이 아닌 값이 들어있는 경우 : 뱀 or 사다리
                    if(visit[snakeAndLadders[nIdx]])continue;
                    Q.offer(snakeAndLadders[nIdx]);
                    visit[snakeAndLadders[nIdx]] = true;
                    dist[snakeAndLadders[nIdx]] = dist[cIdx] + 1;
                }else{
                    // 일반 칸인 경우
                    Q.offer(nIdx);
                    dist[nIdx] = dist[cIdx] + 1;
                }
            }
        }
    }
}
