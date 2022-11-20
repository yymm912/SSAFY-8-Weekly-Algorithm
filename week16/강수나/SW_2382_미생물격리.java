package a22_11_20.study;

import java.io.*;
import java.util.*;
 
public class SW_2382_미생물격리 {
 
    static int N, M, K, ans;
    static Pos[][] map;
    static PriorityQueue<Pos> pq; //미생물 수 많은 순으로 정렬
    static PriorityQueue<Pos> move_q;
     
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
     
    public static void main(String[] args) throws Exception {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
         
        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new Pos[N][N];
            pq = new PriorityQueue<> ((o1, o2) -> o2.cnt-o1.cnt); 
            for (int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                pq.offer(new Pos(y, x, cnt, dir));
                map[y][x] = new Pos(y, x, cnt, dir);
            } //입력 끝
             
            ans = 0;
            solution();
            System.out.println("#"+tc+" "+ans);
        }
    }
     
    static void solution() {
        for (int time=0; time<M; time++) {
            if (time>0) getCell(); //null이 아니면 pq에 넣어줌
            move(); //이동
        }
         
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] ==null) continue;
                ans += map[i][j].cnt;
            }
        }
    }
     
    static void move() {
        move_q = new PriorityQueue<> ((o1, o2) -> o2.cnt-o1.cnt); 
        while (!pq.isEmpty()) {
            Pos cur = pq.poll();
            int y = cur.y;
            int x = cur.x;
            int cnt = cur.cnt;
            int d = cur.dir;
            map[y][x] = null;
             
            int ny = cur.y+dy[d];
            int nx = cur.x+dx[d];
            if (ny==0 || ny==N-1 || nx==0 || nx==N-1) { //가장자리일 때
                if (cnt/2 >= 1) {
                    int nd=0;
                    switch (d) {
                    case 0: nd=1; break;
                    case 1: nd=0; break;
                    case 2: nd=3; break;
                    case 3: nd=2; break;
                    }
                    move_q.offer(new Pos(ny, nx, cnt/2, nd));
                }
            } else {
                move_q.offer(new Pos(ny, nx, cnt, d));
            }
        }
         
        while (!move_q.isEmpty()) {
            Pos cur = move_q.poll();
            int y = cur.y;
            int x = cur.x;
            int cnt = cur.cnt;
            int d = cur.dir;
             
            if (map[y][x] != null) {
                map[y][x].cnt += cnt; //미생물 수만 더해줌
                 
            } else {
                map[y][x] = cur;
            }
        }
    }
     
    static void getCell() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] ==null) continue;
                pq.offer(map[i][j]);
            }
        }
    }
     
     
    static class Pos {
        int y, x, cnt, dir;
        Pos(int y, int x, int cnt, int dir) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
}