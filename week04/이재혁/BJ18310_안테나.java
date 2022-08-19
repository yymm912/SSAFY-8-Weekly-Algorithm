package studygroup.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ18310_안테나 {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            if(n%2==1){
                System.out.println(arr[n/2]);
            }
            else{
                System.out.println(arr[(n-1)/2]);
                //처음엔 arr[arr[n/2]-1 해줘도 된다고 생각
                //만약 1,10 10 이들오면 3/2 = 1 -> 1-1 = 0
                //0번째 값은 1 하지만 답은 1이아님
                //짝수일때도 적용 가능 따라서 바로 위 코드 하나만 적으면 끝
                //짝,홀수를 따로 생각하지 않아도 된다.
            }
        }
}