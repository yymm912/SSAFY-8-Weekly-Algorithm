import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arrB;
	static int[][] arrA;
	
	static int H, W, X, Y;
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer sb = new StringTokenizer(br.readLine());
		// input
		H = Integer.parseInt(sb.nextToken());
		W = Integer.parseInt(sb.nextToken());
		X = Integer.parseInt(sb.nextToken());
		Y = Integer.parseInt(sb.nextToken());
		arrA = new int[H][W];
		arrB = new int[H+X][W+Y];
		for(int i=0; i<H+X; i++) {
			String str = br.readLine();
			sb = new StringTokenizer(str);
			for(int j=0; j<W+Y; j++) {
				arrB[i][j] = Integer.parseInt(sb.nextToken());
			}
		}
		// 0 ~ X: 안겹치는 부분, X~H: 겹치는 부분,H ~ H+X : 안겹치는 부분
		//완전 겹치거나, 일부만 겹치는 경우는 없음.
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(i < X || j < Y) { // 안겹치는 부분
					arrA[i][j] = arrB[i][j];
				} else{
					arrA[i][j] = arrB[i][j] - arrA[i-X][j-Y];
				}
			}
		}
		// answer
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(arrA[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void print() {

		// check
		for(int i=0; i<H+X; i++) {
			System.out.println(Arrays.toString( arrB[i] ));
		}	
		System.out.println("A");
		for(int i=0; i<H; i++) {
			System.out.println(Arrays.toString(arrA[i]));
		}
	}
}
