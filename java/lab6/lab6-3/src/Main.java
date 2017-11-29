import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        //number of testcases
        int times = in.nextInt();
        for (int t=0; t<times; t++){
            Btree btree = new Btree();

            //number of nodes
            int size = in.nextInt();
            int father;
            int child;
            for (int i=0; i<size-1; i++){
                father = in.nextInt();
                child = in.nextInt();
                if (i == 0){
                    Node newNode1 = new Node(father);
                    newNode1.leftChild = child;
                    btree.nodes.add(newNode1);
                }else {
                    int fatherIndex = btree.searchForIndex(father);
                    if (btree.nodes.get(fatherIndex).leftChild == 0){
                        btree.nodes.get(fatherIndex).leftChild = child;
                    }else {
                        btree.nodes.get(fatherIndex).rightChild = child;
                    }
                }
                Node newNode2 = new Node(child);
                btree.nodes.add(newNode2);
                if (i == size-2){
                    btree.markRoot();
                }
            }
            btree.preOrderTrave();
            btree.inOrderTrave();
            btree.postOrderTrave();
        }
    }
}

class Btree{
    ArrayList<Node> nodes = new ArrayList<Node>();

    public int searchForIndex(int father){
        if (nodes.size() != 0){
            for (int i=0; i<nodes.size(); i++){
                if (father == nodes.get(i).selfValue){
                    return i;
                }
            }
        }
        return -1;
    }

    public void markRoot(){
        for (int i=1; i<nodes.size()+1; i++){
            for (int j=0; j<nodes.size(); j++){
                Node exam = nodes.get(j);
                if (exam.leftChild == i||exam.rightChild == i){
                    break;
                }else if (j==nodes.size()-1){
                    nodes.get(searchForIndex(i)).isRoot = true;
                }
            }
        }

    }

    public void printNodes(){
        for (int j=0; j<nodes.size(); j++){
            Node exam = nodes.get(j);
            System.out.println(exam.isRoot + "\t" + exam.selfValue + "\t" + exam.leftChild + "\t" + exam.rightChild);
        }
    }

    public void levelTrave(){
        int root = -1;
        for (int i=0; i<nodes.size(); i++){
            if (nodes.get(i).isRoot == true){
                root = i;
            }
        }
        Node exam = nodes.get(root);

        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(exam.selfValue);
        int i=0;
        while (i<nodes.size()){
            if (exam.leftChild != 0){
                queue.add(exam.leftChild);
            }if (exam.rightChild != 0){
                queue.add(exam.rightChild);
            }
            System.out.print(queue.get(0) + " ");i++;
            queue.remove(0);
            if (queue.size() != 0){
                exam = nodes.get(searchForIndex(queue.get(0)));
            }
        }
    }

    public void preOrderTrave(){
        int root = -1;
        for (int i=0; i<nodes.size(); i++){
            if (nodes.get(i).isRoot == true){
                root = i;
            }
        }
        Node exam = nodes.get(root);
        Stack<Integer> stack = new Stack<Integer>();
        int i=0;
        stack.push(exam.selfValue);
        System.out.print(exam.selfValue + " ");i++;
        boolean retry = false;
        while (i<nodes.size()){
            if (exam.leftChild != 0&&!retry){
                stack.push(exam.leftChild);
                System.out.print(exam.leftChild + " ");i++;
                exam = nodes.get(searchForIndex(exam.leftChild));
                retry = false;
            }else {
                stack.pop();
                exam = nodes.get(searchForIndex(stack.lastElement()));
                retry = true;
                if (exam.rightChild != 0){
                    stack.push(exam.rightChild);
                    System.out.print(exam.rightChild + " ");i++;
                    exam = nodes.get(searchForIndex(exam.rightChild));
                    retry = false;
                }else {
                    stack.pop();
                    exam = nodes.get(searchForIndex(stack.lastElement()));
                    retry = true;
                }
            }
        }
        System.out.println();
    }

    public void inOrderTrave(){
        int root = -1;
        for (int i=0; i<nodes.size(); i++){
            if (nodes.get(i).isRoot == true){
                root = i;
            }
        }
        Node exam = nodes.get(root);
        Stack<Integer> stack = new Stack<Integer>();
        int i=0;
        stack.push(exam.selfValue);
        boolean retry = false;
        while (i<nodes.size()){
            if (exam.leftChild != 0&&!retry){
                stack.push(exam.leftChild);
                exam = nodes.get(searchForIndex(exam.leftChild));
                retry = false;
            }else {
                System.out.print(stack.lastElement() + " ");i++;
                stack.pop();
                exam = nodes.get(searchForIndex(stack.lastElement()));
                retry = true;
                if (exam.rightChild != 0){
                    System.out.print(stack.lastElement() + " ");i++;
                    stack.push(exam.rightChild);
                    exam = nodes.get(searchForIndex(exam.rightChild));
                    retry = false;
                }else {
                    System.out.print(stack.lastElement() + " ");i++;
                    stack.pop();
                    exam = nodes.get(searchForIndex(stack.lastElement()));
                    retry = true;
                }
            }
        }
        System.out.println();
    }

    public void postOrderTrave(){
        int root = -1;
        for (int i=0; i<nodes.size(); i++){
            if (nodes.get(i).isRoot == true){
                root = i;
            }
        }
        Node exam = nodes.get(root);
        Stack<Integer> stack = new Stack<Integer>();
        int i=0;
        stack.push(exam.selfValue);
        boolean retry = false;
        while (i<nodes.size()){
            if (exam.leftChild != 0&&!retry){
                stack.push(exam.leftChild);
                exam = nodes.get(searchForIndex(exam.leftChild));
                retry = false;
            }else {
                System.out.print(stack.lastElement() + " ");i++;
                stack.pop();
                exam = nodes.get(searchForIndex(stack.lastElement()));
                retry = true;
                if (exam.rightChild != 0){
                    if (i != nodes.size()-1){
                        stack.push(exam.rightChild);
                        exam = nodes.get(searchForIndex(exam.rightChild));
                        retry = false;
                    }else {
                        System.out.print(stack.lastElement() + " ");i++;
                    }
                }else {
                    System.out.print(stack.lastElement() + " ");i++;
                    stack.pop();
                    exam = nodes.get(searchForIndex(stack.lastElement()));
                    retry = true;
                }
            }
        }
        System.out.println();
    }
}

class Node{
    Boolean isRoot = false;
    int selfValue = 0;
    int leftChild = 0;
    int rightChild = 0;

    public Node(int self){
        selfValue = self;
    }
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