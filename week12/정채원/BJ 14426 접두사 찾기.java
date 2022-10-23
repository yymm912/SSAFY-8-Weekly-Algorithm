import java.io.*;
import java.util.*;

class Main{
	static class StrNode{
		StrNode[] nxt = new StrNode['z'-'a'+1]; // [alphabet] = 다음 node 
	}
	// 문자열 트리 구성
	static StrNode addChar(StrNode node, char ch) {
		if(node.nxt[ch - 'a'] == null) {
			StrNode nxt = new StrNode();
			return node.nxt[ch - 'a'] = nxt; // 새로 만듦
		}
		return node.nxt[ch - 'a']; // 이미 있음
	}
	static void addString(String str) {
		StrNode cur = root;
		for(char ch : str.toCharArray()) {
			cur = addChar(cur, ch);
		}
	}
	static boolean findString(String str) {
		StrNode cur = root;
		for(char ch : str.toCharArray()) {
			cur = cur.nxt[ch - 'a'];
			if(cur == null) return false;
		}
		return true;
	}
	static StrNode root;
	static int N, M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		root = new StrNode();
		int findCnt=0;
		for(int i=0; i<N; i++) {
			addString(br.readLine());
		}
		for(int i=0; i<M; i++) {
			if(findString(br.readLine())) findCnt++;
		}
		System.out.println(findCnt);
	}
}