package basic;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*

다음은 부울경 3반의 자리 배치도 이다 (10 x 6)
오늘 오전 코로나 확진자는 1로 표시, 비확진자는 0으로 표시되어 있다
코로나 확진자의 맞은편, 뒷편, 양 옆자리가 오늘 오후 추가 확진이 되었다고 할 때
(추가 확진이 된 사람은 다른 사람에게 바이러스를 옮기지 않음, 걸린 사람 아무도 다시 음성판정을 받지 않음)
오늘 23:59분 기준 우리반 코로나 확진자는 총 몇 명인가? */
public class BASIC_PairTest {

static int [][] map = {
        {1, 1, 0, 0, 0, 0 },
        {1, 0, 0, 0, 0, 0 },
        {0, 0, 0, 1, 0, 0 },
        {0, 0, 0, 1, 1, 0 },
        {0, 0, 0, 0, 0, 0 },
        {0, 0, 0, 0, 0, 0 },
        {1, 0, 0, 0, 0, 0 },
        {0, 0, 0, 0, 0, 1 },
        {0, 0, 0, 0, 1, 1 },
        {0, 0, 0, 0, 0, 0 }};

static int[] dy= {-1,1,0,0};
static int[] dx= {0,0,-1,1};
static int COUNT = 0;
static boolean[][] visit;

public static void main(String[] args) {
    bfs();
    System.out.println("오늘 확진자 수 : " + COUNT);
}

static void bfs() {
	Queue<Node> queue = new ArrayDeque<>();
	visit = new boolean[map.length][map[0].length];
	for(int i=0; i<map.length; i++) {
		for(int j=0; j<map[0].length; j++) {
			if(map[i][j] == 1) {
				queue.add(new Node(i, j));
				visit[i][j] = true;
				COUNT++;
			}
		}
	}

	// 추가 확진 전 출력
	for(int i=0; i<map.length; i++) {
		System.out.println(Arrays.toString(map[i]));
	}
	System.out.println();
	
	// bfs
	while(!queue.isEmpty()) {
		Node cur = queue.poll();
		
		for(int d=0; d<4; d++) {
			int nx = cur.x + dx[d];
			int ny = cur.y + dy[d];
			if(nx < 0 || nx >= map[0].length || ny < 0 || ny >= map.length) continue;
			if(visit[ny][nx]) continue;
			visit[ny][nx] = true;
			map[ny][nx] = 1; // 확인 출력을 위해
			COUNT++;
		}
	}
	
	// 추가 확진 후 출력
	for(int i=0; i<map.length; i++) {
		System.out.println(Arrays.toString(map[i]));
	}
}

static class Node {
    int y; int x;
    Node (int y, int x) {
        this.y=y;
        this.x=x;
    }
    
    public String toString() {
        return "Node [y=" + y + ", x=" + x + "]";
    }
}
}