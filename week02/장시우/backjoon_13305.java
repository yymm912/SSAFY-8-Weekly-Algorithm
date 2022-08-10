import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long ans;
	static long[] distances;
	static long[] oilPrices;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		distances = new long[N - 1];
		oilPrices = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			distances[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			oilPrices[i] = Integer.parseInt(st.nextToken());
			if (i != 0 && oilPrices[i] > oilPrices[i - 1]) {
				oilPrices[i] = oilPrices[i - 1];
			}
		}
		
		for (int i = 0; i < N - 1; i++) {
			ans += (oilPrices[i] * distances[i]);
		}
		
		System.out.println(ans);
	}
}
