import java.util.*;
public class gamedevelopment {
	//사방탐색
	static int []dx= {0,1,0,-1};
	static int []dy= {-1,0,1,0};
	static int [][]map;
	
	//접근 체크
	static boolean [][]visited;
	
	static int change_d(int d) {
		d=(d+3)%4;//방향 전환 -1 처럼 
		return d;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st=new(StringTokenizer(br.readLine());
		int n=sc.nextInt();
		int m=sc.nextInt();
		
		int x,y,d;
		y=sc.nextInt();//x=Integer.parseInt(st.nextToken())
		x=sc.nextInt();
		d=sc.nextInt();
				
		map=new int[n][m];
		visited=new boolean[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j]=sc.nextInt();//Integer.parseInt(st.nextToken())
			}
		}
		
		int cnt=1;//처음 시작한 자리부터 1
		int ny=y,nx=x;//ny,nx가 바뀔 자리
		visited[ny][nx]=true;
		
		while(true) {
			int d_count=0;//회전 횟수
			
			while(d_count<=4) {
				d_count++;
				if(d_count==5)break;// 4군데 다 보면 while문 밖으로
				d=change_d(d);//d_count가 5여도 얘는 4번만 바뀜
				ny=ny+dy[d];
				nx=nx+dx[d];//탐색
				if(ny<n&&ny>=0&&nx<n&&nx>=0) {//array를 벗어났는지
					if(map[ny][nx]==0&&!(visited[ny][nx])) {//한 번도 안들리고 땅인지
						visited[ny][nx]=true;
						cnt++;//횟수++
						break;
					}
				}
				
				ny=ny-dy[d];
				nx=nx-dx[d];//초기화
			}
			if(d_count==5) {//갈 곳 없으면
				nx=nx-dx[d];
				ny=ny-dy[d];//빽
				
				if(map[ny][nx]==1)break;//더 이상 물러날 곳이 없을 때까지
			}
		}
		System.out.println(cnt);
	}
}