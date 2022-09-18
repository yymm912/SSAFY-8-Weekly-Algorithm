import java.util.Scanner;

class Lim{
	/** 10개의 비트로 만들 수 있는 숫자들
	 * 	각 숫자들이 가지고 있는 비트 1의 개수를 미리 구해둠 
	 * np로 구하면 깔끔하게 1024?번만 돌아서 빠르다.
	 * */
	
	static int[] bitCnt = new int[1 << 10];
	static void init() {
		int c=0;
		for(int cnt=1; cnt<=10; cnt++) {
			int[] arr = new int[10];
			for(int i=0; i<cnt; i++) arr[9-i] = 1;
			do {
				bitCnt[getBit(arr)] = cnt;
			} while(np(arr));			
		}
	}
	static int getBit(int[] num) {
		int bit = 0;
		for(int i=0; i<10; i++) {
			if(num[i] == 1)	bit |= (1 << i);
		}
		return bit;
	}
	static boolean np(int[] num) {
		int i = num.length-1;
		while(i > 0 && num[i-1] >= num[i]) i--;
		if(i == 0) return false;
		
		int j = num.length-1;
		while(num[i-1] >= num[j]) j--;
		swap(i-1, j, num);
		
		int k = num.length-1;
		while(i < k) swap(i++, k--, num);
		return true;
	}
	static void swap(int i, int j, int[] num) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}
}
class UserSolution {
	final int MAX_N = 10000;
	final int MAX_M = 10;
	int N, M;
	int[][] bitImageList;
	void init(int N, int M, char mImageList[][][])
	{
		Lim.init();
		/**
		 * mImageList -> [][]배열을 bit로 표현한 int[]배열로 변경
		 * */
		this.N = N;
		this.M = M;
		bitImageList = new int[N][M];
		for(int i=0; i<N; i++) {
			convertToBit( mImageList[i], bitImageList[i]);
		}
	}
	int findImage(char mImage[][])
	{
		int[] bitImage = new int[M];
		convertToBit(mImage, bitImage);
		
		/** n개의 이미지 돌면서 diffCnt를 계산함.
		 * 	가장 적은 diffCnt가 나온 이미지.
		 * */
		int minCnt = 10000;
		int minIdx = 0;
		for(int n=0; n<N; n++) {
			int cnt = getDiffBetweenImages(bitImage, bitImageList[n]);
			if(minCnt > cnt) {
				minCnt = cnt;
				minIdx = n;
				if(cnt == 0) break;
			}
		}
		return minIdx+1;
	}
	/** char[][]를 int[]로 변환한 이미지 반환 */
	void convertToBit(char[][] from, int[] to) {
		for(int m=0; m<M; m++) {
			for(int b=0; b<M; b++) {
				to[m] |= (from[m][b] << (M-1-b));
			}
		}
	}
	/** 이미지 끼리 diff 개수 구함 */
	private int getDiffBetweenImages(int[] a, int[] b) {
		int cnt=0;
		for(int i=0; i<M; i++) {
			cnt += getDiffBitCnt(a[i], b[i]);
		}
		return cnt;
	}
	/** bit 차이나는 경우, 몇개의 bit가 다른지 계산*/
	private int getDiffBitCnt(int a, int b) {
		int diff = a ^ b; // xor은 같으면 0, 다르면 1임
		return Lim.bitCnt[diff];
	}
}

class Solution {
	
	private static int seed = 5;

	private static Scanner sc;
	private static UserSolution user = new UserSolution();
	
	private static final int MAX_N = 10000;
	private static final int MAX_M = 10;

	private static char ori_image_list[][][] = new char[MAX_N][MAX_M][MAX_M];
	private static char bak_image_list[][][] = new char[MAX_N][MAX_M][MAX_M];
	private static char dummy[] = new char[5005];
	private static char bak_image[][] = new char[MAX_M][MAX_M];
	 
	private static int pseudo_rand()
	{
		seed = seed * 214013 + 2531011;
		return (seed >> 16) & 0x7fff;
	}

	static int run(int _score)
	{
		int n = sc.nextInt();
		int m = sc.nextInt();
		seed = sc.nextInt();
		int ratio = sc.nextInt();
		int query_cnt = sc.nextInt();
		
		
		for (int i = 0; i < n; i++)
		{
			for (int y = 0; y < m; y++)
			{
				for (int x = 0; x < m; x++)
				{
					ori_image_list[i][y][x] = 0;
					int v = pseudo_rand() % 100;
					if (v >= ratio)
						ori_image_list[i][y][x] = 1;
					
					bak_image_list[i][y][x] = ori_image_list[i][y][x];
				}
			}
		}

		user.init(n, m, bak_image_list);

		int user_ans, correct_ans;

		for (int query = 0; query < query_cnt; query++)
		{
			int num = pseudo_rand() % n;

			for (int y = 0; y < m; y++)
			{
				for (int x = 0; x < m; x++)
				{
					bak_image[y][x] = ori_image_list[num][y][x];
				}
			}

			int bad_sector_cnt = pseudo_rand() % 2 + 1;

			for (int i = 0; i < bad_sector_cnt; i++)
			{
				int by = pseudo_rand() % m;
				int bx = pseudo_rand() % m;

				bak_image[by][bx] ^= 1;
			}

			user_ans = user.findImage(bak_image);
			correct_ans = sc.nextInt();

			if (user_ans != correct_ans)
				_score = 0;
		}

		return _score;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
		
		sc = new Scanner(System.in);

		int tc = sc.nextInt();
		int score = sc.nextInt();

		for (int t = 1; t <= tc; t++)
		{
			int tc_score = run(score);
			System.out.println("#"+ t + " " + tc_score);
		}

		sc.close();
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

}