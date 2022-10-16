import java.io.*;
import java.util.*;

class Solution{
	static int T;
	static int[] fee; // 1d 1m 3m 1y
	static int[] plan;
	static int[] cost;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			fee = new int[4];
			plan = new int[12];
			cost = new int[12];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			/**
			 * 1. 1년
			 * 2. 각 달 1d or 1m 로 계산
			 * 3. 
			 * */
			int minCost = fee[3]; // 1year
			// cost[i] : dp
			cost[0] = Math.min(fee[0] * plan[0], fee[1]);
			cost[1] = Math.min(fee[0] * plan[1], fee[1]) + cost[0];
			cost[2] = Math.min(fee[0] * plan[2], fee[1]) + cost[1];
			cost[2] = Math.min(fee[2], cost[2]);
			for(int i=3; i<12; i++) {
				cost[i] = Math.min(fee[0] * plan[i], fee[1]) + cost[i-1];
				cost[i] = Math.min(cost[i], fee[2] + cost[i-3]);
			}
			minCost = Math.min(minCost, cost[11]);
			System.out.println("#" + t + " " + minCost);
		}
	}
}