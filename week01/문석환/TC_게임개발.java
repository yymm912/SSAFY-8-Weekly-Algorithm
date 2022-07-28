package week01;

import java.io.*;
import java.util.StringTokenizer;

// 문석환
// 첫째주 공통문제
// 이것이 코딩 테스트다 p118 게임개발 문제
// R , C : 맵의 크기
// cY , cX : 캐릭터 위치
// cD : 캐릭터가 바라보고 있는 방향 -> 0(북) , 1(동) , 2(남) , 3(서)
// map : 1(바다) , 0(땅)
public class TC_게임개발 {
    static FastReader sc = new FastReader("src/week01/input/게임개발.txt");
    static StringBuilder sb = new StringBuilder();
    static int R,C,cY,cX,cD,ans=1,turnCnt=0;
    static int[][] map,dir={{-1,0},{0,1},{1,0},{0,-1}};
    static boolean[][] visit;

    static void input(){
        R = sc.nextInt();
        C = sc.nextInt();
        cY = sc.nextInt();
        cX = sc.nextInt();
        cD = sc.nextInt();
        map = new int[R][C];
        for(int r=0;r<R;r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = sc.nextInt();
            }
        }
        visit = new boolean[R][C];
    }

    static void pro(){
        Avatar a = new Avatar(cY,cX,cD);
        visit[a.aY][a.aX] = true;
        while(turnCnt <4){
            a.turnLeft();
            turnCnt++;
            int ny = a.aY + dir[a.aDir][0];
            int nx = a.aX + dir[a.aDir][1];
            // 회전한 이후 간 곳이 맵을 벗어나는 경우
            if(ny < 0 || nx < 0 || ny >= R || nx >= C)continue;
            // 이미 방문 했던 곳인 경우
            if(visit[ny][nx])continue;
            // 바다인 경우
            if(map[ny][nx] == 1) continue;
            // 이동
            a.aY = ny;
            a.aX = nx;
            visit[ny][nx] = true;
            ans++;
            turnCnt = 0;
        }

        System.out.println(ans);
    }

    static class Avatar{
        int aY;
        int aX;
        int aDir;
        public Avatar(int aY,int aX,int aDir){
            this.aY = aY;
            this.aX = aX;
            this.aDir = aDir;
        }

        public void turnLeft(){
            this.aDir -= 1;
            if(aDir == -1) aDir = 3;
        }
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader{
        StringTokenizer st;
        BufferedReader br;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s){
            try{
                br = new BufferedReader(new FileReader(new File(s)));
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

        String next(){
            if(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        Integer nextInt(){
            return Integer.parseInt(next());
        }
    }
}
