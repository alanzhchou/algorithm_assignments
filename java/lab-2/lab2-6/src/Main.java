public class Main {

    public static void main(String[] args) {

    }
}

class SeatArrangement{
    private int[] availableSeats;
    private int  numberOfStudents;
    private int maxSpace = 1;

    //此三个函数用来构建对象，并赋值最初的，用来进行算法的可用位置数据的数组
    public SeatArrangement(){
    }
    public SeatArrangement(int[] availableSeats){
        setAvailableSeats(availableSeats);
    }
    public void setAvailableSeats(int[] availableSeats){
        this.availableSeats = availableSeats;
    }

    private void setNumberOfStudents(int numberOfStudents){
        this.numberOfStudents = numberOfStudents;
    }
    private void setMaxSpace(int maxSpace){
        this.maxSpace = maxSpace;
    }

    public int findMaxSpaceFor(int numberOfStudents){
        setNumberOfStudents(numberOfStudents);
        find(numberOfStudents);

        System.out.println(this.toString());
        return maxSpace;
    }

    //这里是实现set主要返回值：maxSpace的实际函数
    private void find(int numberOfStudents){
        int space = availableSeats[availableSeats.length-1]-availableSeats[0];



        setMaxSpace(space);
    }


    public String toString(){
        String result = "";
        return result;
    }
}
