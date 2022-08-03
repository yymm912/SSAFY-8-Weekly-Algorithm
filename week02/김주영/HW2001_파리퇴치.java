import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea2001 {

	static int N,M,T;
	static int[][] accu;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			accu=new int[N+1][N+1];
			
			for (int i=1; i<=N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=1; j<=N; j++) {
					accu[i][j]=accu[i-1][j]+accu[i][j-1]-accu[i-1][j-1]+Integer.parseInt(st.nextToken());
				}
			}
			
			int maxSum=0;
			
			
			for (int y=M; y<=N; y++) {
				for (int x=M; x<=N; x++) {
					int sum=accu[y][x]-accu[y][x-M]-accu[y-M][x]+accu[y-M][x-M];
					if (sum>maxSum) maxSum=sum;
				}
			}
			
			System.out.println("#"+tc+" "+maxSum);

		}
	}

}
