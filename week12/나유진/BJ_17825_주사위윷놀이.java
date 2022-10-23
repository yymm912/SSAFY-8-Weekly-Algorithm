package STUDY.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17825_주사위윷놀이 {
	static int result, info[], map[][], permuArr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		result = Integer.MIN_VALUE;
		info = new int[10];
		permuArr = new int[10];
		map = new int[6][];

		map[0] = new int[] { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38 };
		map[1] = new int[] { 13, 16, 19 };
		map[2] = new int[] { 22, 24 };
		map[3] = new int[] { 28, 27, 26};
		map[4] = new int[] { 25,30,35};
		map[5] = new int[] { 40 };

		for (int i = 0; i < 10; i++) { // 주사위 굴리는 정보
			info[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= 4; i++) { // 말 개수 선택
			dfs(i, 0); // 중복 순열
		}
		System.out.println(result);

	}

	private static void dfs(int arange, int cnt) {
		if (cnt == 10) {
			moveHorse(); // 말 이동
			return;
		}

		for (int i = 0; i < arange; i++) {
			permuArr[cnt] = i;
			dfs(arange, cnt + 1);
		}
	}

	private static void moveHorse() {
		int horseIndex[] = { -1, -1, -1, -1 }; // 각 말의 초기 좌표는 -1
		int horseLines[] = { 0, 0, 0, 0 };
		int totalScore = 0;

		for (int d = 0; d < 10; d++) {
			int score = 0; // 이번 주사위 굴린거 점수
			int horseNo = permuArr[d]; // 현재 말 번호는 ?
			int horseLine=horseLines[horseNo]; //현재 말이 몇 번 라인에 있는지 (0,1,2,3)
			int horseIdx=horseIndex[horseNo]; //현재 말의 인덱스
			int dice = info[d]; // 몇칸만큼 가는지
			
			if(horseLine==6) continue; //이미 통과 했다면 그냥 넘어감
			
			if(horseLine==0 && horseIdx!=-1) { //0번 라인일 떄 
				if(map[0][horseIdx]==10) { //10에 있다면 파란 화살표 따라가야 한다
					horseLine=1;
					horseIdx=-1;
				}
				else if(map[0][horseIdx]==20) { //20에 있다면 파란 화살표 따라가야 한다
					horseLine=2;
					horseIdx=-1;
				}
				else if(map[0][horseIdx]==30) { //30에 있다면 파란 화살표 따라가야 한다
					horseLine=3;
					horseIdx=-1;
				}
			}
			
			
			for(int i=1; i<=dice; i++) {
				if(horseLine==6) break;
				if(++horseIdx==map[horseLine].length) { //현재 라인의 끝가지 왔다면
					if(horseLine==0) {
						horseLine=5;
						horseIdx=0;
						continue;
					}
					else if(horseLine==1 || horseLine==2 || horseLine==3) {
						horseLine=4;
						horseIdx=0;
						continue;
					}
					else if(horseLine==4)  {
						horseLine=5;
						horseIdx=0;
						continue;
					}
					else if(horseLine==5) {
						horseLine=6;
						horseIdx=0;
						continue;
					}
				}
			}
			
			if( horseLine==6) {
				horseLines[horseNo]=horseLine;
				continue; //이미 통과 했다면 그냥 넘어감
			}
			
			if(!isDuplicate(horseIndex,horseLines, horseIdx, horseLine)) { //안겹치면 점수더해주고 값 옮겨줌
				horseIndex[horseNo]=horseIdx;
				horseLines[horseNo]=horseLine;
				totalScore+=map[horseLine][horseIdx];
			}
			else return;

		}

		result = Math.max(result, totalScore); // 마지막에 스코어랑 result확인

	}

	private static boolean isDuplicate(int[] horseIndex, int[] horseLines, int horseIdx, int horseLine) {
		for(int i=0; i<4; i++) {
			if(horseIndex[i]==horseIdx && horseLines[i]==horseLine) return true;
		}
		return false;
	}

}