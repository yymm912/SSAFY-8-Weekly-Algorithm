package forStudy.week02;

import java.util.*;

public class TC_무지의먹방라이브 {
	class Food implements Comparable<Food> {
	    int time;
	    int idx;
	    
	    public Food(int time, int idx) {
	        this.time = time;
	        this.idx = idx;
	    }
	    
	    @Override
	    public String toString(){
	        return "[" + time + "," + idx + "]";
	    }
	    
	    @Override
	    public int compareTo(Food food) {
	        return Integer.compare(this.time, food.time);
	    }
	}


	class Solution {
	    public int solution(int[] food_times, long k) {
	        long sum = 0;
	        int len = food_times.length;
	        for(int i=0; i<len; i++) {
	            sum += food_times[i];
	        }
	        
	        if(sum <= k) return -1;
	        
	        PriorityQueue<Food> pq = new PriorityQueue<>();
	        for(int i=0; i<food_times.length; i++) {
	            pq.offer(new Food(food_times[i], i+1));
	        }
	        
	        long total = 0;
	        long previous = 0;
	        long length = food_times.length;
	        
	        while(total + ((pq.peek().time - previous) * length) <= k) {
	            int now = pq.poll().time;
	            total += (now - previous) * length;
	            length -= 1;
	            previous = now;
	        }
	              
	        ArrayList<Food> result = new ArrayList<>();
	        while(!pq.isEmpty()) {
	            result.add(pq.poll());
	        }
	              
	        Collections.sort(result, new Comparator<Food>() {
	            @Override
	            public int compare(Food a, Food b) {
	                return Integer.compare(a.idx, b.idx);
	            }
	        });
	        
	        return result.get((int)((k-total) % length)).idx;
	    }
	}

}
