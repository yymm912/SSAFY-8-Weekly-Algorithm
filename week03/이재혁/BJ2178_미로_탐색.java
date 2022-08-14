package studygroup.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_미로_탐색 {
    static int N,M;
    static int min = Integer.MAX_VALUE;
    static int map[][];
    static boolean visited[][];
    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

        bfs();
        System.out.println(min);
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        //큐에 배열형태로 출발좌표와 카운트값을 넣습니다.
        q.add(new int[]{0,0,1});
        //방문 체크
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            //순회도중 목적지 좌표까지 간다면 최소값에 바로 카운팅변수가 담긴 인덱스를 넣고 멈추기
            if(tmp[0] == N-1 && tmp[1] == M-1){
                //min = Math.min(min,tmp[2]);
                min = tmp[2];
                break;
            }

            //델타 순회
            for(int k=0; k<4; k++){
                int nx = tmp[0]+dx[k];
                int ny = tmp[1]+dy[k];
                //배열 범위 밖이거나 방문했거나 이동할 수 없는 칸의 경우 컨티뉴
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]||map[nx][ny] == 0){
                    continue;
                }
                //그렇지 않다면 좌표를 넣고 와일문 시작지점에서 큐에서 꺼낸 배열의 카운팅변수에 +1 한 값을 넣음
                q.add(new int[] {nx,ny,tmp[2]+1});
                //방문체크
                visited[nx][ny] = true;
            }
        }
    }
}
