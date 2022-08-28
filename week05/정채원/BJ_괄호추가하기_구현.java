import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static char[] ops;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * 1. 연산자 -> 부분집합 : 뭉쳐있는 1은 한 괄호에 묶어서 계산
		 * 2. 연산 : 1끼리 뭉쳐있는 거 먼저 계산, 0을만나면  : 앞 서 계산한 결과 + 다음 연산자를 새 배열에 저장
		 * 3. 다음 0 우선순위 연산을 차례로 계산
		 * */
		N = Integer.parseInt(br.readLine());
		ops = br.readLine().toCharArray();
		subset(0,0, false);
		System.out.println(max);
	}
	static boolean isVisited(int select, int idx) {
		return (select & 1 << idx) != 0;
	}
	static int visit(int select, int idx) {
		return select | 1 << idx;
	}
	static int max = Integer.MIN_VALUE;
	static void subset(int idx, int select, boolean prevSelected) {
		if(idx == N>>1) {
			int num = calc(select);
			max = Math.max(num, max);
			return;
		}
		
		subset(idx+1, select, false);
		if(!prevSelected) subset(idx+1, visit(select, idx), true);
	}
	static int calc(int select) {
		StringBuilder nxtOps = new StringBuilder();
		int accum = ops[0]-'0';
		for(int i=0; i<N>>1; i++) {
			if(isVisited(select, i)) { // 먼저 계산
				switch(ops[(i<<1)+1]) { // 연산자 확인
				case '+': {accum = accum + ops[(i<<1)+2]-'0'; break;}
				case '-': {accum = accum - (ops[(i<<1)+2]-'0'); break;}
				case '*': {accum = accum * (ops[(i<<1)+2]-'0'); break;}
				}
			} else {// 다음 계산 시,
				nxtOps.append(accum).append(ops[(i<<1)+1]);
				accum = ops[(i<<1)+2]-'0';				
			}
		}
		nxtOps.append(accum);
		
		// 다음 우선순위 계산
		StringTokenizer st = new StringTokenizer(nxtOps.toString(), "+-*", true);
		String num = st.nextToken();
		if(num.charAt(0) == '-') accum = -Integer.parseInt(st.nextToken());
		else accum = Integer.parseInt(num);
		
		while(st.hasMoreTokens()) {
			char op = st.nextToken().charAt(0);
			num = st.nextToken();
			int nxt = 0;
			if(num.charAt(0) == '-') nxt = -Integer.parseInt(st.nextToken());
			else nxt = Integer.parseInt(num);

			switch(op) { // 연산자 확인
			case '+': {accum = accum + nxt; break;}
			case '-': {accum = accum - nxt; break;}
			case '*': {accum = accum * nxt; break;}
			}
		}
		return accum;
	}
}