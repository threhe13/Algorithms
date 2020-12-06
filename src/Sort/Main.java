package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String[] str_div = divide(str);
        int[] input = new int[str_div.length];

        for(int i = 0; i < str_div.length; i++){
            input[i] = Integer.parseInt(str_div[i]);
        }

        /*
        System.out.println("insertionSort\n");
        int[] insertion_sort = insertionSort(input);
        print_int_Arr(insertion_sort);


         */
        System.out.println("\n");
        System.out.println("mergeSort\n");
        int[] merge_sort = mergeSort(input);
        print_int_Arr(merge_sort);
    }

    public static String[] divide(String str){
        String[] temp = str.split(",");

        return temp;
    }

    public static int[] insertionSort(int[] integers){
        for(int i = 1; i < integers.length; i++){
            int value = integers[i];
            int temp = i-1;

            while(temp >= 0 && integers[temp] > value){
                integers[temp+1] = integers[temp];
                temp--;
            }

            integers[temp+1] = value;
        }

        return integers;
    }

    public static int[] mergeSort(int[] integers){
        if(integers.length < 2) return integers;

        int mid = integers.length/2;

        int[] lower = mergeSort(Arrays.copyOfRange(integers, 0, mid));
        int[] higher = mergeSort(Arrays.copyOfRange(integers, mid, integers.length));

        int[] result = new int[integers.length];
        result = merge(integers, lower, higher);

        return result;
    }

    public static int[] merge(int[] integers, int[] lower, int[] higher){
        int[] temp = new int[integers.length];
        int length = 0;

        int low_length = 0;
        int high_length = 0;

        while(low_length < lower.length && higher.length > high_length){
            if(lower[low_length] >= higher[high_length]){
                temp[length++] = higher[high_length++];
            }
            else{
                temp[length++] = lower[low_length++];
            }
        }

        while(low_length < lower.length){
            temp[length++] = lower[low_length++];
        }

        while(high_length < higher.length){
            temp[length++] = higher[high_length++];
        }

        return temp;
    }

    public static void print_int_Arr(int[] objects){
        for(int i = 0; i < objects.length; i++){
            System.out.print(objects[i]+" ");
        }
    }

    public static void print_str_Arr(String[] strings){
        for(int i = 0; i < strings.length; i++){
            System.out.print(strings[i]+" ");
        }
    }
}