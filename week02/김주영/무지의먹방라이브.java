package greedy;

import java.util.*;

class Food implements Comparable<Food> {
	int time;
	int idx;

	Food(int time, int idx) {
		this.time = time;
		this.idx = idx;
	}

	public int compareTo(Food food) {
		return this.time - food.time;
	}
}

public class 무지의먹방라이브 {
	public int solution(int[] food_times, long k) {

		long sum = 0;
		for (int i = 0; i < food_times.length; i++)
			sum += food_times[i];

		if (sum <= k)
			return -1;

		PriorityQueue<Food> pq = new PriorityQueue<>();
		for (int i = 0; i < food_times.length; i++)
			pq.offer(new Food(food_times[i], i + 1));

		long remain_food = food_times.length; // 남은 음식
		long prev = 0; // 직전 음식을 다 먹은 시간
		sum = 0; // 지금까지 먹기 위해 사용한 시간

		while (sum + (pq.peek().time - prev) * remain_food <= k) {
			int now = pq.poll().time;
			sum += (now - prev) * remain_food;
			remain_food -= 1;
			prev = now;
		}

		ArrayList<Food> list = new ArrayList<>();
		while (!pq.isEmpty())
			list.add(pq.poll());

		// Collections.sort (list, (i1, i2) -> i1.idx-i2-idx);
		list.sort((i1, i2) -> i1.idx - i2.idx);

		return list.get((int) ((k - sum) % remain_food)).idx;
	}
}
