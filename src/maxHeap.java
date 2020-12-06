/*
150, 파일처리론
60, 객체지향설계
230, 수치해석
38, 논리회로 및 실험
70, 자료구조 및 실습
80, 기초물리학
98, 계산이론
29, 프로그래밍 언어
30, 컴퓨터 구조1
41, 고급프로그램설계
45, 소프트웨어 설계
56, 소프트웨어 공학
9, 선형대수
1, 컴퓨터프로그래밍1
3, 컴퓨터프로그래밍2
27, 이산수학
40, 컴퓨터 구조2
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class maxHeap {
    static int[] Heap;
    static int size;
    static int maxSize;

    static HashMap<Integer, String> map;

    public static void main(String[] args) throws IOException {
        String path = maxHeap.class.getResource("").getPath();
        List<String> list = Files.readAllLines(Paths.get(path+"data06_heap.txt"));

        maxHeap(list.size());

        map = new HashMap<Integer, String>();
        int i = 1;
        //파일 배열에 저장
        for(String str : list){
            String[] input = str.split(", ");
            int number = Integer.parseInt(input[0]);
            String lecture = input[1];

            map.put(number, lecture);
            Heap[i++] = number;
        }

        //print_int_arr(Heap, map);
        //Convert Array to Max Heap
        Build_Max_Heap(list.size());
        /*
        System.out.println();
        System.out.print("키값이 최대인 원소 :   ");max();
        System.out.println();
        System.out.println("최대인 원소 제거");
        extractMax();
        print_int_arr(Heap);
        System.out.println();
        System.out.println("원소 x를 새로 넣음");
        insert(Heap, 230);
        print_int_arr(Heap);
        System.out.println();
        System.out.println("increase value of index i");
        increaseKey(3, 300);
        print_int_arr(Heap);
        System.out.println();
        System.out.println("delete");
        delete(3);
        print_int_arr_for_debugging(Heap);
         */
        System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다. ****");
        print_int_arr(Heap);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int check = 0;
        while(true){
            System.out.println(
                    "------------------------------------------------------------\n" +
                    "       1. 작업추가    2.최대값   3.최대 우선순위 작업 처리\n" +
                    "       4. 원소 키값 증가    5.작업제거  6.종료\n"+
                    "------------------------------------------------------------\n");

            check = Integer.parseInt(br.readLine());
            switch (check){
                case 1:
                    System.out.println("우선순위와 작업 내용을 적어주세요.");
                    System.out.print("우선순위 : "); int prior = Integer.parseInt(br.readLine());
                    System.out.print("작업내용 : "); String name = br.readLine();
                    insert(Heap, prior); map.put(prior, name);
                    System.out.println("\n변경된 내용을 확인해주세요.");
                    print_int_arr(Heap);
                    break;
                case 2:
                    System.out.print("현재 우선순위가 가장 큰 대기목록은 "); max(); System.out.println(" 입니다.");
                    break;
                case 3:
                    extractMax();
                    System.out.println("남은 대기목록은 다음과 같습니다.");
                    print_int_arr(Heap);
                    break;
                case 4:
                    System.out.println("증가시키고 싶은 원소와 희망하는 키값을 입력하세요.");
                    System.out.print("원소 : "); int index = Integer.parseInt(br.readLine());
                    System.out.print("키값 : "); int value = Integer.parseInt(br.readLine());
                    increaseKey(index, value);
                    System.out.println("변경된 내용을 확인해주세요.");
                    print_int_arr(Heap);
                    break;
                case 5:
                    System.out.println("제거할 작업을 선택해주세요.");
                    System.out.print("인덱스 : "); int element = Integer.parseInt(br.readLine());
                    delete(element);
                    System.out.println("변경된 내용을 확인해주세요.");
                    print_int_arr(Heap);
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }

            if(check == 6){
                break;
            }
        }

    }

    public static void maxHeap(int maxsize){
        size = 0;
        maxSize = maxsize;
        Heap = new int[maxSize+1];
        Heap[0] = Integer.MAX_VALUE;
    }

    public static int parent(int index){
        return index/2;
    }

    public static int left_child(int index){
        return 2*index;
    }

    public static int right_child(int index){
        return (2*index)+1;
    }

    public static void Swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void Max_Heapify(int index){
        int leftLeaf = left_child(index);
        int rightLeaf = right_child(index);

        int largest = 0;
        if(leftLeaf <= size && Heap[leftLeaf] > Heap[index]){
            largest = leftLeaf;
        }
        else{
            largest = index;
        }

        if(rightLeaf <= size && Heap[rightLeaf] > Heap[largest]){
            largest = rightLeaf;
        }

        if(largest != index){
            Swap(Heap, index, largest);
            Max_Heapify(largest);
        }
    }

    public static void Build_Max_Heap(int length){
        size = length;
        for(int i = parent(length); i >= 1; i--){
            Max_Heapify(i);
        }
    }

    public static void insert(int[] A, int value){
        A[++size] = value;
        int i = size;
        while(i > 1 && A[parent(i)] < A[i]){
            Swap(A, i, parent(i));
            i = parent(i);
        }
    }

    public static void max(){
        System.out.print(Heap[1]+", "+map.get(Heap[1]));
    }

    public static void extractMax(){
        if(size < 1){
            return;
        }
        Heap[1] = Heap[size--];
        Max_Heapify(1);
    }

    public static void increaseKey(int index, int value){
        String name = map.get(Heap[index]);

        Heap[index] = value;
        while(index > 1 && Heap[parent(index)] < Heap[index]){
            Swap(Heap, index, parent(index));
            index = parent(index);
        }

        map.put(value, name);
    }

    public static void delete(int index){
        Swap(Heap, index, size--);
        Max_Heapify(index);
    }

    public static void print_int_arr(int[] A){
        for(int i = 1; i <= size; i++){
            System.out.println(A[i]+", "+map.get(A[i]));
        }
    }
    public static void print_int_arr_for_debugging(int[] A){
        int temp = 1;
        for(int i = 1; i <= size; i++){
            System.out.println(A[i]+", "+map.get(A[i]));
            if(temp == i){
                System.out.println();
                temp = temp*2+1;
            }
        }
    }

}
