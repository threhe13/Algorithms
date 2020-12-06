package Homework_2ndWeek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String[] str_div = divide(str);
        int[] input = new int[str_div.length];

        for(int i = 0; i < str_div.length; i++){
            input[i] = Integer.parseInt(str_div[i]);
        }

        int[] result = new int[input.length];
        result = mergeSort(input);
        print_int_Arr(result);
    }

    public static int[] mergeSort(int[] integers){
        if(integers.length < 2) return integers; //배열의 길이가 1이하이면 실행 안 함

        int mid = integers.length/2; // divide 단게를 위한 mid 설정

        int[] lower = mergeSort(Arrays.copyOfRange(integers, 0, mid)); //왼쪽 배열 정렬
        int[] higher = mergeSort(Arrays.copyOfRange(integers, mid, integers.length)); // 오른쪽 배열 정렬

        int[] result = new int[integers.length];
        result = merge(integers, lower, higher); // 병합

        return result;
    }

    public static int[] merge(int[] integers, int[] lower, int[] higher){
        int[] temp = new int[integers.length];
        int length = 0;

        int low_length = 0;
        int high_length = 0;

        while(low_length < lower.length && higher.length > high_length){ // 각 배열의 크기를 초과하지 않는 이상 반복문 실행
            if(lower[low_length] >= higher[high_length]){
                temp[length++] = higher[high_length++];
            }
            else{
                temp[length++] = lower[low_length++];
            }
        }
        //위에 반복문이 끝남과 동시에 한 쪽 측면의 배열은 이미 다 비워졌으므로 순서 상관없이 요소가 남아있는 배열을 비워주기
        while(low_length < lower.length){
            temp[length++] = lower[low_length++];
        }

        while(high_length < higher.length){
            temp[length++] = higher[high_length++];
        }

        return temp;
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
