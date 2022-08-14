package studygroup.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1303_전투 {
    static int N,M,W,B;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        visited = new boolean[M][N];

        for(int i=0;i<M;i++){
            String str = br.readLine();
            for(int j=0;j<N; j++){
                map[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                //방문했다면 넘어가기
                if(visited[i][j]){
                    continue;
                }
                //방문하지 않았다면 현재 좌표에있는값(W or B)과,좌표를넘김
                bfs(map[i][j],i,j);
            }
        }
        System.out.println(W+" "+B);
    }

    private static void bfs(char c,int i,int j) {
        Queue<int[]> q = new ArrayDeque<>();
        //배열을 담는 큐에 좌표를 저장
        q.add(new int[] {i,j});
        //방문체크
        visited[i][j] = true;
        //자신도 포함한 카운트
        int count = 1;

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            //델타 순회
            for(int k=0; k<4; k++){
                int nx = tmp[0] + dx[k];
                int ny = tmp[1] + dy[k];

                //배열 범위 밖을 나가거나 방문했거나 파라미터로 받은 문자와 다르면 컨티뉴
                if(nx < 0 || nx >=M || ny <0 || ny>=N || visited[nx][ny] || map[nx][ny] !=c ) {
                    continue;
                }
                //그렇지 않으면 큐에 넣고 카운트 증가시키고 방문처리를 해줌
                q.add(new int[]{nx,ny});
                count++;
                visited[nx][ny] = true;
            }
        }
        //그룹하나가 생성되었을때 파라미터로 들어온 값과 비교하여
        //스태틱으로 선언된 변수에 해당카운트의 제곱을 누적시켜줍니다.

        if(c == 'W'){
            W += (count * count);
        }
        else{
            B += (count * count);
        }
    }

}
