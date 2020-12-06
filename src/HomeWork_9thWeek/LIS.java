package HomeWork_9thWeek;

/*
S = {9, 1, 3, 7, 5, 6, 10}
 */

import java.util.ArrayList;

public class LIS {
    static int[] A;
    static int[] LIS;
    static ArrayList<Integer> sequence;
    static int longest_length;

    public static void main(String[] args){
        A = new int[]{9, 1, 3, 7, 5, 6, 10};
        LIS = new int[A.length];
        sequence = new ArrayList<>();


        solution();

        print_int_arr(A);
        print_int_arr(LIS);
        System.out.println("가장 긴 subsequence의 길이 : "+longest_length);
        System.out.println("LIS의 문자열 : "+sequence);
    }

    static void solution(){
        sequence.add(0);

        //DP를 통한 LIS배열 생성
        for(int i = 0; i < A.length; i++){
            int count = 1;
            for(int j = 0; j < i; j++){
                if(A[j] < A[i]){
                    count = Math.max(count, LIS[j]+1);
                }
            }
            LIS[i] = count;
            longest_length = Math.max(count, longest_length);

            //문자열 출력을 위한 과정
            if(i > 0){
                if(LIS[i-1] == LIS[i]){
                    int temp = Math.min(A[i-1], A[i]);
                    sequence.remove(sequence.size()-1);
                    sequence.add(temp);
                }
                else if(LIS[i-1] < LIS[i] && sequence.get(sequence.size()-1) < A[i]){
                    sequence.add(A[i]);
                }
            }
        }
    }

    static void print_int_arr(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

}
