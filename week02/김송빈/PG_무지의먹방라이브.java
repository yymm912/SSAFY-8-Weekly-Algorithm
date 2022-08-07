package Programmers;

import java.util.Arrays;
class solution_1 {
    public int solution(int[] task_times, long k) {
        long sum = 0;
        int len = task_times.length;
        int[] sorted_food = new int[len];

        for (int i = 0; i < len; i++) {
            sorted_food[i] = task_times[i];
            sum += task_times[i];
        }

        if (sum <= k) { // 종료되는 시간이 총 합 시간보다 작으면 -1 리턴
            return -1;
        }

        Arrays.sort(sorted_food);// 먹을 거 순으로 정렬
        int min_idx = 0;
        int size = len;
        long circle = 0;

        while (k >= size) {// 남은 시간이 싸이클 길이보다 클 때
            k -= size;//싸이클만큼 시간 뺴줌
            circle++;//사이클 수 up

            while(sorted_food[min_idx] - circle <= 0) {//circle을 다 돌았을 때 작은 수 다먹으면
                min_idx++;//제일 작은 거 변경
                size--;//길이 줄임
            }
        }

        for (int i = 0; i < len; i++) {//각자 시간에 사이클 수 빼줌
            task_times[i] -= circle;
        }
        
        int i = 0;
        while (k-- >= 0) {//circle돌고 남은 시간 계산
            while (task_times[i] <= 0) { // 음식이 있을때까지 인덱스 바꿔줌
                i ++;
            }
            i++;//남은 음식 중 k번째 찾아감
        }

        return i ; // return
    }
}