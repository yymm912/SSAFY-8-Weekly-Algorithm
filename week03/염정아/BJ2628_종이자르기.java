package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * [입력]
 * W(종이의 가로) H(종이의 세로)
 * N(칼로 잘라야하는 점선의 갯수)
 * 0(가로) 1(세로) , 점선의 번호 
 * return 가장 큰 종이 조각의 넓이  
 * */

//10 8
//3
//0 3
//1 4
//0 2
//-> 30

//10 10
//1
//0 1
//-> 90


public class BJ2628_종이자르기 {
	static int W, H, N;
	static int P = 2;
	static int minW, minH, maxW, maxH;
	static ArrayList<Integer> ws = new ArrayList<>();
	static ArrayList<Integer> hs = new ArrayList<>();


	// 슬라이딩 윈도우 사용
	private static void solve() {
		// 처음 두 개의 값을 구한다. (빼기의 값)
		for (int i = 0; i < P; i++) {
			minW = Math.abs(minW - hs.get(i));
			minH = Math.abs(minH - ws.get(i));
		}

		// 여기서도 한번 확인해야한다.
		maxW = Math.max(maxW, minW);
		maxH = Math.max(maxH, minH);

		// 슬라이딩 윈도우 기법을 사용해서 더하고 뺀다.
		// 그 값들 중에서 최댓값을 찾는다.
		for (int i = P; i < hs.size(); i++) {
			minW = Math.abs(minW + hs.get(i - P));
			minW = Math.abs(minW - hs.get(i));

			maxW = Math.max(maxW, minW);
		}

		for (int i = P; i < ws.size(); i++) {
			minH = Math.abs(minH + ws.get(i - P));
			minH = Math.abs(minH - ws.get(i));

			maxH = Math.max(maxH, minH);

		}

	}


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 변수 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());

		// 종이의 끝과 끝 범위 추가
		ws.add(0);
		ws.add(H);
		hs.add(0);
		hs.add(W);

		// 점선 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int opt = Integer.parseInt(st.nextToken());

			if (opt == 0) ws.add(Integer.parseInt(st.nextToken()));
			else hs.add(Integer.parseInt(st.nextToken()));
		}

		// 정렬
		Collections.sort(ws);
		Collections.sort(hs);

		solve();

		// 출력
		System.out.println(maxW * maxH);

	} // end main
}
