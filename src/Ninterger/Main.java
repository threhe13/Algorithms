package Ninterger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //Random random = new Random();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("정수의 개수를 입력하시오 : ");
        int n = Integer.parseInt(br.readLine());
        System.out.println();
        String str = br.readLine();

        int[] integers = new int[n];
        /*
        for(int i = 0; i < n; i++){
            integers[i] = random.nextInt(100)+1;
        }
         */

        StringTokenizer st = new StringTokenizer(str);
        for(int i = 0; i < n; i++){
            integers[i] = Integer.parseInt(st.nextToken());
        }

        Solution s = new Solution();
        //long time = System.currentTimeMillis();
        int[] result = s.solution(n, integers);
        System.out.println("Min : "+result[0]+", Max : "+result[1]);
        //long time2 = System.currentTimeMillis() - time;
        //System.out.println(time2);
    }
}

class Solution{
    public int[] solution(int n, int[] integers){
        int[] answer = new int[2];

/*
        int length_divide = n/2;

        //first
        int min_first = Integer.MAX_VALUE;
        int max_first = Integer.MIN_VALUE;

        //second
        int min_sec = Integer.MAX_VALUE;
        int max_sec = Integer.MIN_VALUE;

        for(int i = 0, j = n-1; i < length_divide; i++, j--){
            int temp_i = integers[i];
            int temp_j = integers[j];

            if(min_first > temp_i){
                min_first = temp_i;
            }

            if(min_sec > temp_j){
                min_sec = temp_j;
            }

            if(max_first < temp_i){
                max_first = temp_i;
            }

            if(max_sec < temp_j){
                max_sec = temp_j;
            }
        }

        for(int i = length_divide; i < n; i++){
            int temp = integers[i];

            if(min_sec > temp){
                min_sec = temp;
            }

            if(max_sec < temp){
                max_sec = temp;
            }
        }



        answer[0] = Math.min(min_first, min_sec); answer[1] = Math.max(max_first, max_sec);



 */

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            int temp = integers[i];

            if(min > temp){
                min = temp;
            }

            if(max < temp){
                max = temp;
            }
        }

        answer[0] = min; answer[1] = max;


        return answer;
    }
}
