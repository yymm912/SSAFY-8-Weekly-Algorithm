import java.io.*;
import java.util.*;

public class swea_차량정비소_2477 {

    static int T, N, M, K, A, B;
    static int[] arrA, arrB;
    static int[][] answer;
    static PriorityQueue<Person> aw;
    static PriorityQueue<Person> bw;

    static class Person {
        int n;
        int t;
        int dt;
        int idx;
        public Person (int n, int t, int idx){
            this.n = n;
            this.t = t;
            this.idx = idx;
        }
        
        public Person (int n, int t, int dt, int idx){
            this.n = n;         // 사람번호
            this.t = t;         // 경과시간
            this.dt = dt;       // 접수창구 완료한 시간
            this.idx = idx;     // 접수창구 인덱스 & 정비창구 인덱스
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            // 접수시간 입력
            arrA = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arrA[i] = Integer.parseInt(st.nextToken());

            // 정비시간 입력
            arrB = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) arrB[i] = Integer.parseInt(st.nextToken());

            // 사람대기열 입력
            aw = new PriorityQueue<>((p1, p2) -> {
                if (p1.t == p2.t) return p1.n - p2.n;
                return p1.t - p2.t;
            });
            bw = new PriorityQueue<>((p1, p2) -> {
                if (p1.dt == p2.dt) return p1.idx - p2.idx;
                return p1.dt - p2.dt;
            });
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) aw.offer(new Person(i, Integer.parseInt(st.nextToken()), -1));

            Queue<Person> Await = new ArrayDeque<>();
            boolean[] av = new boolean[N];
            Queue<Person> Bwait = new ArrayDeque<>();
            boolean[] bv = new boolean[M];

            // 시작
            int time = 0;
            answer = new int[K][2];
            while(!aw.isEmpty() || !bw.isEmpty() || !Await.isEmpty() || !Bwait.isEmpty()){
                // 접수창구
                for (int i = 0, size = Await.size(); i < size; i++){
                    Person now = Await.poll();

                    if (now.t + 1 == arrA[now.idx]) {
                        av[now.idx] = false;
                        answer[now.n][0] = now.idx + 1;
                        bw.offer(new Person(now.n, 0, time, now.idx));
                    }
                    else Await.offer(new Person(now.n, now.t + 1, now.idx));
                }
                // 시간을 충족하고 빈창구가 존재할 때까지 돌림
                while (!aw.isEmpty() && aw.peek().t <= time && Await.size() < N){
                    Person now = aw.poll();     // 들어갈 사람
                    int idx = find(av);          // 빈창구 인덱스                    
                    av[idx] = true;
                    Await.add(new Person(now.n, 0, idx));
                }


                // 정비창구
                for (int i = 0, size = Bwait.size(); i < size; i++){
                    Person now = Bwait.poll();
                    if (now.t + 1 == arrB[now.idx]){
                        bv[now.idx] = false;
                        answer[now.n][1] = now.idx + 1;                        
                    }
                    else Bwait.offer(new Person(now.n, now.t + 1, now.idx));
                }

                // 빈창구가 존재하고 정비대기열이 있을 때까지 돌림
                while(!bw.isEmpty() && Bwait.size() < M){
                    Person now = bw.poll();
                    int idx = find(bv);
                    bv[idx] = true;
                    Bwait.add(new Person(now.n, 0, idx));
                }
                time++;
            }

            int answerSum = 0;
            for (int i = 0; i < K; i++){
                if (answer[i][0] == A && answer[i][1] == B) answerSum += i + 1;
            }

            sb.append("#").append(t).append(" ").append(answerSum == 0 ? -1 : answerSum).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int find(boolean[] v){
        int idx = 0;
        for (int i = 0; i < v.length; i++){
            if (!v[i]) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}