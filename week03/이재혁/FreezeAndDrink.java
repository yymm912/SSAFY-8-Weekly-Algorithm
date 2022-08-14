package studygroup.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class FreezeAndDrink {
    static int N,M,COUNT;
    static boolean visited[][];
    static int map[][];
    static Queue<int[]> q;
    static int dx[] = {0,0,1,-1};
    static int dy[] = {1,-1,0,0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        q = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

        //방문
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //방문했거나 칸막이를 만나는 경우 컨티뉴
                if(visited[i][j] || map[i][j] == 1) {
                    continue;
                }
                //그렇지 않다면 배열을 담는 큐에 좌표를 넣고 방문체크를함.
                q.add(new int[] {i,j});
                visited[i][j] = true;
                bfs();
                //한번 탐색이 끝나면 한 덩어리가 나오므로 이때 카운트 증가시켜줌
                COUNT++;
            }
        }
        System.out.println(COUNT);
    }

    private static void bfs() {
        while(!q.isEmpty()){
            int[] tmp = q.poll();

            //델타 순회
            for(int k=0; k<4; k++){
                int nx = tmp[0]+dx[k];
                int ny = tmp[1]+dy[k];

                //배열 범위 밖이거나, 방문했거나, 칸막이라면 넘어가기
                if(nx<0 || ny<0 || nx>=N || ny>= M || visited[nx][ny] || map[nx][ny]==1){
                    continue;
                }
                //아니라면 큐에 넣고 방문체크
                q.add(new int[] {nx,ny});
                visited[nx][ny] = true;
            }
        }
    }
}
