import java.io.*;
import java.util.*;

public class BJ17825_주사위윷놀이 {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;
        static int ans = 0;
        static h[] horse = new h[4];
        static int[] move = new int[10];
        static int[] plus = {2,3,2,-1,5};
        static void input() throws IOException{
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) horse[i] = new h(0, 0);
            for (int i = 0; i < 10; i++) move[i] = Integer.parseInt(st.nextToken());

        }

        static class h{
            int num, d;
            public h(int num, int d) {
                this.num = num;
                this.d = d;
            }
        }
        static int change(h horse){
            if(horse.num == 10) return 1;
            if(horse.num == 20) return 2;
            if(horse.d == 0 && horse.num == 30){
                return 3;
            }
            if(horse.num == 25) return 4;
            return horse.d;
        }

        static boolean check(int idx, h hor, int moveCnt){
            int s = hor.num;
            if(hor.num == 30 && hor.d == 3) s -= 1;
            for (int i = 0; i < moveCnt; i++) {
                s += plus[hor.d];
                if(hor.d == 1 && s == 22){
                    s = 25;
                    hor.d = 4;
                }
                else if(hor.d == 2 && s == 26 ){
                    s = 25;
                    hor.d = 4;
                }
                else if(hor.d == 3 && s == 25 ){
                    hor.d = 4;
                }
                else if(hor.d == 0 && s == 40){
                    hor.d = 4;
                }
                if(s > 40){
                    hor.num = 42;
                    return true;
                }
            }

            for (int j = 0; j < 4; j++) {
                if(j == idx) continue;
                if(horse[j].num == s){
                    if(s == 10 || s == 20 || s == 25 || s == 30){
                        if(s != 30 || horse[j].d == hor.d || (hor.d == 0 && horse[j].d == 3)) return false;
                    }else{
                        if(horse[j].d == hor.d ) return false;
                    }
                }
            }
            hor.num = s;
            hor.d = change(hor);
            return true;
        }
        static void game(int idx, int sum){
            if(idx == 10){
                ans = Math.max(ans, sum);
                return;
            }


            for (int i = 0; i < 4; i++) {
                if(horse[i].num > 40) continue;
                int temp = horse[i].num;
                int d = horse[i].d;
                if(check(i, horse[i], move[idx])){
                    if(horse[i].num > 40) game(idx+1, sum);
                    else game(idx+1, sum + horse[i].num);
                }
                horse[i].num = temp;
                horse[i].d = d;


            }

        }
        public static void main(String[] args) throws IOException {
            input();
            game(0, 0);
            System.out.println(ans);

        }
    }
