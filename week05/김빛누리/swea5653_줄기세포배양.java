package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 힘들었음
 * 
 * 풀이시간: 5시간+
 * 참고: swea 댓글 (배열 사용, 배열 크기..,번식 후에도 카운트 포함되는 경우)
 * 
 * 44,556KB		255ms
 * 
 * <풀이방식>
 * - 세포 입력받아 내림차순 정렬한 후 (생명력 큰 세포 우선으로 하기 위해) 큐에 모두 더해줌
 * - 
 * 
 * <삽질목록>
 * - map이랑 activeQ 초기화 안해줘서 30분동안 디버깅 ㅇ<-<
 * - 문제 해석에 많은 시간이 걸림, 그럼에도 놓친 부분 있었음
 * 
 * */
public class swea5653_줄기세포배양 {
	static class Cell{
		int x, y;
		int time;	// 생명력
		int remainT;	// 활성까지 남은 시간
		//boolean active;
		public Cell(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.remainT = time;	// time과 같은 값으로 초기화
		}	
	}
	
	//static int mapSize;
	static int N, M, K;	// 세로, 가로, 배양시간
	
	static int[][] map = new int[651][651];
	
	static Queue<Cell> q = new ArrayDeque<>();
	static Queue<Cell> activeQ = new ArrayDeque<>();
	static LinkedList<Cell> list = new LinkedList<>();
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			// 초기화
			q.clear();
			activeQ.clear();
			resetMap();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for(int i = K; i < K+N; i++) {	// 세로, y
				st = new StringTokenizer(br.readLine());
				for(int j = K; j < K+M; j++) {	// 가로, x
					int input = Integer.parseInt(st.nextToken());
					if(input == 0) continue;
					
					map[i][j] = input;
					list.add(new Cell(j, i, input));
				}
			}
			// 시간 내림차순 정렬 후 큐에 넣음 (생명력 큰 세포 우선)
			list.sort((c1, c2) -> c2.time - c1.time);
			q.addAll(list);
			list.clear();		
			
			addCell();
			
			System.out.println("#"+t+" "+(q.size() + activeQ.size()));
			
		}
		

	}
	static void addCell() {
		for(int t = 1; t <= K; t++) {
			int size = q.size();
			
			// 번식 후에도 살아남은 큐 정리 (시간 줄이기, 세포 버리기 등)
			if(!activeQ.isEmpty()) activeQprocess();
			
			for(int i = 0; i < size; i++) {
			Cell c = q.poll();
			
			if(c.remainT!=0) {	// 활성화까지 남은 시간  있을 경우 -> 1 빼고 다시 넣기
				c.remainT = c.remainT - 1;
				q.add(c);
			} else {	// 활성화까지 남은 시간 0 -> 세포분열하기
				int x = c.x;
				int y = c.y;
				
				if(c.time != 1) {	// 생명력이 1이 아닐 경우 번식 후에도 살아남음, 별도 큐에 넣는다
					c.remainT = c.time-1;	// 현재 지난 시간 빼고 넣기
					activeQ.add(c);
				}
				
				for(int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(map[ny][nx]==0) {	// 빈 곳일 경우 맵에 적고 큐에 넣음
						map[ny][nx] = c.time;
						q.add(new Cell(nx, ny, c.time));
					}
				}
			}
			}
			// 테스트용 : 살아있는 세포 출력 및 맵 출력
			//print(t, q.size()+activeQ.size());
			//printQ(t, q.size()+activeQ.size());
		
		}
	}
	// 번식 후에도 살아남은 세포들, 남은 시간에 따라 죽여줌
	static void activeQprocess() {
		int size = activeQ.size();
		
		for(int i = 0; i < size; i++) {
			Cell c = activeQ.poll();
		
			if(c.remainT>1) {
				c.remainT = c.remainT - 1;
				activeQ.add(c);
			} 
		}
		
	}
	// map 초기화
	static void resetMap() {
	    for (int i=0; i<N + K*2; i++) {
	        for (int j=0; j<N + K*2; j++) {
	            map[i][j] = 0;
	        }
	    }
	}
	// 테스트용
//	static void printQ (int t, int q) {
//	    System.out.println(t + " queue: " + q);
//	    System.out.println("===========================");
//	}
//	static void print (int t, int q) {
//	    System.out.println("===========================");
//	    System.out.println(t + " queue: " + q);
//	    System.out.println("===========================");
//	    for (int i=0; i<N + K*2; i++) {
//	        for (int j=0; j<N + K*2; j++) {
//	            System.out.print(map[i][j]+" ");
//	        }
//	        System.out.println();
//	    }
//	}

}
