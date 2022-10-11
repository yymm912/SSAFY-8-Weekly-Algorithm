package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_17140_이차원배열과연산 {
	
	static int R,C,K, maxRow, maxCol;
	static List<ArrayList<Integer>> list;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken()); 
		
		list = new ArrayList<ArrayList<Integer>>();
		list.add(new ArrayList<>());
		
		for(int i=1;i<=3;i++) {
			list.add(new ArrayList<>());
			list.get(i).add(0);
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) list.get(i).add(Integer.parseInt(st.nextToken()));
		}

		int answer = 0;
		maxRow = maxCol = 3;
		
		while(true) {
			if(list.size()>R && list.get(R).size()>C && list.get(R).get(C)==K) break;
			if(answer==100) {
				answer = -1;
				break;
			}
			sort();
			answer++;
		}
		
		System.out.println(answer);
		
		
		
	}
	
	static void sort() {
		
		PriorityQueue<int[]> pque = new PriorityQueue<>((e1,e2)->{
			if(e1[1]==e2[1]) return e1[0]-e2[0];
			return e1[1]-e2[1];
		});
		
		List<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
		
		
		if(maxRow>=maxCol) {
			maxCol = 0;
			
			for(int i=1;i<=maxRow;i++) {
				if(i>100) break;
				int[] pcs = new int[101];
				
				for(int j=1; j<list.get(i).size(); j++) {
					if(j>100) break;
					pcs[list.get(i).get(j)]++;
				}
				
				for(int j=1; j<=100; j++) {
					if(pcs[j]>0) {
						pque.add(new int[] {j,pcs[j]});
					}
				}
				
				maxCol = Math.max(maxCol, pque.size()*2);
				
				list.get(i).clear();
				list.get(i).add(0);
				
				while(!pque.isEmpty()) {
					int[] rc = pque.poll();
					
					list.get(i).add(rc[0]);
					list.get(i).add(rc[1]);
				}
			}
			
			for(int i=1;i<=maxRow;i++) {
				for(int j=list.get(i).size(); j<=maxCol; j++) {
					list.get(i).add(0);
				}
			}
			
			
			
		}else {
			maxRow = 0;
			int tmpRow = list.get(1).size();
			tmp.add(new ArrayList<>());
			
			for(int i=1;i<tmpRow;i++) {
				if(i>100) break;
				int[] pcs = new int[101];
				
				for(int j=1; j<list.size(); j++) {
					if(j>100) break;
					pcs[list.get(j).get(i)]++;
				}
				
				for(int j=1; j<=100; j++) {
					if(pcs[j]>0) {
						pque.add(new int[] {j,pcs[j]});
					}
				}
				
				maxRow = Math.max(maxRow, pque.size()*2);
				
				tmp.add(new ArrayList<>());
				tmp.get(i).add(0);
				
				while(!pque.isEmpty()) {
					int[] rc = pque.poll();
					
					tmp.get(i).add(rc[0]);
					tmp.get(i).add(rc[1]);
				}
			}
			
			for(int i=1;i<tmp.size();i++) {
				for(int j=tmp.get(i).size(); j<=maxRow; j++) {
					tmp.get(i).add(0);
				}
			}
			
			list.clear();
			list.add(new ArrayList<>());
			
			for(int i=1; i<=maxRow; i++) {
				list.add(new ArrayList<>());
				list.get(i).add(0);
			}
			
			for(int i=1; i<tmpRow; i++) {
				for(int j=1; j<=maxRow; j++) {
					list.get(j).add(tmp.get(i).get(j));
				}
			}
		}
		
	}
	
}