public class Pair<El1,El2>{
    private final El1 element1;
    private final El2 element2;

    public Pair(El1 element1, El2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    @Override
    public String toString(){
        return element1 + " " + element2;
    }
}
