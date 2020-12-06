package HomeWork_4thWeek;

import java.io.*;
import java.util.StringTokenizer;

public class MatrixMultiplication {

    public static void main(String[] args) throws IOException {
        //test_case1
        /*
        long[][] A = {
                {13, -3, -25, 20},
                {-3, -16, -23, 18},
                {20, -7, 12, -5},
                {-22, 15, -4, 7}}; // n * n matrix
        long[][] B = {
                {13, 10, 11, 12},
                {13, 14, -23, 18},
                {20, -7, 12, -11},
                {-12, -13, -14, 7}};

         */
        long[][] A;
        long[][] B;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = st.countTokens();
        A = new long[n][n];

        for(int i = 0; i < n ; i++){
            if(i != 0){
                st = new StringTokenizer(br.readLine());
            }
            for(int j = 0; j < n; j++){
                A[i][j] = Long.parseLong(st.nextToken());
            }
        }

        B = new long[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                B[i][j] = Long.parseLong(st.nextToken());
            }
        }

        double time = System.nanoTime();
        long[][] result = Divide_Conquer(A, B, A.length);
        time = System.nanoTime() - time;
        //rint_matrix(result);
        System.out.println("\n"+time/1000000000.0+"sec");

        time = System.nanoTime();
        long[][] result2 = Strassen(A, B, A.length);
        time = System.nanoTime() - time;
        //print_matrix(result2);
        System.out.println("\n"+time/1000000000.0+"sec");

    }

    public static long[][] Divide_Conquer(long[][] A, long[][] B, int n){
        long[][] matrix = new long[n][n];

        if(n == 1){

            matrix[n-1][n-1] = A[n-1][n-1]*B[n-1][n-1];

        }
        else{

            int x = n/2;//divide to twice

            //Matrix A
            long[][] A11 = divide(A, 0, 0);
            //print_matrix(A11); for debugging
            long[][] A12 = divide(A, 0, x);
            long[][] A21 = divide(A, x, 0);
            long[][] A22 = divide(A, x, x);

            //Matrix B
            long[][] B11 = divide(B, 0, 0);
            long[][] B12 = divide(B, 0, x);
            long[][] B21 = divide(B, x, 0);
            long[][] B22 = divide(B, x, x);

            //Addition
            Add(matrix, Divide_Conquer(A11, B11, x), Divide_Conquer(A12, B21, x), 0, 0);
            Add(matrix, Divide_Conquer(A11, B12, x), Divide_Conquer(A12, B22, x), 0, x);
            Add(matrix, Divide_Conquer(A21, B11, x), Divide_Conquer(A22, B21, x), x, 0);
            Add(matrix, Divide_Conquer(A21, B12, x), Divide_Conquer(A22, B22, x), x, x);
        }

        return matrix;
    }

    public static long[][] Strassen(long[][] A, long[][] B, int n) {
        long[][] matrix = new long[n][n];

        if (n == 1) {

            matrix[n - 1][n - 1] = A[n - 1][n - 1] * B[n - 1][n - 1];

        } else {

            int x = n / 2;//divide to twice

            //Matrix A
            long[][] A11 = divide(A, 0, 0);
            //print_matrix(A11); for debugging
            long[][] A12 = divide(A, 0, x);
            long[][] A21 = divide(A, x, 0);
            long[][] A22 = divide(A, x, x);

            //Matrix B
            long[][] B11 = divide(B, 0, 0);
            long[][] B12 = divide(B, 0, x);
            long[][] B21 = divide(B, x, 0);
            long[][] B22 = divide(B, x, x);

            //P1 ~ P7
            long[][] P1 = Strassen(A11, sub(B12, B22), x);
            long[][] P2 = Strassen(add(A11, A12), B22, x);
            long[][] P3 = Strassen(add(A21, A22), B11, x);
            long[][] P4 = Strassen(A22, sub(B21, B11), x);
            long[][] P5 = Strassen(add(A11, A22), add(B11, B22), x);
            long[][] P6 = Strassen(sub(A12, A22), add(B21, B22), x);
            long[][] P7 = Strassen(sub(A11, A21), add(B11, B12), x);

            //r, s, t, u
            long[][] r = add(sub(add(P5, P4), P2), P6);
            long[][] s = add(P1, P2);
            long[][] t = add(P3, P4);
            long[][] u = sub(sub(add(P5, P1), P3), P7);

            makeC(matrix, r, 0, 0);
            makeC(matrix, s, 0, x);
            makeC(matrix, t, x, 0);
            makeC(matrix, u, x, x);
        }

        return matrix;
    }

    public static void print_matrix(long[][] A){
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[A.length-1].length; j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static long[][] divide(long[][] A, int a, int b){
        int n = A.length/2;
        long[][] matrix = new long[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = A[i+a][j+b];
            }
        }

        return matrix;
    }

    public static void Add(long[][] matrix, long[][] A, long[][] B, int n, int m){
        int size = A.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                matrix[i+n][j+m] = A[i][j]+B[i][j];
            }
        }
    }

    public static long[][] add(long[][] A, long[][] B){
        int size = A.length;
        long[][] temp = new long[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                temp[i][j] = A[i][j] + B[i][j];
            }
        }

        return temp;
    }

    public static long[][] sub(long[][] A, long[][] B){
        int size = A.length;
        long[][] temp = new long[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                temp[i][j] = A[i][j] - B[i][j];
            }
        }

        return temp;
    }

    public static void makeC(long[][] matrix, long[][] temp, int n, int m){
        int size = temp.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                matrix[i+n][j+m] = temp[i][j];
            }
        }
    }

}
