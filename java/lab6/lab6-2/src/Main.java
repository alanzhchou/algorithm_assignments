import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        int times = in.nextInt();
        for (int t=0; t<times; t++){
            Btree btree = new Btree();

            int size = in.nextInt();
            int father;
            int child;
            for (int i=0; i<size-1; i++){
                father = in.nextInt();
                child = in.nextInt();

                Node newNode = new Node();
                newNode.leftChild = father;
                newNode.rightChild = child;
                btree.nodes.add(newNode);
            }
            btree.toString();
        }
    }
}

class Btree{
    ArrayList<Node> nodes = new ArrayList<Node>();

    public String toString(){
        for (int i=0; i<nodes.size(); i++){
            System.out.println(nodes.get(i).leftChild + "\t" + nodes.get(i).rightChild);
        }
        return null;
    }
}

class Node{
    Boolean isRoot = false;
    int leftChild = 0;
    int rightChild = 0;
}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
        reader = new BufferedReader(new InputStreamReader(stream),32768);
        tokenizer = null;
    }

    public String next(){
        while (tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e){
                throw new RuntimeException();
            }
        }
        return  tokenizer.nextToken();
    }

    public int nextInt(){
        return Integer.parseInt(next());
    }

    public String nextLine(){
        while (tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e){
                throw new RuntimeException();
            }
        }
        StringBuffer temp = new StringBuffer("");
        while (tokenizer.hasMoreTokens()){
            temp.append(tokenizer.nextToken());
            temp.append(" ");
        }
        temp.deleteCharAt(temp.length()-1);
        System.out.println(temp.toString());
        return  temp.toString();
    }
}