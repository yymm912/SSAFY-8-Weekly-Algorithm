import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n]; // 배열
        int[] answer = new int[n]; // 오큰수 정답을 넣을 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int temp =0;
        for(int i=n-1; i>=0; i--){ // 뒤에서 부터 검색
            while(!stack.isEmpty()){ // 만일 스택에 값이 있다면 비교를 시작함
                temp = stack.pop(); // 스택에서 마지막에 들어온 값 하나를 pop함
                if(arr[i]<temp){ // arr[i] 와 비교하여 더 크다면
                    stack.push(temp); // 먼저 pop했던 값을 스택에 넣고
                    stack.push(arr[i]); // 마지막으로 방금 비교한 값을 넣음
                    answer[i] = temp; // 결과에는 temp값을 넣어줌
                    break;
                }
            }
            if(stack.isEmpty()){ // 만약 스택이 다 빌때까지 꺼냈지만 더 큰 수가 없었다면?
                stack.push(arr[i]); // arr[i]를 스택에 넣어주고
                answer[i] = -1; // answer에는 -1을 넣어줌
                continue;
            }

        }

        for(int i=0; i<n; i++){
            sb.append(answer[i]+" ");
        }

        System.out.print(sb);
    }
}