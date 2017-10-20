public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> test = new MyLinkedList<Integer>();
        Integer[] a = {111,112,113,211,212,213,12,22};
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