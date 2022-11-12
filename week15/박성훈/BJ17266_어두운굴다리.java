import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.StringTokenizer;

class BJ17266_어두운굴다리 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, K, M;
    static int[] light;

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        light = new int[M];
        for(int i = 0; i < M; i++){
            light[i] = Integer.parseInt(st.nextToken());
        }
    }

    static boolean check(int mid) {
        int last = 0;
        for(int i = 0; i < M; i++){
            if(light[i] - last > mid) return false;
            last = light[i] + mid;
        }

        return last >= N;
    }

    static void pro() {
        int l = 1;
        int r = N;
        int ans = 0;
        while(l<=r){
            int mid = (l + r) / 2;
            if(check(mid)){
                r = mid - 1;
                ans = mid;
            }else{
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