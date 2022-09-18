import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2022_사다리 {
    
    static double x, y, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double l = 1;
        double r = Math.min(x, y);

        while(r - l >= 0.001){
            double w = (l + r) / 2;

            double h1 = Math.sqrt(Math.pow(x, 2) - Math.pow(w, 2));
            double h2 = Math.sqrt(Math.pow(y, 2) - Math.pow(w, 2));
            double calcC = (h1 * h2) / (h1 + h2);

            if (calcC >= c) l = w;
            else r = w;
        }

        System.out.println(r);
        br.close();
    }
}
