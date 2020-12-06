package HomeWork_13thWeek;

/*
8
0 1 1 0 0 0 0 0
1 0 0 1 1 0 0 0
1 0 0 0 0 1 1 0
0 1 0 0 0 0 0 1
0 1 0 0 0 0 0 1
0 0 1 0 0 0 0 1
0 0 1 0 0 0 0 1
0 0 0 1 1 1 1 0
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) throws IOException {
        /*
        String[] input = {
                "8",
                "0 1 1 0 0 0 0 0",
                "1 0 0 1 1 0 0 0",
                "1 0 0 0 0 1 1 0",
                "0 1 0 0 0 0 0 1",
                "0 1 0 0 0 0 0 1",
                "0 0 1 0 0 0 0 1",
                "0 0 1 0 0 0 0 1",
                "0 0 0 1 1 1 1 0",
        };*/

        String path = "./";
        List<String> list = Files.readAllLines(Paths.get(path+"data13.txt"));
        String[] input = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            input[i] = list.get(i);
        }

        BFS_solution bfs_solution = new BFS_solution();
        bfs_solution.solution(input);
    }
}

class BFS_solution{

    class graph{
        int vertex;
        String color;
        int depth;
        int parents;
        public graph(int vertex, String color){
            this.vertex = vertex;
            this.color = color;
            this.depth = Integer.MAX_VALUE;
        }

        public String toString(){
            return "정점 "+(char)(this.vertex+97)+" : 비용 -> "+this.depth+", 부모정점 -> "+(char)(this.parents+97);
        }
    }

    int[][] path;

    public void solution(String[] str){
        int node_number = Integer.parseInt(str[0]); //점 개수
        graph[] graphs = new graph[node_number];
        path = new int[node_number][];

        for(int i = 1; i < str.length; i++){
            String[] split = str[i].split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < split.length; j++){
                int edge = Integer.parseInt(split[j]);
                if(edge == 1){
                    list.add(j);
                }
            }

            path[i-1] = new int[list.size()];
            for(int k = 0; k < list.size(); k++){
                path[i-1][k] = list.get(k);
            }

        }

        System.out.println("인접행렬 구성");
        print(path);

        for(int i = 0; i < node_number; i++){
            graphs[i] = new graph(i, "White");
        }

        graphs[0].color = "Gray";
        graphs[0].depth = 0;
        graphs[0].parents = 0;

        BFS(graphs);
    }

    public void BFS(graph[] graphs){
        Queue<graph> que = new LinkedList<>();
        que.offer(graphs[0]);
        while(!que.isEmpty()){
            graph g = que.poll();
            int vertex = g.vertex;
            for(int i = 0; i < path[vertex].length; i++){
                int edge = path[vertex][i];
                graph h = graphs[edge];
                if(h.color.equals("White")) {
                    h.parents = g.vertex;
                    h.depth = g.depth + 1;
                    h.color = "Gray";

                    que.offer(h);
                }
            }
            g.color = "Black";
        }

        System.out.println("\n각 정점의 부모 정점 및 비용 출력 결과화면");
        for(int i = 0; i < graphs.length; i++){
            System.out.println(graphs[i]);
        }
    }

    public void print(int[][] path){
        for(int i = 0; i < path.length; i++){
            for(int j = 0; j < path[i].length; j++){
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }
    }

}