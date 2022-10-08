package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class SW_차량정비소 {
	
	static int N,M,K,A,B, time, answer;
	static boolean end_check = true; 
	static int[] a, b, t;
	static List<Reception> recept, repair;
	static List<Integer> list; // 정답이 될 수도 있는 애들
	static Queue<Integer> repair_wating; // 정답이 될 수도 있는 애들
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			recept = new ArrayList<>();
			repair = new ArrayList<>();
			list = new ArrayList<>();
			repair_wating = new ArrayDeque<>();
			
			time = 0;
			answer = 0;
			
			for(int i=0; i<N; i++) recept.add( new Reception());
			for(int i=0; i<M; i++) repair.add( new Reception());
			
			a = new int[N];
			b = new int[M];
			t = new int[K];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) b[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) t[i] = Integer.parseInt(st.nextToken());
			
			int idx = 0; 
			
			while(true) {
				
				for(int i=0; i<M; i++) { // 정비완료 확인
					if ( repair.get(i).c_idx != 0 && repair.get(i).time - time<=0) {
						repair.get(i).c_idx = 0;
					}
				}
				
				for(int i=0; i<N; i++) { // 접수 업무 완료 확인
					if ( recept.get(i).c_idx != 0 && recept.get(i).time - time<=0) {
						if(i==A-1) list.add(recept.get(i).c_idx);
						repair_wating.add(recept.get(i).c_idx);
						recept.get(i).c_idx = 0;
					}
				}
				
				while( !repair_wating.isEmpty()) { // 정비에 넣어줌
					if (repair()) break;
				}
				
				
				
				while(idx<K && t[idx]-time<=0) {
					if(reception(idx)) break;
					idx++;
				}
				
				time++;
				
				if(idx>=K) {
					boolean a_check = true;
					boolean b_check = true;
					
					for(int i=0; i<N; i++) {
						if(recept.get(i).c_idx != 0) a_check=false;
					}
					
					if(a_check) {
						for(int i=0; i<M; i++) {
							if(repair.get(i).c_idx != 0) b_check=false;
						}
					}
					
					if(a_check && b_check && repair_wating.isEmpty()) break;
					
				}
				
			}
			if(answer==0) answer = -1;
			sb.append("#"+tc+" "+answer+"\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	static boolean reception(int customer_idx) { // 접수 창구
		
		boolean check = true;
		
		for(int i=0; i<N; i++) {
			if (recept.get(i).c_idx==0) {
				recept.get(i).c_idx = customer_idx+1;
				recept.get(i).time = time+a[i];
				check = false;
				break;
			}
		}
		
		return check;
	}
	
	static boolean repair() { // 정비 창구 접수
		
		boolean check = true;
		
		int customer_idx = repair_wating.peek();
		
		for(int i=0; i<M; i++) {
			if (repair.get(i).c_idx==0) {
				
				repair_wating.poll();
				
				if(i==B-1) { // 후보군에서 찾기
					for(int j=0; j<list.size(); j++) {
						if(list.get(j)==customer_idx) {
							answer += customer_idx;
							list.remove(j);
						}
					}
				}
				else {
					for(int j=0; j<list.size(); j++) {
						if(list.get(j)==customer_idx) list.remove(j);
					}
				}
				
				repair.get(i).c_idx = customer_idx;
				repair.get(i).time = time+b[i];
				check = false;
				break;
			}
		}
		
		return check;
	}
	
	static class Reception{
		int c_idx, time;
		Reception(){
			this.c_idx=0;
			this.time=0;
		}
	}
	
	
}