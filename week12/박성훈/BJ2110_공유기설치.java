import java.io.*;
import java.util.*;

public class BJ2110_공유기설치 {

        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringBuilder sb = new StringBuilder();
        static StringTokenizer st;

        static int N, C;
        static int[] a;

        static void input() throws IOException {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            a = new int[N];
            for (int i = 0; i < N; i++)	a[i] = Integer.parseInt(br.readLine());
            pro();
        }


        static boolean check(int mid) {
            int prev = a[0];
            int cnt = 1;
            for(int i = 1; i < N; i++) {
                if(a[i] - prev >= mid ){
                    cnt++;
                    prev = a[i];
                }
            }
            return cnt >= C;
        }

        static void pro() {
            Arrays.sort(a);
            int l = 1;
            int r = a[N-1];
            int ans = 1;

            while(l <= r) {
                int mid = (l + r)/2;
                if(check(mid)) {
                    l = mid + 1;
                    ans = mid;
                }else {
                    r = mid - 1;
                }
            }
            System.out.println(ans);

        }

        public static void main(String[] args) throws IOException {
            input();
        }
    }


