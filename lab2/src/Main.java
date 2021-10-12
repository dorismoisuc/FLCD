public class Main {

    public static void main(String[] args) {
        var symbolTable = new SymbolTable(10);

        System.out.println(symbolTable.addElement("a"));
        System.out.println(symbolTable.addElement("b"));
        System.out.println(symbolTable.addElement("c"));
        System.out.println(symbolTable.addElement("k"));
        System.out.println(symbolTable.addElement("x"));
        System.out.println(symbolTable.addElement("z"));
        System.out.println(symbolTable.addElement("l"));
        System.out.println(symbolTable.addElement("l"));


        System.out.println("position: " + symbolTable.searchElement("a"));
        System.out.println("position: " + symbolTable.searchElement("b"));
        System.out.println("position: " + symbolTable.searchElement("y"));
        System.out.println("position: " + symbolTable.searchElement("x"));
        System.out.println("position: " + symbolTable.searchElement("c"));

        symbolTable.printAll();
    }
}