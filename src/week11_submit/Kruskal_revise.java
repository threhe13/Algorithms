package week11_submit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class Kruskal_revise {
    public static void main(String[] args) throws IOException {
        String path = Kruskal_revise.class.getResource("").getPath();
        File file = new File(path+"data11_mst.txt");
        List<String> input = Files.readAllLines(file.toPath(), Charset.defaultCharset());

        String vertexs = input.get(0);
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
        kruskal_solution ks = new kruskal_solution();
        ks.solution(vertexs, relation_arr);

    }
}

class kruskal_solution{

    LinkedList<LinkedList<Vertex>> path;
    LinkedList<HashSet<Integer>> set;
    LinkedList<HashSet<Integer>> set_end;
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
        set = new LinkedList<>();
        set_end = new LinkedList<>();
        included = new int[length];

        LinkedList<Vertex> list = new LinkedList<>();
        for(int i = 0; i < relation.length; i++){
            String[] str = relation[i].split(",");
            list.add(new Vertex(askii(str[0]), askii(str[1]), Integer.parseInt(str[2])));
        }
        Collections.sort(list);

        for(int i = 0; i < length; i++){
            included[i] = i;
        }
        System.out.println("list : "+list);

        for(int i = 0; i < list.size(); i++){
            path.add(new LinkedList<>());
            path.get(i).add(list.get(i));

            set.add(new HashSet<>());
            set_end.add(new HashSet<>());
            set.get(i).add(list.get(i).start);
            set_end.get(i).add(list.get(i).end);
            //set.get(i).add(list.get(i).end);
        }

        HashSet<Vertex> result = new HashSet<>();

        for(int i = 0; i < list.size(); i++){
            Vertex vertex = list.get(i);

            if(find(vertex.start) != find(vertex.end)){
                result.add(vertex);
                union(find(vertex.start), find(vertex.end));
            }

            System.out.println("S["+(i+1)+"] : "+result);
        }

        //System.out.println(path);

        System.out.println("result : "+result);

        System.out.println("\nKruskal MST");
        int total = 0;
        Iterator it = result.iterator();
        while(it.hasNext()){
            Vertex v = (Vertex)it.next();
            System.out.println((char)(v.start+97)+" - "+(char)(v.end+97)+" : "+v.weight);
            total += v.weight;
        }
        System.out.println("총합 : "+total);
    }

    public void union(int u, int v){
        if(path.get(u).size() < path.get(v).size()){
            for(int i = 0; i < path.get(u).size(); i++){
                path.get(v).add(path.get(u).get(i));
            }

            path.remove(u);

            set.get(v).addAll(set.remove(u));
            set_end.get(v).addAll(set_end.remove(u));

            included[v] = u;
        }
        else{
            for(int i = 0; i < path.get(v).size(); i++){
                path.get(u).add(path.get(v).get(i));
            }

            path.remove(v);

            set.get(u).addAll(set.remove(v));
            set_end.get(u).addAll(set_end.remove(v));

            included[u] = v;
        }
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
