import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Specification {

    private final String[] operators = {"+","-","*","/","<",">","<=",">=","=","!=","^","$","++","--","%","->","<-","&","&&"};
    private final String[] separators = {"{","}","(",")",":"," ","\n"};
    private final String[] reservedWords = {"number","write","read","if","then","else","else if","until","while","for","string","char"};
    private final HashMap<String,Integer> codification;

    public Specification() {
        this.codification = generateCodification();
    }

    private HashMap<String, Integer> generateCodification() {
        HashMap<String,Integer> codification = new HashMap<>();

        codification.put("id",0);
        codification.put("const",1);

        int code=2;

        for (String separator: separators){
            codification.put(separator,code);
            code++;
        }

        for (String operator: operators){
            codification.put(operator,code);
            code++;
        }

        for (String reservedWord: reservedWords) {
            codification.put(reservedWord,code);
            code++;
        }

        return codification;
    }

    public HashMap<String,Integer> getCodification(){
        return codification;
    }

    public List<String> getOperators(){
        return Arrays.asList(operators);
    }

    public List<String> getSeparators(){
        return Arrays.asList(separators);
    }

    public List<String> getRw(){
        return Arrays.asList(reservedWords);
    }


    @Override
    public String toString(){
        return codification.toString();
    }
}
