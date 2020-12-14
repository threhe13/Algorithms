package HomeWork_6thWeek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        //int[] example1 = {1, 3, 5, 4, 7, 10, 2};
        String path = QuickSort.class.getResource("").getPath();
        List<String> input_data = Files.readAllLines(Paths.get(path+"data06.txt"));

        String[] str = input_data.get(0).split(",");
        //print_int_arr(str);

        int[] data = new int[str.length];
        int i = 0;
        for(String temp : str){
            data[i++] = Integer.parseInt(temp);
        }

        QuickSort(data, 0, data.length-1);
        print_int_arr(data);

        String fileName = path+"data06_quick.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

        i = 0;
        for(int temp : data){
            if(i == data.length-1){
                bw.write(temp);
            }
            else {
                bw.write(temp + ",");
                i++;
            }
        }

        bw.flush();
        bw.close();
    }

    public static void QuickSort(int[] A, int start, int end){
        if(start < end) {
            int partition = partition(A, start, end);

            QuickSort(A, start, partition-1);
            QuickSort(A, partition+1, end);
        }
    }

    public static int partition(int[] A, int start, int end){
        int x = A[end];
        int i = start-1;//-1
        for(int j = start; j < end; j++){
            if(A[j] <= x){
                i += 1;//0~
                Swap(A, i, j);
            }
        }
        i++;
        Swap(A, i, end);

        return i;
    }

    public static int Randomized_partitions(int[] A, int start, int end){
        Random r = new Random();
        int i = r.nextInt(end)+start;
        Swap(A, i, end);
        return partition(A, start, end);
    }

    public static void Swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void print_int_arr(int[] A){
        int length = A.length;
        for(int i = 0; i < length; i++){
            System.out.print(A[i]+" ");
        }
    }

}
