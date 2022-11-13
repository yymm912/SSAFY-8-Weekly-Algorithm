import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238_스타트택시 {
	static int N, M, fuel;
	static int[][] map;
	static Node start = new Node();
	static List<TargetInfo> passengerInfoList = new ArrayList<>();

	static int[][] visited;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		start.y = Integer.parseInt(st.nextToken());
		start.x = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			Node startC = new Node();
			Node endC = new Node();
			st = new StringTokenizer(br.readLine());
			startC.y = Integer.parseInt(st.nextToken());
			startC.x = Integer.parseInt(st.nextToken());

			endC.y = Integer.parseInt(st.nextToken());
			endC.x = Integer.parseInt(st.nextToken());

			passengerInfoList.add(new TargetInfo(startC, endC, 0));
		}

		// M만큼 반복
		for (int t = 0; t < M; t++) {

//			System.out.println("==== 택시 위치 ====");
//			System.out.println("y :" + start.y + "   x : " + start.x);
			// visited배열 초기화
			initVisited();
			// 현재 택시 위치를 기준 전체 map에 대한 이동거리 계산
			BFS(start.y, start.x);

//			System.out.println("==== 거리 계산 ====");
//			for (int i = 0; i <= N; i++) {
//				for (int j = 0; j <= N; j++) {
//					System.out.print(visited[i][j] + " ");
//				}
//				System.out.println();
//			}

			// visited 배열에 저장된 거리를 승객정보 배열에 업데이트
			setDistance();

//			System.out.println(" ==== 승객 정보 ====");
//			for (int i = 0; i < passengerInfoList.size(); i++) {
//				System.out.println(passengerInfoList.get(i).toString());
//			}

			// 현재 택시 위치에서 제일 가까운 승객의 위치를 가져옴
			TargetInfo passenger = getPassenger();

			// 택시와 승객의 위치가 같을 경우와 가지 못하는 경우를 구분해 줘야함
			if (passenger.distance == 0) {
				// 승객의 위치와 택시와 위치가 같은 경우가 아니라면 -1 출력 후 리턴
				if (!((start.y == passenger.start.y) && (start.x == passenger.start.x))) {
					System.out.println(-1);
					return;
				}
			}

//			System.out.println(" ==== 현재 택시와 가장 가까운 승객 ====");
//			System.out.println(passenger.toString());

			// 제일 가까운 승객에게 이동하는 만큼 연료 소비
			fuel -= passenger.distance;

			// 제일 가까운 승객을 목적지까지 데려다 줘야함
			// 현재 승객의 위치에서 이동해야 하기 때문에 visited배열 초기화
			initVisited();

			// 현재 승객의 모든 이동 거리 계산
			// 택시는 승객의 위치에서 이동 시작
			BFS(passenger.start.y, passenger.start.x);

			// 도착지에 도달할 수 없을 때
			if (visited[passenger.end.y][passenger.end.x] == 0) {
				System.out.println(-1);
				return;
			}

//			System.out.println("==== 거리 계산 ====");
//			for (int i = 0; i <= N; i++) {
//				for (int j = 0; j <= N; j++) {
//					System.out.print(visited[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 목적지로 이동한 만큼 연료 소비
			fuel -= visited[passenger.end.y][passenger.end.x];

			// 이동 도중에 연료가 떨어지면 영업 중단
			if (fuel < 0) {
				System.out.println(-1);
				return;
			}

			// 연료 충전
			fuel += (visited[passenger.end.y][passenger.end.x] * 2);

			// 택시의 현재 위체 업데이트
			start.y = passenger.end.y;
			start.x = passenger.end.x;

			// 승객 이동이 끝났다면 승객 정보에서 해당 승객 삭제띠
			passengerInfoList.remove(passenger);

//			System.out.println("==== 승객 삭제 후 승객 정보 배열 ====");
//			for (int i = 0; i < passengerInfoList.size(); i++) {
//				System.out.println(passengerInfoList.get(i).toString());
//			}
//
//			System.out.println("==== 이동 후 남은 연료 ====");
//			System.out.println(fuel);
//
//			System.out.println("================================================================");

		}
		System.out.println(fuel);

	}

	static void setDistance() {
		for (int i = 0; i < passengerInfoList.size(); i++) {
			Node start = passengerInfoList.get(i).start;
			passengerInfoList.get(i).distance = visited[start.y][start.x];
		}
	}

	static TargetInfo getPassenger() {
		PriorityQueue<TargetInfo> pq = new PriorityQueue<>();

		for (int i = 0; i < passengerInfoList.size(); i++)
			pq.offer(passengerInfoList.get(i));

		return pq.poll();

	}

	static void BFS(int y, int x) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(y, x));

		visited[y][x] = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Node cur = q.poll();

				for (int d = 0; d < 4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					if (ny < 1 || nx < 1 || ny > N || nx > N || visited[ny][nx] > 0 || map[ny][nx] == 1
							|| (ny == y && nx == x))
						continue;

					visited[ny][nx] = visited[cur.y][cur.x] + 1;
					q.offer(new Node(ny, nx));
				}
			}
		}
	}

	static void initVisited() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				visited[i][j] = 0;
			}
		}
	}

	static class TargetInfo implements Comparable<TargetInfo> {
		Node start;
		Node end;
		int distance;

		TargetInfo(Node start, Node end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(TargetInfo o) {
			if (this.distance != o.distance) {
				return this.distance - o.distance;
			} else {
				if (this.start.y != o.start.y) {
					return this.start.y - o.start.y;

				} else {
					return this.start.x - o.start.x;
				}
			}

		}

		@Override
		public String toString() {
			return "TargetInfo [start=" + start + ", end=" + end + ", distance=" + distance + "]";
		}

	}

	static class Node {
		int y;
		int x;

		public Node() {
		}

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}

	}

}
