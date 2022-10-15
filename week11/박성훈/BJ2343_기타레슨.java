import java.io.*;
import java.util.*;

class BJ2343_기타레슨 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M, ans;
    static int[] a;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new int[N];
        st = new StringTokenizer(br.readLine());
        ans = 0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

    }

    static boolean check(int mid) {
        int cnt = 1;
        int time = mid;
        for(int v : a) {
            if(time - v < 0) {
                cnt++;
                time = mid - v;
            }else {
                time -= v;
            }
        }


        return cnt <= M;
    }

    static void pro() {

        int l = a[0];
        for(int i = 1; i < N; i++) l = Math.max(l, a[i]);
        int r = 1_000_000_000;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (check(mid)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
