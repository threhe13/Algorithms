package week11_submit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra_revise {
    private static int length;
    public static void main(String[] args) throws IOException {

        String path = Dijkstra_revise.class.getResource("").getPath();
        File file = new File(path+"data11_dijkstra.txt");
        List<String> input = Files.readAllLines(file.toPath(), Charset.defaultCharset());

        //System.out.println(input);

        String[] vertexs = input.get(0).split(",");
        length = vertexs.length;

        String[] relation = new String[input.size()-1];
        for(int i = 1; i < input.size(); i++){
            relation[i-1] = input.get(i);
        }

        /*
        String[] relation ={
                "1,2,10",
                "1,3,3",

                "2,3,1",
                "2,4,2",

                "3,2,4",
                "3,4,8",
                "3,5,2",

                "4,5,7",

                "5,4,9",
        };

         */

        Dijkstra_solution ds = new Dijkstra_solution();
        ds.dijkstra(length, relation);

    }
}

class Dijkstra_solution{
    ArrayList<ArrayList<Vertex>> list;
    int[] d;
    HashSet<Integer> set;

    public void dijkstra(int size, String[] relation){
        list = new ArrayList<>();
        d = new int[size+1];
        set = new HashSet<>();

        for(int i = 1; i < size+1; i++){
            d[i] = Integer.MAX_VALUE;
        }

        int point = 0;
        for(int i = 0; i < size; i++){
            list.add(new ArrayList<>());

            for(int j = point; j < relation.length; j++) {
                String[] str = relation[j].split(",");

                if(i+1 < Integer.parseInt(str[0])){
                    point = j;
                    break;
                }

                int name = Integer.parseInt(str[0]);
                int direct = Integer.parseInt(str[1]);
                int distance = Integer.parseInt(str[2]);
                list.get(i).add(new Vertex(name, direct, distance));
            }
        }

        //System.out.println(vertex.toString());//[1, 1, 2, 2, 3, 3, 3, 4, 5]
        //print();

        PriorityQueue<Vertex> que = new PriorityQueue<>();
        que.offer(new Vertex(1, 0, 0));
        d[1] = 0;

        System.out.println("Dijkstra's Algorithm으로 계산한 결과는 다음과 같습니다.");

        while(set.size() != size){
            Vertex vertex = que.poll();

            set.add(vertex.name);
            System.out.println("S["+vertex.name+"]");
            System.out.println("----------------------------------------------");

            int start_point = vertex.name-1;
            int count = 0;
            for(int i = 0; i < list.get(start_point).size(); i++){
                Vertex temp = list.get(start_point).get(i);
                System.out.print("Q["+(count++)+"] : d["+temp.direct+"] = "+d[temp.direct]);

                if(!set.contains(temp.direct)){
                    int new_distance = d[start_point+1] + temp.distance;

                    if(new_distance < d[temp.direct]){
                        d[temp.direct] = new_distance;
                        System.out.print(" => "+d[temp.direct]);
                    }
                    que.offer(new Vertex(temp.direct, 0, d[temp.direct]));
                }
                System.out.println();
            }
            System.out.println();
        }

        //print();
    }

    public void print(){
        System.out.println(list.toString());
    }
}

class Vertex implements Comparable<Vertex> {
    public int name;//start point
    public int direct;//end point
    public int distance;//point distance

    public Vertex(int name, int direct, int distance){
        this.name = name;
        this.direct = direct;
        this.distance = distance;
    }

    public String toString(){
        return name+"";
    }

    @Override
    public int compareTo(Vertex v) {
        if(this.distance < v.distance) return -1;
        else if(this.distance > v.distance) return 1;
        else return 0;
    }
}