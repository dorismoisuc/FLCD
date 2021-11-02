import java.util.HashMap;

public class PIF {

    private final HashMap<Integer,Integer> pif = new HashMap<>();

    public void add(int code, int position){
        pif.put(code,position);
    }

    public HashMap<Integer,Integer> getPif(){
        return pif;
    }

    public PIF(){}

    public String toString(){
        StringBuilder returningString= new StringBuilder("\nPROGRAM INTERNAL FORM" +
                "\nCode" +
                "              Pos in ST\n");
        for (var keyValue: pif.entrySet()){
            returningString.append(keyValue.getKey());
            returningString.append("             ");
            returningString.append(keyValue.getValue());
            returningString.append("\n");
        }
        returningString.append("\n");
        return returningString.toString();
    }

}
