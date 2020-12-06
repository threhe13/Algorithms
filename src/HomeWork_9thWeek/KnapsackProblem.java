package HomeWork_9thWeek;

/*
item    value   weight
   1        1        1
   2        6        2
   3       18        5
   4       22        6
   5       28        7

capacity(W) : 11
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class KnapsackProblem {
    static String item_list;
    public static void main(String[] args) throws IOException {
        String path = KnapsackProblem.class.getResource("").getPath();
        File file = new File(path+"data09_knapsack.txt");
        List<String> input = Files.readAllLines(file.toPath(), Charset.defaultCharset());
        //System.out.println(input);

        int[] items = new int[input.size()];
        int[] values = new int[input.size()];
        int[] weights = new int[input.size()];

        for(int i = 0; i < input.size(); i++){
            String[] str = input.get(i).split(",");
            items[i] = Integer.parseInt(str[0]);
            values[i] = Integer.parseInt(str[1]);
            weights[i] = Integer.parseInt(str[2]);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("배낭의 사이즈를 입력하세요(0~50) : ");
        int capacity = Integer.parseInt(br.readLine());
        if(capacity > 0 && capacity < 51) {
            int result = solution(items, values, weights, capacity);
            System.out.println("max : " + result);
            System.out.println("items :" + item_list);
        }
        else{
            System.out.println("배낭의 사이즈가 범위를 벗어났습니다.");
        }
    }

    public static int solution(int[] items, int[] values, int[] weights, int capacity){ //return maximize total value
        int number = items.length;
        int[][] knapsack = new int[number+1][capacity+1];
        String[][] returnItems = new String[number+1][capacity+1];

        for(int i = 0; i <= capacity; i++){
            knapsack[0][i] = 0;
            returnItems[0][i] = "";
        }

        for(int i = 0; i <= number; i++){
            returnItems[i][0] = "";
        }

        for(int i = 1; i <= number; i++){
            for(int j = 1; j <= capacity; j++){
                if(weights[i-1] > j){//무게가 커서 배제
                    knapsack[i][j] = knapsack[i-1][j];
                    returnItems[i][j] = returnItems[i-1][j];
                }
                else{//무게가 허용가능
                    int notSelect = knapsack[i-1][j];
                    int newJ = j-weights[i-1];
                    int select = values[i-1]+knapsack[i-1][newJ];

                    if(notSelect > select){
                        knapsack[i][j] = notSelect;
                        returnItems[i][j] = returnItems[i-1][j];
                    }
                    else{
                        knapsack[i][j] = select;
                        returnItems[i][j] = returnItems[i-1][newJ]+" "+i;
                    }

                }
            }
        }

        print_int_double(knapsack);
        enter();
        //print_str_double(returnItems);
        item_list = returnItems[number][capacity];
        return knapsack[number][capacity];//최대 가치를 반환
    }

    static void print_int_double(int[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.printf("%3d", a[i][j]);
            }
            System.out.println();
        }
    }

    static void print_str_double(String[][] str){
        for(int i =1;i < str.length; i++){
            for(int j = 1; j < str[i].length; j++){
                System.out.print(str[i][j]+" ");
            }
            System.out.println();
        }
    }



    static void enter(){
        System.out.println();
    }

}
