package basic;

import java.util.ArrayDeque;
import java.util.Queue;

import basic.basic_dfs_bfs.node;

public class problem_dfs_bfs {

    /*
    시작점은 (1,1) 
    map 안에서 30에 접근하는 가장 적은 이동횟수는?
    
    현재 있는 좌표에서  상,하,좌,우 4방향으로 접근할 수 있다.
    한 번 움직일 때는 한 칸씩만 이동가능하다.
    */
    static int [][]map= {{0,  0,  0,  0,  0,  0,  0},
        {0, 1, 2, 3, 4, 5, 6},
        {0, 11, 12, 13, 4, 5, 6},
        {0, 1, 31, 33, 34, 35, 36},
        {0, 15, 42, 17, 30, 45, 18},
        {0, 12, 30, 3, 30, 40, 56},
        {0, 45, 60, 3, 64, 65, 66},
    };
    static int []dy= {-1,1,0,0},dx= {0,0,-1,1};    
    public static void main(String[] args) {
        Queue<node>q=new ArrayDeque<>();
        q.offer(??);
        int [][]visited=new int[7][7];
        visited[??][??]=??;
        boolean flag=false;
        int ans=0;
        while(!q??) {
            node nd=??;
            int x=nd.x;
            int y=nd.y;
            for(int i=0;i<4;i++) {
                int nx=??,ny=??;
                if(ny>=7||ny<=0||nx>=7||nx<=0)continue;
                if(visited[ny][nx]==0) {
                    visited[ny][nx]=??
                    if(??) {
                        ans=??;
                        flag=true;
                        break;
                    }
                    q.??(new node(??));
                }
            }
            if(flag==true)break;
        }
        System.out.println(ans);
        
    }
    static class node{
        int y, x;
        
        node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
}
