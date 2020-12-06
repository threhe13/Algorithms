package HomeWork_9thWeek;

import java.util.Arrays;

public class LinearSpaceSequenceAlignment {
    static String x;
    static String y;
    static int[][] a;
    static int[][] path;
    public static void main(String[] args){
        x = "tcagaagtacc";
        y = "tcatagttaaca";

        //x = "AGGGCT";
        //y = "AGGCA";

        //x = "GACT";
        //y = "GAACG";

        //x = "actgagttaa";
        //y = "acagaagta";

        //x = "acgtactg";
        //y = "agtctg";

        solution();

        print_int_arr(a);
        enter();
        //print_int_arr(path);
        enter();

        StringBuilder xsb = new StringBuilder("");
        StringBuilder ysb = new StringBuilder("");

        print_sequence(x.length(), y.length(), xsb, ysb);

        //System.out.println(xsb.toString());
        //System.out.println(ysb.toString());

        String[] str_x = xsb.toString().split(" ");
        String[] str_y = ysb.toString().split(" ");

        xsb = new StringBuilder(x.charAt(Integer.parseInt(str_x[0])));
        ysb = new StringBuilder(y.charAt(Integer.parseInt(str_y[0])));

        for(int i = 1; i < str_x.length; i++){
            if(str_x[i-1].equals(str_x[i])){
                xsb.append("-");
            }
            else{
                xsb.append(x.charAt(Integer.parseInt(str_x[i])-1));
            }
        }

        for(int j = 1; j < str_y.length; j++){
            if(str_y[j-1].equals(str_y[j])){
                ysb.append("-");
            }
            else{
                ysb.append(y.charAt(Integer.parseInt(str_y[j])-1));
            }
        }

        System.out.println("result x : "+xsb.toString());
        System.out.println("result y : "+ysb.toString());
    }

    /*
    경로
    중복 : 0
    대각선 : 1
    위쪽: 2
    왼쪽 : 3
     */

    static void solution(){
        int xl = x.length();
        int yl = y.length();
        a = new int[xl+1][yl+1];
        path = new int[xl+1][yl+1];

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

    static void print_sequence(int i, int j, StringBuilder xsb, StringBuilder ysb){
        //System.out.println(temp);

        if(i < 0){
            while(j >= 0){
                ysb.append(j+" ");
                j--;
            }
            return;
        }else if(j < 0){
            while(i >= 0){
                xsb.append(i+" ");
                i--;
            }
            return;
        }

        if(path[i][j] == 1){
            print_sequence(i-1, j-1, xsb, ysb);
        }
        else if(path[i][j] == 2){
            print_sequence(i-1, j, xsb, ysb);
        }
        else if(path[i][j] == 3){
            print_sequence(i, j-1, xsb, ysb);
        }

        int x_char = i;
        int y_char = j;


        xsb.append(x_char+" ");
        ysb.append(y_char+" ");
        //System.out.println(xsb.toString());
        //System.out.println(ysb.toString());
    }

    static int check(int i, int j){
        if(x.charAt(i-1) == y.charAt(j-1)){
            return 1;
        }
        else {
            return -1;
        }
    }

    static void print_int_arr(int[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }
    }

    static void print(int[] a){
        for(int i = 0; i< a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    static void enter(){
        System.out.println();
    }
}
