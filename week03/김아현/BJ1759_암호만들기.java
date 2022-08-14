package forStudy.week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 암호는 서로 다른 L개의 알파벳으로 구성되는데, 주어진 C개의 알파벳 중에
// 최소 한개의 모음과 최소 두개의 자음으로 구성되어 있고, 오름차순 정렬 되어야 한다.

public class BJ1759_암호만들기 {

	static int L, C;
	static String[] src, tgt;  // C개의 알파벳을 담을 배열
	static int minJ, minM; // 최소 자음 모음 카운트
	static String[] moum = {"a", "e", "i", "o", "u"};
	static List<String> list; // 가능성 있는 암호들을 담을 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		tgt = new String[L];
		src = new String[C];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			src[i] = st.nextToken();
		}
		
		Arrays.sort(src);
		comb(0, 0);
		list.forEach(x -> System.out.println(x));
	}

	static void comb(int srcIdx, int tgtIdx) {
		// 기저조건
		if(tgtIdx == L) {
			// 최소 조건을 만족하는지 확인
			minJ = 0;
			minM = 0;
			for(int i=0; i<L; i++) {
				if(Arrays.asList(moum).contains(tgt[i])) minM++;
				else minJ++;
			}
			if(minJ < 2 || minM < 1) return;
			
			// tgt를 list에 삽입
			String str = String.join("", tgt);
			if(!list.contains(str))
				list.add(str);
			return;
		}
		
		if(srcIdx == C) return;
		
		tgt[tgtIdx] = src[srcIdx];
		comb(srcIdx + 1, tgtIdx + 1);
		comb(srcIdx + 1, tgtIdx);
	}
}
