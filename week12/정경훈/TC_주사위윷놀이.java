package baekjoon.주사위윷놀이_17825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int max;
	
	static int[] seq = new int[10]; // 어느 말을 움직일지 정해두는 배열
	
	static int[] dice = new int[10]; // 들어오는 입력
	static boolean[][] map; // 말이 있는지 체크하기 위한 용도
	static Unit[] units = new Unit[4]; // 말 4개 
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		max = 0;
		
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		setting(0);
		
		System.out.println(max);
	}
	
	static void setting(int cnt){
		if(cnt == 10) {
			start();
			return;
		}
		
		
		for (int i = 0; i < 4; i++) {
			seq[cnt] = i;
			setting(cnt+1);
		}
	}
	
	static void start() {
		// seq따라 이동하는 시뮬을 돌린다.
		// 같은 위치에 말이 겹치거나 하는 경우 바로 끝
		 // 초기화
		init();
		int score = 0;
		
		for (int i = 0; i < 10; i++) {
			Unit u = units[seq[i]]; // 움직일 말 선택
			if(u.isArrive) continue; // 이미 도착한 말이면 스킵
			
			if(map[u.isBlue][u.v]) map[u.isBlue][u.v] = false; // 자기 자리에서 방빼기
			int culLo = u.v; // 현재 위치
			if(culLo == 10 || culLo == 20 || culLo == 30) u.isBlue = 1;
			
			int d = u.d; // 이동거리
			int count = dice[i];
			for (int j = 0; j < count ; j++) {
				culLo += d;
				// 특수 경우를 생각해야함
				if(culLo == 19) {
					d = 6;
				}else if(culLo == 24 && u.isBlue == 1) {
					d = 1;
				}else if(culLo == 28 && u.isBlue == 1) {
					d = -1;
				}else if(culLo == 25) {
					d = 5;
				}
			}
			// 출발지를 넘어가는 경우
			if( culLo > 40 ) {
				u.isArrive = true;
				continue;
			}
			// 멈췄는데 이미 그 자리에 말이 있는 경우
			if(map[u.isBlue][culLo]) {
				return; // 게임 종료
			}
			if(culLo == 40) {
				if(map[0][40] || map[1][40]) {
					return;
				}
			}
			
			map[u.isBlue][culLo] = true; // 나 그자리에 있소
			
			// 멈췄을 때 10 20 30인 경우
			if(culLo == 10) {
				d = 3;
			}else if(culLo == 20) {
				d = 2;
			}else if(culLo == 30 && u.isBlue == 0) {
				d = -2;
			}
			
			// 정보 갱신
			score += culLo;
			u.v = culLo;
			u.d = d;
		}
		
		// 게임이 다 끝났을 때
//		if(score > max) {
//			for (int i = 0; i < 10; i++) {
//				System.out.print(seq[i]);
//			}
//			System.out.println('\n' + score);
//		}
		max = Math.max(max, score);
		
	}
	
	static void init() {
		map = new boolean[2][41];
		for (int i = 0; i < 4; i++) {
			units[i] = new Unit();
		}
	}
	
	static class Unit{
		int v = 0;
		int d = 2; // 시작 이동거리
		int isBlue = 0;
		boolean isArrive = false;
	}
}
