import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크 {
	static int N, min;
	static int[][] team;
	static boolean[] select;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		team = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) { 
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		select = new boolean[N];
		min = Integer.MAX_VALUE;
		comb(0, 0); // 조합 방식 이용
		System.out.println(min);

	}
	static void comb(int cur, int cnt) {
		if(cnt == N/2) { // 한 팀의 인원이 맞춰지면
			diff(); // 점수 차이를 구한다.
			return;
		}
		for (int i = cur; i < N; i++) { // 한 팀(N/2명)을 한 명씩 조합하여 시너지 점수 차가 가장 적은 조합을 구한다.
			if(select[i]) continue;
			select[i] = true;
			comb(i+1, cnt+1);
			select[i] = false;
		}
	
	}
	
	static void diff() {
		int team1 = 0;
		int team2 = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if(select[i]&&select[j]) { // 선택배열(팀1로 선택된 인원) 참이면, 시너지 점수들을 누적합
					team1 += team[i][j] + team[j][i];
				}
				else if(!select[i] && !select[j]) {// 선택배열(팀2로 선택된 인원) 거짓이면, 시너지 점수들을 누적합
					team2 += team[i][j] + team[j][i];
				}
			}
		}

		int dif = Math.abs(team1 - team2); // 점수차를 절대값으로 구하고
		
		if(dif == 0) { // 그 차이가 0이라면 0을 출력하고 프로그램 종료.
			System.out.println(0);
			System.exit(0); // 프로그램을 종료시킴
		}
		import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14889_스타트와링크 {
	static int N, min;
	static int[][] team;
	static boolean[] select;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		team = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) { 
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		select = new boolean[N];
		min = Integer.MAX_VALUE;
		comb(0, 0); // 조합 방식 이용
		System.out.println(min);

	}
	static void comb(int cur, int cnt) {
		if(cnt == N/2) { // 한 팀의 인원이 맞춰지면
			diff(); // 점수 차이를 구한다.
			return;
		}
		for (int i = cur; i < N; i++) { // 한 팀(N/2명)을 한 명씩 조합하여 시너지 점수 차가 가장 적은 조합을 구한다.
			if(select[i]) continue;
			select[i] = true;
			comb(i+1, cnt+1);
			select[i] = false;
		}
	
	}
	
	static void diff() {
		int team1 = 0;
		int team2 = 0;
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if(select[i]&&select[j]) { // 선택배열(팀1로 선택된 인원) 참이면, 시너지 점수들을 누적합
					team1 += team[i][j] + team[j][i];
				}
				else if(!select[i] && !select[j]) {// 선택배열(팀2로 선택된 인원) 거짓이면, 시너지 점수들을 누적합
					team2 += team[i][j] + team[j][i];
				}
			}
		}

		int dif = Math.abs(team1 - team2); // 점수차를 절대값으로 구하고
		
		if(dif == 0) { // 그 차이가 0이라면 0을 출력하고 프로그램 종료.
			System.out.println(0);
			System.exit(0); // 프로그램을 종료시킴
		}
		
		min = Math.min(min, dif); // 최소값을 구함
	
	}

}

		min = Math.min(min, dif); // 최소값을 구함
	
	}

}
