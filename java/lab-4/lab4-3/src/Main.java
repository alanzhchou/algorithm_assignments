import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        BracketsMatching test = new BracketsMatching();
        String[] a = {"(","()","{]","[(){}]","(])[","[[{{}}]]","[][{]]"};
        test.cheaking(a);
    }
}

class BracketsMatching{
    HashMap<Character,Character> set = new  HashMap();

    //三个括号的映射表
    public BracketsMatching(){
        this.set.put(')','(');
        this.set.put(']','[');
        this.set.put('}','{');
    }

    public void checking(String test){
        //判断输出字符的操作符，0是设置为输出为“NO”，1是设置输出为“YES”
        int judge = 0;

        //利用与运算快速判断是否为偶数，为偶数则进行计算，否则直接输出"NO"
        if ((test.length()&1) == 0){
            MyLinkedList<Character> stack = new MyLinkedList<Character>();

            for (int i=0; i<test.length(); i++){
                //判断是否和前一个配对，配对则pop，不配对则append
                if (set.get(test.charAt(i)) == stack.getLast()){
                    stack.pop();
                }else {
                    stack.append(test.charAt(i));
                }
            }

            if (stack.getSize() == 0){
                judge = 1;
            }
        }

        if (judge == 0){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }
    }

    public void cheaking(String[] testArray){
        for (int i= 0; i<testArray.length; i++){
            this.checking(testArray[i]);
        }
    }
}

class MyLinkedList<T> {
    private int size;
    private Node first;
    private Node last;

    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    public<T> void append(T nodeContent) {
        Node<T> aNode = new Node(nodeContent);

        if (size != 0&&size != 1){
            Node<T> current = last;
            last.next = aNode;
            last = aNode;
            last.next = null;
            last.prev = current;
        }else if (size == 0) {
            first = last = aNode;
            first.prev = null;
            first.next = null;
        }else if(size == 1) {
            first.next = aNode;
            last = aNode;
            last.next = null;
            last.prev = first;
        }
        size++;
    }

    public<T> void pop(){
        if (size != 0&&size != 1){
            Node<T> before = last.prev;
            last.prev.next = null;
            last.prev = null;
            last.content = null;
            last = before;
            size--;
        } else if (size == 1){
            first.content = null;
            first.next = null;
            first.prev = null;
            first = last = null;
            size--;
        }else if (size == 0){
        }
    }

    public int getSize(){
        return size;
    }

    public<T> T getFirst(){
        if (first == null){
            return (T) "";
        }
        return (T) first.content;
    }

    public<T> T getLast(){
        if (last == null){
            return (T) "";
        }
        return (T) last.content;
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

    public Node(T nodeContent){
        content = nodeContent;
        prev = null;
        next = null;
    }
}
