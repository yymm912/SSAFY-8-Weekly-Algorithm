import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p6497 {
    static int[] parent;
    static PriorityQueue<Integer[]> costs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            parent = new int[m];
            for (int i = 0; i < m; i++) parent[i] = i;

            costs = new PriorityQueue<>(new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] n1, Integer[] n2){
                    return n1[2] - n2[2];
                }
            });
            
            for (int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                Integer[] temp = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                costs.add(temp);
            }

            int answer = 0;
            
            while (!costs.isEmpty()){
                Integer[] cost = costs.poll();
                int sParent = find(cost[0]);
                int eParent = find(cost[1]);

                if (sParent == eParent) {                
                    answer += cost[2];
                    continue;
                }
                parent[eParent] = sParent;
            }
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();    
    }
    public static int find(int x){
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
}