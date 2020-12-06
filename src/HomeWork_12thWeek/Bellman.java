package HomeWork_12thWeek;

/*
6
0 5
11
0 1 3
0 3 2
0 4 -2
1 5 7
2 3 1
2 4 3
3 1 -9
3 2 -2
4 1 -3
4 5 5
5 3 -1
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bellman {
    public static void main(String[] args) throws IOException {
        /*
        String[] input_txt = {
                "6",//node 개수
                "0 5",//s, t
                "11"//edge 개수
        };

        String[] input_edge ={
                //edge
                "0 1 3",
                "0 3 2",
                "0 4 -2",
                "1 5 7",
                "2 3 1",
                "2 4 3",
                "3 1 -9",
                "3 2 -2",
                "4 1 -3",
                "4 5 5",
                "5 3 -1"
        };
         */

        String path = "./";
        List<String> list = Files.readAllLines(Paths.get(path+"data12_bellman.txt"));
        int data = 3;
        String[] input_data = new String[data];
        String[] input_edge = new String[list.size()-data];
        for(int i = 0; i < 3; i++){
            input_data[i] = list.get(i);
        }
        for(int i = data; i < list.size(); i++){
            input_edge[i-data] = list.get(i);
        }

        //print(input_data);
        //print(input_edge);

        bellman_solution bs = new bellman_solution();
        bs.solution(input_data, input_edge);
    }

    public static void print(String[] str){
        for(int i = 0; i < str.length; i++){
            System.out.println(str[i]+" ");
        }
        System.out.println();
    }
}

class bellman_solution{

    int[] d;
    int[][] edge;

    class vertex{
        int start;
        int end;
        int weight;
        public vertex(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        public String toString(){
            return this.start+" "+this.end+" "+this.weight;
        }
    }

    public void solution(String[] str1, String[] str2) {
        //노드 개수
        int length = Integer.parseInt(str1[0]);

        d = new int[length];
        edge = new int[length][length];

        //시작과 끝점
        String[] split_str1_1 = str1[1].split(" ");
        int start = Integer.parseInt(split_str1_1[0]);
        int end = Integer.parseInt(split_str1_1[1]);
        for(int i = 0; i < d.length; i++){
            d[i] = Integer.MAX_VALUE;
        }
        d[start] = 0;

        //간선 개수
        int number = Integer.parseInt(str1[2]);

        ArrayList<vertex> list = new ArrayList<>();

        for(int i = 0; i < number; i++){
            String[] split = str2[i].split(" ");
            int u = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);
            int weight = Integer.parseInt(split[2]);

            edge[u][v] = weight;
            list.add(new vertex(u, v, weight));
        }

        //print(edge);
        /*
              0  3  0  2 -2  0
              0  0  0  0  0  7
              0  0  0  1  3  0
              0 -9 -2  0  0  0
              0 -3  0  0  0  5
              0  0  0 -1  0  0
         */

        /*
        for(int j = 1; j <= length-1; j++) {
            for (int i = 0; i < number; i++) {
                vertex vertex = list.get(i);
                System.out.println(vertex);

                int u = vertex.start;
                int v = vertex.end;
                int weight = vertex.weight;

                if(d[u] != Integer.MAX_VALUE && d[v] > d[u]+weight){
                    d[v] = d[u]+weight;
                }
            }
        }
         */

        int[][] test = new int[length][length];

        for(int i = 1; i < length; i++){
            //System.out.println(i);
            for(int j = 0; j < number; j++){
                vertex vertex = list.get(j);
                int u = vertex.start;
                int v = vertex.end;
                int w = vertex.weight;
                //test[i][v] = w;
                //print(d);

                if(d[u] != Integer.MAX_VALUE && d[v] > d[u]+w){
                    d[v] = d[u]+w;
                    test[i][v] = d[v];
                }
            }

            for(int j = 1; j < length; j++){
                if(test[i][j] == 0){
                    test[i][j] = test[i-1][j];
                }
            }
        }

        //print(test);

        boolean isCycle = false;

        for(int i = 0; i < number; i++){
            vertex vertex = list.get(i);
            int u = vertex.start;
            int v = vertex.end;
            int w = vertex.weight;
            if(d[u] != Integer.MAX_VALUE && d[v] > d[u]+w){
                isCycle = true;
            }
        }

        if(isCycle){
            System.out.println("음수 사이클이 존재합니다.");
        }
        else{
            System.out.println("최단 거리는 다음과 같습니다.");
            for(int i = 0; i < d.length; i++){
                System.out.print(d[i]+" ");
            }
            System.out.println();
        }

    }

    public void print(int[][] a){
        System.out.println("  d    1  2  3  4  5");
        System.out.println("------------------");
        for(int i = 0; i < a.length; i++){
            System.out.printf("%3d |", i);
            for(int j = 1; j < a[i].length; j++){
                System.out.printf("%3d", a[i][j]);
            }
            System.out.println();
        }
    }

    public void print(int[] a){

        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
}