
import java.io.*;
import java.util.*;

public class TC_모험가길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr, (n1, n2) -> n1 - n2);

        int answer = 0;

        int danger = arr[0];
        int count = 0;
        int idx = 0;
        while(idx < N){
            if (danger > count){
                count++;
                idx++;
            } else{
                answer++;
                danger = arr[idx];
                count = 0;
                idx++;
            }
        }

        System.out.println(answer);
        br.close();
    }
}