package studygroup.week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3190_뱀 {
    static int N,K,L,ans;
    static int map[][];
    static int[] tail = {0,0};
    static int[] head = {0,0};
    static int dxy[] = {0,1};
    static Queue<int[]> q = new ArrayDeque<>();
    static char[] check;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            map[x][y] = 2;
        }
        map[0][0] = 1;

        L = Integer.parseInt(br.readLine());
        check = new char[10000];
        for(int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dis = st.nextToken().charAt(0);
            check[time] = dis;
        }
        while(true){


            System.out.println();
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }

            head[0] += dxy[0];
            head[1] += dxy[1];
            //내몸과 닿거나 배열 밖에나가면 끝
            if(head[0] <0||head[1]<0||head[0]>=N||head[1]>=N||map[head[0]][head[1]] == 1){
                break;
            }

            q.add(new int[] {dxy[0],dxy[1]});
            //사과를 못먹으면 꼬리를 줄임
            if(map[head[0]][head[1]]==0){
                if(!q.isEmpty()){
                    int tmp[] = q.poll();
                    map[tail[0]][tail[1]] = 0;
                    tail[0] += tmp[0];
                    tail[1] += tmp[1];
                }
            }
            else if(map[head[0]][head[1]]==2){
                map[head[0]][head[1]] = 1;
            }
            map[head[0]][head[1]] = 1;

            ans++;

            if(check[ans] == 'D'){
                if(dxy[0] == 0 && dxy[1] == 1){
                    dxy[0] = 1;
                    dxy[1] = 0;
                }
                else if(dxy[0] == 0 && dxy[1] == -1) {
                    dxy[0] = -1;
                    dxy[1] = 0;
                }
                else if(dxy[0] == -1 && dxy[1] == 0) {
                    dxy[0] = 0;
                    dxy[1] = 1;
                }
                else if(dxy[0] == 1 && dxy[1] == 0) {
                    dxy[0] = 0;
                    dxy[1] = -1;
                }

            }
            else if(check[ans]=='L'){
                if(dxy[0] == 0 && dxy[1] == 1){
                    dxy[0] = -1;
                    dxy[1] = 0;
                }
                else if(dxy[0] == 0 && dxy[1] == -1) {
                    dxy[0] = 1;
                    dxy[1] = 0;
                }
                else if(dxy[0] == -1 && dxy[1] == 0) {
                    dxy[0] = 0;
                    dxy[1] = -1;
                }
                else if(dxy[0] == 1 && dxy[1] == 0) {
                    dxy[0] = 0;
                    dxy[1] = 1;
                }
            }

            }
        System.out.println(ans+1);
       }
    }

