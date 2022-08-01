import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int N; // 스위치 개수
	static int[] state; // 스위치 상태
	static int studN; // 학생 수
	static int[] studGender; // 성별
	static int[] studSwitch; // 각 학생들이 받은 스위치 번호
	public static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		state = new int[N+1]; // 스위치 1번부터 시작하므로 N+1 할당
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			state[i] = Integer.parseInt(st.nextToken());
		}
		studN = Integer.parseInt(br.readLine());
		studGender = new int[studN];
		studSwitch = new int[studN];		
		for(int i=0; i<studN; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			studGender[i] = Integer.parseInt(st.nextToken());
			studSwitch[i] = Integer.parseInt(st.nextToken());
		}
	}
	/**남학생 스위치 변경 함수*/
	public static void boy(int idx) {
		int curSwit = studSwitch[idx];
		int nxtSwit = curSwit;
		while(nxtSwit <= N) {
			state[nxtSwit] = state[nxtSwit] == 1 ? 0 : 1;
			nxtSwit += curSwit;
		}
	}
	/**여학생 스위치 변경 함수*/
	public static void girl(int idx) {
		int swit = studSwitch[idx];
		state[swit] = state[swit] == 1 ? 0 : 1;
		int i = 1; // 인덱스 i 늘려가면서 대칭 좌우 비교해줌.
		while(true) {
			if(swit-i < 1 || swit+i > N) break; // 스위치 번호 1부터 시작하므로, 범위 체크 주의!
			if(state[swit-i] != state[swit+i]) break; // 대칭이 아니면 stop
			state[swit-i] = state[swit-i] == 1 ? 0 : 1; // 대칭인 스위치는 바꿔켜기
			state[swit+i] = state[swit+i] == 1 ? 0 : 1;
			i++;
		}
	}
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		init(); // 입력받음
		
		for(int i=0; i<studN; i++) {
			if(studGender[i] == 1) boy(i); // 성별 남자인 경우
			else girl(i); // 성별 여자인 경우
		}
		
		for(int i=1; i<=N; i++) {
			System.out.print(state[i] + " ");
			if(i % 20 == 0) System.out.println(); // 20개씩 한줄 출력 주의!
		}
	}
}