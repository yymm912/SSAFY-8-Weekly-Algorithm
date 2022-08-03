import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int channel = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        boolean[] broken = new boolean[10];
        int min = Integer.MAX_VALUE;

        if(n>0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i<n; i++){
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }
        int first_channel = 100;

        if(channel==first_channel){
            System.out.println(0);
            return;
        }

        if(channel>first_channel) min = Math.min(min,channel-first_channel);
        else min = Math.min(min,first_channel-channel);

        if(n<10){
            String temp = "";
            boolean check = true;
            for(int i=0; i<1_000_001; i++){
                temp = String.valueOf(i);
                for(int j=0; j<temp.length(); j++){
                    if(broken[(int)temp.charAt(j)-'0']){
                        check =false;
                        break;
                    }
                }
                if(check){
                    if(i>channel) {
                        if(temp.length()+(i-channel)>=0) min = Math.min(min,temp.length()+(i-channel));
                    }
                    else if(i<channel) {
                        if(temp.length()+(channel-1)>=0) min = Math.min(min, temp.length()+(channel-i));
                    }
                    else min = Math.min(min,temp.length());
                }
                check = true;
            }
        }

        System.out.println(min);

    }

}