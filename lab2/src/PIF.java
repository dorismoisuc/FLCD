import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PIF {

    private final List<Pair<String, Integer>> pairs;

    public PIF() {
        this.pairs = new ArrayList<>();
    }

    public void add(int token, int pos){
        Pair pair = new Pair(token,pos);
        this.pairs.add(pair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Token  Index\n");
        for(Pair<String, Integer> pair: pairs) {
            result.append(pair.toString()).append("\n");
        }

        return result.toString();
    }


}
