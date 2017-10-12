/**
 * Created by ZH-AlanChou on 2017/10/12.
 */

public class MyLinkedList<E> {

    private int size = 0;
    Node<E> first;
    Node<E> last;

    public static class Node<E> {
        E item;

        Node<E> pre;
        Node<E> next;

        public Node(Node<E>pre,E item,Node<E>next){
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    public void insert(int index,E item){
        if (index == 0){
            Node current = new Node(null,item,null);
            this.first = current;
            this.last = current;
            size++;
        }else {
            Node current = new Node(last,item,null);
            this.last.next = current;
            this.last = current;
            size++;
        }
    }

    public String toString(){
        System.out.println(this.last.item);
        return null;
    }
}


