package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class sw1767_프로세서연결하기2트 {
    static int T,N,minLen,maxCore;
    static int[][] map;
    static ArrayList<Node> list;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static class Node{
        int x,y;
        public Node(int x , int y){
            this.x = x;
            this.y=y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            map= new int[N][N];
            list = new ArrayList<>();
            minLen = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;
            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]==1 && i!=0 && j!=0 && i!=N-1 && j != N-1){
                        list.add(new Node(i,j));
                    }
                }
            }
            dfs(0, 0,0);
            System.out.println("#"+t+" "+minLen);
        }
    }

    private static void dfs(int depth, int length, int core) {
        if(depth==list.size()){
            if(core > maxCore){
                minLen = length;
                maxCore = core;
            }
            else if(core == maxCore){
                minLen = Math.min(minLen,length);
            }
            return;
        }

        Node tmp = list.get(depth);

        for(int i=0; i<4; i++){
            boolean check = false;
            int count = 0;
            int nx = tmp.x;
            int ny = tmp.y;

            while(true){
                nx += dx[i];
                ny += dy[i];
                if(nx<0||ny<0||nx>=N||ny>=N){
                    check = true;
                    break;
                }
                else{
                    if(map[nx][ny] != 0){
                        break;
                    }
                    else{
                        count++;
                    }
                }
            }

            if(check){
                makeLine(tmp.x,tmp.y,i,1);
                dfs(depth+1,length+count,core+1);
                makeLine(tmp.x,tmp.y,i,0);
            }
            //여기부분을 할 필요 없다고 생각했음 근데 49/50 맞춤;;;
            //코어가 가장 많이 연결 됐을 때의 가장 짧은 전선의 길이를 출력하게끔 하니 49개
            else{
                dfs(depth+1,length,core);
            }
        }
    }

    private static void makeLine(int x,int y,int i,int num) {
        int nx = x;
        int ny = y;
        while(true){
            nx += dx[i];
            ny += dy[i];
            if(nx<0||ny<0||nx>=N||ny>=N){
                return;
            }
            map[nx][ny] = num;
        }
    }
}
