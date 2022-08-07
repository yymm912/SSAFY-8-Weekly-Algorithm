package pg;

import java.util.Arrays;

public class PG_무지의먹방라이브 {
	public int solution(int[] food_times, long k) {
		int answer = -1;
		int food_len = food_times.length;
		Food[] food = new Food[food_len + 1];
		food[0] = new Food();
		for (int j = 1; j <= food_len; j++) {
			food[j] = new Food();
			food[j].time = food_times[j - 1];
			food[j].idx = j;
		}
		// 정렬 - 먹어야 하는 시간과 음식 순서를 같이 묶어서 정렬
		Arrays.sort(food, (o1, o2) -> o1.time - o2.time);

		int i = 1;
		while (i <= food_times.length) {

			// food_time 원소가 100,000,000 까지 들어올 수 있으므로 long 형변환 -> 효율성 문제
			// ( 현재 먹는 시간 - 이전 먹는 시간 ) * 현재 남아있는 음식 배열 길이 
			long eat = ((long) food[i].time - (long) food[i - 1].time) * (long) food_len;

			// k가 0보다 크면 eat만큼 k 횟수 차감  
			if (k - eat >= 0) {
				k = k - eat;
			} else {
				// k가 0보다 크면 남아있는 음식 인덱스에 대해 (i~food_len) 오름차순 정렬
				// k를 food_len으로 나눈 나머지에 대해 해당 idxArr[k]가 다시 섭취하게 되는 음식의 번호임 
				k %= (long) food_len;
				
				// 남아있는 인덱스만 오름차순 정렬 
				int idxArr[] = new int[food_len];
				for (int j = 0; j < food_len; j++) {
					idxArr[j] = food[i + j].idx;
				}
				Arrays.sort(idxArr); // 오름차순 정렬
				
				return idxArr[(int) k];
			}

			i++;
			food_len--;
		}

		return -1;
	}

	static class Food {
		int time;
		int idx;

		public Food() { }
		
		public Food(int time, int idx) {
			this.time = time;
			this.idx = idx;
		}

	}

}
