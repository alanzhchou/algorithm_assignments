/**
 * Created by ZH-AlanChou on 2017/9/25.
 */
public class main2 {
    public static void main(String args[]){
        //用数字100,99,12-0 代表大王，小王，2，A，K,Q,J,10,9，8，7，6，5，4，3
        //出RJ,WJ外每个数字最后的整数表示为“原有整数+13*出现次数”
        String[] Dizhu ={
                "WJ","RJ","9","8","K","Q","Q","4","3","8",
                "5","4","3","8","K","6","J","J","7","A"
                            };
        String[] Farmer = {
                    "RJ","K","2","2","K","K","K","2","9","9",
                    "A","6","6","5","5","9","9"
                            };
        farmer framer1 = new farmer(Farmer);
        //or use public method "getFarmer" to get the sorted String array

        System.out.println();

        dizhu dizhu1 = new dizhu(Dizhu);
        //or use public method "getDizhu" to get the sorted String array
    }
}

//处理farmer的类farmer
/*
*public方法有下：
*farmer(String[] a):传入一组字符串数组代表的排序，构造方法返回该字符串数组的对象,默认打印排序前后数组（通过printFarmer()方法）
*getFarmer():返回（已排序的）farmer代表牌的字符串数组（return String[]）,不进行打印。
*/
class farmer{
    //储存输入和输出供阅读的字符数组
    private String[] cards = new String[17];
    //储存实现算法的整型数组（字符数组转化）
    private int[] forSort = new int[17];
    //构造函数
    public farmer(String[] a){
        setCards(cards,a);
        cardsToSort();
        printFarmer();
        sort(forSort);
        sortToCards();
        printFarmer();
    }

    //完整的排序，将无序int数组按要求排序
    private void sort(int[] arr){
        insertSort(arr);
        toHash(forSort,findCount(forSort));
        insertSort(arr);
        outHash(forSort);
    }
    //用于排序完成后，将进行计算的较大牌面值回到可进行字符串转化的较小牌面值
    private void outHash(int num[]){
        for (int i=0; i<num.length; i++){
            if (num[i]!=100&&num[i]!=99)
                num[i] = num[i]%13;
        }
    }
    //用于排序完成前，将进行字符串转化的较小牌面值换成进行计算的较大牌面值
    private void toHash(int num[],int count[]){
        for (int i=0; i<num.length; i++){
            if (num[i]!=100&&num[i]!=99)
            num[i]+=count[i]*13;
        }
    }
    //计算每种牌各出现多少次
    private int[] findCount(int[] arr){
        int kind =0;
        int temp = -1;
        for (int i=0; i<arr.length; i++){
            if (arr[i]>temp){
                temp=arr[i];
                kind++;
            }
        }

        int[] count = new int[arr.length];
        for (int i=0; i<count.length; i++){
            count[i] = 1;
        }

        for (int i=0; i<arr.length; i++){
            if (i==0||arr[i]>arr[i-1]){
                for (int j=i+1; j<arr.length; j++){
                    if (arr[j]==arr[i]){
                        count[i]++;
                    }
                }
            }
            for (int j=0; j<arr.length; j++){
                if (arr[j]==arr[i]){
                    count[j] = count[i];
                }
            }
        }
        return count;
    }
    //插入排序进行最终转化成的大牌面值数组排序
    private void insertSort(int arr[]){
        int i, j;
        int temp = 0;
        for(i = 1; i < arr.length; i++)
        {
            temp = arr[i]; //取出一个元素
            for(j = i; j > 0 && temp < arr[j - 1]; j--)       //取出的元素和其已排序的元素逐一比较
            {
                //如果前面的元素较大，则后移一位
                if(temp < arr[j - 1])
                {
                    arr[j] = arr[j - 1];
                }
            }
            //取出的元素填充到前面的元素空位，即被排序数放到正确的位置
            arr[j] = temp;
        }
    }
    //更新forsort数组，将牌面的字符串数组转化为计算的整型数组
    private void cardsToSort(){
        for (int i=0; i<cards.length; i++){
            switch (cards[i]){
                case "RJ":forSort[i] = 100;break;
                case "WJ":forSort[i] = 99;break;
                case "2":forSort[i] = 12;break;
                case "A":forSort[i] = 11;break;
                case "K":forSort[i] = 10;break;
                case "Q":forSort[i] = 9;break;
                case "J":forSort[i] = 8;break;
                case "10":forSort[i] = 7;break;
                case "9":forSort[i] = 6;break;
                case "8":forSort[i] = 5;break;
                case "7":forSort[i] = 4;break;
                case "6":forSort[i] = 3;break;
                case "5":forSort[i] = 2;break;
                case "4":forSort[i] = 1;break;
                case "3":forSort[i] = 0;break;
            }
        }
    }
    //更新cards数组，将计算的整型数组转化为牌面的字符串数组
    private void sortToCards(){
        for (int i=0; i<forSort.length; i++){
            switch (forSort[i]){
                case 100: cards[i] = "RJ";break;
                case 99: cards[i] = "WJ";break;
                case 12: cards[i] = "2";break;
                case 11: cards[i] = "A";break;
                case 10: cards[i] = "K";break;
                case 9: cards[i] = "Q";break;
                case 8: cards[i] = "J";break;
                case 7: cards[i] = "10";break;
                case 6: cards[i] = "9";break;
                case 5: cards[i] = "8";break;
                case 4: cards[i] = "7";break;
                case 3: cards[i] = "6";break;
                case 2: cards[i] = "5";break;
                case 1: cards[i] = "4";break;
                case 0: cards[i] = "3";break;
            }
        }
    }
    //取构造函数输入字符串，赋值给内部cards数组
    private void setCards(String[] dest,String[] src){
        for (int i=0; i<dest.length; i++){
            dest[i] = src[i];
        }
    }
    //打印字符串牌面数组
    private void printFarmer(){
        for (int i=0; i<cards.length; i++){
            System.out.printf("%s\t",cards[i]);
        }
        System.out.println();
    }

    public String[] getFarmer(){
        return cards;
    }
}

//处理dizhu的类dizhu
/*
*public方法有下：
*dizhu(String[] a):传入一组字符串数组代表的排序，构造方法返回该字符串数组的对象,默认打印排序前后数组（通过printDizhu()方法）
*getDizhu():返回（已排序的）dizhu代表牌的字符串数组（return String[]）,不进行打印。
*/
class dizhu{
    private String[] cards = new String[20];
    private int[] forSort = new int[20];

    public dizhu(String[] a){
        setCards(cards,a);
        cardsToSort();
        printDizhu();
        sort(forSort);
        sortToCards();
        printDizhu();
    }

    private void sort(int[] arr){
        insertSort(arr);
        toHash(forSort,findCount(forSort));
        insertSort(arr);
        outHash(forSort);
    }

    private void outHash(int num[]){
        for (int i=0; i<num.length; i++){
            if (num[i]!=100&&num[i]!=99)
                num[i] = num[i]%13;
        }
    }

    private void toHash(int num[],int count[]){
        for (int i=0; i<num.length; i++){
            if (num[i]!=100&&num[i]!=99)
                num[i]+=count[i]*13;
        }
    }

    private int[] findCount(int[] arr){
        int kind =0;
        int temp = -1;
        for (int i=0; i<arr.length; i++){
            if (arr[i]>temp){
                temp=arr[i];
                kind++;
            }
        }

        int[] count = new int[arr.length];
        for (int i=0; i<count.length; i++){
            count[i] = 1;
        }

        for (int i=0; i<arr.length; i++){
            if (i==0||arr[i]>arr[i-1]){
                for (int j=i+1; j<arr.length; j++){
                    if (arr[j]==arr[i]){
                        count[i]++;
                    }
                }
            }
            for (int j=0; j<arr.length; j++){
                if (arr[j]==arr[i]){
                    count[j] = count[i];
                }
            }
        }
        return count;
    }

    private void insertSort(int arr[]){
        int i, j;
        int temp = 0;
        for(i = 1; i < arr.length; i++)
        {
            temp = arr[i]; //取出一个元素
            for(j = i; j > 0 && temp < arr[j - 1]; j--)       //取出的元素和其已排序的元素逐一比较
            {
                //如果前面的元素较大，则后移一位
                if(temp < arr[j - 1])
                {
                    arr[j] = arr[j - 1];
                }
            }
            //取出的元素填充到前面的元素空位，即被排序数放到正确的位置
            arr[j] = temp;
        }
    }

    private void cardsToSort(){
        for (int i=0; i<cards.length; i++){
            switch (cards[i]){
                case "RJ":forSort[i] = 100;break;
                case "WJ":forSort[i] = 99;break;
                case "2":forSort[i] = 12;break;
                case "A":forSort[i] = 11;break;
                case "K":forSort[i] = 10;break;
                case "Q":forSort[i] = 9;break;
                case "J":forSort[i] = 8;break;
                case "10":forSort[i] = 7;break;
                case "9":forSort[i] = 6;break;
                case "8":forSort[i] = 5;break;
                case "7":forSort[i] = 4;break;
                case "6":forSort[i] = 3;break;
                case "5":forSort[i] = 2;break;
                case "4":forSort[i] = 1;break;
                case "3":forSort[i] = 0;break;
            }
        }
    }

    private void sortToCards(){
        for (int i=0; i<forSort.length; i++){
            switch (forSort[i]){
                case 100: cards[i] = "RJ";break;
                case 99: cards[i] = "WJ";break;
                case 12: cards[i] = "2";break;
                case 11: cards[i] = "A";break;
                case 10: cards[i] = "K";break;
                case 9: cards[i] = "Q";break;
                case 8: cards[i] = "J";break;
                case 7: cards[i] = "10";break;
                case 6: cards[i] = "9";break;
                case 5: cards[i] = "8";break;
                case 4: cards[i] = "7";break;
                case 3: cards[i] = "6";break;
                case 2: cards[i] = "5";break;
                case 1: cards[i] = "4";break;
                case 0: cards[i] = "3";break;
            }
        }
    }

    private void setCards(String[] dest,String[] src){
        for (int i=0; i<dest.length; i++){
            dest[i] = src[i];
        }
    }

    private void printDizhu(){
        for (int i=0; i<cards.length; i++){
            System.out.printf("%s\t",cards[i]);
        }
        System.out.println();
    }

    public String[] getDizhu(){
        return cards;
    }
}

