import java.io.IOException;

public class LanguageGuesser {
    public static void  main(String[] args) throws IOException {
        String file_path = args[0];
        CompareResult result = new CompareResult(file_path);
        //result.toString();
        result.getResult();
    }
}
