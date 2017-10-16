import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        MyLinkedList<Integer> myList1 = new MyLinkedList();
        Integer[] a = {1,2,3};
        myList1.append(a);

        MyLinkedList<Integer> myList2 = new MyLinkedList();
        Integer[] b = {1,2,3,4,5};
        myList2.append(b);

        MyLinkedList<Integer> myList3 = new MyLinkedList();
        Integer[] c = {1,1,1,2};
        myList3.append(c);


        MyLinkedList<Integer> myList4 = new MyLinkedList();
        Integer[] d = {1,1,2,2,3,3,4};
        myList4.append(d);


        MyLinkedList<Integer> sum1 = myList1.plus(myList2);
        sum1.toString();
        System.out.println("++++++++++++++++++++++++++++++++++++");

        MyLinkedList<Integer> sum2 = myList3.plus(myList4);
        sum2.toString();
        System.out.println("++++++++++++++++++++++++++++++++++++");

        MyLinkedList<Integer> myList5 = new MyLinkedList();
        Integer[] e = {4,3,2,1};
        myList5.append(e);
        myList5.toString();
        myList5.sort();
        myList5.toString();

//        myList.toString();
//        System.out.println("++++++++++++++++++");
//
//        myList.delete(0,5);
//        myList.toString();
//
//        myList.update(0,150);
//        myList.toString();
//
//        Integer[] b = {11,12,15};
//        myList.insert(0,b);
//        myList.toString();
//
//        Integer[] g = {1,2,5,6,1,2};
//        Integer[] t = myList.toArray(g);
//        for (int i=0; i<myList.getSize(); i++){
//            System.out.println(t[i]);
//        }
    }
}

class MyLinkedList<T>{
    private int size;
    private Node first;
    private Node last;

    public MyLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    public<T> MyLinkedList(T[] nodeContents){
        first = null;
        last = null;
        size = 0;
        append(nodeContents);
    }

    public<T> void clear(){
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.next;
            x.content = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public<T> void append(Node<T> aNode){
        if (size == 0){
            first = last = aNode;
            first.prev = null;
            first.next = null;
            size++;
        }else {
            if (size == 1){
                first.next = aNode;
                last = aNode;
                last.next = null;
                last.prev = first;
            }else {
                Node<T> current = last;
                last.next = aNode;
                last = aNode;
                last.next = null;
                last.prev = current;
            }
            size++;
        }
    }

    public<T> void append(T nodeContent){
        append(new Node(nodeContent));
    }

    public<T> void append(T[] nodeContents){
        for (int i=0; i<nodeContents.length; i++){
            append(new Node(nodeContents[i]));
        }
    }

    public<T> void insert(int index, Node<T> aNode){
        if (index < 0||index > size){
            System.out.println("insert: out of index");
        }else if (size == 0||index == size){
            append(aNode);
        }else if (index == 0){
            first.prev = aNode;
            aNode.next = first;
            first = aNode;
        }else {
            int i=0;
            Node<T> find = new Node();
            find = first;
            while (i < index){
                find = find.next;
                i++;
            }
            aNode.prev = find.prev;
            find.prev.next = aNode;
            aNode.next = find;
            find.prev = aNode;
        }
        size++;
    }

    public<T> void insert(int index, T nodeContent){
        insert(index,new Node(nodeContent));
    }

    public<T> void insert(int index, T[] nodeContents){
        int remin = nodeContents.length;
        while (remin != 0){
            insert(index,nodeContents[nodeContents.length-remin]);
            index++;
            remin--;
        }
    }

    public<T> void pop(){
        delete(size-1);
    }

    public<T> void delete(int index){
        if (index >= size ||index < 0){
            System.out.println("delete: out of index");
        }else if (size == 0){
            System.out.println("No element!");
        }else if (index == 0){
            if (size == 1){
                first.content = null;
                first.next = null;
                first.prev = null;
                first = last = null;
            }else {
                Node after = first.next;
                first.next.prev = null;
                first.content = null;
                first.next = null;
                first = after;
            }
        }else if (index == size-1){
            Node<T> before = last.prev;
            last.prev.next = null;
            last.prev = null;
            last.content = null;
            last = before;
        }else {
            int i=0;
            Node<T> find = new Node();
            find = first;
            while (i < index){
                find = find.next;
                i++;
            }
            find.prev.next = find.next;
            find.next.prev = find.prev;
            find.content = null;
            find.next = null;
            find.prev = null;
        }
        size--;

    }

    public<T> void delete(int index1, int index2){
        if (index1 > index2){
            System.out.println("delete: bad area");
        }else if (index1 == index2){
            delete(index1);
        }else {
            int count = index2 - index1;
            while (count != -1){
                delete(index1);
                count--;
            }
        }
    }

    public<T> void deleteFirst(T item){
        Node<T> find = new Node();
        find = first;

        int i=0;
        boolean result = false;
        while (find != null){
            if (find.content == item){
                delete(i);
                result = true;
                break;
            }else {
                find = find.next;
            }
            i++;
        }
        if (!result){
            System.out.println("No such element");
        }
    }

    public<T> void deleteLast(T item){
        Node<T> find = new Node();
        find = first;

        int i=0;
        int index = -1;
        while (find != null){
            if (find.content == item){
                index = i;
            }
            find = find.next;
            i++;
        }
        if (index != -1){
            delete(index);
        }else {
            System.out.println("No such element");
        }
    }

    public<T> void deleteAllItem(T item){
        Node<T> find = new Node();
        find = first;

        boolean result = false;
        while (find != null){
            if (find.content == item){
                find = find.next;
                deleteFirst(item);
                result = true;
            }else {
                find = find.next;
            }
        }
        if (!result){
            System.out.println("No such element");
        }
    }

    public<T> void update(int index, Node<T> aNode){
        if (index < 0||(size != 0&&index >= size)){
            System.out.println("update: out of index");
        }else if (size == 0&&index == 0){
            append(aNode);
        }else {
            insert(index,aNode);
            delete(index+1);
        }
    }

    public<T> void update(int index, T nodeContent){
        update(index,new Node(nodeContent));
    }

    public<T> int indexOf(T item){
        int index = 0;
        Node<T> find = new Node<T>();
        find = first;
        while (find != null){
            if (item.equals(find.content)){
                return index;
            }
            find = find.next;
            index++;
        }
        return -1;
    }

    public<T> int indexOfLast(T item){
        int result = -1;
        int index = 0;
        Node<T> find = new Node<T>();
        find = first;
        while (find != null){
            if (item.equals(find.content)){
                result = index;
            }
            find = find.next;
            index++;
        }
        return result;
    }

    public<T> T[] toArray(T[] a) {
        if (a.length < size){
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }

        int i = 0;
        Object[] result = a;
        for (Node<T> x = first; x != null; x = x.next){
            result[i++] = x.content;
        }

        if (a.length > size)
            a[size] = null;

        return a;
    }

    public<T> MyLinkedList<T> myclone(){
        MyLinkedList<T> listNew = new MyLinkedList<>();
        Node<T> element = new Node<T>();
        element = (Node<T>) this.getFirst();

        while (element != null){
            listNew.append(element.content);
            element = element.next;
        }
        return listNew;
    }

    public <T extends Comparable<T>> void sort(){
        T[] getClass = (T[]) new Comparable[1];
        getClass = (T[]) java.lang.reflect.Array.newInstance(
                getClass.getClass().getComponentType(), size);
        int i = 0;
        while (first != null){
            getClass[i] = (T) first.content;
            first = first.next;
            i++;
        }
        Arrays.sort(getClass);

        this.clear();

        for (int j=0; j<getClass.length; j++){
            this.append(getClass[j]);
        }
    }

    public <T extends Comparable<T>> MyLinkedList<T> plus(MyLinkedList<T> anotherList){
        MyLinkedList<T> list1 = this.myclone();
        MyLinkedList<T> list2 = anotherList.myclone();

        MyLinkedList<T> sum = new MyLinkedList();

        Node<T> forPlus1 = new Node<T>();
        forPlus1 = list1.getFirst();

        Node<T> forPlus2 = new Node<T>();
        forPlus2 = list2.getFirst();

        while (forPlus1 !=null && forPlus2 != null){
            int compareResult = forPlus1.content.compareTo(forPlus2.content);

            if (compareResult <= 0){
                sum.append(forPlus1.content);
                forPlus1 = forPlus1.next;
            }else {
                sum.append(forPlus2.content);
                forPlus2 = forPlus2.next;
            }
        }

        while (forPlus1 != null){
            sum.append(forPlus1.content);
            forPlus1 = forPlus1.next;
        }
        while (forPlus2 != null){
            sum.append(forPlus2.content);
            forPlus2 = forPlus2.next;
        }

        return sum;
    }

    public Node<T> getFirst(){
        return first;
    }
    public Node<T> getLast(){
        return last;
    }
    public int getSize(){
        return size;
    }

    public String toString(){
        Node forPrint = new Node();
        forPrint = first;

        int i = 0;
        while (forPrint != null){
            System.out.print(forPrint.content + "\t");
            i++;
            if (i%10 == 0){
                System.out.println();
            }
            forPrint = forPrint.next;
        }
        System.out.println();
        return null;
    }

    public void listInfo(){
        Node forPrint = new Node();
        forPrint = first;

        int i = 0;
        while (forPrint != null){
            i++;
            if (forPrint.prev == null){
                if (forPrint.next == null){
                    System.out.print(forPrint.content + "\t");
                }else {
                    System.out.print(forPrint.content + "-->" + forPrint.next.content + "\t");
                }
            }else if (forPrint.prev != null){
                if (forPrint.next == null){
                    System.out.print(forPrint.content + "-->" + forPrint.prev.content + "\t");
                }else {
                    System.out.print(forPrint.content + "-->" + forPrint.prev.content + "&&" + forPrint.next.content + "\t");
                }
            }
            if (i%10 == 0){
                System.out.println();
            }
            forPrint = forPrint.next;
        }
        System.out.println();
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
