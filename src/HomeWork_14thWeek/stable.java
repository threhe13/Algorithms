package HomeWork_14thWeek;

/*
Man1 Woman1 Woman2 Woman3
Man2 Woman1 Woman3 Woman2
Man3 Woman3 Woman2 Woman1
Woman1 Man2 Man1 Man3
Woman2 Man1 Man3 Man2
Woman3 Man3 Man1 Man2
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class stable {
    public static void main(String[] args) throws IOException {
        /*
        String[] input = {
            "Man1 Woman1 Woman2 Woman3",
            "Man2 Woman1 Woman3 Woman2",
            "Man3 Woman3 Woman2 Woman1",
            "Woman1 Man2 Man1 Man3",
            "Woman2 Man1 Man3 Man2",
            "Woman3 Man3 Man1 Man2"
        };*/

        String path = "./";
        List<String> list = Files.readAllLines(Paths.get(path+"data14.txt"));
        String[] input = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            input[i] = list.get(i);
        }

        stable_solution ss = new stable_solution();
        ss.solution(input);

    }
}

class stable_solution{

    int[][] man;
    int[][] woman;

    //save couple after stable matching algorithm
    int[] count;
    int[] result;
    int[] result_convert;

    public void solution(String[] input){
        int length = input.length;
        int people = length/2;
        //System.out.println(people);

        man = new int[people+1][people+1];
        woman = new int[people+1][people+1];

        for(int i = 0; i < length; i++) {
            String[] str = input[i].replaceAll("[a-zA-Z]", "").split(" ");
            int row = Integer.parseInt(str[0]);

            if (i < people) {//man
                //print(str); //for debugging
                int k = 1;
                for (int j = 1; j < str.length; j++) {
                    int column = Integer.parseInt(str[j]);
                    man[row][column] = k++;
                }
            }
            else {//woman
                //print(str); //for debugging
                int k = 1;
                for (int j = 1; j < str.length; j++) {
                    int column = Integer.parseInt(str[j]);
                    woman[row][column] = k++;
                }
            }
        }
        System.out.println("man preference array");
        print(man);
        System.out.println("woman preference array");
        print(woman);
        System.out.println();

        //for stable matching
        Queue<Integer> que = new LinkedList<>();
        result = new int[people+1];//initialization to zero
        result_convert = new int[people+1];
        count = new int[people+1];
        for(int i = 0; i < count.length; i++){
            count[i] = 1;//initialization to one
        }

        for(int i = 1; i <= people; i++){
            que.offer(i);
        }
        int plus = 1;
        while(!que.isEmpty()){
            System.out.println("process "+(plus++));
            int _man = que.poll();//1
            int count_temp = count[_man];//해당 남자의 순위 -> 기각 당한 적이 있는지 확인

            int wantedWoman = man[_man][count_temp];//남성이 고백하는 여성(1순위부터 순차적으로) //1

            if(result_convert[wantedWoman] == 0){//만약 좋아하는 여성이 커플이 아니라면
                result_convert[wantedWoman] = _man;
                result[_man] = wantedWoman;
                count[_man] += 1;
            }
            else{//만약 선호하는 여성이 커플이라면
                int competition = result_convert[wantedWoman];//경쟁하는 남자 번호 //1

                int cpt_score = woman[wantedWoman][competition];
                int man_score = woman[wantedWoman][_man];

                if(cpt_score > man_score){//순위가 같을 일이 없으니까 단순히 구분
                    System.out.println("break couple!");
                    result[competition] = 0;//disconnect couple
                    //기존 커플 깨짐ㅠ

                    result_convert[wantedWoman] = _man;
                    result[_man] = wantedWoman;

                    que.offer(competition);
                }
                else{
                    count[_man] += 1;
                    que.offer(_man);
                }

            }

            //for debugging
            System.out.print("man  : ");print(result);
            System.out.print("woman: ");print(result_convert);
            //print(count);
            System.out.println();
        }

        System.out.println();
        System.out.println("stable matching algorithm result");
        for(int i = 1; i < result.length; i++){
            System.out.println("man"+i+" is couple with woman"+result[i]);
        }
    }

    public void print(int[] str){
        for(int i = 1; i < str.length; i++){
            System.out.print(str[i]+" ");
        }
        System.out.println();
    }

    public void print(String[] str){
        for(int i = 0; i < str.length; i++){
            System.out.println(str[i]);
        }
    }

    public void print(int[][] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j < arr[i].length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

}
