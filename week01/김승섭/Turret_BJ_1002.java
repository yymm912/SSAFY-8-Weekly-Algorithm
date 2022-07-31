package study;

import java.util.Scanner;

public class Turret_BJ_1002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			
		int x1 = sc.nextInt();	
		int y1 = sc.nextInt();	
		int r1 = sc.nextInt();	
		int x2 = sc.nextInt();	
		int y2 = sc.nextInt();	
		int r2 = sc.nextInt();	
		int type = 0; // 경우의 수 초기화-선언
		double distance = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2)); // 두 점 사이의 거리 root{(y2-y1)^2+(x2-x1)}
		
		// 두 인물이 검색할 수 있는 최대 거리의 자취를 그리면 원이 된다.
		// 이 두 원이 만나는 곳(점)이 곧 목표물이 위치한 좌표이다.
		
		// 두 원이 일치하는 경우 => -1 (r1 == r2) && 터렛 좌표 같음
		if (r1 == r2 && x1 == x2 && y1 == y2) type = -1;
		// 두 원이 두 점에서 만나는 경우 => 2 (r1 + r2) > 터렛 사이 거리 > (r1 - r2)
		else if((r1 + r2) > distance && Math.abs(r1 - r2) < distance) type = 2;
		
		// 두 원이 한 점에서 만나는 경우 => 1 (r1 + r2) == 터렛 사이 거리 || (r1 - r2) == 터렛 사이 거리
		else if((r1 + r2) == distance || Math.abs(r1 - r2) == distance) type = 1;
		
		// 두 원이 만나지 않는 경우 => 0 (r1 + r2) < 터렛 사이 거리
		else if((r1 + r2) < distance) type = 0;
		
		
					
		System.out.println(type);
					
		}
		

	}

}
