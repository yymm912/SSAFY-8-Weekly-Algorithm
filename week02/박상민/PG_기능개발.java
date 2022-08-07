import java.util.*;

public class PG_기능개발{
    public static void main(String[] args){
        System.out.println(Arrays.toString(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5})));
        System.out.println(Arrays.toString(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1})));
    }

    static int[] solution(int[] progresses, int[] speeds) {
        int[] days = new int[progresses.length];

        for (int i = 0; i < progresses.length; i++){
            int day = 0;
            while(true){
                if (progresses[i] + (speeds[i] * day) >= 100) break;
                day++;
            }
            days[i] = day;
        }    
        
        List<Integer> answer = new ArrayList<>();   

        int temp = 0;
        int count = 0;
        for (int i = 0; i < days.length; i++){
            if (temp >= days[i]) count++;
            else {
                if (i > 0) answer.add(count);
                temp = days[i];
                count = 1;
            }
        }
        answer.add(count);

        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) ans[i] = answer.get(i);

        return ans;
    }
}