package boj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
class City {
    int end;
    int weight;
 
    City(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}
 
public class boj_11657 {
    static int n, m;
    static ArrayList<ArrayList<City>> a;
    static long[] dist;
    static final int INF = Integer.MAX_VALUE;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        n = Integer.parseInt(st.nextToken()); 
        m = Integer.parseInt(st.nextToken()); 
 
        a = new ArrayList<>(); 
        dist = new long[n + 1]; 
 
        for (int i = 0; i <= n; i++) {
            a.add(new ArrayList<>());
            dist[i] = INF;
        }
 
       
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
 
            a.get(A).add(new City(B, C));
        }
 
        StringBuilder sb = new StringBuilder();
        if (bellmanFord()) {
            sb.append("-1\n");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) {
                    sb.append("-1\n");
                } else {
                    sb.append(dist[i] + "\n");
                }
            }
        }
 
        System.out.println(sb.toString());
        
    }
 
    public static boolean bellmanFord() {
        dist[1] = 0; 
        boolean flag = false;
 

        for (int i = 1; i < n; i++) {
            flag = false;
 
         
            for (int j = 1; j <= n; j++) {
                for (City city : a.get(j)) {
                    if (dist[j] == INF) {
                        break;
                    }
 
                    if (dist[city.end] > dist[j] + city.weight) {
                        dist[city.end] = dist[j] + city.weight;
                        flag = true;
                    }
                }
            }
 
            
            if (!flag) {
                break;
            }
        }
 
        
        if (flag) {
            for (int i = 1; i <= n; i++) {
                for (City city : a.get(i)) {
                    if (dist[i] == INF) {
                        break;
                    }
 
                    if (dist[city.end] > dist[i] + city.weight) {
                        return true;
                    }
                }
            }
        }
 
        return false;
    }
 
}
