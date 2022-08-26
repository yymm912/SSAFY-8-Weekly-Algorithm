package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class BJ_1697_숨바꼭질{
    public static Queue<Integer> q = new ArrayDeque<>();
    public static Integer[] visit = new Integer[100001];
    public static int n,m;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        q.add(n);
        bfs();
        System.out.print(visit[m]);
    }
    
    public static void bfs(){
        int tmp=0, idx=0;
        Queue<Integer> q2 = new ArrayDeque<>();
        q2.add(0);
        while(!q.isEmpty()){
            tmp = q.poll();
            idx = q2.poll();
            if(visit[tmp]==null) {
            	visit[tmp] = idx; 
            	if(tmp==m) return;
            	if(tmp>0) {
                	q.add(tmp-1);
                	q2.add(idx+1);
                }
                if(tmp<100000) {
                	q.add(tmp+1);
                	q2.add(idx+1);
                }
                if(tmp<50001) {
                	q.add(tmp*2);
                	q2.add(idx+1);
                }
            }
        }
        
    }
}