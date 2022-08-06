import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PM_기능개발 {
    public static void main(String[] args) {
        int[] prog = {95,90,99,99,80,99};
        int[] speed = {1,1,1,1,1,1};
        System.out.println(Arrays.toString(solution(prog,speed)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {

        int tmp[] = new int[speeds.length];

        for(int i=0; i<progresses.length; i++){
            //100에서 현재 진행률을 빼고 스피드로 나누면 몇일걸리는지 값이 나옴
            tmp[i] = (100-progresses[i])/speeds[i];
           //만약 나머지가 생긴다면 하루가 더 소요됨 그렇기 때문에 나머지가 있는 값은 +1 해주기
            if((100-progresses[i])%speeds[i] != 0){
                tmp[i]++;
            }
        }
        for(int i=1; i<tmp.length; i++){
            //앞에 있는 값이 뒤에 있는 값보다 크다면 앞에 있는 값으로 변경시킴
            if(tmp[i-1] > tmp[i]){
                tmp[i] = tmp[i-1];
            }
        }
        //공간값을 정할 수 없기 때문에 리스트 생성
        List<Integer> list = new ArrayList<>();
        //현재 맨앞값을 비교값으로 두고 횟수를 1로 설정
        int arrTmp = tmp[0];
        int count = 1;

        for(int i=1; i<tmp.length; i++){
            //만일 처음값과 같다면 횟수증가
            if(tmp[i] == arrTmp){
                count++;
            }
            //달라진다면 지금까지 횟수를 리스트에 넣고
            //카운트를 1로 초기화시킴
            //비교값은 현재 값으로 변경
            else{
                list.add(count);
                count = 1;
                arrTmp = tmp[i];
            }
        }
        //마지막 값은 넣어지지 않았기에 마지막 카운트도 저장
        list.add(count);

        //리턴타입이 정수배열이라 변환함
        int[] result = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i++) {
            result[i] = list.get(i).intValue();
        }
        return result;
    }
}
