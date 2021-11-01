import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Specification {

    private final List<String> operators = Arrays.asList("+","-","*","/","<",">","<=",">=","=","!=","^","$","++","--","%","->","<-");
    private final List<String> separators = Arrays.asList("[","]","{","}","(",")",":"," ","\n");
    private final List<String>  reservedWords = Arrays.asList("number","write","read","if","then","else","else if","until","while","for","string","char");
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


    public boolean operator(String operator){
        return operators.contains(operator);
    }

    public boolean separator(String separator){
        return separators.contains(separator);
    }

    public boolean reservedWord(String reservedWord){
        return reservedWords.contains(reservedWord);
    }

    public boolean isStringANumber(String number){
        Pattern pattern = Pattern.compile("[-+]?[0-9]*\\\\.?[0-9]+$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public boolean isStringAChar(String charac){
        Pattern pattern = Pattern.compile("^'[a-zA-Z0-9]'$");
        Matcher matcher = pattern.matcher(charac);
        return matcher.matches();
    }

    public List<String> getOperators(){
        return this.operators;
    }

    public List<String> getSeparators(){
        return this.separators;
    }

    public List<String> getRw(){
        return this.reservedWords;
    }


    @Override
    public String toString(){
        return codification.toString();
    }
}
