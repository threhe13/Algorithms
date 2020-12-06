package HomeWork_12thWeek;

/*
faaabbaaaaaabbaaaeabdddddaacaaeeabbbaaaaeddaaaeaacaaddaadaaaaeffaaabbbccccffdaaeaddbbeeacccdacccddaa
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Huffman {
    public static void main(String[] args) throws IOException {
        //String input = "faaabbaaaaaabbaaaeabdddddaacaaeeabbbaaaaeddaaaeaacaaddaadaaaaeffaaabbbccccffdaaeaddbbeeacccdacccddaa";
        String path = "./";
        List<String> list = Files.readAllLines(Paths.get(path+"data12_huffman.txt"));
        //System.out.println(list);

        String input = list.get(0);
        Solution s = new Solution();
        s.huffman(input);
    }
}

class Solution{

    //for encode
    int length;
    HashMap<String, Integer> freq;
    HashMap<String, String> table;

    //for decode
    HashMap<String, String> decode_table;

    class node implements Comparable<node>{
        String name;
        node left;
        node right;
        int frequency;

        public node(String name, int frequency){
            this.name = name;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }

        public String toString(){
            return this.name+" : "+this.frequency;
        }

        @Override
        public int compareTo(node node) {
            if(this.frequency < node.frequency) return -1;
            else if(this.frequency > node.frequency) return 1;
            else return 0;
        }
    }

    public void huffman(String str) throws IOException {
        length = str.length();
        frequency(str);//빈도수 확인

        int size = freq.size();
        System.out.println("문자 빈도수 확인 : "+freq);

        PriorityQueue<node> que = new PriorityQueue<>();

        Iterator it = freq.keySet().iterator();
        while(it.hasNext()){
            String name = (String)it.next();
            que.offer(new node(name, freq.get(name)));
        }

        System.out.println("빈도수를 우선순위로 한 최소힙 : "+que);

        System.out.println("\n탐색");
        for(int i = 0; i < size-1; i++){
            node left = que.poll();
            node right = que.poll();

            int total = left.frequency + right.frequency;

            node node = new node("extra", total);
            node.left = left;
            node.right = right;

            que.offer(node);
            System.out.println("진행상황 확인 ["+i+"] : "+que);
        }

        table = new HashMap<>();
        System.out.println("\nVriable-length codeword");
        print_codeword(que.poll(), "");
        System.out.println();

        System.out.println("인코딩 중...");
        makeFile(str);
        System.out.println("인코딩 완료!\n");

        System.out.println("디코딩 중...");
        String path = "./";
        List<String> encode = Files.readAllLines(Paths.get(path+"data12_encoded.txt"));
        List<String> table = Files.readAllLines(Paths.get(path+"data12_table.txt"));

        //debugging
        //System.out.println(encode);
        //System.out.println(table);
        decode_table = new HashMap<>();

        String encoded = encode.get(0);
        for(int i = 0; i < table.size(); i++){
            String[] input = table.get(i).split(" ");
            decode_table.put(input[1], input[0]);
        }

        decoding(encoded, decode_table, path);
    }

    public void decoding(String encoded, HashMap<String, String> table, String path) throws IOException {
        //debugging
        //System.out.println(encoded);
        //System.out.println(table);
        StringBuilder sb = new StringBuilder("");
        String str = "";
        for(int i = 0; i < encoded.length(); i++){
            str += encoded.charAt(i);
            //System.out.println(str);
            if(table.containsKey(str)){
                sb.append(table.get(str));
                str = "";
            }
        }

        System.out.println("디코딩 완료\n디코딩한 값은 아래와 같습니다.\n"+sb.toString());
        String fileName = path+"20150513_decode";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        bw.write(sb.toString());

        bw.flush();
        bw.close();
    }

    public void makeFile(String str) throws IOException {
        String path = "./";
        String fileName_encode = path+"201501513_encoded.txt";
        String fileName_table = path+"201501513_table.txt";

        String[] forEncode = str.split("");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName_encode, true));
        String encoding = "";
        for(int i = 0; i < forEncode.length; i++){
            encoding += table.get(forEncode[i]);
        }
        bw.write(encoding);
        System.out.println("인코딩된 값은 아래와 같습니다.\n"+encoding);
        bw.flush();

        bw = new BufferedWriter(new FileWriter(fileName_table, true));
        Iterator it = table.keySet().iterator();
        while(it.hasNext()){
            String s = (String)it.next();
            bw.write(s+" "+table.get(s)+"\n");
        }

        bw.flush();
        bw.close();
    }

    public void print_codeword(node root, String code){
        if(root.left == null && root.right == null && root.name.length() == 1){
            System.out.println(root.name+" : "+code);
            table.put(root.name, code);
            return;
        }

        print_codeword(root.left, code+"0");
        print_codeword(root.right, code+"1");
    }



    public void frequency(String str){
        freq = new HashMap<>();
        String[] split = str.split("");

        for(int i = 0; i < length; i++){
            String temp = split[i];

            if(!freq.containsKey(temp)){
                freq.put(temp, 1);
            }
            else{
                int number = freq.get(temp)+1;
                freq.put(temp, number);
            }
        }
    }

}
