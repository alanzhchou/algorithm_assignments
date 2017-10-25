import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int length = 0;

        int b = in.nextInt();
        for (int i=0; i<b; i++){
            length = in.nextInt();
        }
        Integer a[] = new Integer[length];;

        int element = 0;
        int first = 0;
        int second = 0;
        int third = 0;
        for (int i=0; i<length; i++){
            first = in.nextInt();
            second = in.nextInt();
            if (second == 2){
                third = 0;
                element = first*10 + second;
            }else {
                third = in.nextInt();
                element = first*100 + second*10 + third;
            }
            a[i] = element;
        }

        MyLinkedList<Integer> test = new MyLinkedList<Integer>();

//        Integer[] a = {111,112,113,211,212,213,12,22};
        test.testQuenu(a);
    }
}

class MyLinkedList<T> {
    private int size;
    private Node first;
    private Node last;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public <T> void push_first(T nodeContent){
        Node<T> aNode = new Node(nodeContent);
        if (size != 0){
            first.prev = aNode;
            first.prev.next = first;
            first = aNode;
        }else{
            first = last = aNode;
        }
        size++;
    }

    public <T> void push_last(T nodeContent) {
        Node<T> aNode = new Node(nodeContent);
        if (size != 0&&size != 1){
            Node<T> current = last;
            last.next = aNode;
            last = aNode;
            last.prev = current;
        }else if (size == 0) {
            first = last = aNode;
        }else if (size == 1) {
            first.next = aNode;
            last = aNode;
            last.prev = first;
        }
        size++;
    }

    public<T> void pop_first(){
        if (size != 0&&size != 1){
            Node<T> after = first.next;
            first.next.prev = null;
            first.next = null;
            first.content = null;
            first = after;
            size--;
        }else if (size == 1){
            first.content = null;
            first.next = null;
            first.prev = null;
            first = last = null;
            size--;
        }
    }

    public<T> void pop_last(){
        if (size != 0&&size != 1){
            Node<T> before = last.prev;
            last.prev.next = null;
            last.prev = null;
            last.content = null;
            last = before;
            size--;
        }else if (size == 1){
            first.content = null;
            first.next = null;
            first.prev = null;
            first = last = null;
            size--;
        }
    }

    public<T> void testQuenu(T[] test){
        for (int i=0; i<test.length; i++){
            String b = String.valueOf(test[i]);
            switch (b.substring(0,2)){
                case "11":
                    this.push_first(Integer.parseInt(b.substring(2,b.length())));
                    break;
                case "12":
                    this.pop_first();
                    break;
                case "21":
                    this.push_last(Integer.parseInt(b.substring(2,b.length())));
                    break;
                case "22":
                    this.pop_last();
                    break;
                default:
                    break;
            }
        }
        this.endPrint();
    }

    public<T> void endPrint(){
        Node<T> forPrint = first;
        while (forPrint != null){
            System.out.println(forPrint.content);
            forPrint = forPrint.next;
        }
    }
}

//节点类，
/*
T content;
Node prev;
Node next;
 */
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
