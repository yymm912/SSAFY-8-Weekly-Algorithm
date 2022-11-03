package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 마감기한이 긴 것부터 탐색
// 6일자에 할 수 있는 과제 5 -> 5 선택
// 5일자에 할 수 있는 과제 x
// 4일자에 할 수 있는 과제 10, 40, 60 -> 60 선택
// 3일자에 할 수 있는 과제 10, 30, 40 -> 40 선택
// 2일자에 할 수 있는 과제 10, 20, 30, 50 -> 50 선택
// 1일자에 할 수 있는 과제 10, 20, 30 -> 30 선택

public class BJ13904_과제 {

	static int N, res;
	static List<HW> hw;     // 모든 과제들을 담을 리스트
	static List<Integer> able;   // 마감일 별 할 수 있는 과제들을 담을 리스트
	
	static class HW {
		int d, s;
		HW (int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		res = 0;
		N = Integer.parseInt(br.readLine());
		hw = new ArrayList<>();
		able = new ArrayList<>();
		
		int day = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			hw.add(new HW(d, s));
			day = Math.max(day, d);  // 제일 긴 마감일 구하기
		}
		
		while(day > 0) {
			// 마감일이 day인 과제의 점수 가져오기
			int size = hw.size();
			for (int i = size-1; i >= 0; i--) {
				if(hw.get(i).d == day) {
					able.add(hw.get(i).s);
					hw.remove(i);
				}
			}
			
			// day일에 할 수 있는 과제 중 가장 점수가 높은 것 선택
			int high = 0;
			int idx = -1;
			size = able.size();
			for (int i = size-1; i >= 0; i--) {
				if(high < able.get(i)) {
					high = Math.max(high, able.get(i));
					idx = i;
				}
			}
			
			if(idx != -1) {
				res += high;
				able.remove(idx);
			}
			
			day--; // 하루 끝
		}
		
		System.out.println(res);
	}

}
