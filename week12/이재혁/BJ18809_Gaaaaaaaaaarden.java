package undone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18809_Gaaaaaaaaaarden {
    static class Pos {
        int x;
        int y;
        boolean chk;

        public Pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, boolean chk) {
            super();
            this.x = x;
            this.y = y;
            this.chk = chk;
        }

    }
    static int N, M, G, R;// 행,열,초록,빨강
    static int[][] map;
    static int ans = 0;
    static List<Pos> soil, red, green;
    static int[] dx= {0,0,1,-1};
    static int[] dy= {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];// 0호수, 1배양액 뿌릴수X, 2배양액 뿌릴수O
        soil = new ArrayList<Pos>();
        red = new ArrayList<Pos>();
        green = new ArrayList<Pos>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    soil.add(new Pos(i, j, false));
                }
            }
        } // 입력끝

        inject(0, -1,0);// 빨강 먼저 놓기

        System.out.println(ans);
    }

    private static void inject(int cnt, int color,int index) {// -1빨강 -2초록
        if (color == -1 && cnt == R) {// 빨강색 다놓았으면
            inject(0, -2,0);
            return;
        }
        if (color == -2 && cnt == G) {// 초록색 다놓았으면
            bfs();// 퍼뜨려서 답구하기
            return;
        }
        for (int i = index; i < soil.size(); i++) {
            Pos p = soil.get(i);
            if (p.chk == false) {
                p.chk = true;
                map[p.x][p.y] = color;
                if (color == -1) {
                    red.add(new Pos(p.x, p.y));
                }
                if (color == -2) {
                    green.add(new Pos(p.x, p.y));
                }
                inject(cnt + 1, color,i+1);
                p.chk = false;
                if (color == -1) {
                    red.remove(red.size()-1);
                }
                if (color == -2) {
                    green.remove(green.size()-1);
                }
                map[p.x][p.y] = 2;
            }
        }
    }

    private static void bfs() {

        //맵복사
        int[][] copy=new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copy[i][j]=map[i][j];
            }
        }

        Deque<Pos> redQ=new ArrayDeque<Pos>();
        Deque<Pos> greenQ=new ArrayDeque<Pos>();
        for(Pos p:red) {
            redQ.offer(p);
        }
        for(Pos p:green) {
            greenQ.offer(p);
        }

        //visit배열
        int[][] visit=new int[N][M];
        int flowerCnt=0;
        int time=1;
        while(true) {

            if(redQ.isEmpty() && greenQ.isEmpty()) {
                if(flowerCnt>ans)ans=flowerCnt;

                break;
            }

            int redSize=redQ.size();
            int greenSize=greenQ.size();

            //빨강거 먼저
            while(redSize-->0) {
                Pos p=redQ.poll();
                if(copy[p.x][p.y]==10)continue;
                for(int i=0; i<4; i++) {
                    int nx=p.x+dx[i];
                    int ny=p.y+dy[i];
                    if(nx<0||ny<0||nx>=N||ny>=M ||visit[nx][ny]>0)
                        continue;
                    if(copy[nx][ny]==1 || copy[nx][ny]==2) {
                        //퍼뜨릴수 있으면
                        visit[nx][ny]=time;
                        copy[nx][ny]=-1;
                        redQ.offer(new Pos(nx,ny));
                        //overlap.add(new Pos(nx,ny));
                    }
                }
            }
            //초록 확산
            while(greenSize-->0) {
                Pos p=greenQ.poll();
                for(int i=0; i<4; i++) {
                    int nx=p.x+dx[i];
                    int ny=p.y+dy[i];
                    if(nx<0||ny<0||nx>=N||ny>=M)
                        continue;
                    if(copy[nx][ny]==1 || copy[nx][ny]==2) {
                        //퍼뜨릴수 있으면
                        copy[nx][ny]=-2;
                        greenQ.offer(new Pos(nx,ny));
                    }
                    if(copy[nx][ny]==-1 && visit[nx][ny]==time) {//빨간색이고,같은 턴에 넣은거면
                        flowerCnt++;
                        copy[nx][ny]=10;
                    }
                }
            }
            time++;
        }
    }
}