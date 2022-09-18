package studygroup.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ10775_공항 {
        static int[] parent;
        public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            for(int i=1;i<=N;i++) {
                parent[i] = i;
            }
            int answer = 0;

            int M = Integer.parseInt(br.readLine());
            for(int i=0;i<M;i++) {
                int airplane = Integer.parseInt(br.readLine());
                if(airplane > N) break;
                if(find(airplane)==0) break;
                else answer++;
                union(airplane);
            }
            System.out.println(answer);
        }

        public static int find(int x) {
            if(parent[x]==x) return x;
            else return parent[x] = find(parent[x]);
        }

        public static void union(int x) {
            x = find(x);
            parent[x] = x-1;
        }
    }