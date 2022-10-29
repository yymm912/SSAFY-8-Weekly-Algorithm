package baekjoon.아기상어_16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
   
   static int N, sy, sx, size, ans, level;
   static int[][] map;
   static Node target;
   
   static int[] dy = {-1, 1, 0, 0};
   static int[] dx = {0, 0, -1, 1};
   
   public static void main(String[] args) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      
      map = new int[N+1][N+1];
      ans = 0;
      level = 0;
      for (int i = 1; i <= N; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         for (int j = 1; j <= N; j++) {
            int n = Integer.parseInt(st.nextToken());
            if(n == 9) {
               sy = i;
               sx = j;
            }
            map[i][j] = n;
         }
      }
      
      size = 2;
      shark();
      
      System.out.println(ans);
   }
   
   static void shark() {
      int ny = sy;
      int nx = sx;
      map[ny][nx] = 0;
      while(true) {
    	  findEat(ny, nx);
    	  if(target == null) return;
    	  map[target.y][target.x] = 0;
          ans += target.cnt;
          ny = target.y;
          nx = target.x;
          level++;
          if(size == level) {
        	  level = 0;
        	  size++;
          }
      }
   }
   
   static void findEat(int y, int x) {
      Queue<Node> q = new ArrayDeque<>();
      boolean[][] visit = new boolean[N+1][N+1];
      visit[y][x] = true;
      q.offer(new Node(y,x,0));
      target = null;
      while(!q.isEmpty()) {
         Node node = q.poll();
         if(map[node.y][node.x] != 0 && map[node.y][node.x] < size) {
        	 if(target == null) {
        		 target = new Node(node.y, node.x, node.cnt);
        	 }else {
        		 if(node.cnt > target.cnt) return;
        		 if(node.cnt == target.cnt) {
        			 if(node.y == target.y) {
        				 if(node.x < target.x) target = node;
        			 }else if(node.y < target.y) {
        				 target = node;
        			 }
        		 }else if(node.cnt < target.cnt) {
        			 target = node;
        		 }
        		 
        	 }
         }
         
         for (int i = 0; i < 4; i++) {
            int ny = node.y + dy[i];
            int nx = node.x + dx[i];
            if(ny<1 || nx<1 || ny>N || nx>N || visit[ny][nx]) continue;
            if(map[ny][nx] <= size) {
            	visit[ny][nx] = true;
                q.offer(new Node(ny, nx, node.cnt+1));
            }
            
         }
      }
   }
   
   static class Node{
      int y,x,cnt;
      
      public Node(int y, int x, int cnt) {
         this.y = y;
         this.x = x;
         this.cnt = cnt;
      }
   }
}