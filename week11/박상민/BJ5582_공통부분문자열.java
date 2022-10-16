import java.io.*;

// KMP
public class BJ5582_공통부분문자열 {

    static char[] s1, s2;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input1 = br.readLine();
        String input2 = br.readLine();
        // String input1 = "ECADADABRBCRDARA";
        // String input2 = "DABRB";
        
        int size1 = input1.length();
        int size2 = input2.length();

        s1 = size1 > size2 ? input1.toCharArray() : input2.toCharArray();    // 긴 것
        s2 = size1 > size2 ? input2.toCharArray() : input1.toCharArray();    // 짧 것

        // 공통 테이블 생성 (비교할 문자열의 접두사와 접미사의 일치하는 부분문자열의 갯수를 찾음)
        // 대가리와 꼬리의 비교
        // 일치하면 i, j 동시증가
        // 일치하지 않으면 i 증가 -> j 는 이전에 일치하던 인덱스 + 1 로 위치이동

        // i = 1 / j = 0 (불일치 -> j 에 pi[0] 값 들어감)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //     a   b   a   b   a   c   a
        //[0, '0', 0,  0,  0,  0,  0]

        // i = 2 / j = 0 (일치 -> 1 부터 비교시작해라)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //         a   b   a   b   a   c   a
        //[0,  0, '1',  0,  0,  0,  0]

        // i = 3 / j = 1 (일치 -> 2부터 비교시작해라)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //         a   b   a   b   a   c   a
        //[0,  0,  1, '2',  0,  0,  0]

        // i = 4 / j = 2 (일치 -> 3부터 비교시작해라)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //         a   b   a   b   a   c   a
        //[0,  0,  1,  2, '3', 0,  0]
        
        // i = 5 / j = 3 (불일치 -> j 에 p[3] 값 들어감)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //         a   b   a   b   a   c   a
        //[0,  0,  1,  2,  3,  0,  0]

        // i = 5 / j = 2 (불일치 -> j 에 p[2] 값 들어감)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //             a   b   a   b   a   c   a
        //[0,  0,  1,  2,  3,  0,  0]

        // i = 5 / j = 1 (불일치 -> j 에 p[1] 값 들어감)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //                 a   b   a   b   a   c   a
        //[0,  0,  1,  2,  3,  0,  0]

        // i = 5 / j = 0 (불일치 -> j 에 p[0] 값 들어감)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //                     a   b   a   b   a   c   a
        //[0,  0,  1,  2,  3,  0,  0]

        // i = 6 / j = 0 (일치 -> 1부터 비교시작해라)
        // 0   1   2   3   4   5   6
        // a   b   a   b   a   c   a
        //                         a   b   a   b   a   c   a
        //[0,  0,  1,  2,  3,  0,  1]

        answer = 0;
        for (int front = 0; front < s2.length - 1; front++){
            for (int rear = s2.length - 1; rear >= front; rear--){
                char[] next = new char[rear - front + 1];
                for (int i = front, j = 0; i <= rear; i++, j++){
                    next[j] = s2[i];
                }
                KMP(next);
            }
        }

        System.out.println(answer);
        br.close();
    }

    static void KMP(char[] s2){
        int[] pi = new int[s2.length];

        for (int i = 1, j = 0; i < s2.length; i++){
            while(s2[i] != s2[j] && j > 0) j = pi[j - 1];

            if (s2[i] == s2[j]) pi[i] = ++j;
            else pi[i] = 0;
        }

        // 위와 동일하고 길이만 다름
        for (int i = 0, j = 0; i < s1.length; i++){     // 고정
            while (s1[i] != s2[j] && j > 0) j = pi[j - 1];         

            if (s1[i] == s2[j]) {
                answer = Math.max(answer, j + 1);
                if (j == s2.length - 1) break;          // 끝까지 도착했으면
                else j++;                               // 다음
            }
        }
    }
}