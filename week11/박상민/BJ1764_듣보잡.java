import java.io.*;
import java.util.*;

public class BJ1764_듣보잡 {

    static int N, M;
    static Set<String> set;
    static PriorityQueue<String> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 중복발생하지 않게 Set 에 다 넣음 (듣도못한사람)
        set = new HashSet<>();
        for (int i = 0; i < N; i++) set.add(br.readLine());

        // 자동정렬하기 위해 우선순위 큐사용
        q = new PriorityQueue<>();  // 듣보잡 집합소
        for (int i = 0; i < M; i++){
            String input = br.readLine();
            // 듣도못한사람에 포함되는 사람은 큐에 저장
            if (set.contains(input)) q.add(input);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(q.size()).append("\n");
        while(!q.isEmpty()) sb.append(q.poll()).append("\n");

        System.out.println(sb.toString());
        br.close();
    }
}
