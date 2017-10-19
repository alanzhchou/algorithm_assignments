public class Main {
    public static void main(String[] args) {
        MyLinkedList testList = new MyLinkedList(5);
        //负数代表pop，正数则代表将相应的正数push进栈中
        Integer a[] = {1,-1,2,3,4,-1,5,6,7,-1,-1,8};
        testList.testStack(a);
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
        }
        if (size == 0) {
            first = last = aNode;
            first.prev = null;
            first.next = null;
            size++;
        } else {
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
            System.out.println(last.content);
            first.content = null;
            first.next = null;
            first.prev = null;
            first = last = null;
            size--;
        }else {
            System.out.println(last.content);
            Node<T> before = last.prev;
            last.prev.next = null;
            last.prev = null;
            last.content = null;
            last = before;
            size--;
        }
    }

    public<T> void endPrint(){
        Node<T> forPrint = last;
        while (forPrint != null){
            System.out.println(forPrint.content);
            forPrint = forPrint.prev;
        }
    }

    public<T extends Comparable<T>> void testStack(T[] a){
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