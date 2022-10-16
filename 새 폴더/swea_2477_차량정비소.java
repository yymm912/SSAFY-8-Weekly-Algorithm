package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_2477_차량정비소 {
    static int ans, T, N, M, K, A, B;
    static int[] arr, brr, trr;

    static PriorityQueue<Node> wa = new PriorityQueue<>((e1, e2) -> e1.no - e2.no);
    static Node[] a;
    static Queue<Node> wb = new ArrayDeque<>();
    static Node[] b;

    static List<Node> res = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

            brr = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) brr[i] = Integer.parseInt(st.nextToken());

            trr = new int[K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) trr[i] = Integer.parseInt(st.nextToken());

            ans = -1;
            wa.clear();
            a = new Node[N + 1];
            wb.clear();
            b = new Node[M + 1];
            res.clear();

            int time = 0;
            while (true) {
                if (res.size() == K) break;

                for (int i = 1; i <= K; i++) {
                    if (time == trr[i]) wa.offer(new Node(i, -1, -1, 0, 0));
                }

                for (int i = 1; i <= N; i++) {
                    if (!wa.isEmpty() && a[i] == null) {
                        a[i] = wa.poll();
                        a[i].ai = i;
                    }

                }

                for (int i = 1; i <= N; i++) {
                    if (a[i] != null) a[i].aTime++;
                }

                for (int i = 1; i <= N; i++) {
                    if (a[i] != null && a[i].aTime == arr[i]) {
                        wb.offer(a[i]);
                        a[i] = null;
                    }

                }


                for (int i = 1; i <= M; i++) {
                    if (!wb.isEmpty() && b[i] == null) {
                        b[i] = wb.poll();
                        b[i].bi = i;
                    }

                }

                for (int i = 1; i <= M; i++) {
                    if (b[i] != null) b[i].bTime++;
                }

                for (int i = 1; i <= M; i++) {
                    if (b[i] != null && b[i].bTime == brr[i]) {
                        res.add(b[i]);
                        b[i] = null;
                    }

                }

                time++;
            }

            ans = calc();
            sb.append("#" + t + " " + ans + "\n");


        }

        System.out.println(sb.toString());

    } 


    private static int calc() {
        int sum = 0;
        for (Node node : res) if (node.ai == A && node.bi == B) sum += node.no;
        return sum == 0 ? -1 : sum;
    } // end calc


    static class Node {
        int no;
        int ai;
        int bi;
        int aTime;
        int bTime;


        public Node(int no, int ai, int bi, int aTime, int bTime) {
            super();
            this.no = no;
            this.ai = ai;
            this.bi = bi;
            this.aTime = aTime;
            this.bTime = bTime;
        }

    } // end Node

}