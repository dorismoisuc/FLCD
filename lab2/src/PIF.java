import java.util.ArrayList;
import java.util.List;

public class PIF {

    private final List<Pair<Integer,Integer>> pif = new ArrayList<>();

    public void add(int code, int position){
        pif.add(new Pair<>(code,position));
    }

    public List<Pair<Integer,Integer>> getPif(){
        return pif;
    }

    public PIF(){}

    public String toString(){
        StringBuilder returningString= new StringBuilder("PROGRAM INTERNAL FORM" +
                "\nCode" +
                "              Pos in ST\n");
        for (Pair<Integer,Integer> pair: pif){
            returningString.append(pair.getFirst());
            returningString.append("             ");
            returningString.append(pair.getSecond());
            returningString.append("\n");
        }
        returningString.append("\n");
        return returningString.toString();
    }

}
