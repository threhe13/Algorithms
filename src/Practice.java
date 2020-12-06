import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Practice {

    public static void main(String[] args) throws IOException {

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


        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String input = br.readLine();

        Solution s = new Solution();
        String[] answer = s.solution(input);
        for(int i = 0; i < answer.length; i++){
            System.out.println(answer[i]);
        }
    }

}

class Solution{
    ArrayList<ID> list_id;
    ArrayList<Integer> key;
    HashMap<Integer, Integer> key_location;

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public String[]solution(String str){
        String[] answer;

        String[] divide = divide(str);
        key = new ArrayList<>();
        list_id = new ArrayList<>();
        key_location = new HashMap<>();

        StringTokenizer st;
        for(int i = 0; i < divide.length; i++){
            st = new StringTokenizer(divide[i]);

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list_id.add(new ID(x, y));
        }

        Collections.sort(list_id);
        for(int i = 0; i < list_id.size(); i++){
            int x = list_id.get(i).x;
            key.add(x);
            if(!key_location.containsKey(x)){
                key_location.put(x, i);
            }
        }

        for(int i = 0; i < list_id.size(); i++){
            ArrayList<Integer> input = new ArrayList<>();
            input.add(list_id.get(i).x);
            input.add(list_id.get(i).y);
            find_key(input);
        }

        answer = new String[result.size()];

        for(int i = 0; i < result.size(); i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < result.get(i).size()-1; j++){
                sb.append("("+result.get(i).get(j)+", "+result.get(i).get(j+1)+"), ");
            }
            answer[i] = sb.toString().substring(0, sb.toString().length()-2);
        }

        return answer;
    }

    public void find_key(ArrayList<Integer> list){
        int list_peek = list.get(list.size()-1);
        //System.out.println(list_peek);

        for(int i = 0; i < list_id.size(); i++){
            ArrayList<Integer> list_dummy = new ArrayList<>(list);
            if (list_id.get(i).x == list_peek) {
                //System.out.println(list_id.get(i));
                int temp = list_id.get(i).y;
                list_dummy.add(temp);
                if(temp == list.get(0)){
                    //System.out.println(list_dummy);
                    result.add(new ArrayList<>(list_dummy));
                    continue;
                }
                if(list_dummy.size() > list_id.size()){
                    break;
                }
                find_key(list_dummy);
            }
        }
    }

    public String[] divide(String str){
        String[] convert = str.split(", ");
        return convert;
    }

    class ID implements Comparable<ID>{
        int x; int y;

        public ID(int x, int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "("+this.x+", "+this.y+")";
        }

        @Override
        public int compareTo(ID id) {
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
