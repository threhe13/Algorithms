package HomeWork_10thWeek;
/*
* K = 4
* Cache = ABCD
* Requests = BCAFGCABFD
* */

import java.util.Arrays;
import java.util.HashSet;

public class FIF {

    public static void main(String[] args){
        int k = 4;
        String cache = "ABCD";
        String requests = "BCAFGCABFD";

        schedule(k, cache, requests);

    }

    static void schedule(int k, String cache, String requests){

        String[] cache_arr = cache.split("");
        String[] request_arr = requests.split("");

        int miss_point = -1;
        int return_point = -1;
        for(int i = 0; i < request_arr.length; i++){ //문자열 차례대로 스케줄 시작
            System.out.println("Schedule "+(i+1));

            int check = check(cache_arr, request_arr[i]);
            String save = "";
            if(check == -1){ //memory miss가 일어날 경우
                int future = fif(k, cache_arr, request_arr, i);// 가장 먼 거리에 존재하는 value ex. D
                save = cache_arr[future];
                cache_arr[future] = request_arr[i];//값을 새로 지정
            }
            //memory hit인 경우 생략

            /*
            //cache miss가 일어나기 직전 포인트
            if(return_point == i-1){
                cache_arr[return_point] = request_arr[i+1];
                return_point = -1;//다시 원래 값으로
            }*/

            System.out.print("Cache = ");print_arr(cache_arr);
            if(check == -1){
                System.out.println("Evict = "+save);
            }
        }

    }

    static int fif(int k, String[] cache, String[] requests, int j){
        int index = -1;
        boolean[] isCheck = new boolean[k];
        //ystem.out.println(Arrays.toString(isCheck));
        int checkCount = 0;

        for(int i = j+1; i < requests.length; i++){
            int check = check(cache, requests[i]);

            if(check != -1){//겹치는 게 있는 경우
                if(isCheck[check] == false){
                    isCheck[check] = true;
                    checkCount++;
                }
            }

            if(checkCount == k-1){
                break;
            }
        }

        for(int i = 0; i < k; i++){
            if(isCheck[i] == false){
                return i;
            }
        }
        /*
        for(int i = 0; i < k; i++){
            System.out.print(isCheck[i]+" ");
        }
        System.out.println();
         */

        return index;
    }

    static int check(String[] cache, String str){
        for(int i = 0; i < cache.length; i++){
            String temp = cache[i];
            if(str.equals(temp)){
                return i; //값이 존재하면 해당 인덱스를 반환
            }
        }

        return -1; //값이 존재하지 않으면 -1을 반환
    }

    static void print_arr(String[] str){
        for(int i = 0; i < str.length; i++){
            System.out.print(str[i]);
        }
        System.out.println();
    }

    static void enter(){
        System.out.println();
    }
}
