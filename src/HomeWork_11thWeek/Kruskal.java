package HomeWork_11thWeek;

import java.util.*;

public class Kruskal {
    public static void main(String[] args){
        String[] relation_arr = {
                "a,b,4",
                "a,h,8",
                "b,c,8",
                "b,h,11",
                "c,d,7",
                "c,f,4",
                "c,i,2",
                "d,e,9",
                "d,f,14",
                "e,f,10",
                "f,g,2",
                "g,h,1",
                "g,i,6",
                "h,i,7"
        };
        kruskal_solution ks = new kruskal_solution();
        ks.solution("a,b,c,d,e,f,g,h,i", relation_arr);

    }
}

class kruskal_solution{

    LinkedList<LinkedList<Vertex>> path;
    HashSet<Integer> set;
    int[] included;

    class Vertex implements Comparable<Vertex>{
        int start;
        int end;
        int weight;

        public Vertex(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public String toString(){
            return this.start+" - "+this.end+" : "+this.weight;
        }

        @Override
        public int compareTo(Vertex v) {
            if(this.weight > v.weight) return 1;
            else if(this.weight < v.weight) return -1;
            else return 0;
        }
    }

    public void solution(String vertexs, String[] relation){
        String[] index = vertexs.split(",");
        int length = index.length;

        path = new LinkedList<>();
        set = new HashSet<>();
        included = new int[length+1];

        LinkedList<Vertex> list = new LinkedList<>();
        for(int i = 0; i < relation.length; i++){
            String[] str = relation[i].split(",");
            list.add(new Vertex(askii(str[0]), askii(str[1]), Integer.parseInt(str[2])));
        }
        Collections.sort(list);

        for(int i = 1; i <= length; i++){
            included[i] = i;
        }

        System.out.println("list : "+list);

        HashSet<Vertex> result = new HashSet<>();
        for(int i = 0; i < list.size(); i++){
            Vertex v = list.get(i);

            if(find(v.start) != find(v.end)){
                set.add(v.start);
                result.add(v);
                included[find(v.end)] = find(v.start);//union
            }

        }
        System.out.println(result);

        int total = 0;
        Iterator it = result.iterator();
        while(it.hasNext()){
            Vertex v = (Vertex)it.next();
            total += v.weight;
        }
        System.out.println("총합 : "+total);
    }

    public int find(int u){
        if(included[u] == u){
            return u;
        }
        return included[u] = find(included[u]);
    }

    public int askii(String str){ //a기준으로 정수만들기
        int output = str.charAt(0) - 97;
        return output;
    }


}
