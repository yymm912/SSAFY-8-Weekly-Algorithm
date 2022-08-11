package basic;

public class BASIC_DFS2_q {
	/* 
	 * 기존에 있던 2차원 배열 map을 0으로 싹 갈아엎어(어떤 방식이든 가능) 출력하고 (map print는 메서드를 제작)
	 * dfs 방식을 활용하여 map 윗 부분의 절반 이상, (0,0)~(3,6) 까지만 1로 덮고 출력해봅시다..
	 */
	
	static int[][] map = {
			{0,  0,  0,  0,  0,  0,  0}, // row 0
	        {0, 11, 12, 13, 14, 15, 16}, // row 1 
	        {0, 21, 22, 23, 24, 25, 26}, // row 2
	        {0, 31, 32, 33, 34, 35, 36}, // row 3
	        {0, 41, 42, 43, 44, 45, 46}, // row 4
	        {0, 51, 52, 53, 54, 55, 56}, // row 5
	        {0, 61, 62, 63, 64, 65, 66}, // row 6
	       //    1   2   3   4   5   6 // 0 Dummy
	};
	
	//delta: 상 -하- 좌 - 우 순서
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	// vistied 재방문 체크
	
	static boolean [][] visit;
	
	public static void main(String[] args) {

		visit = new boolean[7][7];
		map = new int[7][7];
		
		////////////////////////
		
		////////////////////////
		print();
		dfs(0, 0);
		print();


	}
	static void dfs(int y, int x) {
		////////////////////////
						
		
		
		////////////////////////
	}
	static void print() {
		////////////////////////
		
		////////////////////////
	}



}
