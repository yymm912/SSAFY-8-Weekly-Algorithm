package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ17281야구 {

	static int N;
	static int inning[][];
	static int tgt[]; // 타자가 타석에 서는 순서
	static int src[];
	static boolean select[];
	static int cnt, sum, ans;
	static List<Integer> list = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	static boolean flag;
	static int player[] = new int[4];
	static int ex;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		inning = new int[N][9];
		tgt = new int[9];
		select = new boolean[9];
		src = new int[9];


		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				inning[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < 9; i++)	src[i] = i;
		tgt[3] = 0; //0번 타자가 항상 3번째 투수 
		select[3] = true;

		perm(1);
		
		System.out.println(max);

	}

	static void search() {
		
		// 타순은 정해졌고, 해당 타순에 대해 점수 계산 
		int out = 0;
		int num = -1; // out 이후 연결되는 타순 

			for (int n = 0; n < N; n++) { // 이닝 횟수 만큼 반복
				flag = false;
				Arrays.fill(player, 0);
				while(true) { // out이 3번 나올때까지 이닝은 끝나지 않음 
					if(flag) break;
					for (int i = 0; i < 9; i++) {
						
						if( i == 0 && num != -1 ) { // 이전에 out으로 끝났다면 i 갱신
							i = num;
							num = -1;
						}
						
						switch (inning[n][tgt[i]]) {
						case 0: // 아웃
							out++;
							break;
						case 1: // 안타
							if(player[3] == 1) ans+=1;
							player[3] = player[2];
							player[2] = player[1];
							player[1] = 1;
							break;
						case 2: // 2루타
							if(player[3] == 1) ans+=1;
							if(player[2] == 1) ans+=1;
							player[3] = player[1];
							player[2] = 1;
							player[1] = 0;
							break;
						case 3: // 3루타
							if(player[3] == 1) ans+=1;
							if(player[2] == 1) ans+=1;
							if(player[1] == 1) ans+=1;
							player[2] = player[1] = 0;
							player[3] = 1;
							break;
						case 4: // 홈런
							//리스트에 있는 주자와 새로 들어온 타자 모두 1점씩
							if(player[3] == 1) ans+=1;
							if(player[2] == 1) ans+=1;
							if(player[1] == 1) ans+=1;
							Arrays.fill(player, 0);
							ans+=1;
							break;
						}

						if (out == 3) {
							flag = true;
							Arrays.fill(player, 0);
							num = (i+1)%9;
							out = 0;
							break;
						}

					} // i
				}
				
			}

		}

	

	static void perm(int tgtIdx) {
		
		if (tgtIdx == tgt.length) {
			ex++;
			System.out.print(Arrays.toString(tgt));
			System.out.println();
			ans = 0;
			search();
			max = Math.max(max, ans);

			return;
		}

		for (int i = 0; i < 9; i++) {

			if(select[i]) continue;
			
			tgt[i] = tgtIdx;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		
		
		}

	}

}