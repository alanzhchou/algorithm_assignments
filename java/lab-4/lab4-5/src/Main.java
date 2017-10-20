public class Main {
    public static void main(String[] args) {
        Integer[] a = {3,2,6,1,1,2};

        MyLinkedList<Integer> test = new MyLinkedList<Integer>();
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

//    public <T> void push_first(T nodeContent){
//        Node<T> aNode = new Node(nodeContent);
//        if (size != 0){
//            first.prev = aNode;
//            first.prev.next = first;
//            first = aNode;
//        }else{
//            first = last = aNode;
//        }
//        size++;
//    }

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

//    public<T> void pop_first(){
//        if (size != 0&&size != 1){
//            Node<T> after = first.next;
//            first.next.prev = null;
//            first.next = null;
//            first.content = null;
//            first = after;
//            size--;
//        }else if (size == 1){
//            first.content = null;
//            first.next = null;
//            first.prev = null;
//            first = last = null;
//            size--;
//        }
//    }

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

    public<T extends Comparable <T>> void testQuenu(T[] test){
        int[] count = new int[test.length];
        for (int i=0; i<test.length; i++){
            count[i] = 0;
        }

        for (int i=0; i<test.length; i++) {
            if (size == 0 || (Integer) last.content >= (Integer) test[i]) {
                push_last(test[i]);
            } else if ((Integer) last.content < (Integer) test[i]) {
                Node<T> a = last;
                int j = i-1;
                while (a != null && (Integer) a.content < (Integer) test[i]) {
                    count[j] = i+1;
                    j--;
                    a = a.prev;
                    pop_last();
                }
                push_last(test[i]);
            }
        }

        for (int i=0; i<test.length; i++){
            System.out.println(count[i]);
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