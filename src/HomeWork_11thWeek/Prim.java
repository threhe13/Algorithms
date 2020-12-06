package HomeWork_11thWeek;

/*
Node
a,b,c,d,e,f,g,h,i
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Prim {
    public static void main(String[] args){
        String path = Prim.class.getResource("").getPath();

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
        Prim_Solution ps = new Prim_Solution("a,b,c,d,e,f,g,h,i", relation_arr);
        ps.solution();

    }
}

class Prim_Solution{
    private String make;
    private String[] relation;

    private int length;
    int total;

    ArrayList<ArrayList<Vertex>> graph;

    public Prim_Solution(String make_arr, String[] relation_arr){
        this.make = make_arr;
        this.relation = relation_arr;
    }

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
            return start+" - "+end+" : "+weight;
        }

        @Override
        public int compareTo(Vertex v) {
            if(this.weight > v.weight) return 1;
            else if(this.weight < v.weight) return -1;
            else return 0;
        }
    }

    public void solution(){
        makeGraph();//그래프 생성

        ArrayList<Vertex> list = new ArrayList<>();

        PriorityQueue<Vertex> que = new PriorityQueue<>();
        for(int i = 0; i < graph.get(0).size(); i++){
            que.offer(graph.get(0).get(i));
        }
        System.out.println("initial queue : "+ que);

        HashSet<Integer> set = new HashSet<>();

        System.out.println(graph);


        System.out.println("\nPrim's Algorithm");
        while(!que.isEmpty()){
            Vertex v = que.poll();
            System.out.println(v.toString());

            if(set.contains(v.start) && set.contains(v.end)){
                continue;
            }
            set.add(v.start);

            for(int i = 0; i < graph.get(v.end).size(); i++){
                Vertex v_temp = graph.get(v.end).get(i);

                if(!set.contains(v_temp.end)){
                    que.offer(v_temp);
                }
            }

            set.add(v.end);
            list.add(v);
            total += v.weight;

            System.out.println("Q : "+que);
            System.out.println("List : "+list);

            //ystem.out.println(que);
        }
        System.out.println(list);
        System.out.println("total : "+total);

    }

    public String[] stringToArr(String str){
        String[] output = str.split(",");
        return output;
    }

    public int askii(String str){ //a기준으로 정수만들기
        int output = str.charAt(0) - 97;
        return output;
    }

    public void makeGraph(){
        String[] make_str = stringToArr(make);
        length = make_str.length;

        graph = new ArrayList<>();
        for(int i = 0; i < length; i++){ //vertex개수만큼 배열형태 생성
            graph.add(new ArrayList<>());
        }

        System.out.println("Graph 그리기");
        for(int i = 0; i < relation.length; i++){
            String[] str = stringToArr(relation[i]);
            int start = askii(str[0]);
            int end = askii(str[1]);
            int weight = Integer.parseInt(str[2]);

            Vertex u = new Vertex(start, end, weight);
            Vertex n = new Vertex(end, start, weight);

            graph.get(start).add(u);
            graph.get(end).add(n);

            System.out.println(u.toString()+" <=> "+n.toString());
        }
    }

    public void print_arr(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

}
