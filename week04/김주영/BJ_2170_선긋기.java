package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2170 {

	static int N;
	static long ans;
	static List<int[]> line=new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st=null;
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			
			line.add(new int[] {start, end});
		}
		
		//시작 순서가 빠른 순으로 정렬
		Collections.sort(line, (o1,o2)-> o1[0]-o2[0]);
		
		int start=line.get(0)[0];
		int end=line.get(0)[1];
		
		for (int i=1; i<N; i++) {
			int s=line.get(i)[0];
			int e=line.get(i)[1];
			
			//새로 들어온 선이 더 뒤에 있는 경우
			if (s>end) {
				ans+=(end-start);
				start=s;
				end=e;
			}
			else 
				end=Math.max(end, e);
		}
		
		ans+=end-start;
		
		System.out.println(ans);
	}

}
