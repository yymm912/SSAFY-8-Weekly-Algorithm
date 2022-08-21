
import java.io.*;
import java.util.*;

public class BJ_숨바꼭질_1697 {
	static int N, K;
	static int v[] = new int[100001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String[] inputs = input.split(" ");
		
		N = Integer.valueOf(inputs[0]);
		K = Integer.valueOf(inputs[1]);
		
		int result = bfs(N);
		System.out.println(result);
	}

	private static int  bfs(int node) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(node);
		int index = node;
		int n = 0;
		v[index] = 1;
		while (q.isEmpty() == false) {
			n = q.remove();
			
			if (n == K)
				return v[n]-1;
			
			
			if (n-1>=0 && v[n-1] == 0) {
				v[n-1] = v[n]+1;
				q.add(n-1);
			}
			if (n+1 <= 100000 && v[n+1] == 0) {
				v[n+1] = v[n]+1;
				q.add(n+1);
			}
			if (2*n <= 100000 && v[2*n] == 0) {
				v[2*n] = v[n] + 1;
				q.add(2*n);
			}
		}
		return -1;
	}
}
