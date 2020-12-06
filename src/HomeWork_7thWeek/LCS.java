package HomeWork_7thWeek;

/*
input data
7
ABCBDAB
6
BDCABA
 */

public class LCS {
    static int X_length = 7;
    static String X = "ABCBDAB";
    static int Y_length = 6;
    static String Y = "BDCABA";

    static int[][] lcs;
    static int[][] print;

    public static void main(String[] args) {
        LCS_length();

        print_int_arr(lcs);

        //System.out.println("\n");

        StringBuilder sb = new StringBuilder("");
        print_LCS(sb, X_length, Y_length);

        //print_int_arr(print);

        System.out.println(sb.toString());
    }

    public static void print_LCS(StringBuilder sb, int i, int j){
        if(i == 0 || j == 0){
            return;
        }

        if(print[i][j] == 1){
            print_LCS(sb, i-1, j-1);
            sb.append(Y.charAt(j-1));
        }
        else if(print[i][j] == 2){
            print_LCS(sb, i-1, j);
        }
        else{
            print_LCS(sb, i, j-1);
        }
    }

    public static void LCS_length() {
        lcs = new int[X_length+1][Y_length+1];
        print = new int[X_length+1][Y_length+1];

        for (int i = 1; i <= X_length; i++) {
            lcs[i][0] = 0;
        }

        for (int i = 1; i <= Y_length; i++) {
            lcs[0][i] = 0;
        }

        for (int i = 1; i <= X_length; i++) {
            for (int j = 1; j <= Y_length; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    print[i][j] = 1;
                } else if (lcs[i - 1][j] >= lcs[i][j - 1]) {
                    lcs[i][j] = lcs[i - 1][j];
                    print[i][j] = 2;
                } else {
                    lcs[i][j] = lcs[i][j - 1];
                    print[i][j] = 3;
                }
            }
        }
    }

    public static void print_int_arr(int[][] A){
        int length = A.length;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < A[i].length; j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
    }
}
