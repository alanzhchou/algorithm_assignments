import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by ZH-AlanChou on 2017/10/13.
 */
public class LanguageResult {
    public static void  main(String[] args) throws IOException {


        WordsLibrary lib = new WordsLibrary();
        lib.toString();
        System.out.println("++++++++++++++++++++++++");
        lib.getAllToken("german.txt");




//        lib.getMapping();


//        Tokenizer fileToken = new Tokenizer("languages\\english.txt");
//        String a = fileToken.nextToken();
//        while (a!=null){
//            System.out.println(a);
//            a = fileToken.nextToken();
//        }
    }
}
