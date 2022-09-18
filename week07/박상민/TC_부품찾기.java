import java.io.*;
import java.util.*;

public class TC_부품찾기 {
    static int N, M;
    static int[] items, check;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        items = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) items[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(items);     // 이분탐색을 위한 오름차순정렬

        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(br.readLine());    
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {       // 입력되어지는 찾아야하는 부품을 이분탐색 함수를 통해 확인
            if (checkItem(Integer.parseInt(st.nextToken()))) sb.append("yes ");
            else sb.append("no ");
        }

        System.out.println(sb.toString());
        br.close();
    }
    // 이분탐색을 이용한 부품 유무 확인
    static boolean checkItem(int item){
        int l = 0;
        int r = N - 1;

        while(l <= r){
            int m = (l + r) / 2;
            
            if (items[m] == item) return true;      // 부품이 존재하면 true 반환
            
            if (items[m] < item) {  // 현재 부품이 찾아야하는 부품보다 작으면 오른쪽 (= l을 현재 인덱스 + 1 로 변경)
                l = m + 1;
            } else {                // 현재 부품이 찾아야하는 부품보다 크면 왼쪽 (= r을 현재 인덱스 - 1 로 변경)
                r = m - 1;
            }
        }
        return false;               // 이분탐색 다돌고 나오면 부품이 존재하지 않는 경우라 false 반환
    }
}
