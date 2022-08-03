package hwalgo03_부울경_03반_박성아;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			int num2=Integer.parseInt(st.nextToken());
			
			int arr[][]=new int[num][num];
			
			int max=0;
			for(int i=0;i<num;i++){
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<num;j++){
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<num-num2+1;i++){
				for(int j=0;j<num-num2+1;j++){
					int sum=0;
					for(int k=0;k<num2;k++){
						for(int l=0;l<num2;l++){
							sum+=arr[i+k][j+l];
						}
					}
					if(max<sum){
						max=sum;
					}
				}
			}
			System.out.println("#"+t+" "+max);
		}
	}

}
	
