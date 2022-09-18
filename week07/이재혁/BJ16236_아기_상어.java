package studygroup.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16236_아기_상어 {
    static int N,sx,sy,sSize=2;
    static int ans, eatcount;
    static int map[][];
    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};

    static class Fish implements Comparable<Fish>{
        int x;
        int y;
        int size;
        int dis;
        public Fish(int x,int y,int size, int dis) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.dis = dis;
        }
        @Override
        public int compareTo(Fish o) {
            if(this.dis == o.dis) {
                if(this.x == o.x) {
                    return Integer.compare(this.y, o.y);
                }
                else {
                    return Integer.compare(this.x, o.x);
                }
            }
            else {
                return Integer.compare(this.dis, o.dis);
            }
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j]=0;
                }
            }
        }

        bfs(sx,sy);
        System.out.println(ans);
    }
    private static void bfs(int sx1, int sy1) {
        /**
         * 들어온 값은 상어의 현재 좌표
         * 방문체크, fish변수를 두고 먹을 수 있는 상어들을 걸러냄
         */
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sx1,sy1,0});
        boolean visited[][] = new boolean[N][N];
        visited[sx1][sy1] = true;
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        int fish = 0;

        re:
        while(!q.isEmpty()) {
            int[] tmp = q.poll();

            for(int k=0; k<4; k++) {
                int nx = tmp[0]+dx[k];
                int ny = tmp[1]+dy[k];

                if(nx<0||ny<0||nx>=N||ny>=N||visited[nx][ny]||map[nx][ny]>sSize) continue;

                //방문체크
                visited[nx][ny] = true;

                //이동할 수 있는 곳이라면 이동
                if(map[nx][ny]==0 || map[nx][ny] == sSize){
                    q.add(new int[]{nx,ny,tmp[2]+1});
                }

                //그 곳이 내 크기보다 작다 -> 먹을 수 있다면
                else if(map[nx][ny] < sSize) {
                    //처음 먹거나 같은 거리에 있는 놈을 먹거나
                    if(fish== 0 || fish == tmp[2]+1){
                        //일단 우선순위큐에 담음
                        pq.offer(new Fish(nx,ny,map[nx][ny],tmp[2]+1));
                        //fish변수를 현재 거리로 초기화
                        fish = tmp[2]+1;
                    }
                    else{
                        //먹을 수 있는 고기가 내가 이전에 먹었던 고기의 거리보다 멀다면 종료해야함
                        break re;
                    }
                }
            }
        }
        //위의 코드가 끝나고 우선순위큐에 담긴것이 없다면 엄마상어 호출해야함
        if(pq.isEmpty()){
            return;
        }
        else{
            //우선순위 큐에서 하나 뺌
            Fish f = pq.poll();

            // 그 놈의 거리를 정답에 누적시킴
            ans += f.dis;
            System.out.println(f.dis+" "+map[f.x][f.y]);
            //현재 먹은놈의 위치 값을 0으로 바꿈
            map[f.x][f.y]= 0;
            //먹었으니 먹은 횟수를 증가시킴
            eatcount++;
            //증가 시키고 보니 내 크기와 같다면
            //내 크기가 하나 커지고 먹은 횟수를 다시 0으로 초기화
            if(eatcount == sSize){
                sSize++;
                eatcount=0;
            }
            //현재 상어의 위치를 조정해줌
            sx = f.x;
            sy = f.y;
            //다시 bfs 돌러감
            bfs(sx,sy);
        }
    }

}