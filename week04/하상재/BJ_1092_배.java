package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1092_배 {
	
	static int n, m, ans;
	static ArrayList<Integer> cargo, crane;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ans = 0;
		
		crane = new ArrayList<>();
		cargo = new ArrayList<>();
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			crane.add(Integer.parseInt(st.nextToken()));
		}
		
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<m; i++) {
			cargo.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(crane, (o1,o2)->o2-o1);
		Collections.sort(cargo, (o1,o2)->o2-o1);
		
		if(crane.get(0)<cargo.get(0)) {
			System.out.println(-1);
			return;
		}
		
		int craneIdx=0, cargoIdx=0;
		
		while(!cargo.isEmpty()) {
			craneIdx = 0;
			cargoIdx = 0;
			while(cargoIdx!=cargo.size() && craneIdx<n) {
				
				if(cargo.get(cargoIdx)<=crane.get(craneIdx)) {
					cargo.remove(cargoIdx);
					craneIdx++;
				}
				else {
					cargoIdx++;
				}
			}
			ans++;
			
		}
		
		
		System.out.println(ans);
		
	}
	
}