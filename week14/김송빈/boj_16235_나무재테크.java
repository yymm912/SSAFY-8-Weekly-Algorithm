package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class boj_16235_나무재테크 {
	 
    static int N, M, K, food[][], add[][];
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }, dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static Queue<Tree> trees;
    static class Tree implements Comparable<Tree>{
        int x, y, age;
 
        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
 
        @Override
        public int compareTo(Tree t) {
            return this.age- t.age;
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(st.nextToken()); 
            
        food = new int[N][N]; // 양분 정보
        add = new int[N][N]; // 추가 양분
        trees = new LinkedList<>(); // 나무 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                // 가장 처음에 양분은 모든 칸에 5만큼
                food[i][j] = 5;
                // 추가 양분 정보
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken());
            
            trees.add(new Tree(x, y, a));
        }
        
        // 초반에만 정렬해주면 더이상 정렬해줄 필요가 없다.
        Collections.sort((List<Tree>)trees);
        
        System.out.println(process());
    }
 
    private static int process() {
        
        // K년 후
        while(K > 0) {
            springToSummer();
            fall();
            winter();
            K--;
        }
        
        return trees.size();
    }
 
    private static void winter() {
        
        // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                food[i][j] += add[i][j];
            }
        }
        
    }
 
    // 가을에는 나무가 번식한다.
    private static void fall() {
 
        // 부모 나무를 보관
        ArrayList<Tree> parents = new ArrayList<>();
        
        int size = trees.size();
        while (size-- > 0) {
            
            Tree now = trees.poll();
            
            // 번식하는 나무는 나이가 5의 배수이어야 하며,
            if(now.age % 5 == 0) {
                // 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
                for (int d = 0; d < 8; d++) {
                    int xx = now.x + dx[d];
                    int yy = now.y + dy[d];
                    // 범위를 벗어나면
                    if(xx < 0 || xx >= N || yy < 0 || yy >= N) continue;
                    
                    trees.add(new Tree(xx, yy, 1));
                }
            }
            
            parents.add(now);
        }
        // 아기 나무들이 먼저 Queue에 들어간 후 부모 나무를 Queue에 추가해주자.
        for (Tree t : parents) {
            trees.add(t);
        }
    }
 
    // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
    // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다
    private static void springToSummer() {
        // 죽은 나무 정보 저장
        ArrayList<Tree> die = new ArrayList<>();
        
        int size = trees.size();
        while (size -- > 0) {
            Tree now = trees.poll();
            
            // 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
            if(food[now.x][now.y] - now.age < 0) {
                // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다
                die.add(new Tree(now.x, now.y, now.age / 2));
            } else {
                food[now.x][now.y] -= now.age;
                trees.add(new Tree(now.x, now.y, now.age + 1));
            }        
        }
        
        // 여름에는 봄에 죽은 나무가 양분으로 변하게 된다
        for (Tree t : die) {
            food[t.x][t.y] += t.age;
        }
    }
}