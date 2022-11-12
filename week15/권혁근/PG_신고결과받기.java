import java.util.*;

class Solution {
    	static public int[] solution(String[] id_list, String[] reports, int k) {
		int[] answer = new int[id_list.length];
        Map<String, Integer> reporting = new HashMap<>();
        Map<String, Set<String>> reported = new HashMap<>();
        
        for(String report : reports){
            String[] list= report.split(" ");
            String n1=list[0];
            String n2=list[1];
            
            if(reported.containsKey(n2)){
            	reported.get(n2).add(n1);
            }else{
                reported.put(n2,new HashSet<>());
                reported.get(n2).add(n1);
            }
            
        }
        
        for(String key: reported.keySet()) {
        	if(reported.get(key).size()>=k) {
        		for(String r: reported.get(key)) {
        			if(!reporting.containsKey(r)) {
        				reporting.put(r,1);
        			}else {
        				reporting.put(r, reporting.get(r)+1);
        			}
        		}
        	}
        }
            
        int i=0;
        for(String id :id_list) {
        	if(reporting.containsKey(id)) {
        		answer[i]=reporting.get(id);
        	}
        	i++;
        }
        

        
        return answer;
    }
}