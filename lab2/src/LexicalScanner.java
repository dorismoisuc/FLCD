import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalScanner {
    SymbolTable symbolTable = new SymbolTable(50);
    PIF pif = new PIF();
    Specification specif = new Specification();

    public LexicalScanner(){
    }

    public void scan(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int i = 0;
            String line;
            StringBuilder result = new StringBuilder();

            while ((line=bufferedReader.readLine())!=null){
                List<String> tokensList = tokenizeLine(line);
                readLine(tokensList, i, result);
                i++;
            }

            writePif(fileName,result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLine(List<String> tokensList, int line, StringBuilder result) {
        for (int i=0;i<tokensList.size();++i){
            String token=tokensList.get(i);

            if (isConstant(token)){
                //1
                int code = specif.getCodification().get("const");
                symbolTable.addElement(token);
                var pos = symbolTable.searchElement(token);
                pif.add(code,pos);
                result.append("Token CONST " + token + " on position " + pos +  " code " + code + "\n");
            }

            else if (operator(token) || separator(token) || reservedWord(token)){
                int code = specif.getCodification().get(token);
                pif.add(code,-1);
                if(operator(token))
                    result.append("Token OPERATOR " + token + " on position " + -1 + " code " + code +"\n");
                else if (separator(token))
                    result.append("Token SEPARATOR " + token + " on position " + -1 + " code " + code + "\n");
                else if (reservedWord(token))
                    result.append("Token RW "+ token +" on position " + -1 +  " code " + code + "\n");
            }

            else if (isIdentifier(token)){
                //0
                int code = specif.getCodification().get("id");
                symbolTable.addElement(token);
                var pos = symbolTable.searchElement(token);
                pif.add(code,pos);
                result.append("Token IDENTIFIER " + token + " on position " + pos +  " code " + code + "\n");
            }


            else {
                result.append("\nLexical ERROR at line → " + line + ". Invalid token → " + token + "\n\n");
            }
        }
    }

    private List<String> tokenizeLine(String line) {
        List<String> tokens = new ArrayList<>();
        int i = 0;

        while (i < line.length()) {

            if (line.charAt(i) == '"') {
                // users declared strings
                StringBuilder stringToken = new StringBuilder("\"");
                i++;
                while (i < line.length() && line.charAt(i) != '"') {
                    stringToken.append(line.charAt(i));
                    i++;
                }
                stringToken.append("\"");
                i++;
                tokens.add(stringToken.toString());
            }
            else if (separator(String.valueOf(line.charAt(i)))) {
                // separators excluding space, tab and new line
                if (!(line.charAt(i) == ' ') && !(line.charAt(i) == '\t') && !(line.charAt(i) == '\n')) {
                    tokens.add(String.valueOf(line.charAt(i)));
                }
                i++;
            }
            else if (operator(String.valueOf(line.charAt(i)))) {
                // composed operators
                if(operator(String.valueOf(line.charAt(i + 1)))) {
                    tokens.add(line.charAt(i) + String.valueOf(line.charAt(i + 1)));
                    i = i + 2;
                }
                else {
                    tokens.add(String.valueOf(line.charAt(i)));
                    i++;
                }
            }
            else {
                // other tokens like reserved words, constants and identifiers
                StringBuilder token = new StringBuilder(String.valueOf(line.charAt(i)));
                i++;
                while (i < line.length() && !separator(String.valueOf(line.charAt(i))) && !operator(String.valueOf(line.charAt(i)))) {
                    token.append(line.charAt(i));
                    i++;
                }
                tokens.add(token.toString());
            }
        }

        return tokens;
    }

    public void writePif(String outputFile, StringBuilder result){
        try{
            outputFile = "lab2/src/files/" +  "output.txt";
            String pifFile = "lab2/src/files/pif.txt";
            String stFile = "lab2/src/files/st.txt";
            FileWriter fileWriterOutput= new FileWriter(outputFile);
            FileWriter fileWriterST= new FileWriter(stFile);
            FileWriter fileWriterPif= new FileWriter(pifFile);
            fileWriterST.write(symbolTable.toString());
            fileWriterOutput.write(result.toString());
            fileWriterPif.write(pif.toString());
            fileWriterST.close();
            fileWriterOutput.close();
            fileWriterPif.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public boolean operator(String operator){
        return this.specif.getOperators().contains(operator);
    }

    public boolean separator(String separator){
        return this.specif.getSeparators().contains(separator);
    }

    public boolean reservedWord(String reservedWord){
        return this.specif.getRw().contains(reservedWord);
    }


    public boolean isNumber(String token){
        String number = "^([+|-]?[1-9][0-9]*)|0$";
        return token.matches(number);
    }

    public boolean isString(String token){
        String string = "^\"[a-zA-Z0-9_.:;,?!*' ]*\"$";
        return token.matches(string);
    }

    public boolean isCharacter(String token){
        String character = "^\'[a-zA-Z0-9_.:;,?!*' ]\'$";
        return token.matches(character);
    }

    public boolean isConstant(String token){
        return isNumber(token) || isString(token) || isCharacter(token);
    }

    public boolean isIdentifier(String token){
        String pattern = "^[a-zA-Z]([a-zA-Z0-9_]*$)";
        return token.matches(pattern);
    }

}
