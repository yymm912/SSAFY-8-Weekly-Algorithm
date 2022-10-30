package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15666_N과M_12 {
    static int n,m;
   // static List<List<Integer>>ans=new ArrayList<>();
    static int []num;
    static int []tgt;
   
    public static void main(String[] args)throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        num=new int[n];
        tgt=new int[m];
        
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            num[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        perm(0,0);
        
      
        
    }
    
    static void perm(int tgtidx,int srcidx) {
        if(tgtidx==m) {
            List<Integer>list=new ArrayList<>();
            boolean dup=false;
            for(int i=0;i<m;i++) {
            
                System.out.print(num[tgt[i]]+" ");
                
            }
            System.out.println();

            return ;
        }
        
        int bef=0;
        for(int i=srcidx;i<n;i++) {
            if(bef==num[i])continue;
        	tgt[tgtidx]=i;
            perm(tgtidx+1,i);
            bef=num[i];
        }
        
    }
}