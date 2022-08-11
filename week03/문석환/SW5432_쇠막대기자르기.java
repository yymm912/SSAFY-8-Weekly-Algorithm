package SWEA.D4.쇠막대기자르기_5432;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 번호 : 5432
// 난이도 : D4
// 제목 : 쇠막대기 자르기
// T : 테스트 케이스
// ()이면 레이저 , (())면 쇠막대기 하나에 레이저 하나
// 이때 레이저로 모든 쇠막대기가 잘렸을 때 총 막대기 개수를 출력
public class Solution {
    static int T,ans;
    static List<Integer> arr = new ArrayList<>();
    static String input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            input = br.readLine();
            // 해당 케이스에 대한 프로세스 시작
            pro(t);
        }
        // 출력
        System.out.println(sb);
    }

    static void pro(int t){
        ans = 0;
        int bar = 0;
        int len = input.length();
        for(int i=0;i<len;i++){
            char c = input.charAt(i);
            if(c == '('){
                bar++;
            }else{
                bar--;
                if(input.charAt(i-1) == '('){
                    // 레이저인 경우
                    ans += bar;
                }else{
                    ans++;
                }
            }
        }
        sb.append("#").append(t).append(" ").append(ans).append("\n");
    }
}
