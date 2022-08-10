import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
	int startTime;
	int endTime;
	
	Meeting(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	@Override
	public int compareTo(Meeting m) {
		if (this.endTime == m.endTime) {
			return this.startTime - m.startTime;
		} 
		
		return this.endTime - m.endTime;
	}
}

public class Main {
	
	static int N, startTime, endTime, ans;
	static List<Meeting> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			startTime = Integer.parseInt(st.nextToken());
			endTime = Integer.parseInt(st.nextToken());
			list.add(new Meeting(startTime, endTime));
		}
		
		Collections.sort(list);
		
		startTime = 0;
		endTime = 0;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).startTime >= endTime) {
				ans += 1;
				endTime = list.get(i).endTime;
			}
		}
		
		System.out.println(ans);
	}
}
