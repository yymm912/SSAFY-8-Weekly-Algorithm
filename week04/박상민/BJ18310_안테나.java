import java.io.*;
import java.util.*;

// 모든 집까지 거리의 총 합이 가장 최소가 되는 곳은 중간지점
// 입력된 집들의 위치를 "정렬"하여 가장 작은 값과 가장 큰값의 중간 값을 출력
public class BJ18310_안테나{

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 홀 짝 구분해서 가운데 부분 찾음
        System.out.println(N % 2 == 0 ? arr[N / 2 - 1] : arr[N / 2]);
        br.close();
    }
}