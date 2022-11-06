package baekjoon;

import java.awt.image.BandedSampleModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17136_색종이붙이기 {
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[] paper = {0,5,5,5,5,5};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        for(int i=0; i<10; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(ans);
        }
    }

    private static void dfs( int x, int y, int count) {
        if (x >= 9 && y > 9) {
            ans = Math.min(ans, count);
            return;
        }

        if(y>9){
            dfs(x+1,0,count);
            return;
        }
        if(count >= ans){
            return;
        }

        if(map[x][y] == 1){
            for(int i=5; i>=1; i--){
                if(paper[i] > 0 && isPossible(x,y,i)){
                    check(x,y,i,0);
                    paper[i]--;
                    dfs(x,y+1,count+1);
                    paper[i]++;
                    check(x,y,i,1);
                }
            }
        }
        else{
            dfs(x,y+1,count);
        }
    }

    private static void check(int x, int y, int t, int num) {
        for(int i=x; i<x+t; i++){
            for(int j=y; j<y+t; j++){
               map[i][j]= num;
            }
        }
    }

    private static boolean isPossible(int x, int y, int t) {
        for(int i=x; i<x+t; i++){
            for(int j=y; j<y+t; j++){
                if( isIn(i,j)||map[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isIn(int x , int y) {
        if(x<0||y<0||x>=10||y>=10){
            return true;
        }
        else{
            return false;
        }
    }
}
