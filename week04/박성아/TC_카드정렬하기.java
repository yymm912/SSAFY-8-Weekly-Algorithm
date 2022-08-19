import java.util.*;

public class Main {

    public static int n, res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.offer(sc.nextInt());
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            
            res += a + b;
            pq.offer(a + b);
        }

        System.out.println(res);
    }
}