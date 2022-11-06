package week14;

import java.io.*;
import java.util.*;

public class BJ2234_성곽 {
	private static int N, M, room, wall;
    private static String[][] map;
    private static boolean[][] visit;

    private static HashMap<String, Integer> rooms = new HashMap<>(); //방 번호, 넓이

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[M+1][N+1];
        visit = new boolean[M+1][N+1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = String.format("%04d", Integer.parseInt(Integer.toBinaryString(num)));
            }
        }
        System.out.println(Arrays.deepToString(map));

        int roomCnt = 0;
        int roomMax = 0;
        //탐색
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visit[i][j]) {
                    roomCnt++;//방개수
                    room = 1;//방넓이
                    bfs(i,j,roomCnt);
                    rooms.put(String.valueOf(roomCnt), room);
                    roomMax = Math.max(roomMax, room);
                }
            }
        }

        //벽부수고 최대넓이 가져오기기
        removeWall();

        //1. 방 개수
        System.out.println(roomCnt);

        //2. 가장 넓은 방
        System.out.println(roomMax);

        //3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
        System.out.println(wall);
    }

    private static void removeWall() {
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                String myRoom = map[i][j];
                for (int k = 0; k < dx.length; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 1 || ny < 1 || nx > M || ny > N) {
                        continue;
                    }

                    String otherRoom = map[nx][ny];

                    if (myRoom.equals(otherRoom)) {
                        continue;
                    }

                    if (!myRoom.equals(otherRoom)) {
                        wall = Math.max(wall, rooms.get(myRoom) + rooms.get(otherRoom));
                    }
                }
            }
        }

    }//removeWall

    private static void bfs(int x, int y, int cnt) {

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visit[x][y] = true;

        int[][] temp = new int[M+1][N+1];
        temp[x][y] = cnt;

        while (!q.isEmpty()) {

            Pair p = q.remove();
            int qx = p.x;
            int qy = p.y;

            String direct = map[qx][qy];//이진수 가져오기

            map[qx][qy] = String.valueOf(cnt);//방번호 넣기

            for (int i=0; i<dx.length; i++) {
                int nx = qx + dx[i];
                int ny = qy + dy[i];

                if (nx < 1 || ny < 1 || nx > M || ny > N) {
                    continue;
                }

                if (direct.charAt(1) == '0' && i==1 && !visit[nx][ny]) {
                    //동
                    //dx,dy : 1
                    addQueue(cnt, (Queue<Pair>) q, temp, nx, ny);
                }
                if (direct.charAt(0) == '0' && i==2 && !visit[nx][ny]) {
                    //남
                    //dx,dy : 2
                    addQueue(cnt, q, temp, nx, ny);
                }
                if (direct.charAt(2) == '0' && i==0 && !visit[nx][ny]) {
                    //북
                    //dx,dy : 0
                    addQueue(cnt, q, temp, nx, ny);
                }
                if (direct.charAt(3) == '0' && i==3 && !visit[nx][ny]) {
                    //서
                    //dx,dy : 3
                    addQueue(cnt, q, temp, nx, ny);
                }

            }

        }//while

    }//bfs

    private static void addQueue(int cnt, Queue<Pair> q, int[][] temp, int nx, int ny) {
        visit[nx][ny] = true;
        temp[nx][ny] = cnt;
        q.add(new Pair(nx, ny));
        room++;
    }//addQueue

    static class Pair {
        int x, y;
        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }//Pair

}