import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyScanner {

    private final List<String> tokens;
    private final List<String> operators;
    private final List<String> separators;
    private final SymbolTable symbolTable;
    private final PIF pif;
    private final int size = 120;

    private final String tokensFile = "lab2/src/token.in";
    private final String pifOutput = "lab2/src/pif.out";
    private final String stOutput = "lab2/src/st.out";


    public MyScanner() {
        this.tokens = new ArrayList<>();
        this.operators = Arrays.asList("+","-","*","/","<",">","<=",">=","=","!=","^","$","++","--","%","->","<-");;
        this.separators = Arrays.asList("[","]","{","}","(",")",":"," ","\n");;
        this.symbolTable = new SymbolTable(this.size);
        this.pif = new PIF();
        this.readTokens();
    }

    private void readTokens() {
        File file = new File(this.tokensFile);
        Scanner scanner = null;
        try{
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    private void writeToFile(String fileName, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }

    private void writeResultsToFiles() {
        try {
            writeToFile(this.pifOutput, this.pif.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeToFile(this.stOutput, this.symbolTable.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCharacter(String character) {
        var pattern = Pattern.compile("^'[a-zA-Z0-9]'$");
        return pattern.matcher(character).matches();
    }

    public boolean isNumber(String integer) {
        var pattern = Pattern.compile("[-]?\\\\d+");
        return pattern.matcher(integer).matches();
    }

    public boolean isStringConst(String st){
        String stringConstant = "^\"[a-zA-Z0-9 ]*\"$";
        return st.matches(stringConstant);
    }

    private boolean isBooleanConstant(String bool){
        return bool.equals("true") || bool.equals("false");
    }


}
