package studygroup.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11404_플로이드 {
    /**
     *
     */
        static int map[][];
        //여기를 MAX_VALUE로 줘버리면 오버플로우가 발생할 수 있음
        static final int INF = 9900001;
        
        public static void main (String[]args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer stk;
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            map = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    //자기 자신은 0, 아니면 INF로 초기화
                    if (i == j) continue;
                    else map[i][j] = INF;
                }
            }
            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(stk.nextToken());
                int e = Integer.parseInt(stk.nextToken());
                int w = Integer.parseInt(stk.nextToken());
                //시작 도시와 도착 도시를 연결하는 도로가 여러개 나오는 케이스 존재
                map[s][e] = Math.min(w, map[s][e]);
            }
            //플로이드 와샬 알고리즘
            floydWarshall(n);

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] == INF) {
                        sb.append("0 ");
                    } else {
                        sb.append(map[i][j] + " ");
                    }
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
        public static void floydWarshall(int n) {
             for(int k=1; k<=n; k++) { 
             //경유지 			
                for(int i=1; i<=n; i++) { 
             //출발지
                    if(i==k) continue;
                    for(int j=1; j<=n; j++) {
             //도착지
                        //출발지로 시작해서 목적지로 가야하는데 이런 아래경우는 의미없음
                        if(i==j || j==k) continue;
                        if(map[i][j] > map[i][k]+ map[k][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }				
                     }			
                }
             }	
        }
                     
}
