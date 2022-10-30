import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13702_이상한술집 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K;
    static int[] mak;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mak = new int[N];
        for (int i = 0; i < N; i++) mak[i] = Integer.parseInt(br.readLine());
    }

    static boolean check(long mid) {
        long cnt = 0;
        if (mid == 0) return true;
        for(int i = 0; i < N; i++){
            if(mak[i] / mid != 0){
                cnt += mak[i] / mid;
            }
        }
        return cnt >= K;

    }

    static void pro() {
        long l = 0;
        long r = Integer.MAX_VALUE;
        long ans = 0;

        while(l<=r){
            long mid = (l + r) / 2;
            if(check(mid)){
                l = mid + 1;
                ans = mid;
            }else{
                r = mid - 1;
            }
        }
        System.out.println(ans);
    }


    public static void main(String[] args) throws IOException {
        input();
        pro();

    }
}
