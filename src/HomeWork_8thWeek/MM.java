package HomeWork_8thWeek;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MM {
    static int[][] mm;
    static int[][] s;
    static int[] r;

    public static void main(String[] args) throws FileNotFoundException {
        String path = MM.class.getResource("").getPath();
        File file = new File(path+"data08_matrix_chain.txt");

        //파일읽기
        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNextLine()){
            list.add(scanner.nextLine());
        }

        //초기 사이즈 설정
        int size = list.size()+1;
        mm = new int[size][size];
        r = new int[size];
        s = new int[size][size];

        //행렬 크기 저장
        for(int i = 0; i < size-1; i++){
            String str = list.get(i);
            String[] split = str.split(",");

            System.out.println("A"+(i+1)+" = "+ split[0] +" X "+ split[1]);

            r[i] = Integer.parseInt(split[0]);
            if(i == size-1-1){
                r[i+1] = Integer.parseInt(split[1]);
            }
        }

        //print_int_arr1(r);
        mm();

        System.out.println("-------------------------------------------------------------");
        print_matrix(mm);
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        print_matrix(s);
        System.out.println("-------------------------------------------------------------");
        System.out.print("Optimal solution : "+mm[1][size-1]+"\n");
        System.out.print("Optimal parens : "); print_optimal_parens(1, size-1);
    }

    public static void print_optimal_parens(int i, int j){
        if(i == j){
            System.out.print("A"+i);
        }
        else{
            System.out.print("(");
            print_optimal_parens(i, s[i][j]);
            print_optimal_parens(s[i][j]+1, j);
            System.out.print(")");
        }
    }

    public static void mm(){
        int n = r.length;

        for(int i = 1; i < n; i++){
            mm[i][i] = 0;
        }

        for(int l = 2; l < n; l++){
            for(int i = 1; i < n-l+1; i++){
                int j = i+l-1;
                mm[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++){
                    int q = mm[i][k] + mm[k+1][j] + r[i-1]*r[k]*r[j];

                    if(q < mm[i][j]){
                        mm[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public static void print_int_arr1(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void print_matrix(int[][] a){
        for(int i = 1; i < a.length; i++){
            for(int j = 1; j < a[i].length; j++){
                System.out.printf("%8d", a[i][j]);
            }
            System.out.println();
        }
    }
}
