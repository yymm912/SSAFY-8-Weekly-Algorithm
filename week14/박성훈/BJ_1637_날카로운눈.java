import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1637_날카로운눈{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, A, C, B;
    static int[][] arr;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int count(int A, int C, int B, int mid) {
        if (mid < A) return 0;
        return (Math.min(C, mid) - A) / B + 1;
    }

    static boolean check(int mid) {
        long s = 0;
        for (int i = 0; i < N; i++) {
            s += count(arr[i][0], arr[i][1], arr[i][2], mid);
        }

        return s % 2 == 1;
    }

    static void pro() {
        long l = 1, r = Integer.MAX_VALUE, ans = 0, ansCnt = 0;
        while (l <= r) {
            long mid = (l + r) / 2;
            if (check((int) mid)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (ans == 0) System.out.println("NOTHING");
        else {
            for(int i = 0; i < N; i++){
                if(arr[i][0] <= ans && ans <= arr[i][1] && (ans - arr[i][0]) % arr[i][2] == 0 ) ansCnt++;
            }
            System.out.println(ans + " " + ansCnt);
        }


    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
