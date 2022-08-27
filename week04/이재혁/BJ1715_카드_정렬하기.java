package studygroup.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1715_카드_정렬하기 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        /*
            10 20 40
            3묶음 이있다면
            (10 +20)+(30+40) = 100
            묶음으로 완성된 숫자는 다음 연산에 누적되어 연산에 계속 포함되게 됨
            -> 정렬을 하면 되겠다고 생각했지만
            단순히 정렬만 해서는 값이 계속 바뀌기 때문에 합치는 시점 기준으로
            최소값들을 합쳐야 하므로 우선순위 큐를 사용
         */
        N = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> q = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            q.add(Long.parseLong(st.nextToken()));
        }

        long c =0;
        while( q.size() >=2){
            Long a = q.poll();
            Long b = q.poll();
            c += a+b; //누적합 저장
            q.add(a+b); //
        }
        System.out.println();
    }
}
