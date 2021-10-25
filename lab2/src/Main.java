public class Main {

    public static void main(String[] args) {
        var symbolTable = new SymbolTable(10);

        System.out.println(symbolTable.addElement("a"));
        System.out.println(symbolTable.addElement("b"));
        System.out.println(symbolTable.addElement("c"));
        System.out.println(symbolTable.addElement("d"));
        System.out.println(symbolTable.addElement("e"));
        System.out.println(symbolTable.addElement("f"));
        System.out.println(symbolTable.addElement("g"));

        System.out.println(symbolTable.searchElement("h"));
        System.out.println(symbolTable.searchElement("a"));

        symbolTable.printAll();

    }
}