import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static int[] dr={0,1,0,-1};
    static int[] dc={-1,0,1,0};
    static int[] start_point;
    static int[][] arr;
    static int n,m, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        st = new StringTokenizer(br.readLine());
        start_point = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search();
        System.out.println(cnt);

    }

    static void search(){
        cnt++;
        arr[start_point[0]][start_point[1]] = 2;
        int direction = start_point[2];
        int end_cnt = 0;
        int nr = start_point[0], nc=start_point[1];
        while(true){
            if(end_cnt==4) {
                nr -= dr[direction%4];
                nc -= dc[direction%4];
                if(nr >=n || nc >= m || nc < 0 || nr<0) break;
                if(arr[nr][nc]==1) break;
                end_cnt=0;
                continue;
            }
            nr += dr[direction%4];
            nc += dc[direction%4];

            if(nr >=n || nc >= m || nc < 0 || nr<0){
                nr -= dr[direction%4];
                nc -= dc[direction%4];
                end_cnt++;
                direction++;
                continue;
            }

            if(arr[nr][nc]!=0){
                nr -= dr[direction%4];
                nc -= dc[direction%4];
                end_cnt++;
                direction++;
                continue;
            }
            else {
                arr[nr][nc] = 2;
                end_cnt=0;
                cnt++;
            }


        }

    }

}