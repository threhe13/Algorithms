package week11_submit;

/*
Node
a,b,c,d,e,f,g,h,i
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Prim_revise {
    public static void main(String[] args) throws IOException {

        String path = Prim_revise.class.getResource("").getPath();
        File file = new File(path+"data11_mst.txt");
        List<String> input = Files.readAllLines(file.toPath(), Charset.defaultCharset());

        String make_arr = input.get(0);
        String[] relation_arr = new String[input.size()-1];
        for(int i = 1; i < input.size(); i++){
            relation_arr[i-1] = input.get(i);
        }
        /*
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

         */
        Prim_Solution ps = new Prim_Solution(make_arr, relation_arr);
        ps.solution();

    }
}

class Prim_Solution{
    private String make;
    private String[] relation;

    private int length;
    int total;
    HashSet<Integer> set;

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

        int[] key = new int[length];
        for(int i = 1; i < length; i++){
            key[i]= Integer.MAX_VALUE;
        }
        int[] parent = new int[length];
        set = new HashSet<>();

        PriorityQueue<Vertex> que = new PriorityQueue<>();
        que.offer(graph.get(0).get(0));
        key[graph.get(0).get(0).end] = graph.get(0).get(0).weight;

        /*
        que.offer(new Vertex(0, 0, 0);
         */

        System.out.println("initial queue : "+ que);
        System.out.println("graph list : "+graph);
        System.out.println("\nPrim's Algorithm");

        while(!que.isEmpty()){
            Vertex u = que.poll();
            set.add(u.start);

            System.out.println("S["+u.end+"]");
            System.out.println("--------------------------------------------");
            for(Vertex v : graph.get(u.end)){
                System.out.print(v.start+" - "+v.end+" : "+key[v.end]);
                if(v.weight < key[v.end] && !set.contains(v.end)){
                    key[v.end] = v.weight;
                    parent[v.end] = v.start;
                    System.out.print(" => "+key[v.end]);
                    que.offer(v);
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("\nPrim MST");
        for(int i = 1; i < parent.length; i++){
            System.out.println((char)(parent[i]+97)+" - "+(char)(i+97)+" : "+key[i]);
            total += key[i];
        }
        System.out.println("총합 : "+total);
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
