package week06;

import java.io.*;
import java.util.*;

public class BJ2980_도로와신호등 {
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    int time = 0, curr = 0;
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      int D = Integer.parseInt(st.nextToken());
      int R = Integer.parseInt(st.nextToken());
      int G = Integer.parseInt(st.nextToken());
      time += D - curr;
      curr = D;

      int tmp = time %(R+G);
      if(tmp < R) time += R-tmp;
      
    }
    System.out.println(time + L-curr);
  }
}
