package PairIntegerNode;

import java.util.*;

public class usingNode {
    static HashSet<ProcessID> set = new HashSet<>();
    static HashMap<Integer, Integer> map_key_number = new HashMap<>();
    static ArrayList<ProcessID> list_id;

    public static void main(String[] args){
        /*
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        ArrayList<Integer> list = new ArrayList<>(stack);
        System.out.println(list);
         */

        String str = "1 16, 2 21, 2 25, 2 22, 23 50, " +
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

        list_id = new ArrayList<>();
        int max = Integer.MIN_VALUE;

        String[] nonDivide = str.split(", ");
        StringTokenizer st;
        for(int i = 0; i < nonDivide.length; i++){
            String temp = nonDivide[i];
            st = new StringTokenizer(temp);

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            System.out.println(x+" "+y);

            if(x > max){
                max = x;
            }

            if(y > max){
                max = y;
            }

            if(map_key_number.containsKey(x)){
                map_key_number.put(x, map_key_number.get(x)+1);
            }
            else{
                map_key_number.put(x, y);
            }

            list_id.add(new ProcessID(x, y));
        }

        System.out.println(max);

        //그래프 채워넣기
        int[][] Graph_input = new int[max][max];
        for(int i = 0; i < list_id.size(); i++){
            ProcessID id = list_id.get(i);

            Graph_input[id.next-1][id.pre-1] = 1;
        }

        //그래프 그리기
        for(int i = 0; i < max; i++){
            for(int j = 0; j < max; j++){
                System.out.print(Graph_input[i][j]);
            }
            System.out.println();
        }

        for(int i = 0; i < list_id.size(); i++){
            DFS(list_id.get(i));
        }
    }

    public static void DFS(ProcessID id){
        Stack<ProcessID> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        stack.push(id);

        while(!stack.isEmpty()){
            ProcessID temp = stack.pop();
            sb.append(temp);

            int count = 0;
            for(int i = 0; i < list_id.size(); i++){
                if(list_id.get(i).next == temp.pre) {
                    DFS(list_id.get(i));
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static boolean isPossible(ProcessID id){
        if(!set.contains(id)){
            return true;
        }
        return false;
    }

    static class ProcessID{
        int next;
        int pre;

        public ProcessID(int next, int pre){
            this.next = next;
            this.pre = pre;
        }

        public String toString(){
            return "("+this.next+", "+this.pre+")";
        }
    }
}
