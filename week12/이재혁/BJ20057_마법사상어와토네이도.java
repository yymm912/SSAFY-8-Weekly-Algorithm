package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20057_마법사상어와토네이도 {
        static class Pos {
            int x, y;
            Pos(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        static int N, map[][], sum=0;
        static Pos start;
        static int[][] dir = { { 0, 1, 0, -1 }, { -1, 0, 1, 0 } }; // 좌 하 우 상 으로 움직임.
        // 좌, 하(y*-1,x반대로), 우(x,y*-1), 상(y,x반대로)
        static int[][] tor = { { 0, -1, 1, -2, -1, 1, 2, -1, 1 }, { -2, -1, -1, 0, 0, 0, 0, 1, 1 } };
        static double[] percent = { 0.05, 0.1, 0.1, 0.02, 0.07, 0.07, 0.02, 0.01, 0.01 };
        static int[][] alphaDir = { { 0, 1, 0, -1 }, { -1, 0, 1, 0 } };

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine()); // 격자의 크기
            map = new int[N][N];
            for (int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            process(); // 토네이도 처리
            System.out.println(sum);
        }

        static void process() {
            start = new Pos(N / 2, N / 2); // 시작 위치
            int len = 1, cnt =0; // 1,1,2,2,3,3,...,N,N
            while(true) {
                if(start.x==0 && start.y==0) break;
                for (int d = 0; d < 4; ++d) {
                    for(int i=0;i<len;++i) {
                        if(start.x==0 && start.y==0) break;
                        int nx = start.x + dir[0][d];
                        int ny = start.y + dir[1][d];
                        if (isBoundary(nx, ny)) {
                            // 방향에따라 처리한다.
                            tornaido(d, nx, ny);
                            start.x = nx;
                            start.y= ny;
                        }
                    }
                    if(++cnt==2) {
                        ++len;
                        cnt=0;
                    }
                }
            }
        }

        static void tornaido(int d, int x, int y) {
            int alpha = map[x][y];// a 값을 계산하기 위함
            for (int t = 0; t < 9; ++t) {
                int torx =0,tory=0;
                if(d==0) {
                    torx=tor[0][t];
                    tory=tor[1][t];
                }else if(d==1) {
                    torx=tor[1][t]*-1;
                    tory=tor[0][t];
                }else if(d==2) {
                    torx=tor[0][t];
                    tory=tor[1][t]*-1;
                }else if(d==3) {
                    torx=tor[1][t];
                    tory=tor[0][t];
                }
                int nx = x + torx;
                int ny = y + tory;
                int val = (int)(map[x][y] * percent[t]); // 모래 비율을 계산한다.
                alpha -= val; // 사용된 값은 빼준다.
                if (isBoundary(nx, ny)) {
                    map[nx][ny] += val;
                }else sum+=val;
            }
            // y기준으로 알파값을 갱신해준다.
            if(isBoundary(x+alphaDir[0][d],y+alphaDir[1][d]))
                map[x + alphaDir[0][d]][y + alphaDir[1][d]] += alpha;
            else sum+=alpha;
            map[x][y]=0;//y위치의 모래는사라진다.
        }

        static boolean isBoundary(int x, int y) { // 범위 검사.
            return 0 <= x && x < N && 0 <= y && y < N;
        }

    }
}
