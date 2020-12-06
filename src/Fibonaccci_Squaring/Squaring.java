package Fibonaccci_Squaring;

public class Squaring {

    //power
    public static void POW(long[][] A, int n){//n 은 제곱(n-1임)
        if(n < 2){
            return;
        }

        long[][] B = {
                {1, 1},
                {1, 0}
        };


        if(n%2 == 0){
            for(int i = 1; i < n/2; i++){
                square_multiply(A, B);
            }
        }
        else{
            for(int i = 1; i < (n-1)/2; i++){
                square_multiply(A, B);
            }
        }

        square_multiply(A, A);
        if(n%2 != 0){
            square_multiply(A, B);
        }
    }

    //multiplication
    public static void square_multiply(long[][] A, long[][] B){

        long a = A[0][0]*B[0][0] + A[0][1]*B[1][0];
        long b = A[0][0]*B[0][1] + A[0][1]*B[1][1];
        long c = A[1][0]*B[0][0] + A[1][1]*B[1][0];
        long d = A[1][0]*B[0][1] + A[1][1]*B[1][1];

        A[0][0] = a;
        A[0][1] = b;
        A[1][0] = c;
        A[1][1] = d;
    }

    //fibonacci
    public static long fib(int n){
        long[][] A = {
                {1, 1},
                {1, 0}
        };

        if(n == 0){
            return 0;
        }

        POW(A, n-1);

        return A[0][0];
    }

    public static void main(String[] args){
        int n = 50;
        long start;
        long end;
        for(int i = 0; i <= n; i++){
            start = System.nanoTime();
            //System.out.print("f("+i+") : "+fibonacci_bottomUp(i)+"    ");
            fib(i);
            end = System.nanoTime();
            System.out.printf("%.8f\n", (end-start)/1000000000.0);
        }
    }
}
