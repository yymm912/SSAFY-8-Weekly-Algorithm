import java.io.*;
import java.util.*;

/*
 * 진실 아는 사람과 같은 파티 참가하면 같은 집합으로
 * 집합 만들고 나서 집합 사람들이 포함된 파티 X */
// 거짓말
public class D_boj_1043_bnuri00 {
	
	static int N, M, P;	// 사람 수, 파티 수, 진실 사람 수
	static int[] parent;
	static List<Integer>[] party;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		
		if(P == 0) {	// 진실을 아는 사람이 없음
			System.out.println(M);
			return;
		}
			
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		int trueFirst = Integer.parseInt(st.nextToken()); // 진실 아는 처음 사람
		for (int i = 1; i < P; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			union(trueFirst, tmp);  // 진실 아는 사람 집합
		}
		
		party = new List[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			int partyFirst = Integer.parseInt(st.nextToken());
			party[i].add(partyFirst);
			for (int j = 1; j < n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				party[i].add(tmp);
				union(partyFirst, tmp);
			}
		}
		
		System.out.println(checkParty(trueFirst));
		
	}
  // 과장된 말 할 수 있는 파티 계산
  // 파티 사람 중 truePerson과 같은 집합 있으면 party개수 줄이고
  // 다른 파티 사람 검사
	private static int checkParty(int truePerson) {
		int result = M;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < party[i].size(); j++) {
				if(check(truePerson, party[i].get(j))) {
					result--;
					break;
				}
			}
		}
		return result;
	}
	static boolean check(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return true;
		return false;
	}
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return;
		parent[bRoot] = aRoot;
		
	}
	private static int find(int a) {
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}
}
