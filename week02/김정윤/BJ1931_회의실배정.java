package week2.김정윤;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ1931_회의실배정 {
	static int N, cnt;
	static int[][] meetingRoom;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 회의 시작 - 종료 시간 init
		setMeetingTime(br);

		// 2차원 배열 정렬 - 끝나는 시간 기준으로 정렬
		Sorting_2D_Array();
		
		// 회의 진행한 횟수
		cnt = countCompletedMeetings(cnt);
		System.out.println(cnt);
	}
	private static int countCompletedMeetings(int count) {
		int endTime = 0;
		for (int i = 0; i < N; i++) {
			// 종료 시간이 다음 미팅 시작시간보다 빠르거나 같은 경우
			if (endTime <= meetingRoom[i][0]) {
				// 종료 시간 == 다음 미팅 시작시간 => cnt 증가
				endTime = meetingRoom[i][1];
				count++;
			}
		}
		return count;
	}
	private static void Sorting_2D_Array() {
		Arrays.sort(meetingRoom, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 끝나는 시간이 같으면 시작시간 기준으로 정렬
				if (o1[1] == o2[1]) return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
	}
	private static void setMeetingTime(BufferedReader br) throws IOException {
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		meetingRoom = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				meetingRoom[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
	}
}
