import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1062
public class BJ1062_가르침{

    static int N, K, answer;
    static String[] strs;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        strs = new String[N];

        for (int i = 0; i < N; i++){
            String input = br.readLine();
            strs[i] = input.substring(4, input.length() - 4);   // beginIndex ~ endIndex 전까지
        }

        answer = 0;
        if (K >= 5){
            v = new boolean[26];
            v['a'-'a'] = true;
            v['c'-'a'] = true;
            v['i'-'a'] = true;
            v['n'-'a'] = true;
            v['t'-'a'] = true;

            K -= 5;
            solve(0, 0);
        }
        
        System.out.println(answer);
        br.close();
    }

    static void solve(int count, int start){
        if (count == K){
            int strCount = 0;
            for (String str : strs){
                boolean check = true;
                char[] arr = str.toCharArray();
                for (char a : arr){
                    if (!v[a-'a']) {
                        check = false;
                        break;
                    }
                }
                if (check) strCount++;
            }
            answer = Math.max(answer, strCount);
            return;
        }

        for (int i = start; i < 26; i++){
            if (v[i]) continue;
            v[i] = true;
            solve(count + 1, i + 1);
            v[i] = false;
        }
    }
}