package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ3967 {
	
	static int size;
	static boolean done;
	
	static char[][] map=new char[5][];
	static boolean[] selected=new boolean[13];
	static List<Pos> pos=new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));

		for (int i=0; i<5; i++) {
			String line=br.readLine();
			map[i]=line.toCharArray();
			
			for (int j=0; j<9; j++) {
				if (map[i][j]=='x')
					pos.add(new Pos (i,j));
				if (map[i][j]>='A' && map[i][j]<='L')
					selected[map[i][j]-'A'+1]=true;
			}
		}
		size=pos.size();
		dfs (0);
		
	}
	
	static void dfs (int tgtIdx) {
		if (tgtIdx==size) {
			if (check ()) {
				done=true;
			}
			return ;
		}
		
		int y=pos.get(tgtIdx).y;
		int x=pos.get(tgtIdx).x;
		
		
		// 1- 'A', 2-'B' ...
		for (int i=1; i<=12; i++) {
			if (selected[i]) continue;
			
			selected[i]=true;
			map[y][x]=(char) ('A'+i-1);
			
			dfs (tgtIdx+1);
			if (done) return;
			
			selected[i]=false;
			map[y][x]='x';
		}
	}
	
	static boolean check () {
//		for (int i=0; i<5; i++) {
//			for (int j=0; j<9; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		int y=0, x=4;
		int sum=0;
		for (int i=0; i<4; i++) {
			sum+=(map[y++][x--]-'A'+1);
			
		}
		if (sum!=26) return false;
		
		y=3; x=1; sum=0;
		for (int i=0; i<4; i++) {
			sum+=(map[y][x]-'A'+1);
			x+=2;
		}
		if (sum!=26) return false;
		
		y=3; x=7; sum=0;
		for (int i=0; i<4; i++) {
			sum+=(map[y--][x--]-'A'+1);
		}
		if (sum!=26) return false;
		
		y=1; x=1; sum=0;
		for (int i=0; i<4; i++) {
			sum+=(map[y++][x++]-'A'+1);
		}
		if (sum!=26) return false;
		
		y=4; x=4; sum=0;
		for (int i=0; i<4; i++) {
			sum+=(map[y--][x++]-'A'+1);
		}
		if (sum!=26) return false;
		
		y=1; x=7; sum=0;
		for (int i=0; i<4; i++) {
			sum+=(map[y][x]-'A'+1);
			x-=2;
		}
		if (sum!=26) return false;
		
		
		for (int i=0; i<5; i++) {
			for (int j=0; j<9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		return true;
	}
	
	static class Pos {
		int y, x;
		Pos (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
}
