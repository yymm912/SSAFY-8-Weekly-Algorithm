import java.util.*;

public class Main {

	static int n, k, res;
	static int [] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		
		arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int i=n-1; i>=0; i--) {
			if(k - arr[i] < 0) continue;
			res += k / arr[i];
			k %= arr[i];
		}
		
		System.out.println(res);

	}

}