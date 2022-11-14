import java.io.*;
import java.util.*;

public class boj_매직스타_tableMinPark {

    static final int N = 5, M = 9, SUM = 26;
    static List<P> blank;
    static char[] tgt;
    static char[][] map;
    static boolean[] alp;
    static class P {
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        blank = new ArrayList<>();
        map = new char[N][M];
        alp = new boolean[12];
        for (int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++){
                if (map[i][j] == '.') continue;
                if (map[i][j] == 'x') blank.add(new P(i, j));
                else alp[map[i][j]-'A'] = true;
            }
        }

        tgt = new char[blank.size()];
        solve(0);

        System.out.println(sb.toString());
        br.close();
    }

    // 순열
    static StringBuilder sb;
    static boolean flag = false;
    static void solve(int tgtIdx) {
        if (flag) return;
        if (tgtIdx == blank.size()){
            for (int i = 0; i < blank.size(); i++){
                P now = blank.get(i);
                map[now.y][now.x] = tgt[i];
            }

            if (check()){
                flag = true;
                sb = new StringBuilder();
                for (int y = 0; y < N; y++){
                    for (int x = 0; x < M; x++) sb.append(map[y][x]);
                    sb.append("\n");
                }
            }
            return;
        }

        for (int i = 0; i < 12; i++){
            if (alp[i]) continue;
            alp[i] = true;
            tgt[tgtIdx] = (char) (i + 'A');
            solve(tgtIdx + 1);
            alp[i] = false;
        }
    }

    public static boolean check() {
        int sum1 = (map[0][4]-'A'+1) + (map[1][3]-'A'+1)+ (map[2][2]-'A'+1)+(map[3][1]-'A'+1);
        int sum2 = (map[0][4]-'A'+1)+(map[1][5]-'A'+1)+(map[2][6]-'A'+1)+(map[3][7]-'A'+1);
        int sum3 = (map[1][1]-'A'+1)+(map[1][3]-'A'+1)+(map[1][5]-'A'+1)+(map[1][7]-'A'+1);
        int sum4 = (map[3][1]-'A'+1)+(map[3][3]-'A'+1)+(map[3][5]-'A'+1)+(map[3][7]-'A'+1);
        int sum5 = (map[4][4]-'A'+1)+(map[3][3]-'A'+1)+(map[2][2]-'A'+1)+(map[1][1]-'A'+1);
        int sum6 = (map[4][4]-'A'+1)+(map[3][5]-'A'+1)+(map[2][6]-'A'+1)+(map[1][7]-'A'+1);
        
        if(sum1 == SUM && sum2 == SUM && sum3 == SUM && sum4 == SUM && sum5 == SUM && sum6 == SUM) {
            return true;
        }
        return false;
    }
}
