package studygroup.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2980_도로와_신호등 {
    static int N,L,D,R,G;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int wait = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());

            int time = wait + D; //현재 까지 걸린 시간 시간 = 대기 시간 + 신호등 좌표 ( 만약 안멈추고 그냥 간다면 좌표가 곧 시간 값)
            time %= (R+G); // 신호등에 대기를 하는지 안하는지 구해야함
            if(time <= R){ // R+G로 나눈 나머지값이 R보다 작거나 같다면 멈춰야 하는 상황이 생김
                wait += R-time; //그렇다면 얼마나 더 기다려야할까? 현재 시간에서 R-time초 만큼 기다리게 되므로 누적시켜줌
            }
        }
        System.out.println(L+wait); //신호등에 걸리지 않고 한번에 왔을때 == 최소값 // 최소값 + 대기 시간 = 정답
    }
}
