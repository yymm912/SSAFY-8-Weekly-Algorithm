import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		// 가장 높은 기둥을 찾고 그 기둥까지의 거리와 높이를 곱해준다
		// 가장 높은 기둥을 기준으로 좌 우로 탐색
		// 왼쪽 탐색 기준으로 현재 높이가 이전보다 작으면 스택에 넣어주고 아니면 pop하여 전 배열값에 현재값 더함

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] arr = new int[1001];
		
		int start = Integer.MAX_VALUE;
		int end = 0;
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			arr[l] = h;
			start = Math.min(l, start);
			end = Math.max(l,  end);
		}
		
		Stack<Integer> s = new Stack<>();
		
		// left
		int tmp = arr[start];
		for(int i = start + 1; i<=end; i++) {
			if(arr[i] < tmp) s.push(i);
			else {
				while(!s.empty()) {
					int x = s.pop();
					arr[x] = tmp;
				}
				tmp = arr[i];
			}
		}
		s.clear();
		
		// right
		tmp = arr[end];
		for(int i=end-1; i>=start; i--) {
			if(arr[i] < tmp) s.push(i);
			else {
				while(!s.empty()) {
					int x = s.pop();
					arr[x] = tmp;
				}
				tmp = arr[i];
			}
		}
		
		int res = 0;
		for(int i=start; i<=end; i++) {
			res += arr[i];
		}
		System.out.println(res);
	}

}
