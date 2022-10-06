import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW차량정비소 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T,N,M,K,A,B, ans, time, recepN, repairN;
    static int[] a,b,t;
    static cus[] reception, repair; // 접수, 정비중인사람
    static Queue<cus> receptionWait, repairWait; // 기다리는 사람


    static int pro(){
        while(K > 0){
            if(recepN != 0) { // 접수중인사람 있을 경우
                for (int i = 0; i < N; i++) { // 접수 끝났으면 repairwait로
                    if (reception[i] == null) continue;
                    cus c = reception[i];
                    if(c.receptVisit + a[i] <= time){
                        reception[i] = null;
                        c.receptGo = time;
                        repairWait.add(c);
                        recepN--;
                    }

                }
            }

            if(recepN < N) { // 접수자리 남았으면
                for (int i = 0; i < N; i++) {
                    if (reception[i] == null){
                        if(!receptionWait.isEmpty() && receptionWait.peek().time <=time){ // 현재 시간보다 도착 시간이 빨라야 repairwait로 보냄
                            reception[i] = receptionWait.poll();
                            reception[i].recepNum = i;
                            reception[i].receptVisit = time;
                            recepN++;
                        }
                    }

                }
            }


            if(repairN != 0) { // 정비에서 뺄 자리 있으면
                for (int i = 0; i < M; i++) {
                    if (repair[i] == null) continue;
                    cus c = repair[i];

                    if(c.repairVisit + b[i] <= time){ // 현재 시간보다 들어온시간 + 정비소요시간이 더 빨라야 정비 완료 가능
                        if(c.recepNum == A && c.repairNum == B) ans += c.num;
                        repair[i] = null;
                        repairN--;
                        K--;
                    }

                }
            }

            if(repairN < M) { // 정비자리에 빈자리가 있다면
                for (int i = 0; i < M; i++) {

                    if(repair[i] == null && !repairWait.isEmpty() ){
                        repair[i] = repairWait.poll();
                        repair[i].repairNum = i;
                        repair[i].repairVisit = time;
                        repairN++;
                    }

                }
            }
            time++; // 1초 증가



        }

        return ans == 0 ? -1 : ans;
    }

    static void input() throws IOException{
        T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            sb.append("#"+tc+" ");
            ans  = time = recepN = repairN = 0;
            receptionWait = new PriorityQueue<>((o1, o2) -> (o1.num - o2.num));
            repairWait = new PriorityQueue<>((o1, o2) -> {
                if(o1.receptGo == o2.receptGo) return o1.recepNum - o2.recepNum;
                return o1.receptGo - o2.receptGo;
            });
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken()) - 1;
            B = Integer.parseInt(st.nextToken()) - 1;
            a = new int[N];
            b = new int [M];
            t = new int[K];
            reception = new cus[N];
            repair = new cus[M];
            st = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; n++){ //  접수 창구가 고장을 접수하는 데 걸리는 시간
                a[n] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){ //  정비 창구가 고장을 접수하는 데 걸리는 시간
                b[m] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int k = 1; k <= K; k++){ //  고객 번호, 정비소 방문하는 시간
                receptionWait.offer(new cus(k, Integer.parseInt(st.nextToken())));
            }

            sb.append(pro()).append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {
        input();

    }

    static class cus{
        int num, recepNum,repairNum, receptVisit, receptGo, repairVisit, time;

        public cus(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
}
