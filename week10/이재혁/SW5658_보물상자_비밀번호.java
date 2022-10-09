package studygroup.week9;

import java.util.*;
import java.io.*;

/**
 * 각 변에 16진수 숫자가 적힌 보물 상자가 있음
 * 보물상자의 뚜껑은 시꼐방향으로 돌릴 수 있음 돌릴때마다 숫자가 시계방향으로 회전
 * 각 변에 동일한 개수의 숫자가 있음 , 시계방향 순으로 높은 자리숫자에 해당하며 하나의 수를 나타냄
 * 보물 상자에는 좌물쇠가 있음 -> 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K 번쨰 큰수를 10진수로 만든 수
 */

public class SW5658_보물상자_비밀번호 {
    static int T,N,K;
    static char[] map;
    static TreeSet<Integer> set;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new char[N];
            String str = br.readLine();
            for(int i=0; i<N; i++){
                map[i] = str.charAt(i);
            }
            //내림차순으로 정렬 하는 셋 
            set = new TreeSet<>(Collections.reverseOrder());

            get();


            for(int i=0; i<N-1; i++){
                rotate();
                get();
            }
            int count = 0;
            for(int a : set){
                count++;
                if(count == K){
                    System.out.println("#"+t+" "+a);
                    break;
                }
            }


        }
    }

    private static void rotate() {
    	//회전시키는 함수
        char c = map[N-1];
        for(int i=N-1; i>0; i--){
            map[i] = map[i-1];
        }
        map[0] = c;
    }

    private static void get() {
    	//4변이니까 
        int n = N/4;
        	
        for(int i=0; i<N; i+=n){
            String str = "";
            for(int j=i; j<i+n; j++){
                str += map[j];
            }
            //16진수를 10진수로 변환 후 넣기
            set.add(Integer.valueOf(str,16));
        }
    }

}
