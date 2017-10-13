import java.io.IOException;

/**
 * Created by ZH-AlanChou on 2017/10/13.
 */

/*
此类用来对比，输入文件和文件库中的各个参照文件的相似程度，
按最高相似度，输出为String language，即代表该文件中的主要语言
 */
public class CompareResult {
    private String[] fileName;
    private String[] filePath;
    private int size = 0;
    private float[] similarity;

    private String[] current_file_lib;

    public CompareResult(String current_file_path) throws IOException {
        Tokenizer current_file = new Tokenizer(current_file_path);
        this.current_file_lib = current_file.getAllTokens();

        WordsLibrary lib = new WordsLibrary();
        this.fileName = lib.getFileName();
        this.filePath = lib.getFilePath();
        this.size = lib.getSize();
        similarity = new float[this.size];

        this.fill_one_similarity();
    }

    private void fill_one_similarity() throws IOException {
        int all = current_file_lib.length-1;

        for (int index=0; index<fileName.length; index++){
            int same = 0;
            Tokenizer lib = new Tokenizer(filePath[index]);
            String a = lib.nextToken();
            while (a!=null){
                for (int i=0; i<current_file_lib.length-1; i++){
                    if (a.equals(current_file_lib[i])){
                        same++;
                    }
                }
                a = lib.nextToken();
            }
            similarity[index] = (float) same/all;
        }
    }

    public String getResult(){
        String result_language = this.fileName[getMaxIndex()].split("\\.")[0];
        System.out.printf("the text maybe %s\n",result_language);
        return result_language;
    }

    public String toString(){
        for (int i=0; i<this.similarity.length; i++){
            System.out.printf("%s --> %.3f\n",this.fileName[i],this.similarity[i]);
        }
        return null;
    }

    private int getMaxIndex(){
        int index = 0;
        float max = -1;
        for (int i=0; i<this.similarity.length; i++){
            if (this.similarity[i] > max){
                max = this.similarity[i];
                index = i;
            }
        }
        return index;
    }
}
