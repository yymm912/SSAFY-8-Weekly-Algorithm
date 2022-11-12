import java.util.*;

class Solution {
    
    static int[][] mat;
    static int[][][] visited;
    static int N;
    static int[][] dirs={{1,0},{0,1},{-1,0},{0,-1}};
    
    static void turn0(int cx, int cy, int cn, Deque<Node> que){ //가로회전
        if(cy+1<N){
            if(mat[cy+1][cx]==0 && mat[cy+1][cx+1]==0){ 
                if(visited[cy][cx][1]>cn+1){ //좌하
                    visited[cy][cx][1]=cn+1;
                    que.add(new Node(cx,cy,1,cn+1));
                }
                if(visited[cy][cx+1][1]>cn+1){ //좌상
                    visited[cy][cx+1][1]=cn+1;
                    que.add(new Node(cx+1,cy,1,cn+1));
                }
            }
        }
        if(cy>0){
            if(mat[cy-1][cx]==0 && mat[cy-1][cx+1]==0){
                if(visited[cy-1][cx][1]>cn+1){ //우하
                    visited[cy-1][cx][1]=cn+1;
                    que.add(new Node(cx,cy-1,1,cn+1));
                }
                if(visited[cy-1][cx+1][1]>cn+1){ //우상
                    visited[cy-1][cx+1][1]=cn+1;
                    que.add(new Node(cx+1,cy-1,1,cn+1));
                }
            }
        }
    }
    
    static void turn1(int cx, int cy, int cn, Deque<Node> que){ //세로회전
        if(cx>0){
            if(mat[cy][cx-1]==0  && mat[cy+1][cx-1]==0){
                if(visited[cy][cx-1][0]>cn+1){ //좌상
                    visited[cy][cx-1][0]=cn+1;
                    que.add(new Node(cx-1, cy, 0, cn+1));
                }
                if(visited[cy+1][cx-1][0]>cn+1){//좌하
                    visited[cy+1][cx-1][0]=cn+1;
                    que.add(new Node(cx-1, cy+1, 0, cn+1));
                }
            }
        }
        if(cx+1<N){
            if(mat[cy][cx+1]==0  && mat[cy+1][cx+1]==0){
                if(visited[cy][cx][0]>cn+1){ //우상
                    visited[cy][cx][0]=cn+1;
                    que.add(new Node(cx, cy, 0, cn+1));
                }
                if(visited[cy+1][cx][0]>cn+1){//우하
                    visited[cy+1][cx][0]=cn+1;
                    que.add(new Node(cx, cy+1, 0, cn+1));
                }
            }
            
            
        }
    }
    
    static int bfs(){
        Deque<Node> que = new ArrayDeque<>();
        visited[0][0][0]=0;
        que.add(new Node(0,0,0,0));
        while(!que.isEmpty()){
            Node cur= que.pollFirst();
            int cx=cur.x, cy=cur.y, cd=cur.d, cn=cur.n;
            if(cd==0){ //가로
                int cx2=cx+1;
                if(cx2==N-1 && cy==N-1){
                    return cn;
                }
                for(int dr=0; dr<4; dr++){
                    int nx=cx+dirs[dr][0];
                    int ny=cy+dirs[dr][1];
                    int nx2=nx+1;
                    if(nx<0 || ny<0 || nx2>=N|| ny>=N) continue;
                    if(mat[ny][nx]==1 || mat[ny][nx2]==1) continue;
                    if(visited[ny][nx][cd]>cn+1){
                        visited[ny][nx][cd]=cn+1;
                        que.add(new Node(nx,ny,cd,cn+1));
                    }
                }
                turn0(cx, cy, cn, que); //회전
            }else{ //세로
                int cy2=cy+1;
                if(cx==N-1 && cy2==N-1){
                    return cn;
                }
                for(int dr=0; dr<4; dr++){
                    int nx=cx+dirs[dr][0];
                    int ny=cy+dirs[dr][1];
                    int ny2=ny+1;
                    if(nx<0 || ny<0 || nx>=N|| ny2>=N) continue;
                    if(mat[ny][nx]==1 || mat[ny2][nx]==1) continue;
                    if(visited[ny][nx][cd]>cn+1){
                        visited[ny][nx][cd]=cn+1;
                        que.add(new Node(nx,ny,cd,cn+1));
                    }
                }
                turn1(cx, cy, cn, que); //회전
            }
        }
        return -1;
    }
    public int solution(int[][] board) {
        int answer = 0;
        mat=board;
        N= board[0].length;
        visited=new int[N][N][2];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<2; k++){
                    visited[i][j][k]=N*N;
                }
            }
        }
        answer=bfs();
        return answer;
        
    }
    
    static class Node{
        int x,y,d,n;
        Node(int x, int y, int d, int n){
            this.x=x; this.y=y; this.d=d; this.n=n;
        }
    }
}