package HomeWork_11thWeek;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void main(String[] args){

        int size = 5;

        int[] d = new int[size+1];
        int[][] path = new int[size+1][size+1];

        String[] input_data = {
                "1,2,10",
                "1,3,3",
                "2,3,1",
                "2,4,2",
                "3,2,4",
                "3,4,8",
                "3,5,2",
                "4,5,7",
                "5,4,9"
        };

        Solution s = new Solution();
        //이동 경로 배열 생성
        path = s.makeArr(input_data, path);

        //거리 기록 배열 초기화
        d = s.resetDistance(d);

        //solution
        s.solution(path, d);

        s.print(path);
        s.print_distance(d);


    }
}

class Solution{

    public int[] solution(int[][] path, int[] d){
        PriorityQueue<Integer> que = new PriorityQueue<>();
        boolean[] isChecked = new boolean[d.length];

        que.offer(1); /// 큐에 추가되는 것 수정할 것

        while(!que.isEmpty()){
            int node = que.poll();
            System.out.println(node);
            isChecked[node] = true;

            int quePoint = 0;
            int count = 0;
            //int previous = d[node];

            for(int i = 1; i < path[node].length; i++){
                if(path[node][i] != 0){
                    System.out.print("Q["+(count++)+"] : d["+i+"] = "+d[i]); // 출력포인트 수정할 것

                    if(d[i] > d[node] + path[node][i]){
                        d[i] = d[node] + path[node][i];
                        System.out.print(" => "+d[i]);
                    }

                    if(!que.contains(d[i]) && !isChecked[i]) {
                        quePoint = i;
                    }

                    System.out.println();
                }
            }

            if(quePoint != 0) {
                que.offer(quePoint);
            }
        }


        return d;
    }

    public int[] resetDistance(int[] distance){
        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i = 0; i <= 1; i++){
            distance[i] = 0;
        }
        return distance;
    }

    public int[][] makeArr(String[] data, int[][] path){
        for(int i = 1; i < path.length; i++){
            for(int j = 1; j < path[i].length; j++){
                path[i][j] = 0;
            }
        }

        for(int i = 0; i < data.length; i++){
            String[] data_split = data[i].split(",");

            int x = Integer.parseInt(data_split[0]);
            int y = Integer.parseInt(data_split[1]);
            int weight = Integer.parseInt(data_split[2]);

            path[x][y] = weight;
        }
        return path;
    }

    public void print(int[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                System.out.printf("%12d", a[i][j]);
            }
            System.out.println();
        }
    }

    public void print_distance(int[] a){
        for(int i = 1; i < a.length; i++){
            System.out.print(a[i]+" ");
        }
    }
}
