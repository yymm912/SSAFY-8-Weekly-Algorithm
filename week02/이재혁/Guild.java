import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Guild {

    static int N;
    static int[] fears;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        //입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        fears = new int[N];
        for (int i = 0; i < N; i++) {
            fears[i] = Integer.parseInt(st.nextToken());
        }

        //공포도 정렬
        Arrays.sort(fears);

       // 횟수는 0부터 증가시키다가
        int count = 0;
        for (int i = 0; i < N; i++) {
            count++;
            // 공포도와 같다면
            if (count == fears[i]) {
                //그룹을 하나 생성
                answer++;
                count = 0;
            }
        }

        System.out.println(answer);
    }
}