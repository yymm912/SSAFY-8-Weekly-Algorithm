import java.io.*;
import java.util.*;

// 범위 계산하는 습관이 필요함 (int 범위를 벗어남)
public class BJ3079_입국심사{
    
    static int N, M;
    static long[] times;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new long[N];

        long max = 0;
        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            max = Math.max(max, times[i]);
        }

        long l = 1;
        long r = max * M;
        long answer = max * M;
        while(l <= r){
            long m = (l + r) / 2;

            long count = 0;
            for (int i = 0; i < N; i++) {
                count += m / times[i];
            }

            if (count >= M){
                answer = Math.min(answer, m);
                r = m - 1;
            } else l = m + 1;
        }
        
        System.out.println(answer);
        br.close();
    }
}