import java.io.*;
import java.util.*;

public class BJ2110_공유기설치 {
    
    static int N, C, answer;
    static int[] h;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        h = new int[N];
        for (int i = 0; i < N; i++) h[i] = Integer.parseInt(br.readLine());
        Arrays.sort(h);

        // 최소거리 최대거리를 GAP으로 설정
        int l = 1;                      // 가장 가까운 집의 거리 (= 1)
        int r = h[N - 1] - h[0];        // 가장 멀리 떨어져 있는 집의 거리        
        answer = 0;
        while(l <= r) {
            int mid = (l + r) / 2;      // 중간 거리부터 시작
            int s = h[0];               // 시작은 첫번째 집
            int c = 1;                  // 공유기 개수 카운트

            for (int i = 0; i < N; i++){
                int d = h[i] - s;       // 집과의 거리 계산
                if (d >= mid){          // 집과의 거리가 mid GAP 보다 크면
                    c++;                // 공유기 설치
                    s = h[i];           // 기준 집 변경
                }
            }

            if (c >= C){                // 공유기를 C 개만큼 다 설치할 수 있으면
                answer = mid;           // 일단 정답 저장
                l = mid + 1;            // 한칸 더 올려서 더 큰 것이 있는지 욕심내봄
            } else {                    // 공유기를 C 개만큼 설치할 수 없으면
                r = mid - 1;            // 나대지말고 하나 내리자
            }
        }

        System.out.println(answer);
        br.close();
    }
}