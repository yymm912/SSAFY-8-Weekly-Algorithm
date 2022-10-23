package baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ17825_주사위윷놀이 {
    static class Node{
        int score;

        Node red;
        Node blue;
        Horse horse;

        public Node(int score){
            this.score =score;
        }
    }
    static class Horse{
        boolean isEnd;
        Node node = start;
    }
    static Node start = new Node(0), end=new Node(0);
    static Node center = new Node(25);
    static int[] dices;
    static Horse[] horse;
    static int max;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init();
        dices = new int[10];
        horse = new Horse[4];
        StringTokenizer st=  new StringTokenizer(br.readLine());
        for(int i=0; i<10; i++) {
            dices[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<4; i++){
            horse[i] = new Horse();
        }
        max = Integer.MIN_VALUE;
        perm(0,0);
        System.out.println(max);
    }

    private static void perm(int depth, int score) {
        if(depth == 10){
            max = Math.max(max,score);
            return;
        }

        for(int i=0; i<4; i++){
            Horse h = horse[i];
            if(h.isEnd){
                continue;
            }
            int dice = dices[depth];
            Node n = h.node;
            for(int d=0; d<dice; d++){
                 if(d==0 && n.blue != null){
                     n = n.blue;
                 }
                 else{
                     n = n.red;
                 }
                 if(n.equals(end)){
                     break;
                 }
            }
            if(n != end && n.horse != null){
                continue;
            }

            boolean isEnd = h.isEnd;
            if(n.equals(end)){
                h.isEnd = true;
            }
            Node current = h.node;
            current.horse = null;
            n.horse = h;
            h.node = n;

            perm(depth+1, score+n.score);

            h.isEnd = isEnd;
            h.node = current;
            current.horse = h;
            n.horse = null;
        }
    }

    private static void excepPath(Node st , Node en, int step, int count){
        for(int i=0; i<count; i++){
            st.red = new Node(st.score+step);
            st =  st.red;
        }
        st.red = en;
    }
    private static void init() {
        Node pre = start;
        //바깥족 생성
        for(int i=1; i<=20; i++){
            pre.red = new Node(i*2);
            if(i==6){
                pre.blue = new Node(13);
                excepPath(pre.blue, center,3,2);
            }else if(i==11){
                pre.blue = new Node(22);
                excepPath(pre.blue,center,2,1);
            }else if(i==16){
                pre.blue = new Node(28);
                excepPath(pre.blue,center,-1,2);
            }
            pre = pre.red;
        }
        pre.red = end;
        excepPath(center, pre,5,2);
    }
}
