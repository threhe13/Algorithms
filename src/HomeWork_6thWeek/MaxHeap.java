package HomeWork_6thWeek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class MaxHeap {
    static int[] Heap;
    static int size;
    static int maxSize;

    static HashMap<Integer, String> map;

    public static void main(String[] args) throws IOException {
        String path = MaxHeap.class.getResource("").getPath();
        List<String> list = Files.readAllLines(Paths.get(path+"data06_heap.txt"));

        maxHeap(list.size());
        map = new HashMap<Integer, String>();
        int i = 1;

        for(String str : list){
            String[] input = str.split(", ");
            int number = Integer.parseInt(input[0]);
            String lecture = input[1];

            map.put(number, lecture);
            Heap[i++] = number;
        }

        Build_Max_Heap(Heap.length-1);
        //print_int_arr_for_debugging(Heap);
        print_int_arr(Heap);
    }

    public static void maxHeap(int maxsize){
        size = 0;
        maxSize = maxsize;
        Heap = new int[maxSize+1];
        Heap[0] = Integer.MAX_VALUE;
    }

    public static int parents(int index){
        return index/2;
    }

    public static int left_child(int index){
        return index*2;
    }

    public static int right_child(int index){
        return index*2+1;
    }

    public static void Swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void Max_Heapify(int index){
        int leftChild = left_child(index);
        int rightChild = right_child(index);

        int temp = 0;
        if(leftChild <= size && Heap[leftChild] > Heap[index]){
            temp = leftChild;
        }
        else {
            temp = index;
        }

        if(rightChild <= size && Heap[rightChild] > Heap[temp]){
            temp = rightChild;
        }

        if(temp != index){
            Swap(Heap, temp, index);
            Max_Heapify(temp);
        }
    }

    public static void Build_Max_Heap(int length){
        size = length;
        for(int i = parents(length); i >= 1; i--){
            Max_Heapify(i);
        }
    }

    public static void print_int_arr_for_debugging(int[] A){
        int temp = 1;
        for(int i = 1; i <= size; i++){
            System.out.println(A[i]+", "+map.get(A[i]));
            if(temp == i){
                System.out.println();
                temp = temp*2+1;
            }
        }
    }

    public static void print_int_arr(int[] A){
        for(int i = 1; i <= size; i++){
            System.out.println(A[i]+", "+map.get(A[i]));
        }
    }
}
