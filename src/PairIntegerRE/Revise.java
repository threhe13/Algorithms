package PairIntegerRE;

import java.util.*;

public class Revise {
    public static void main(String[] args){
        String input = "1 16, 2 21, 2 25, 2 22, 23 50, " +
                "23 47, 24 1, 25 10, 35 7, 36 45, 36 37, " +
                "38 42, 39 41, 12 37, 12 23, 12 3, 12 20, " +
                "14 25, 41 9, 42 3, 43 5, 43 22, 29 2, " +
                "30 48, 31 15, 32 17, 6 45, 6 1, 5 35, " +
                "5 20, 5 28, 5 11, 48 4, 48 10, 49 32, " +
                "7 31, 7 4, 5 33, 6 29, 6 12, 6 11, 6 3, " +
                "6 17, 45 27, 47 34, 48 20, 7 40, 7 34, " +
                "8 11, 9 19, 11 30, 11 4, 11 22, 11 25, " +
                "20 24, 21 23, 21 46, 22 47, 23 49, 3 39, " +
                "3 34, 4 14, 4 37, 5 42, 5 8, 15 2, 15 50, " +
                "15 4, 15 37, 16 13, 17 38, 18 28, 19 8, " +
                "26 15, 26 42, 27 18, 28 35, 13 36, 13 50, " +
                "13 34, 13 22, 29 34, 29 38, 29 30, 29 16, " +
                "44 33, 44 36, 44 7, 44 3, 44 32, 44 21, " +
                "33 9, 33 21, 33 35, 33 19, 33 41, 26 10, " +
                "26 44, 26 16, 26 39, 26 17";

        Solution s = new Solution();
        String[] output = s.solution(input);
        for(String str : output){
            System.out.println(str);
        }
    }
}
//BFS로 풀이!
class Solution{
    ArrayList<Integer> list_key = new ArrayList<Integer>();
    ArrayList<Integer> list_value = new ArrayList<Integer>();
    String[] answer;

    boolean[] isVisit;

    public String[] solution(String str){
        String[] pair_nonDivide = comma_split(str);
        System.out.println(pair_nonDivide.length);
        Divide(pair_nonDivide);

        HashMap<Integer, Integer> map_key_number = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> map_value_number = new HashMap<Integer, Integer>();
        for(int i = 0; i < list_key.size(); i++){
            int key = list_key.get(i);
            int value = list_value.get(i);


        }

        isVisit = new boolean[list_key.size()];

        for(int i = 0; i < list_key.size(); i++){
            DFS(i);
        }

        return answer;
    }

    public void DFS(int i){
        if(isVisit[i] == false) {
            Stack<Pair> stack = new Stack<>();

            stack.push(new Pair(list_key.get(i), list_value.get(i)));
            isVisit[i] = true;

            StringBuilder sb = new StringBuilder();

            while (!stack.isEmpty()) {
                Pair pair = stack.pop();


            }
        }
    }

    public String[] comma_split(String str){
        String[] convert = str.split(", ");
        return convert;
    }

    public void Divide(String[] pair_nonDivide) {
        for (int i = 0; i < pair_nonDivide.length; i++) {
            String[] temp = pair_nonDivide[i].split(" ");

            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);

            list_key.add(x);
            list_value.add(y);
        }
    }

    public StringBuilder makePair(int x, int y, StringBuilder sb){
        sb.append("("+x+", "+y+")");
        return sb;
    }

    class Pair{
        int x;
        int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return "("+this.x+", "+this.y+")";
        }
    }
}

