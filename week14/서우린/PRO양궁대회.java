import java.util.Arrays;
class Solution {
    static int diff = 0;
    static int [] ans = {-1};
    static int [] iter = new int[11];
    public void solve(int n,int idx,int[] info){
        if(n == 0 || idx==11){
            if(n != 0) iter[10] += n;
            //점수 계산
            int a = 0;//어피치
            int b = 0;//라이언
            for(int i = 0;i<11;i++){
                if(iter[i] == 0 && info[i] == 0) continue;
                if(iter[i] <= info[i]){
                    a+=(10-i);
                }else{
                    b+=(10-i);
                }
            }
            if(a < b){
                int d = (b - a);
                if(diff < d){
                    diff = d;
                    ans = Arrays.copyOf(iter,11);
                }else if(diff == d){
                    for(int i = 10;i>=0;i--){
                        if(ans[i] < iter[i]){
                            ans = Arrays.copyOf(iter,11);
                            break;
                        }else if(ans[i] > iter[i]) break;
                    }
                }
            }
            if(n != 0) iter[10] -= n;
            return;
        }
        
        //이번 점수를 얻음
        if(n > info[idx]){
            iter[idx] = info[idx]+1;
            solve(n-iter[idx],idx+1,info);
            iter[idx] = 0;
        }
        //이번 점수를 거름
        solve(n,idx+1,info);
    }
    public int[] solution(int n, int[] info) {
        solve(n,0,info);
        return ans;
    }
}