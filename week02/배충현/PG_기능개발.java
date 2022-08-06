import java.util.ArrayList;
import java.util.List;

class PG_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        int l = progresses.length;
        
        // 남은 작업일 배열 ( 남은 진행도/속도 --> 반올림 )
        int[] ends = new int[l];
        for(int i = 0; i < l; i++) {
            ends[i] = (int) Math.ceil(1.0 * (100 - progresses[i]) / speeds[i]);
        }
        
        int tmp = ends[0];
        int cnt = 0;
        List<Integer> al = new ArrayList<>();
        
        // 남은 작업일이 나보다 클 경우 --> 이전까지는 같이 배포된다. 
        for (int i = 0; i < ends.length; i++) {
			if (ends[i] > tmp) {
				al.add(cnt);
				tmp = ends[i];
				cnt = 1;
			} else {
				cnt++;
			}
		}
        
        // 마지막 작업 배포
        al.add(cnt);
        
        // List 를 배열로 변환
        int[] answer = al.stream().mapToInt(i -> i).toArray();
        
        return answer;
    }
}