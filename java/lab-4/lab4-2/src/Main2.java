import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int size = 0;
        int length = 0;

        int b = in.nextInt();
        for (int i=0; i<b; i++){
            size = in.nextInt();
            length = in.nextInt();
        }
        Integer a[] = new Integer[length];;

        int element = 0;
        for (int i=0; i<length; i++){
            int operate = in.nextInt();
            if (operate == 2){
                element = -1;
            }else {
                element = in.nextInt();
            }
            a[i] = element;
        }
        MyLinkedList testList = new MyLinkedList(size);
        testList.testQuenu(a);
    }
}

class MyLinkedList<T> {
    int max;
    private int size;
    private Node first;
    private Node last;

    public MyLinkedList(int max) {
        this.max = max;
        first = null;
        last = null;
        size = 0;
    }

    public <T> void push(Node<T> aNode) {
        if (size == max){
            System.out.println(aNode.content);
        }else if (size == 0) {
            first = last = aNode;
            first.prev = null;
            first.next = null;
            size++;
        }else {
            if (size == 1) {
                first.next = aNode;
                last = aNode;
                last.next = null;
                last.prev = first;
            } else {
                Node<T> current = last;
                last.next = aNode;
                last = aNode;
                last.next = null;
                last.prev = current;
            }
            size++;
        }
    }

    public <T> void push(T nodeContent) {
        push(new Node(nodeContent));
    }

    public<T> void pop(){
        if (size == 0){
        }else if (size == 1){
            System.out.println(first.content);
            first.content = null;
            first.next = null;
            first.prev = null;
            first = last = null;
            size--;
        }else {
            System.out.println(first.content);
            Node<T> after = first.next;
            first.next.prev = null;
            first.next = null;
            first.content = null;
            first = after;
            size--;
        }
    }

    public<T> void endPrint(){
        Node<T> forPrint = first;
        while (forPrint != null){
            System.out.println(forPrint.content);
            forPrint = forPrint.next;
        }
    }

    public<T extends Comparable<T>> void testQuenu(T[] a){
        for (int i=0; i<a.length; i++){
            if ((Integer) a[i] > 0){
                this.push(a[i]);
            }else {
                this.pop();
            }
        }
        this.endPrint();
    }
}

class Node<T>{
    T content;
    Node prev;
    Node next;

    public Node(){
        content = null;
        prev = null;
        next = null;
    }

    public Node(T nodeContent){
        content = nodeContent;
        prev = null;
        next = null;
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
}