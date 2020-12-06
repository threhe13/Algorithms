package Fibonacci_BottomUp;

public class bottomUp {
    public static void main(String[] args){
        int n = 50;

        long start;
        long end;
        for(int i = 0; i <= n; i++){
            start = System.nanoTime();
            //System.out.print("f("+i+") : "+fibonacci_bottomUp(i)+"    ");
            fibonacci_bottomUp(i);
            end = System.nanoTime();
            System.out.printf("%.8f\n", (end-start)/1000000000.0);
        }
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
}
