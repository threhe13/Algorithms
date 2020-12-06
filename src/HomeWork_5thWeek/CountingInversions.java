package HomeWork_5thWeek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountingInversions {
    public static void main(String[] args) throws IOException {
        //int[] test = {1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7};
        //Counting inversions : 22;

        int[] test;
        //Counting inversions : 5;

        String path = CountingInversions.class.getResource("").getPath();
        List<String> list = Files.readAllLines(Paths.get(path+"data05_inversion.txt"));

        String[] input_data = list.get(0).split(",");
        test = new int[input_data.length];
        int i = 0;
        for(String str : input_data){
            test[i++] = Integer.parseInt(str);
        }

        int result = CountingInversion(test);
        System.out.println(result);

        //print_int_Arr(test);
    }

    public static int CountingInversion(int[] test){
        if(test.length == 1){
            return 0;
        }

        int[] temp = new int[test.length];

        int mid = test.length/2;
        int[] left = Arrays.copyOfRange(test, 0, mid);
        int[] right = Arrays.copyOfRange(test, mid, test.length);

        int leftCount = CountingInversion(left);
        int rightCount = CountingInversion(right);
        int mergeCount = MergeAndCount(left, right, temp);

        for(int i = 0; i < test.length; i++){
            test[i] = temp[i];
        }

        return leftCount+rightCount+mergeCount;
    }

    public static int MergeAndCount(int[] left, int[] right, int[] temp){
        int count = 0;
        int indexL = 0; int indexR = 0;
        int countL = left.length; int countR = right.length;
        int i = 0;
        while(indexL < countL && indexR < countR){
            if(left[indexL] > right[indexR]){
                count += (countL - indexL);
                temp[i++] = right[indexR];
                indexR++;
            }
            else{
                temp[i++] = left[indexL];
                indexL++;
            }
        }

        while(indexL < countL){
            temp[i++] = left[indexL++];
        }

        while(indexR < countR){
            temp[i++] = right[indexR++];
        }

        //print_int_Arr(temp);

        return count;

    }

    public static void print_int_Arr(int[] objects){ //정수배열 출력 함수
        for(int i = 0; i < objects.length; i++){
            System.out.print(objects[i]+" ");
        }
        System.out.println();
    }
}
