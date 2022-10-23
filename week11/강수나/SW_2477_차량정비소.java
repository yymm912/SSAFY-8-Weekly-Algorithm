import java.io.*;
import java.util.*;
 
public class SW_2477_차량정비소 {
    static class Desk{
        int no;
        int time;
        int numK;
         
        public Desk(int no, int time, int numK) {
            this.no = no;
            this.time = time;
            this.numK = numK;
        }
    }
    static int N,M,K,A,B;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
             
            int Ai[] = new int[N+1];
            Desk nA[] = new Desk[N+1];
            int Bi[] = new int[M+1];
            Desk nB[] = new Desk[M+1];
             
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++) {
                Ai[i] = Integer.parseInt(st.nextToken());
                nA[i] = new Desk(i,0,0);
            }
             
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=M;i++) {
                Bi[i] = Integer.parseInt(st.nextToken());
                nB[i] = new Desk(i,0,0);
            }
             
            int k[] = new int[K+1];
            int visited[][] = new int[K+1][2];
             
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=K;i++) {
                k[i] = Integer.parseInt(st.nextToken());
            }
             
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            Queue<Integer> q = new LinkedList<>();
             
            int time=0;
            outer: while(true) {
                 
                for(int i=1;i<=M;i++) {
                    if(nB[i].time==0) continue;
                    else nB[i].time--;
                }
                 
                for(int i=1;i<=N;i++) {
                    if(nA[i].time==0) continue;
                    else nA[i].time--;
                }
                 
                for(int i=1;i<=N;i++) {
                    if(nA[i].time==0 && nA[i].numK!=0) {
                        q.offer(nA[i].numK);
                        nA[i].numK=0;
                    }
                }
                 
                if(!q.isEmpty()) {
                    for(int i=1;i<=M;i++) {
                        if(q.isEmpty()) break;
                        if(nB[i].time==0) {
                            int num = q.poll();
                            nB[i].time=Bi[i];
                            nB[i].numK = num;
                            visited[num][1]=i;
                        }
                    }
                }
                 
                for(int i=1;i<=K;i++) {
                    if(time==k[i]) pq.offer(i);
                }
                 
                if(!pq.isEmpty()) {
                    for(int i=1;i<=N;i++) {
                        if(pq.isEmpty()) break;
                        if(nA[i].time==0) {
                            int num = pq.poll();
                            nA[i].time=Ai[i];
                            nA[i].numK = num;
                            visited[num][0]=i;
                        }
                    }
                }
                time++;
                for(int i=1;i<=K;i++) {
                    if(visited[i][1]==0) continue outer;
                }
                break;
            }
            int ans=0;
            for(int i=1;i<=K;i++) {
                if(visited[i][0]==A && visited[i][1]==B) {
                    ans+=i;
                }
            }
            if(ans==0) System.out.println("#"+t+" -1");
            else System.out.println("#"+t+" "+ans);
        }
    }
}
