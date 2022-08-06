package _2주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class TC_모험가길드 {
	static int [] p;
	static int N;
	static int [] temp;
	static List<Integer> s;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		temp = new int[N];
		s = new Stack<>();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0;i<N;i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(p,0,N-1);
		int m = -1;
		int answer = 0;
		for(int i = 0;i<N;i++) {
			if(s.isEmpty()) {
				m = p[i];
			}
			s.add(p[i]);
			if(s.size()==m) {
				m = -1;
				answer++;
				s.clear();
			}
			
		}
		System.out.println(answer);
		

	}
	static void swap(int [] arr,int a,int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	static void mergeSort(int [] arr,int st,int ed){
		if(ed-st <=1) {
			if(arr[st] < arr[ed]) swap(arr,st,ed);
			return;
		}
		
		
		int mid = (st+ed)/2;
		
		mergeSort(arr,st,mid);
		mergeSort(arr,mid+1,ed);
		
		int l = st, r = mid+1;
		int t = st;
		
		while(l<=mid && r<=ed) {
			if(arr[l] < arr[r]) temp[t++] = arr[r++];
			else temp[t++] = arr[l++];
		}
		
		while(l<=mid) temp[t++] = arr[l++];
		while(r<=ed) temp[t++] = arr[r++];
		
		for(int i = st; i<=ed;i++)
			arr[i] = temp[i];
		
	}

}
