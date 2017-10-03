import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RandomArray rand1 = new RandomArray(10,2,109);
        rand1.toString();

        SeatArrangement testSeat1 = new SeatArrangement(rand1.getRandomArray());
        System.out.println(testSeat1.findMaxSpaceFor(7));
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
        int bigSpace = (availableSeats[availableSeats.length-1]-availableSeats[0])/(numberOfStudents-1);
        int smallSpace = 1;

        int space = 1;

        boolean flag = false;
        while (!flag){
            if (judge(bigSpace) == true){
                space = bigSpace;
                flag = true;
            }else if (judge(bigSpace) == false &&judge(smallSpace) == true){
                int midSpace = (bigSpace + smallSpace)/2;

                if (judge(midSpace) == true){
                    smallSpace = midSpace;
                }else {
                    bigSpace = midSpace;
                }
            }else if (judge(bigSpace) == false &&judge(smallSpace) == false){
                space = -1;
                flag = true;
            }
        }

        setMaxSpace(space);
    }

    private boolean judge(int testSpace){
        int counter = 1;
        int lastIndex = 0;
        for (int i=0; i<availableSeats.length; i++){
            if (availableSeats[i]-availableSeats[lastIndex] >= testSpace){
                lastIndex = i;
                counter++;
            }
        }
        boolean flag;
        if (counter >= numberOfStudents){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }


    public String toString(){
        String result = "";
        return result;
    }
}

//生成不重复随机int数组的类，构造函数中依次传入长度，上限，下限，用getRandomArray返回改数组，也可以用toString格式化返回该数组信息
class RandomArray{
    private int[] randomArray;

    //构造方法，用于传入长度，上限和下限
    public RandomArray(int length,int min,int max){
        setRandomArray(length,min,max);
    }
    //这里接受长度，上下限，生成所需的不重复的数组，set到私有变量randomArray中
    private void setRandomArray(int length,int min,int max){
        Random rand = new Random();
        if (length > (max - min + 1) || max < min) {
            randomArray = null;
        }else {
            int[] result = new int[length];

            int count = 0;
            while(count < length) {
                int randnum = rand.nextInt(max-1) + min;

                boolean judge = true;
                for (int j = 0; j < length; j++) {
                    if(randnum == result[j]){
                        judge = false;
                        break;
                    }
                }
                if(judge){
                    result[count] = randnum;
                    count++;
                }
            }
            Arrays.sort(result);
            randomArray = result;
        }
    }

    public int[] getRandomArray(){
        return randomArray;
    }

    public String toString(){
        String result = "";
        for (int i=0; i<randomArray.length; i++){
            result = result + String.valueOf(randomArray[i]) + "\t";
        }
        System.out.println(result);
        return result;
    }
}