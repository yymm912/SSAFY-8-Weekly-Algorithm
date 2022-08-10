package _2주차;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class PG_무지의먹방라이브 {
    static class Pii implements Comparable<Pii>{
        int a,b;
        public Pii(int a, int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Pii o) {
		    return this.a - o.a;
	    }
    }
    public static int solution(int[] food_times, long k) {
        int answer = 0;
        int n = food_times.length;
        List<Pii> food = new ArrayList<>();
        long sum = 0;
        for(int i = 0;i<n;i++){
            food.add(new Pii(food_times[i],i+1));
            sum += food_times[i];
        }
        if(sum <= k) return -1;
        Collections.sort(food);
        int i = 0;
        int prev = 0;
        long time = 0;
        for(;i<n;i++){
            long t = food.get(i).a - prev;
            t *= (n-i);
            if(time+t <= k){
                prev = food.get(i).a;
                time += t;
            }else break;
        }
    
        List<Integer> a = new ArrayList<>();
        for(;i<n;i++){
            a.add(food.get(i).b);
        }
        k -= time;
        Collections.sort(a);
        long idx = k%a.size();
        answer = a.get((int)idx);
        
    
        return answer;
    }
    public static void main(String[] args) throws Exception {
    	int [] food_times = {3,1,2};
    	System.out.println(solution(food_times , 5)); // 1
    	int [] food_times1 = {4, 2, 3, 6, 7, 1, 5, 8};
    	System.out.println(solution(food_times1 , 16));// 3
    	int [] food_times2 = {4, 2, 3, 6, 7, 1, 5, 8};
    	System.out.println(solution(food_times2 ,27)); // 5
    }
}