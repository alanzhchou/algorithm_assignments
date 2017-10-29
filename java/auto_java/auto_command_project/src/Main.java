import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class ShellTest {

    public static void main(String[] args) {
        Process process = null;
        InputStream inputStream = null;
        try {
            process = Runtime.getRuntime().exec("cmd.exe /k start A:\\gitStore\\Alogorithm_C\\Algorithm_C\\java\\auto_java\\test.bat");
            process.waitFor();
            inputStream = process.getInputStream();
            byte[] b = new byte[1024];
            int size = 0;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((size = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, size);
            }
            System.out.println(outputStream.toString("gbk"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != process) {
                process.destroy();
            }
        }
    }

}
//
//class InputReader{
//    public BufferedReader reader;
//    public StringTokenizer tokenizer;
//
//    public InputReader(InputStream stream){
//        reader = new BufferedReader(new InputStreamReader(stream),32768);
//        tokenizer = null;
//    }
//
//    public String next(){
//        while (tokenizer == null || !tokenizer.hasMoreTokens()){
//            try{
//                tokenizer = new StringTokenizer(reader.readLine());
//            }catch (IOException e){
//                throw new RuntimeException();
//            }
//        }
//        return  tokenizer.nextToken();
//    }
//
//    public int nextInt(){
//        return Integer.parseInt(next());
//    }
//
//    public String nextLine(){
//        while (tokenizer == null || !tokenizer.hasMoreTokens()){
//            try{
//                tokenizer = new StringTokenizer(reader.readLine());
//            }catch (IOException e){
//                throw new RuntimeException();
//            }
//        }
//        StringBuffer temp = new StringBuffer("");
//        while (tokenizer.hasMoreTokens()){
//            temp.append(tokenizer.nextToken());
//            temp.append(" ");
//        }
//        temp.deleteCharAt(temp.length()-1);
//        System.out.println(temp.toString());
//        return  temp.toString();
//    }
//}