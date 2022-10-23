package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_23290_마법사상어와복제 {
	
	static int M, T, P1, P2, turn, eat;
	static int[][][] monsters;
	static int[][] dead;
	static boolean[][] moved;
	static int[] dr = {0,-1,-1,-1,0,1,1,1}; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dc = {-1,-1,0,1,1,1,0,-1};
	static int[] pdr = {-1,0,1,0}; // 상좌하우
	static int[] pdc = {0,-1,0,1};
	static int[] moving = new int[3]; // 팩맨 경로
	
	static Queue<Egg> eggs;
	static Queue<int[]> que;
	
	static class Egg{
		int r, c, dir, numbers;
		
		Egg(int r, int c, int dir, int numbers){
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.numbers = numbers;
		}
	}
	
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        
        monsters = new int[8][4][4];
        dead = new int[4][4];
        eggs = new ArrayDeque<>();
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int r = Integer.parseInt(st.nextToken())-1;
        	int c = Integer.parseInt(st.nextToken())-1;
        	int dir = Integer.parseInt(st.nextToken())-1;
        	
        	monsters[dir][r][c]++;
        }
        
        st = new StringTokenizer(br.readLine());
        P1 = Integer.parseInt(st.nextToken())-1;
        P2 = Integer.parseInt(st.nextToken())-1;
        
        turn=0;
        
        while(turn<T) {
        	copy();
        	monster_move();
        	packman_move();
        	dead();
        	copy_complete();
        	
        	turn++;
        }
        
        System.out.println(count());
        
    }
    
    static int count() {
    	int answer = 0;
        
        for(int k=0; k<8; k++) {
    		for(int i=0; i<4; i++) {
    			for(int j=0; j<4; j++) {
    				if(monsters[k][i][j]>0) {
    					answer += monsters[k][i][j];
    				}
    			}
    		}
    	}
        
        return answer;
    }
    
    static void copy_complete() {
		
    	while(!eggs.isEmpty()) {
    		Egg egg = eggs.poll();
    		
    		monsters[egg.dir][egg.r][egg.c] += egg.numbers;
    		
    	}
		
	}

	static void dead() {
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(dead[i][j]>0) dead[i][j]--;
			}
		}
		
	}

	static void packman_move() {
		
		eat = -1;
		moved = new boolean[4][4];
		dfs(0, P1, P2, 0, new int[3]);
		
		for(int i = 0; i<3; i++) {
			P1+=pdr[moving[i]];
			P2+=pdc[moving[i]];
			for(int k=0; k<8; k++) {
				if(monsters[k][P1][P2]>0) {
					dead[P1][P2] = 3;
					monsters[k][P1][P2] = 0;
				}
	    	}
		}
		
	}
	
	static void dfs(int depth, int r, int c, int many, int[] move) {
		
		if(depth>=3) {
			if(eat<many) {
				eat = many;
				moving[0] = move[0];
				moving[1] = move[1];
				moving[2] = move[2];
			}
			return;
		}
		
		for(int dir=0; dir<4; dir++) {
			int nr = r+pdr[dir];
			int nc = c+pdc[dir];
			if(nr <0 || nc <0 || nr>=4 || nc>=4) continue;
			int tmp = 0;
			
			if(!moved[nr][nc]) {
				for(int k=0; k<8; k++) {
					tmp += monsters[k][nr][nc];
				}
				moved[nr][nc] = true;
				move[depth] = dir;
				dfs(depth+1, nr, nc, many+tmp, move);
				moved[nr][nc] = false;
			}
			else {
				move[depth] = dir;
				dfs(depth+1, nr, nc, many+tmp, move);
			}
		}
	}

	static void monster_move() {
		
		monsters = new int[8][4][4];
		
		while(!que.isEmpty()) {
			int[] monster = que.poll();
			boolean check = false;
			
			for(int dir = monster[2]+8; dir>=monster[2]; dir--) {
				int nr = monster[0]+dr[dir%8];
				int nc = monster[1]+dc[dir%8];
				
				if(nr <0 || nc <0 || nr>=4 || nc>=4) continue;
				if( (P1==nr && P2==nc) || dead[nr][nc]>0 ) continue;
				check = true;
				monsters[dir%8][nr][nc] += monster[3];
				break;
			}
			
			if(check) continue;
			monsters[monster[2]][monster[0]][monster[1]] += monster[3];
			
		}
		
	}

	static void copy() {
		que = new ArrayDeque<>();
		
    	for(int k=0; k<8; k++) {
    		for(int i=0; i<4; i++) {
    			for(int j=0; j<4; j++) {
    				if(monsters[k][i][j]>0) {
    					eggs.add(new Egg(i, j, k, monsters[k][i][j]));
    					que.add(new int[] {i,j,k,monsters[k][i][j]}); // r, c, dir, 몇마리
    				}
    			}
    		}
    	}
    }
    
}