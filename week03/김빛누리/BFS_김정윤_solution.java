import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static int[][] map = {
            {1, 0, 1, 1, 1, 1, 1}, // row 0
            {1, 1, 1, 0, 0, 1, 1}, // row 1
            {1, 0, 1, 0, 1, 0, 1}, // row 2
            {1, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 1, 1, 1, 1} 
//             0  1  2  3  4  5  6
    };

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) {
        bfs(0, 0);
        System.out.println(map[6][6]);
    }


    public static void bfs(int x, int y) {
        // 초기화
        visited = new boolean[7][7];

    // 자료구조
    Queue<Node> q = new ArrayDeque<>(); 
    
    // 방문
     // 시작 점을 queue에 담고 방문 시작
     q.offer(new Node(y, x));
     visited[x][y] = true;

    while(!q.isEmpty()) {
        Node now = q.poll();
        
        for (int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= 7 || ny >= 7 || visited[nx][ny] || map[nx][ny] == 0) continue;
            q.offer(new Node(nx, ny));
            map[nx][ny] = map[now.x][now.y] + 1;
            visited[nx][ny] = true;
        }
        
    }
}

static class Node {
    int x, y;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
}
