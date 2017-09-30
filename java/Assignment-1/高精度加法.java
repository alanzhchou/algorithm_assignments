public class Main {
    public static void main(String[] args){
        int[] a = new int[1000];
        int[] b = new int[1000];
        int[] result = new int[1001];

        //初始化储存数组
        for (int i=0; i<a.length; i++){
            a[i]=b[i]=result[i] = 0;
        }
        result[result.length-1] = 0;

        String input1 = "111111111111111111111111111111111111111111111111111111111111111111";
        String input2 =                     "2222222222222222222222222222222222222222222222";
        //结果应该为       111111111111111111113333333333333333333333333333333333333333333333

        createData(a,input1);
        createData(b,input2);
        printarray(a);
        printarray(b);
        calculate(a,b,result);
        printarray(result);
    }
    //静态方法createData:将对应的字符串b转换为数组a储存
    public static void createData(int[] a, String b){
        for (int i=0; i<b.length(); i++){
            a[i] = b.charAt(i) - 48;
        }
    }
    //静态方法calculate:具体的计算方法，将整数数组a，b各位相加值的个位赋给c数组的对应项
    public static void calculate(int[] a, int[]b, int[]c){
        int temp = 0;
        for (int i=0; i<a.length; i++){
            c[i] = (a[i] + b[i])%10 +temp;
            if ((a[i] + b[i]) > 10){
                temp = 1;
            }else {
                temp = 0;
            }
        }
    }
    //静态方法printarray:打印一个整数数组，忽略其高位含零的项，并在最后一位换行
    public static void printarray(int[] a){
        int temp1 = a[a.length-1];
        for (int i=a.length-1; i>0; i--){
            if (temp1 == 0 && a[i] == 0){
                temp1 = a[i];
            }else{
                System.out.print(a[i]);
            }
        }
        System.out.println();
    }
}


//
//class BigAdd{
//    private int[] number1;
//    private int[] number2;
//    private int[] result;
//
//    public BigAdd(String str1,String str2){
//        boolean result1 = str1.matches("[0-9]+");
//        boolean result2 = str1.matches("[0-9]+");
//        if(!result1||!result2){
//            throw new IllegalArgumentException("String is not a number.");
//        }else {
//
//        }
//    }
//
////    private int[] add(int[] num1,int[] num2){
////
////    }
//
////    private int[] getSuitableArray(int[] num1,int[] num2){
////        int[] result;int length = 0;
////        if (num1.length==num2.length){
////            if ((num1[num1.length-1]+num2[num2.length-1])>=10){
////                int[] a = new int[num1.length+1];
////                length = num1.length+1;
////                result = a;
////            }else if ((num1[num1.length-1]+num2[num2.length-1])<10&&(num1[num1.length-1]+num2[num2.length-1])>0){
////                int[] a = new int[num1.length];
////                length = num1.length;
////                result = a;
////            }
////        }else if (num1.length>num2.length&&num1[num1.length-1] == 9&&(num1[num1.length-2]+num2[num2.length-1]>=10)){
////            int[] a = new int[num1.length+1];
////            length = num1.length+1;
////            result = a;
////        }else if (num1.length<num2.length&&num2[num2.length-1] == 9&&(num2[num1.length-2]+num1[num1.length-1]>=10)){
////            int[] a = new int[num2.length+1];
////            length = num2.length+1;
////            result = a;
////        }else {
////            if (num1.length>num2.length){
////                int[] a = new int[num1.length];
////                length = num1.length;
////                result = a;
////            }else if (num1.length<num2.length){
////                int[] a = new int[num2.length];
////                length = num2.length;
////                result = a;
////            }
////        }
////        for (int i=0; i<length; i++){
////            result[i] = 0;
////        }
////        return result;
////    }
//
//    private int[] toIntArray(String str){
//        int[] num1 = new int[str.length()];
//        for (int i=0; i<str.length(); i++){
//            num1[i] = str.charAt(i) - 48;
//        }
//        return num1;
//    }
//
//    private void setNum1(int[] num){
//        number1 = num;
//    }
//    private void setNum2(int[] num){
//        number2 = num;
//    }
//
//    public int[] getresult(){
//        return result;
//    }
//}