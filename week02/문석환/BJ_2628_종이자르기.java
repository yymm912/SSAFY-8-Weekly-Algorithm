package boj.p2628;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author 문석환
 * 백준 2628번 종이자르기
 * W : 가로길이
 * H : 세로길이
 * M : 자를 점선의 개수
 * K : 가로(0)인지 세로(1)인지 판단하는 숫자
 * N : 점선의 넘버
 * 입력)
 * 	W H
 * 	M
 * M개의 점선들)
 * 	K N
 *  K N
 *  ...
 * 출력)
 * 	모든 점선을 자른 후의 영역 중 가장 큰 넓이값
 */

public class Main {
	static int W,H,M,K,N,maxW,maxH;
	static ArrayList<Integer>[] A = new ArrayList[2];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		for(int i=0;i<2;i++) {
			A[i] = new ArrayList<>();
		}

		// 첫번째 점선과 마지막 점선을 계산하기 위한 작업
		A[0].add(0);
		A[0].add(H);
		A[1].add(0);
		A[1].add(W);

		// A[0]에는 가로 점선의 넘버값 저장
		// A[1]에는 세로 점선의 넘버값 저장
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			A[K].add(N);
		}

		// max값들 최소값으로 초기화
		maxW = Integer.MIN_VALUE;
		maxH = Integer.MIN_VALUE;

		for(int i=0;i<2;i++) {
			// 순서대로 비교하기 위해서 정렬
			Collections.sort(A[i]);
			for(int j=0;j<A[i].size()-1;j++) {
				if(i == 0) {
					// 가로로 자르기
					maxH = Math.max(maxH, A[i].get(j+1) - A[i].get(j));

				}else {
					// 세로로 자르기
					maxW = Math.max(maxW, A[i].get(j+1) - A[i].get(j));
				}
			}
		}

		System.out.println(maxW*maxH);

	}
}
