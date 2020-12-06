package HomeWork_4thWeek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("원하는 방법을 선택해주세요.");
        System.out.println("방법\n1. Recursive\n2. Bottom-Up\n3. Recursive Squaring");
        int method = Integer.parseInt(br.readLine());
        System.out.println("원하는 N의 값을 입력해주세요.");
        int n = Integer.parseInt(br.readLine());

        long start;
        long end;

        if(method == 1){
            for(int i = 0; i <= n; i++){
                start = System.nanoTime();
                long result = fibonacci_general(i);
                end = System.nanoTime();
                System.out.print("f("+i+") : "+result+"    ");
                System.out.printf("%.8f  sec\n", (end-start)/1000000000.0);

                if(i%10 == 0 && i != 0){
                    System.out.println("----------------------------------");
                }
            }
        }
        else if(method == 2){
            for(int i = 0; i <= n; i++){
                start = System.nanoTime();
                long result = fibonacci_bottomUp(i);
                end = System.nanoTime();
                System.out.print("f("+i+") : "+result+"    ");
                System.out.printf("%.8f  sec\n", (end-start)/1000000000.0);

                if(i%10 == 0 && i != 0){
                    System.out.println("----------------------------------");
                }
            }
        }
        else if(method == 3){
            for(int i = 0; i <= n; i++){
                start = System.nanoTime();
                long result = fib(i);
                end = System.nanoTime();
                System.out.print("f("+i+") : "+result+"    ");
                System.out.printf("%.8f  sec\n", (end-start)/1000000000.0);


                if(i%10 == 0 && i != 0){
                    System.out.println("----------------------------------");
                }
            }
        }
        else{
            System.out.println("프로그램을 다시 실행해주세요.");
        }

    }

    public static long fibonacci_general(int n){
        long answer = 0;

        if(n == 0){
            answer = 0;
        }
        else if(n == 1){
            answer = 1;
        }
        else if(n >= 2){
            answer = fibonacci_general(n-1)+ fibonacci_general(n-2);
        }

        return answer;
    }

    public static long fibonacci_bottomUp(int n){
        if(n < 1){
            return 0;
        }

        long[] sequence = new long[n+1];
        sequence[0] = 0;
        sequence[1] = 1;
        for(int i = 2; i <= n; i++){
            sequence[i] = sequence[i-1] + sequence[i-2];
        }
        return sequence[n];
    }

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
}
