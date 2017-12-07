import java.util.*;

class Lamaba{
    private ArrayList<MyInt> arrayList;

    public Lamaba(){
        arrayList = new ArrayList<MyInt>();
        for (int i=0; i<100; i++){
            this.arrayList.add(new MyInt(i));
        }
    }

    static class MyInt{
        private int value;

        public MyInt(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        public static void out(MyInt a){
            System.out.print(a.getValue() + "\t");
        }
    }

    class comp implements Comparator<MyInt>{
        @Override
        public int compare(MyInt a1,MyInt a2) {
            if (a1.getValue() > a2.getValue()){
                return -1;
            }else {
                return 1;
            }
        }
    }


    public void pr(){
//        arrayList.parallelStream().filter(e -> e.getValue()%2 == 1).forEach(MyInt::out);
        arrayList.stream().filter(e -> e.getValue()%2 == 1).forEach(MyInt::out);
        System.out.println();
        Collections.sort(arrayList,new comp());
//        arrayList.parallelStream().filter(e -> e.getValue()%2 == 1).forEach(MyInt::out);
        arrayList.stream().filter(e -> e.getValue()%2 == 1).forEach(MyInt::out);
    }
}



public class Main{
    public static void main(String[] args) {
        Lamaba la = new Lamaba();
        la.pr();
    }
}