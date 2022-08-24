import java.io.*;
import java.util.*;

// 작은 수 부터 꺼내서 더하고 다시 넣고의 반복 -> 하나만 남을 때까지
public class BJ1715_카드정렬하기{

    static int N;
    static PriorityQueue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        // 우선순위 큐를 이용해 큐 안에서 가장 작은 수부터 나오게 함
        q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) q.add(Integer.parseInt(br.readLine()));

        int answer = 0;
        // 두수를 꺼내서 더하고 넣고의 반복 -> 하나만 남을 때까지
        while(q.size() > 1){
            int sum = q.poll() + q.poll();
            answer += sum;
            q.add(sum);
        }

        System.out.println(answer);

        br.close();
    }

}