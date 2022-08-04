package pg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2309일곱난쟁이 {

	static int dwarf[] = new int[9]; // 입력 
	static int tgt[] = new int[7];
	static int sum;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		System.setIn(new FileInputStream("BJ2309.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
		StringTokenizer st;
		for(int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine());
			dwarf[i] = Integer.parseInt(st.nextToken());
		}
		//입력 완료 
		
		Arrays.sort(dwarf);
	
		eleven(0, 0);
		
		for(int i=0;i<7;i++) System.out.println(tgt[i]);
		
	}
	
	 static void eleven( int dwarfIdx, int tgtIdx){
		
		 if(tgtIdx == 7)  return;
		  
		 for (int i = dwarfIdx; i < dwarf.length; i++) {
			tgt[tgtIdx] = dwarf[i]; // 선택 
			sum += dwarf[i];
			
			eleven(i+1, tgtIdx+1); // 다음 난쟁이 추가 
			
			if(sum == 100)return;
			sum -= dwarf[i];

		}	
	}
}
