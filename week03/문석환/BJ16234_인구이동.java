package BOJ.인구이동_16234;

// 번호 : 16234
// 난이도 :
// 제목 : 인구이동
// https://www.acmicpc.net/problem/16234

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static Queue<Country> Q = new ArrayDeque<>();
    static int N,L,R,ans;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static List<Country> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 프로세스 시작
        pro();

        // 출력
        System.out.println(ans);
    }

    static void pro(){
        ans = 0;
        while(true) {
            boolean flag = false;
            visit = new boolean[N+1][N+1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (visit[i][j]) continue;
                    int sum = bfs(i, j);
                    if (list.size() > 1) { // 연합이 가능한 국가들이 존재하므로 인구 이동 + flag 변경
                        change(sum);
                        flag = true;
                    }
                }
            }
            if(!flag)break;
            ans++;
        }
    }

    static int bfs(int y,int x){
        Q.clear();
        list.clear();
        Q.offer(new Country(y,x));
        list.add(new Country(y,x));
        visit[y][x] = true;
        int sum = map[y][x];
        while(!Q.isEmpty()){
            Country c = Q.poll();
            for(int i=0;i<4;i++){
                int ny = c.yIdx + dy[i];
                int nx = c.xIdx + dx[i];
                if(ny < 1 || nx < 1 || ny > N || nx > N)continue;
                if(visit[ny][nx])continue;
                if(!isCartel(Math.abs(map[c.yIdx][c.xIdx]-map[ny][nx])))continue;
                // 이동 가능
                Q.offer(new Country(ny,nx));
                list.add(new Country(ny,nx));
                sum+= map[ny][nx];
                visit[ny][nx] = true;
            }
        }
        return sum;
    }

    static void change(int sum){
        int avg = sum/list.size();
        for(Country c : list){
            map[c.yIdx][c.xIdx] = avg;
        }
    }

    static boolean isCartel(int X){
        if(X >= L && X <= R) return true;
        else return false;
    }

    static class Country{
        int yIdx; // 나라의 row 인덱스
        int xIdx; // 나라의 col 인덱스

        public Country(int yIdx,int xIdx){
            this.yIdx = yIdx;
            this.xIdx = xIdx;
        }
    }
}
