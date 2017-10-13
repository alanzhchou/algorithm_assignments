import java.io.IOException;

public class Language {
    public static void  main(String[] args) throws IOException {

        CompareResult result = new CompareResult("test.txt");
        result.toString();

        System.out.println("++++++++++++++++++++++++++++++++");
        result.getResult();
    }
}
