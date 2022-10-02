package studygroup.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1939_중량제한 {
    static int N, M, A, B;
    static int[] dist;
    static ArrayList<Node> node[];
    static int INF = Integer.MAX_VALUE;
    //큰값으로 초기화

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            //dist배열에는 최대 중량을 담아야함 일단 -1로 초기화
            dist = new int[N + 1];
            Arrays.fill(dist, -1);

            //노드 객체를 담는 리스트 생성
            node = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                node[i] = new ArrayList<>();
            }

            //연결생성
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                node[from].add(new Node(to, weight));
                node[to].add(new Node(from, weight));
            }
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            Dijkstra();

            System.out.println(dist[B]);

        }

        public static void Dijkstra() {
            //중량기준 내림차순
            PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (o2.weight - o1.weight));
            pq.offer(new Node(A, INF));
            dist[A] = INF;
            /**
             *  priority_queue에 후보를 넣고,
             *  그 중 가장 중량이 큰 후보를 뽑기
             */
            while (!pq.isEmpty()) {
                int from = pq.peek().to;
                int weight = pq.peek().weight;
                pq.poll();
                if (dist[from] > weight) continue; // 이미 중량이 크면 넘기기

                /**
                 * 후보의 최대 중량 값은 이전 정점의 최대 중량값과 이전 정점과 자신이 연결된 간선의 가중치(중량) 중 더 작은 값을 선택
                 * 그리고 후보의 최대 중량 값이 더 큰 값으로 갱신될 때 큐에 넣기
                 */
                for (int i = 0; i < node[from].size(); i++) {
                    int to = node[from].get(i).to;
                    int weightTo = Math.min(weight, node[from].get(i).weight);
                    if (weightTo > dist[to]) {
                        dist[to] = weightTo;
                        pq.offer(new Node(to, weightTo));
                    }
                }
            }
        }
    }
