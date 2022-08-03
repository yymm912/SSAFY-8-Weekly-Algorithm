package baekjoon.회의실배정_1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N,cnt;
	static int[][] meeting;
	static int startTime,endTime,meetingEndTime;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		meeting = new int[N][2];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meeting[i][0] = Integer.parseInt(st.nextToken());
			meeting[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(meeting, (o1, o2) -> o1[1] == o2[1] ?  o1[0] - o2[0] : o1[1] - o2[1]); // 정렬하기 참고

		
		cnt = 1;
		meetingEndTime = meeting[0][1];
		for(int i=1;i<N;i++) {
			startTime = meeting[i][0];
			endTime = meeting[i][1];
			if(startTime >= meetingEndTime) {
				meetingEndTime = endTime;
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}

}
