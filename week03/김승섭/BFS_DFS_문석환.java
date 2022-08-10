package basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*  작성자 : 문석환
	문제는 문제일뿐 다들 코로나 안걸리게 몸조심 하세요 !!! 
	제목 : 경훈이가 쏘아올린 공 
	경훈이는 쉬는시간에 항상 여기저기 돌아다닌다. 
	그랬던 경훈이가 220810 오늘 코로나에 확진 !!! 
	경훈이는 어제 교실에 있는 모든 학생들의 자리에 가서 말을 걸었을 때 
	코로나에 걸린 경험이 있는 학생들은 코로나에 전염되지 않지만 코로나에 걸리지 않았던 사람들은 내일 코로나에 걸린다.
	내일 코로나에 걸리는 사람들의 총 수와 명단을 출력하세요 !
	단 명단 출력은 이름 오름차순으로 ! 
	
	입력)  0 : 코로나에 걸린 경험이 없음 , 1 : 코로나에 걸린 경험이 있음  
	김아현 0 권용진 1 강수나 1 박성훈 1 장시우 1 이수경 1 
	하상재 0 김빛누리 1 김정윤 1 김진호 1 염정아 1 김송빈 1 
	정경훈 1 박성아 1 이재혁 0 안명수 1 문석환 0 김승섭 0 
	정채원 1 김주영 1 서우린 1 박상민 1 나유진 1 반유진 1 
	나나나 1 배충현 1 권혁근 0 가가가 1 문문문 1 바바바 1
	
	출력)
	경훈이가 퍼뜨린 코로나에 걸린 총인원 : 7
	인원 명단
	권혁근
	김승섭
	김아현
	문석환
	이재혁
	정경훈
	하상재

*/
public class 경훈이가쏘아올린공 {
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int ROW = 5, COL = 6,totalCnt,jkhX,jkhY;
	static List<String> ansList = new ArrayList<>();
	static Student[][] classRoom = new Student[ROW+1][COL+1];
	static boolean[][] visit;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/basic/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	static void bfs(int y,int x) {
		
	}
	
	static class Student{
		
		
	}
}
