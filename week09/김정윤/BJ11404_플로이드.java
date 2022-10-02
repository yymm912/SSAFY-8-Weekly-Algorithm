package week9.김정윤;

import java.io.*;
import java.util.*;

public class BJ11404_플로이드 {
	static int N, M, INF = 999999999;
	static int[][] city;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		city = new int[N+1][N+1]; // 0 dummy
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) city[i][j] = 0;
				else city[i][j] = INF;
			}
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if (city[start][end] > cost)
				city[start][end] = cost;
		}
		
		for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(int k = 1; k <= N; k++) {
                    if(city[j][k] > city[j][i] + city[i][k]) {
                    	city[j][k] = city[j][i] + city[i][k];
                    	
                    }
                }
            }
        }

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (city[i][j] == INF) System.out.print(0 + " ");
				else System.out.print(city[i][j] + " ");
			}
			System.out.println();
		}
	}

}
