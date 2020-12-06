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
import java.util.List;

public class DFS {
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

        DFS_solution dfsSolution = new DFS_solution();
        dfsSolution.solution(input);
    }
}

class DFS_solution{

    int[][] path;
    int time;

    class vertex{
        int vertex;
        String color;
        int parents;
        int findTime;
        int outTime;
        public vertex(int vertex, String color){
            this.vertex = vertex;
            this.color = color;
            this.parents = 0;
            this.findTime = Integer.MAX_VALUE;
            this.outTime = Integer.MAX_VALUE;
        }
        public String toString(){
            return "정점 "+this.vertex+" : 최초 발견 시간 -> "+this.findTime+", 탐색완료 시간 -> "+this.outTime+", 부모정점 -> "+(char)(this.parents+97);
        }
    }

    public void solution(String[] str){
        int vertex_number = Integer.parseInt(str[0]);

        vertex[] vertices = new vertex[vertex_number];
        for(int i = 0; i < vertex_number; i++){
            vertices[i] = new vertex(i, "White");
        }

        path = new int[vertex_number][];

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

        time = 0;
        System.out.println("인접행렬 구성");
        print(path);
        System.out.println("\n각 정점의 부모 정점 및 발견된 시간, 탐색완료 시간 출력 결과");
        for(int i = 0; i < vertices.length; i++){
            if(vertices[i].color.equals("White")){
                DFS(vertices, vertices[i]);
            }
        }

        for(int i = 0; i < vertices.length; i++){
            System.out.println(vertices[i]);
        }
    }

    public void DFS(vertex[] vertices, vertex u){
        time = time+1;
        u.findTime = time;
        u.color = "Gray";
        for(int i = 0; i < path[u.vertex].length; i++){
            int v = path[u.vertex][i];
            if(vertices[v].color.equals("White")){
                vertices[v].parents = u.vertex;
                DFS(vertices, vertices[v]);
            }
        }
        u.color = "Black";
        time = time+1;
        u.outTime = time;
    }

    public void print(int[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

}
