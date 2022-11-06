package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ19238_스타트택시 {
    static int N,M,F;
    static int mx,my;
    static int map[][];
    static Node[] nodes;
    static class Node  {
        int sx;
        int sy;
        int ex;
        int ey;
        boolean alive=true;
        int dis;
        public Node(int sx,int sy, int ex, int ey){
            this.sx = sx;
            this.sy =sy;
            this.ex = ex;
            this.ey = ey;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "sx=" + sx +
                    ", sy=" + sy +
                    ", ex=" + ex +
                    ", ey=" + ey +
                    ", end=" + alive +
                    '}';
        }

    }
    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};
    static boolean visited[][];
    static Queue<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        nodes = new Node[M+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    map[i][j] =-1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        mx = Integer.parseInt(st.nextToken());
        my = Integer.parseInt(st.nextToken());

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(a,b,c,d);
            map[a][b] = i;
        }

        simul();
        System.out.println(F);
    }

    private static void simul() {
        PriorityQueue<Node> pq = new PriorityQueue<>((p1,p2)->{
            if(p1.dis==p2.dis){
                if(p1.sx==p2.sx){
                    return p1.sy - p2.sy;
                }
                else{
                    return p1.sx - p2.sx;
                }
            }
            else{
                return p1.dis - p2.dis;
            }
        });

        while(true){
            if(F<=0){
                break;
            }
            int count = 0;
            pq.clear();
            //거리구하기

            q.clear();
            visited = new boolean[N+1][N+1];
            q.add(new int[]{mx, my, 0});
            visited[mx][my] = true;
            while(!q.isEmpty()){
                int arr[] = q.poll();

                if(map[arr[0]][arr[1]] > 0){
                    if(nodes[map[arr[0]][arr[1]]].alive){
                        nodes[map[arr[0]][arr[1]]].dis = arr[2];
                        pq.add(nodes[map[arr[0]][arr[1]]]);
                    }
                    else{
                        count++;
                    }
                }

                for(int k=0; k<4; k++){
                    int nx = arr[0]+dx[k];
                    int ny = arr[1]+dy[k];

                    if(nx<1||ny<1||nx>N||ny>N||visited[nx][ny]||map[nx][ny]==-1) continue;
                    q.add(new int[]{nx,ny,arr[2]+1});
                    visited[nx][ny] = true;
                }
            }

            if(pq.size()==0){
                if(count==M){
                    return;
                }
                else{
                 F = -1;
                 return;
                }
            }
            else{
                Node tmp = pq.poll();
                // 해당 손님에게 까지가는데 갈 기름이 없다면 break;
                if(F < tmp.dis){
                    F = -1;
                    return;
                }
                // 그렇지 않으면 데리러 간다.
                else{
                    int val = 0;
                    //연료 배출
                    F -= tmp.dis;
                    //최단 거리로 손님을 데려다 준다.
                    q.clear();
                    visited = new boolean[N+1][N+1];
                    q.add(new int[]{tmp.sx,tmp.sy,0});
                    visited[tmp.sx][tmp.sy] = true;
                    while(!q.isEmpty()){
                        int[] arr = q.poll();
                        val = arr[2];
                        if(arr[0]==tmp.ex && arr[1]==tmp.ey){
                            break;
                        }
                        for(int i=0; i<4; i++){
                            int nx = arr[0]+dx[i];
                            int ny = arr[1]+dy[i];

                            if(nx<1||ny<1||nx>N||ny>N||visited[nx][ny]||map[nx][ny]==-1) continue;
                            q.add(new int[]{nx,ny,arr[2]+1});
                            visited[nx][ny] = true;
                        }
                    }
                    //손님을 모시는 도중 기름이 바닥나면 실패
                    if(val > F){
                        F = -1;
                        return;
                    }
                    else{
                        tmp.alive = false;
                        F += val;
                        mx = tmp.ex;
                        my = tmp.ey;
                    }
                }
            }
        }
    }
}
