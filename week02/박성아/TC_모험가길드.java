import java.util.*;
import java.io.*;

public class Main {

    public static int n, res, count;
    public static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }

        Collections.sort(arr);

        for (int i = 0; i < n; i++) { 
            count++; 
            if (count >= arr.get(i)) { 
                res++;
                count = 0;
            }
        }

        System.out.println(res);
    }
}