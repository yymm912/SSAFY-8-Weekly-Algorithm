package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2477 {

	static int T,N,M,K,A,B;
	static int[] a,b;
	static int[] stateA, stateB;

	static Customer[] customer;
	static PriorityQueue<Customer> pq1, pq2;

	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken());
			
			a=new int[N+1];
			stateA=new int[N+1];
			b=new int[M+1];
			stateB=new int[M+1];
			customer=new Customer [K+1];
			
			// 접수 창구에서 기다리는 우선순위 큐
			pq1=new PriorityQueue<>( (Customer c1,  Customer c2) -> c1.no-c2.no);
			
			// 정비 차구에서 기다리는 우선순위 큐
			pq2=new PriorityQueue<> ( (Customer c3, Customer c4) -> c3.queueTime==c4.queueTime ?
					c3.receptionNo-c4.receptionNo : c3.queueTime-c4.queueTime);
			
			st=new StringTokenizer (br.readLine());
			for (int i=1; i<=N; i++)
				a[i]=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer (br.readLine());
			for (int i=1; i<=M; i++)
				b[i]=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer (br.readLine());
			for (int i=1; i<=K; i++) {
				int t=Integer.parseInt(st.nextToken());
				customer[i]=new Customer (i, t);
				pq1.offer(customer[i]);
			}
			
			simulation ();
			sb.append("#").append(tc).append(" ").append(findNo()).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void simulation () {
		int Time=0;
		int cnt=0;
		while (cnt!=K) {
			
			
			// #1. reception에 있는 사람들 중,
			// pq2로 갈 사람이 있는지 확인
			
			for (int i=1; i<=N; i++) {
				if (stateA[i]==0) continue;
				int n=stateA[i];
				
				if (Time-customer[n].enterTime==a[i]) {
					stateA[i]=0;
					customer[n].queueTime=Time;
					pq2.offer(customer[n]);		
				}
			}


			
			// #2. repair에 있는 사람들 중,
			// 완료된 사람이 있는지 확인
			for (int i=1; i<=M; i++) {
				if (stateB[i]==0) continue;
				int n=stateB[i];
				
				if (Time-customer[n].enterTime==b[i]) {
					stateB[i]=0;
					cnt++;
				}
			}


			
			// #3. 접수 창구에서 기다리고 있는 사람들 중, 
			// 우선 순위가 높은 애를 선택해 비어있는 칸에 넣어준다
			// (pq1에 있는 사람들)
			
			for (int i=1; i<=N; i++) {
				if (stateA[i]!=0 ) continue;
				
				if (!pq1.isEmpty()) {
					Customer c=pq1.peek();
					
					if (c.arriveTime<=Time) {
						pq1.poll();
						stateA[i]=c.no;
						c.enterTime=Time;
						c.receptionNo=i;
					}
				}
			}


			// #4. 접수 창구를 지나 정비 창구로 들어가기 위해 기다리고 있는 사람들 중
			// 우선 순위가 높은 애를 선택해 비어있는 칸에 넣어줌
			// (pq2 에 있는 사람들)
			
			for (int i=1; i<=M; i++) {
				if (stateB[i]!=0) continue;
				
				if (!pq2.isEmpty()) {
					Customer c=pq2.poll();
					stateB[i]=c.no;
					c.enterTime=Time;
					c.repairNo=i;
				}
			}
			

			Time++;
		}
	}
	
	static int findNo () {
		int cnt=0;
		for (int i=1; i<=K; i++) {
			if (customer[i].receptionNo==A && customer[i].repairNo==B)
				cnt+=i;
		}
		if (cnt==0) return -1;
		return cnt;
	}
	
	static class Customer{
		int no;
		int arriveTime, enterTime, queueTime;
		int receptionNo, repairNo;
	
		Customer (int no, int arriveTime) {
			this.no=no;
			this.arriveTime=arriveTime;
		}
	}
	
	static void print (int Time) {
		System.out.println("Time : "+Time);
		System.out.println("A: "+Arrays.toString(stateA));
		System.out.println("B: "+Arrays.toString(stateB));
		System.out.println();
	}
}
