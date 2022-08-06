import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class PG_무지의먹방라이브 {

	public static int solution(int[] food_times, long k) {
		int result = 0;
		long total = 0;
		
		PriorityQueue<Tuple> pq = new PriorityQueue<>();
		for (int i = 0; i < food_times.length; i++) {
			total += food_times[i];
			pq.add(new Tuple(i + 1, food_times[i]));
		}
		
		// 음식을 다 먹고도 시간이 남는 상황
		if (k >= total) return -1;
		
		int prev = 0;
		
		// while 문을 빠져 나간다 --> 최종 메뉴들만 남았다. (더이상 줄어들지 않음)
		while (true) {
			long t = pq.peek().getTime() - prev;	// 현재 가장 짧은 시간의 음식 (이전에 빠진 음식의 시간을 빼줘야 함) 
			int l = pq.size();						// 음식의 종류
			long tmp = t * l;
			
			if (k >= tmp) {
				k -= tmp;
				prev += t;
				pq.poll();
			} else {
				break;
			}
		}
		
		// 남은 음식들을 담을 List 생성 --> 번호순으로 정렬하기 위해
		List<Tuple> tl = new ArrayList<>();
		
		// pq 에 남은 음식들을 tl 에 전부 넣는다.
		while(!pq.isEmpty()) {
			tl.add(pq.poll());
		}
		
		// 번호순으로 정렬
		Collections.sort(tl, (o1, o2) -> o1.getNumber() - o2.getNumber());
		
		int idx = (int)(k % tl.size());
		
		result = tl.get(idx).getNumber();
		
		return result;
	}

}

class Tuple implements Comparable<Tuple> {
	private int number;
	private int time;
	
	public Tuple() {}
	
	public Tuple(int n, int t) {
		this.number = n;
		this.time = t;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public int compareTo(Tuple o) {
		return this.time - o.time;
	}

}
