package studygroup.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PopulationMovement {
    static int N,L,R;
    static int[][] map;
    static boolean[][] visited;
    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,-1,1};
    static Queue<int[]> q;
    static Queue<int[]> tmpq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 입력 끝 로직 시작 부분
        int answer = 0;
        while(true){
            //방문 배열 초기화
            visited = new boolean[N][N];
            //탈출 조건 선언 더 이상 인구이동을 하지 않을때
            boolean check = false;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    // 방문했다면 다음 반복으로
                    if(visited[i][j]){
                        continue;
                    }
                    //그렇지 않고 인구이동이 가능했다면 플래그를 참으로 바꿔줌
                    else{
                        if(bfs(i,j)) check = true;
                    }
                }
            }
            //더 이상 인구이동을 할 수 없는 상황
            if(!check) break;
            //한 사이클 돌면 답증가
            else answer++;
        }
        System.out.println(answer);
    }

    private static boolean bfs(int i,int j) {

        //방문 가능한 좌표를 담는 큐
        q = new ArrayDeque<>();
        //인구이동 하는 국가들을 담는 큐
        tmpq = new ArrayDeque<>();
        q.add(new int[]{i,j});
        tmpq.add(new int[] {i,j});
        // 누적합을 담는 변수
        int sum = map[i][j];
        // 방문체크
        visited[i][j] = true;

        while(!q.isEmpty()){
            int[]tmp=q.poll();

            for(int k=0; k<4; k++){
                int nx = tmp[0]+dx[k];
                int ny = tmp[1]+dy[k];
                //방문 했거나 범위 밖을 벗어나면 컨티뉴
                if(nx < 0 || ny < 0 || ny >= N|| nx >= N || visited[nx][ny]){
                    continue;
                }
                //꺼낸 큐 와 델타를 통해 만들어낸 값의 차이를 절대값으로 표현
                int abs = Math.abs(map[tmp[0]][tmp[1]] - map[nx][ny]);

                //이 값이 L <= abs <= R 일때
                if(abs >= L && abs <= R){
                    q.add(new int[]{nx,ny});
                    tmpq.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                    sum += map[nx][ny];
                    //방문체크, 누적 , 큐에 담기 진행
                }
            }
        }

        // 만약 tmpq의 사이즈가 1이라면 와일문 내부에서 조건을
        // 충족하지 못했기 때문에 false 리턴하고
        // 1보다 크다면 와일문 내부 조건에 만족하여 값이 들어온 것이 있다.
        // 즉 인구이동이 가능했다 라고 볼 수 있고
        // 이때 평균을 구해서 tmpq에 있는 좌표들에 값을 평균으로 바꿔주고 true를 리턴합니다.
        if(tmpq.size() > 1){
            int avg = sum / tmpq.size();

            while(!tmpq.isEmpty()){
                int[] tmp = tmpq.poll();
                map[tmp[0]][tmp[1]] = avg;
            }
            return true;
        }
        return false;
    }
}
