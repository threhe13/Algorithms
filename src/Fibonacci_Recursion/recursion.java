package Fibonacci_Recursion;

public class recursion {
    public static void main(String[] args){
        int n = 50;
        long start;
        long end;
        for(int i = 0; i <= n; i++){
            start = System.nanoTime();
            //System.out.print("f("+i+") : "+fibonacci_bottomUp(i)+"    ");
            fibonacci_general(i);
            end = System.nanoTime();
            System.out.printf("%.8f\n", (end-start)/1000000000.0);
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
}
