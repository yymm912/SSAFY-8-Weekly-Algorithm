import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n,m;
	static int []parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		parent=new int[n+1];
		for(int i=0;i<n+1;i++) {
			parent[i]=i;
		}
		
		for(int j=0;j<m;j++) {
			st=new StringTokenizer(br.readLine());
			int c=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			if(c==0) union(a,b);
			else if(c==1) {
				if(findset(a)==findset(b))System.out.println("yes");
				else System.out.println("no");
			}
		}
		

	}
	static int findset(int x) {
		if(x==parent[x])return x;
		return parent[x]=findset(parent[x]);
	}
	static void union(int a,int b) {
		int pa=findset(a);
		int pb=findset(b);
		if(pa>pb)parent[pb]=pa;
		else parent[pa]=pb;
	}
}
