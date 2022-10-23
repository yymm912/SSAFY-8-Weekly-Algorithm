import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	
	static void play(int turn, int cnt) {
		if(turn==10) {
			answer=Math.max(answer, cnt);
			return;
		}
		if(mals[0]==32 && mals[1]==32 && mals[2]==32 && mals[3]==32) { //모두도착
			answer=Math.max(answer, cnt);
			return;
		}
		boolean[] used = new boolean[32]; //같은 위치 말 사용안도록
		for(int i=0; i<4; i++) {
			int cmal=mals[i]; //현재말 위치
			if (cmal == 32 || used[cmal]) continue;
			int nmal=go(cmal,dices[turn]); //옮긴 말 위치
			if(nmal!=-1) { 
				used[cmal]=true;
				mals[i]=nmal;
				visited[nmal]=1;
				play(turn+1, cnt+score[nmal]);
				mals[i]=cmal;
			}
		}
			
	}
	
	static int go(int now, int d) { //now위치 말 d만큼이동
		if(now==5 || now==10 || now==15) {
			now=move[now].get(1);
			d--;
		}
		for(int i=0; i<d; i++) {
			now=move[now].get(0);
			if(now==32) return 32;
		}
		for(int i=0; i<4; i++) {
			if(now==mals[i]) return -1;
		}
		return now;
	}
	
	static List<Integer>[] move;
	static int[] dices;
	static int[] score;
	static int[] mals;
	static int answer;
	static int[] visited=new int[41];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stn;
		move=new ArrayList[33];
		score=new int[33];
		for(int i=0; i<32; i++) {
			move[i]=new ArrayList<>();
			
		}
		for(int i=0; i<=19; i++) {
			move[i].add(i+1);
			score[i]=i*2;
		}
		move[5].add(21); move[21].add(22); move[22].add(23); move[23].add(29);
		move[10].add(24); move[24].add(25); move[25].add(29);
		move[15].add(26); move[26].add(27); move[27].add(28); move[28].add(29);
		move[29].add(30); move[30].add(31);move[31].add(20); move[20].add(32);
		score[20]=40;
		score[21]=13;score[22]=16;score[23]=19;
		score[24]=22;score[25]=24;
		score[26]=28;score[27]=27;score[28]=26;
		score[29]=25;
		score[30]=30;score[31]=35;score[32]=0;
		
		 
		
		
		dices=new int[10];
		stn=new StringTokenizer(br.readLine());
		for(int i=0; i<10; i++) {
			dices[i]=Integer.parseInt(stn.nextToken());
		}
		mals=new int[4];
		answer=0;
		play(0,0);
		System.out.println(answer);
		
		
	}
}
