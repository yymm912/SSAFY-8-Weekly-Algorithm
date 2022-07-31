/*
* 백준 2252 줄 세우기 문제
* https://www.acmicpc.net/problem/2252
* 시간 제한 2초
* [입력]
* N : 학생 수
* M : 키 비교 횟수
* M개의 키 비교 입력
* [출력]
* 학생들을 앞에서부터 줄을 세운 결과를 출력(답이 여러가지인 경우에는 아무거나 출력)
* */

/*
 * 문제를 보고 가장 먼저 떠오른 것이 위상 정렬
 * 위상 정렬이란 Directed Acyclic Graph로 간선에 방향이 있고 싸이클이 없는 그래프를 뜻한다.
 * 학생을 정점 , 키 비교를 간선으로 생각 -> N개의 정점과 M개의 간선을 가진다.
 * 이때 방향은 A가 B보다 앞에 서야 한다면 A->B라는 간선을 가진다고 약속
 * 시간 복잡도 O(V+E) -> N의 최대값 M의 최대값을 더하면 132000이므로 시간 제한으로 걸린 2초는 충분
 */
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] indeg;
    static void input(){
        // Adjacent List 생성 및 indegree 계산
        N = sc.nextInt();
        M = sc.nextInt();
        adj = new ArrayList[N+1];
        indeg = new int[N+1];

        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<M;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            adj[x].add(y); // x -> y로 가는 간선 표현
            indeg[y]++; // y 정점의 indegree 증가
        }
    }

    static void topological_sort(){
        Queue<Integer> Q = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(indeg[i] == 0) Q.add(i);
        }
        while(!Q.isEmpty()){
            int x = Q.poll();
            sb.append(x).append(" ");
            for(int y : adj[x]){
                indeg[y]--;
                if(indeg[y] == 0) Q.add(y);
            }
        }
    }
    static void pro(){
        topological_sort();
        System.out.println(sb);
    }
    public static void main(String[] args) {
        input();
        pro();
    }
    static class FastReader{
        StringTokenizer st;
        BufferedReader br;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s){
            try{
                br = new BufferedReader(new FileReader(new File(s)));
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }

        String next(){
            if(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();;
                }
            }

            return st.nextToken();
        }

        Integer nextInt(){
            return Integer.parseInt(next());
        }
    }
}
