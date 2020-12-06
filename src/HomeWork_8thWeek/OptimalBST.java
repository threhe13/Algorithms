package HomeWork_8thWeek;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OptimalBST {
    static int[] node;
    static double[] probability;

    static double[][] e;
    static double[][] w;
    static int[][] root;

    public static void main(String[] args) throws FileNotFoundException {
        String path = OptimalBST.class.getResource("").getPath();
        File file = new File(path+"data08_optimal_bst.txt");

        Scanner scanner = new Scanner(file);

        //배열 사이즈 입력
        int size = Integer.parseInt(scanner.nextLine());

        //노드 입력
        String input_node = scanner.nextLine();
        String[] split_node = input_node.split(" ");

        String input_probability = scanner.nextLine();
        String[] split_probability = input_probability.split(" ");

        node = new int[size+1];
        probability = new double[size+1];
        //루트 결정 배열
        root = new int[size+2][size+2];
        //확률 가중합산 배열
        e = new double[size+2][size+1];
        //확률 합산 배열
        w = new double[size+2][size+1];

        for(int i = 0; i < size; i++){
            node[i+1] = Integer.parseInt(split_node[i]);
            probability[i+1] = Double.parseDouble(split_probability[i]);
        }

        optimal_bst(size);
        System.out.println("root 배열");
        print_root(size);
        enter();
        System.out.println("확률 가중합산 배열");
        print_double_arr(e);
        enter();
        System.out.println("확률 합산 배열");
        print_double_arr(w);
        enter();
        System.out.println("-------print structure-------");
        //root[1][size] 배열에 저장되어 있는 루트를 depth 0으로 잡고 출발
        construct_optimal_bst(root, 1, size, 0);
        System.out.println("-----------------------------");
    }

    public static void optimal_bst(int n){
        for(int i = 1; i <= n+1; i++){
            e[i][i-1] = probability[i-1];
            w[i][i-1] = probability[i-1];
        }
        for(int l = 1; l <= n; l++){
            for(int i = 1; i <= n-l+1; i++){
                int j = i+l-1;
                e[i][j] = Float.MAX_VALUE;
                w[i][j] = w[i][j-1] + probability[j];
                for(int r = i; r <= j; r++){
                    double t = e[i][r -1]+e[r+1][j]+w[i][j];
                    if(t < e[i][j]){
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }

        }
    }

    private static void print_root(int size){
        System.out.println("   1 2 3 4");
        System.out.println("-----------");
        for(int i = 1; i <= size; i++){
            System.out.print(i+"| ");
            for(int j = 1; j <= size; j++) {
                System.out.print(root[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void print_double_arr(double[][] a){
        for(int i = 1 ; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.printf("%.3f  ", a[i][j]);
            }
            System.out.println();
        }
    }

    private static void enter(){
        System.out.println();
    }

    private static void construct_optimal_bst(int[][] root, int i, int j, int depth){
        if(root[i][j] == 0){
            return;
        }
        if(depth == 0){
            System.out.print(root[i][j] + " is the root");
            enter();
        }
        else if(j < depth){
            System.out.print(root[i][j] + " is the left child of "+depth);
            enter();
        }
        else{
            System.out.print(root[i][j]+" is the right child of "+depth);
            enter();
        }

        construct_optimal_bst(root, i, root[i][j]-1, root[i][j]);
        construct_optimal_bst(root, root[i][j]+1, j, root[i][j]);
    }
}