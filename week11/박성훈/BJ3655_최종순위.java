import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3655_최종순위 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, n,m, indegree[], rank[][];

    static Queue<Integer> q = new ArrayDeque<>();

    static void input() throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            indegree = new int[n+1];
            rank = new int[n+1][n+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                indegree[num] = i;
                for (int j = 1; j <= n; j++) {
                    if(j != num && rank[j][num] == 0) rank[num][j] = 1;

                }
            }
            m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                change(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            pro();
            sb.append('\n');
        }
    }
    static void pro(){
        String word = "";
        for (int i = 1; i <= n ; i++) {
            if(indegree[i] == 0) q.add(i);
        }
        for (int i = 0; i < n; i++) {
            if (q.isEmpty()) {
                sb.append("IMPOSSIBLE");
                return;
            }
            if(q.size() > 1){
                sb.append("?");
                return;
            }
            int next = q.poll();
            word += next + " ";
            for (int j = 1; j <= n; j++) {
                if(rank[next][j] == 1){
                    rank[next][j] = 0;
                    if(--indegree[j] == 0) q.add(j);
                }
            }

        }
        sb.append(word);

    }
    static void change(int x, int y){
        if(rank[x][y] == 1){
            rank[x][y] = 0;
            rank[y][x] = 1;
            indegree[x]++;
            indegree[y]--;

        }else{
            rank[x][y] = 1;
            rank[y][x] = 0;
            indegree[x]--;
            indegree[y]++;

        }
    }
    public static void main(String[] args) throws IOException{
        input();
        System.out.println(sb);
    }
}
