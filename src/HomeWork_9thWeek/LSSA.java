package HomeWork_9thWeek;

public class LSSA {

    static String x;
    static String y;

    static int[][] costs;
    static int[][] path;
    static int[][] solution;

    public static void main(){
        x = "GACT";
        y = "GAACG";


    }

    static void solution(int x_start, int y_start, int x_end, int y_end){
        int xl = x.length();
        int yl = y.length();

        if(xl < 2 || yl < 2){
            optimal_solution(x_start, y_start, x_end, y_end);
        }



    }

    static void optimal_solution(int x_start, int y_start, int x_end, int y_end){
        String x_sub = x.substring(x_start-1, x_end-1);
        String y_sub = y.substring(y_start-1, y_end-1);

        int xl = x_sub.length();
        int yl = y_sub.length();
        int[][] a = new int[xl+1][yl+1];

        int q = -2; //mismatch penalty

        for(int i = 0; i <= xl; i++){
            a[i][0] = i*q;
        }
        for(int i = 0; i <= yl; i++){
            a[0][i] = i*q;
        }

        for(int i = 1; i <= xl; i++){
            for(int j = 1; j <= yl; j++){
                int max = Math.max(a[i][j-1]+q, Math.max(a[i-1][j-1]+check(i,j), a[i-1][j]+q));
                //print(max);

                a[i][j] = max;

                if(max == a[i-1][j-1]+check(i,j)){
                    path[i][j] = 1;
                }else if(max == a[i-1][j]+q){
                    path[i][j] = 2;
                }else if(max == a[i][j-1]+q){
                    path[i][j] = 3;
                }
            }
        }
    }

    static int check(int i, int j){
        if(x.charAt(i-1) == y.charAt(j-1)){
            return 1;
        }
        else {
            return -1;
        }
    }
}
