package sw;
import java.util.*;
import java.io.*;
 
class SWEA1767프로세서연결하기4
{
 
     
    static int T, N;
	static int dx[] = {0,0,-1,1}; // 상 하 좌 우
    static int dy[] = {-1,1,0,0};
    static int map[][];
    static boolean visit[][];
    static boolean visitCopy[][];
    static List<Dist> list = new ArrayList<>();
    static int core = Integer.MAX_VALUE;
    static int ans;
    static int px, py;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
        	list.clear();
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visit = new boolean[N][N];
            visitCopy = new boolean[N][N];
            core = 0;
            ans = Integer.MAX_VALUE;
            // 가장자리는 이미 연결 
            for (int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) visitCopy[i][j] = true;
					if(map[i][j] == 1 && ( i != 0 && j != 0 && i != N-1 && j != N-1 )) list.add(new Dist(i,j));
				}
			}
            
            //초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit[i][j] = visitCopy[i][j];
				}
			}
    
            // 프로세서 1개를 상하좌우로 연결하는 방법 
            dfs(0, 0,0);
       
            System.out.println(ans);
        } // testcase
        
        
    
    }
    static void dfs(int idx, int coreCnt, int wireSum )  {
    	
    	if(idx == list.size()) {
    		
    		// 프로세서 개수가 많으면 무조건 갱신 
    		if(core < coreCnt) {
    			core = coreCnt;
    			ans = wireSum;
    		}
    		// 프로세서 개수가 같으면, wireSum이 작을 때만 갱신 
    		else if( core == coreCnt ) {
    			if(ans > wireSum) {
    				ans = wireSum;
    			}
    		}
    		return;
    	}
    	
    	// 모든 리스트 안의 프로세서 꺼내서 dfs 
    	
    	Dist e = list.get(idx);
    	
    	
    		for (int d = 0; d < 4; d++) {
    			
    			py = e.y;
    			px = e.x;
    			int cnt = 0;
    	    	
        		while(true) {
        			
        			py += dy[d];
            		px += dx[d];
            		
            		if(py < 0 || py >= N || px < 0 || px >= N) break;
            		
            		
            		if( visit[py][px] ) { // 중간에 프로세서가 있다면 전선 연결 불가능 
            			cnt = 0;
            			break;
            		}
            		
            		cnt++; // 전선 길이 
            		
        		}
        		
        		
    		
    		
        		// len이 1 이상일때만 visit 표시 
    			py = e.y;
    			px = e.x;
    			for (int i = 0; i < cnt; i++) {
    				py += dy[d];
    				px += dx[d];
    				visit[py][px] = true;
    			}
    			
    			if(cnt == 0) dfs(idx + 1, coreCnt,  wireSum ); 
    			else {
    				dfs(idx + 1, coreCnt + 1,  wireSum + cnt ); 
    				
    				// visit 배열 원래대로 되돌리기 
    				py = e.y;
    				px = e.x;
    				for (int i = 0; i < cnt; i++) {
    					py += dy[d];
    					px += dx[d];
    					visit[py][px] = false;
    				}
    				
    			}
    			
    			
		}
			
			
    	
    	
    }
    
    static class Dist{
    	int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
    	
    }
}


/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0

*/