class Solution {
    
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        StringBuilder sb=new StringBuilder ();
        
        int [] personality=new int[8];
        char[] charPersonality= {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        
        for (int i=0, size=survey.length; i<size; i++) {
            int choice=choices[i];      //몇 점인지 받아옴
            int idx=0;                  //성격 유형의 인덱스를 받아옴
            
            if (choice<=3) {
                idx=personalityIndex(survey[i].charAt(0));
                switch (choice) {
                    case 1: personality[idx]+=3; break;
                    case 2: personality[idx]+=2; break;
                    case 3: personality[idx]+=1; break;
                }
                
            } else if (choice>=5) {
                idx=personalityIndex(survey[i].charAt(1));
                personality[idx]+= (choice-4);
            }
            
            // for (int k=0; k<8; k++) {
            //     System.out.print(personality[k]+" ");
            // }
            // System.out.println();
        }
        
        for (int i=0; i<8; i+=2) {
            if (personality[i]>personality[i+1]) {
                sb.append(charPersonality[i]);
            } else if (personality[i]<personality[i+1]) {
                sb.append(charPersonality[i+1]);
            } else sb.append(
                charPersonality[i]<charPersonality[i+1] ? 
                charPersonality[i] : charPersonality[i+1]);
        }
        
        answer=sb.toString();
        
        return answer;
    }
    
    static int personalityIndex (char c) {
        switch (c) {
            case 'R' : return 0;
            case 'T' : return 1;
            case 'C' : return 2;
            case 'F' : return 3;
            case 'J' : return 4;
            case 'M' : return 5;
            case 'A' : return 6;
            case 'N' : return 7;
        }
        return 0;
    }
}