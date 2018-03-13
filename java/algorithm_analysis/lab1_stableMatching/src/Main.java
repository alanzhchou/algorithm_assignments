import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        MatchEvent event = new MatchEvent();
        event.initPeople();
        event.initMatch();
        System.out.println(event);
    }
}

class MatchEvent{
    public static InputReader reader = new InputReader(System.in);

    private int peopleNumber;
    private ArrayList<Person> dalao = new ArrayList<Person>();
    private ArrayList<Person> mengxin = new ArrayList<Person>();
    private HashMap<String,Person> nameToclass = new HashMap<String,Person>();


    public void initPeople(){
        try{
            peopleNumber = reader.nextInt();
        }catch (Exception e){
            e.printStackTrace();
        }
        initName();
        initPerfer();
    }

    public void initMatch(){
        while(!allMatched()){
            for (int i=0; i < dalao.size(); i++){
                Person thisDalao = dalao.get(i);
                if (thisDalao.getMatchedName().equals("")){
                    thisDalao.match(nameToclass);
                }
            }
        }
    }

    private boolean allMatched(){
        boolean dAllMatched = false;

        for (int i=0; i < dalao.size(); i++){
            if (dalao.get(i).getMatchedName().equals("")){
                break;
            } else if(i == dalao.size()-1) {
                dAllMatched = true;
            }
        }
        return dAllMatched;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        dalao.stream().forEach(q->{
            buffer.append(q.getMatchedName() + " ");
        });
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }

    private void initName(){
        for (int i=0; i< peopleNumber*2 ; i++){
            try{
                if (i < peopleNumber){
                    dalao.add(new Person(reader.next()));
                }else {
                    mengxin.add(new Person(reader.next()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        dalao.stream().forEach(q->{nameToclass.put(q.getName(),q);});
        mengxin.stream().forEach(q->{nameToclass.put(q.getName(),q);});
    }

    private void initPerfer(){
        for (int i=0; i < peopleNumber*2; i++){
            for (int j=0; j < peopleNumber; j++){
                if (i < peopleNumber){
                    try{
                        dalao.get(i).setPerfer(reader.next());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try{
                        mengxin.get(i-peopleNumber).setPerfer(reader.next());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Person{
    private String name = "";
    private ArrayList<String> perfer = new ArrayList<String>();
    private int bestNow = 0;
    private String matchedName = "";

    public Person(String name){
        this.name = name;
    }

    public void match(HashMap<String,Person> nameToclass){
        Person mengxinToMatch = nameToclass.get(perfer.get(bestNow));
        ArrayList<String> mPerfer = mengxinToMatch.getPerfer();

        if(mengxinToMatch.getMatchedName().equals("")
                || mPerfer.indexOf(name) < mPerfer.indexOf(mengxinToMatch.getMatchedName())){
            if (!mengxinToMatch.getMatchedName().equals("")){
                nameToclass.get(mengxinToMatch.getMatchedName()).setMatchedName("");
            }
            this.setMatchedName(mengxinToMatch.getName());
            mengxinToMatch.setMatchedName(name);
        }
        bestNow++;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPerfer() {
        return perfer;
    }

    public void setPerfer(String name) {
        this.perfer.add(name);
    }

    public String getMatchedName() {
        return matchedName;
    }

    public void setMatchedName(String matchedName) {
        this.matchedName = matchedName;
    }
}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
        reader = new BufferedReader(new InputStreamReader(stream),32768);
        tokenizer = null;
    }

    public String next(){
        while (tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e){
                throw new RuntimeException();
            }
        }
        return  tokenizer.nextToken();
    }

    public int nextInt(){
        return Integer.parseInt(next());
    }
}
