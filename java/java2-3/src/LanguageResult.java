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
        ReadCnf cnf = new ReadCnf();

        PathMapping mapping = new PathMapping();
        mapping.getPathMapping(cnf.getStop_words_dir());
        mapping.toString();
    }
}
