package Homework_2ndWeek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertionSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String[] str_div = divide(str);
        int[] input = new int[str_div.length];

        for(int i = 0; i < str_div.length; i++){
            input[i] = Integer.parseInt(str_div[i]);
        }

        int[] insertion_sort = insertionSort(input);
        print_int_Arr(insertion_sort);
    }

    //pseudo 코드를 참고하여 작성해보았습니다.
    public static int[] insertionSort(int[] integers){
        for(int i = 1; i < integers.length; i++){
            int value = integers[i]; //i에 위치한 값 보존
            int temp = i-1; // 이동을 위한 위치 저장

            while(temp >= 0 && integers[temp] > value){ //역순으로 value의 값이 들어갈 위치를 탐색
                integers[temp+1] = integers[temp]; //위차가 아닐 시 오른쪽으로 이동시키고 다시 탐색 시작
                temp--; //위치 이동
            }

            integers[temp+1] = value; // 위치가 발견될 시, 반복문이 종료되고 30번 줄로 인해 줄어들었던 temp를 +1시켜 올바른 위치에 저장
        }

        return integers;
    }

    public static String[] divide(String str){ //배열로 분할해주는 역할 함수
        String[] temp = str.split(",");
        return temp;
    }

    public static void print_int_Arr(int[] objects){ //정수배열 출력 함수
        for(int i = 0; i < objects.length; i++){
            System.out.print(objects[i]+" ");
        }
    }
}
