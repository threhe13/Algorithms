package HomeWork_4thWeek;

public class PoweringNumber {
    public static void main(String[] args){
        int a = 2;
        int n = 10;

        int lgN = Powering_Number(a, n);
        int N = normal_method(a, n);

        System.out.println(lgN+" "+N);
    }

    public static int Powering_Number(int a, int n){
        int answer = 1;

        if(n == 0){
            return 1;
        }
        else if(n == 1){
            return a;
        }
        else if(n%2 != 0){
            for(int i = 0; i < (n-1)/2; i++){
                answer *= a;
            }

            answer = answer*answer*a;
        }
        else if(n%2 == 0){
            for(int i = 0; i < n/2; i++){
                answer *= a;
            }

            answer = answer*answer;
        }

        return answer;
    }

    public static int normal_method(int a, int n){
        int answer = 1;
        for(int i = 0; i < n; i++){
            answer *= a;
        }
        return answer;
    }
}