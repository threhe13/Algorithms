package HomeWork2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class check {
    static ArrayList<id> list_id;
    static ArrayList<Integer> list_key;
    static HashMap<Integer, Integer> map_numbering;
    static HashSet<Integer> set_include;
    static ArrayList<id> result_id;
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

        String[] array = input.split(", ");
        list_id = new ArrayList<>();
        list_key = new ArrayList<>();
        map_numbering = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            String[] check = array[i].split(" ");
            int x = Integer.parseInt(check[0]);
            int y = Integer.parseInt(check[1]);

            list_id.add(new id(x, y));

            if(map_numbering.containsKey(x)){
                map_numbering.put(x, map_numbering.get(x)+1);
            }
            else{
                map_numbering.put(x, 1);
            }
        }

        Collections.sort(list_id);//tim sort\
        for(int i = 0;i < list_id.size(); i++){
            list_key.add(list_id.get(i).x);

        }
        //System.out.println(list_key);
        //debugging
        //System.out.println(list_id);

        for(int i = 0; i < list_id.size(); i++){
            ArrayList<id> new_id = new ArrayList<>();
            HashSet<Integer> new_set = new HashSet<>();
            new_id.add(list_id.get(i));
            new_set.add(list_id.get(i).x);
            dfs(new_id, i, new_set);
        }
    }

    public static void dfs(ArrayList<id> list, int current, HashSet<Integer> new_set){
        int location = list_key.indexOf(list_id.get(current).y);//현재 위치(i, list_id)의 y값의 위치
        //System.out.println(location);

        if(location < 0){
            return;
        }

        for (int j = 0; j < map_numbering.get(list_id.get(current).y); j++, location++) {
            result_id = list;
            set_include = new_set;

            if(!list_key.contains(list_id.get(location).y)){
                continue;
            }else {
                result_id.add(list_id.get(location));
                set_include.add(list_id.get(location).y);

                if(set_include.contains(list_id.get(location))){
                    continue;
                }

                if (result_id.get(0).x == result_id.get(result_id.size() - 1).y) {
                    System.out.println(result_id);
                    continue;
                }

                if(result_id.size() > list_id.size()){
                    break;
                }

                dfs(result_id, location, set_include);

            }
            //System.out.println(temp);
        }

        /*

        for(int i = 0; i < list_id.size(); i++){
            if(list_id.get(current).y == list_id.get(i).x){
                result_id.add(list_id.get(i));

                if(result_id.get(0).x == result_id.get(result_id.size()-1).y) {
                    System.out.println(result_id);
                    result_id = new ArrayList<>(id);
                }

                if(!list_key.contains(result_id.get(result_id.size()-1).y)){
                    result_id = new ArrayList<>(id);
                }

                System.out.println(result_id);
                dfs(result_id, i);

            }

        }

         */
    }

    static class id implements Comparable<id>{
        int x;
        int y;
        public id(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return "("+this.x+", "+this.y+")";
        }

        @Override
        public int compareTo(id id) {
            if(this.x > id.x){
                return 1;
            }
            else if(this.x == id.x){
                if(this.y > id.y){
                    return 1;
                }
                else if(this.y < id.y){
                    return -1;
                }
                else return 0;
            }
            else return -1;
        }
    }
}
