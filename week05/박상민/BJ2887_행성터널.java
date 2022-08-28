import java.io.*;
import java.util.*;

public class BJ2887_행성터널 {

    static int N, answer;
    static int[] parent;
    static P[] ps;
    static class P {
        int n;
        int x;
        int y;
        int z;
        public P(int n, int x, int y, int z){
            this.n = n;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static class Vertex{
        int a;
        int b;
        int d;
        public Vertex(int a, int b, int d){
            this.a = a;
            this.b = b;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ps = new P[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            ps[i] = new P(i, x, y, z);
        }

        List<Vertex> vertexs = new ArrayList<>();

        // x 축 정렬
        Arrays.sort(ps, (p1, p2) -> p1.x - p2.x);
        for (int i = 1; i < N; i++) vertexs.add(new Vertex(ps[i - 1].n, ps[i].n, Math.abs(ps[i].x - ps[i - 1].x)));
        // y 축 정렬
        Arrays.sort(ps, (p1, p2) -> p1.y - p2.y);
        for (int i = 1; i < N; i++) vertexs.add(new Vertex(ps[i - 1].n, ps[i].n, Math.abs(ps[i].y - ps[i - 1].y)));
        // z 축 정렬
        Arrays.sort(ps, (p1, p2) -> p1.z - p2.z);
        for (int i = 1; i < N; i++) vertexs.add(new Vertex(ps[i - 1].n, ps[i].n, Math.abs(ps[i].z - ps[i - 1].z)));

        // 저장한 간선들을 거리기준으로 오름차순 정렬
        Collections.sort(vertexs, (v1, v2) -> v1.d - v2.d);

        makeSet();

        // 가장 짧은 간선부터 싸이클 확인하면서 N - 1 개 선택 (크루스칼)
        answer = 0;
        int count = 0;
        int size = vertexs.size();
        for (int i = 0; i < size; i++){
            if (count == N) break;
            if (!union(vertexs.get(i).a, vertexs.get(i).b)) continue;
            count++;
            answer += vertexs.get(i).d;
        }
        
        System.out.println(answer);
        br.close();
    }

    static void makeSet(){
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
    }

    static int findSet(int x){
        if (parent[x] == x) return x;
        return parent[x] = findSet(parent[x]);
    }

    static boolean union (int a, int b){
        a = findSet(a);
        b = findSet(b);
        if (a == b) return false;
        if (a > b) parent[b] = a;
        else parent[a] = b;
        return true;
    }
}