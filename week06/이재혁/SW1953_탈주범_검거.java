package studygroup.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 흉악범이 탈출!
 * 어느 한 지점으로 들어감
 * 해당 파이프로 갈 수 있는 방향이 있고
 * 총 파이프 갯수는 7개이고 각 파이프 마다 연결된 모양이 다름
 * 만약 상하로 이어진 파이프에 위치해 있다고 가정 -> 상으로 갔을때 상 좌표에 있는 파이프가 하와 연결되어 있어야 이동이 가능함
 * 탈주범이 X초 뒤에 위치할 수 있는 곳의 갯수를 구해야함
 */

public class SW1953_탈주범_검거 {
    static int T,N,M,R,C,S,ans;
    static int[][] map;
    static boolean connect[][] = {
            {},
            {true,true,true,true},
            {true,false,true,false},
            {false,true,false,true},
            {true,true,false,false},
            {false,true,true,false},
            {false,false,true,true},
            {true,false,false,true}
    };
    //상우하좌 델타 초기화
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new int[N][M];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(R,C);
            System.out.println("#"+t+" "+ans);
        }

    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[]{r,c,map[r][c],1});
        visited[r][c] = true;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            if(tmp[3] <= S){
                //주어진 시간 S 전까지 이동한 모든 경로를 구해야하기 때문
                ans++;
            }
            if(tmp[3] > S){
                //시간이 넘어가버리면 종료
                return;
            }
            //tmp[2] == 현재 파이프의 종류
            for(int k=0; k<connect[tmp[2]].length; k++){
                // 이 파이프가 상우하좌 중 어디로 갈 수 있는지를 2차원 불린 배열으로 정의해놓음
                if(connect[tmp[2]][k]){
                    int nx = tmp[0] +dx[k];
                    int ny = tmp[1] +dy[k];
                    //방문,배열의 범위를 벗어나거나, 파이프가 있는 곳이 아니라면 컨티뉴
                    if(nx<0||ny<0||nx>=N||ny>=M||visited[nx][ny]||map[nx][ny]==0) continue;

                    //해당 위치에 가보니 파이프가 있다 -> 하지만 현재 파이프가 갈수 있는 곳과 연결이 되어있는지 확인을 해야함
                    int nextNum = map[nx][ny];
                    //상우하좌 -> (0+2) % 4 == 2 -> 0은 상 2는 하 이렇게 짝을 맞출 수 있음
                    if(connect[nextNum][(k+2)%4]){
                        //연결되어있다면 큐에 넣고 3번인덱스에 시간값을 넣어줌
                        q.add(new int[]{nx,ny,map[nx][ny],tmp[3]+1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}

