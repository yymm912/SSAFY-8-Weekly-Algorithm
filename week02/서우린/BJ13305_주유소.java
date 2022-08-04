package _2주차;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13305_주유소 {
	static class City implements Comparable<City>{
		int price,pos;
		public City(int price,int pos) {
			this.price = price;
			this.pos = pos;
		}
		@Override
		public int compareTo(City o) {
			// TODO Auto-generated method stub
			return this.price - o.price;
		}
	}
	static long [] prefix;
	static int [] dist;
	static List<City> city; 
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		prefix = new long[N];
		dist = new int[N];
		city = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0;i<N-1;i++) {
			dist[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0;i<N;i++) {
			city.add(new City(Integer.parseInt(st.nextToken()),i));
		}
		
		Collections.sort(city);
		
		for(int i = N-2;i>=0;i--) {
			prefix[i] = prefix[i+1]+dist[i];
		}
		
		int p = N-1;
		long answer = 0;
		for(int i = 0;i<N;i++) {
			City cur = city.get(i);
			if(p > cur.pos) {
				long total = prefix[cur.pos] - prefix[p];
				answer += (total*cur.price);
				p = cur.pos;
			}
		}
		
		System.out.println(answer);
	}

}
