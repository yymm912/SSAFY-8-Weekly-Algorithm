import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();

        int tc_max = Integer.parseInt(br.readLine());

        for(int tc=0; tc<tc_max; tc++){
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String val = br.readLine().replace("[","").replace("]","");
            StringTokenizer st = new StringTokenizer(val,",");
            for(int i=0; i<n; i++){
                queue.add(Integer.parseInt(st.nextToken()));
            }

            boolean check = true;
            boolean is_reverse = false;

            for(int i=0; i<command.length(); i++){
                if(command.charAt(i)=='R'){
                    if(!is_reverse) is_reverse=true;
                    else is_reverse=false;
                }
                else{
                    if(queue.isEmpty()){
                        check = false;
                        break;
                    }
                    if(is_reverse) queue.pollLast();
                    else queue.poll();
                }
            }
            if(check){
                sb.append("[");
                int size = queue.size();

                if(is_reverse){
                    for(int i=0; i<size; i++){
                        sb.append(queue.pollLast());
                        if(i!=size-1) sb.append(",");
                    }
                }
                else{
                    for(int i=0; i<size; i++){
                        sb.append(queue.poll());
                        if(i!=size-1) sb.append(",");
                    }
                }
                sb.append("]\n");
            }
            else sb.append("error\n");
            queue.clear();

        }
        System.out.println(sb);
    }

}