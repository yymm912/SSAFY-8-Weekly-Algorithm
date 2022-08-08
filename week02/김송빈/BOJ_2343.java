package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[] lectures=new int[n];//강의 수
		
		
		int right=0,left=0;
		StringTokenizer st1=new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			lectures[i]=Integer.parseInt(st1.nextToken());
			right+=lectures[i];//모두 다 합친 강의 길이가 블루레이 최대 길이->블루레이 1개 일 때
			left=Math.max(left, lectures[i]);//가장 긴 강의길이가 블루레이 최소 길이
		}
		
		int middle=0;
		while(left <= right) {//이분탐색
            int mid = (left + right)/2;//중간 값
            int sum = 0;//강의 길이
            int cnt = 0;//블루레이 수
            for(int i=0; i<n; i++) {
                if(sum + lectures[i] > mid) {//블루레이 길이에 강의 하나 더 더했는데 중간 값보다 커
                    sum = 0;//블루레이 길이 초기화
                    cnt++;//블루레이 하나 더
                }
                sum += lectures[i];//블루레이 길이가 중간 값보다 작거나 같으면 일단 강의 한 편 더 더해봐 or 크면 마지막 강의 길이가 블루레이 길이
            }
            if(sum != 0) cnt++;//남의 강의들로 한 개 블루레이
            if(cnt <= m) right = mid-1;//블루레이 수가 같거나 많으면 최대 길이 줄임
            else if(cnt>m) left = mid+1;//블루레이 수가 많으면 최소 길이 늘림
        }
        System.out.println(left);
		
	}

}
