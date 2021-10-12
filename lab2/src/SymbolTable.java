public class SymbolTable {
    private final int size;
    private final String[] hashTable;

    public SymbolTable(int size) {
        this.size = size;
        this.hashTable = new String[size];
    }

    private int hashFunction(String key) {
        // sum of ascii codes
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % size;
    }

    public String addElement(String key){
        int i = hashFunction(key);

        // Linear probing for the collision resolution
        while (hashTable[i] != null) {
            if (hashTable[i].equals(key)) {
                return "Element is already added on position " + i;
            }
            i++;
            if (i>=size){
                i=0;
            }
        }
        hashTable[i]=key;
        return "Element added on position " + i;
    }

    public int searchElement(String key){
        int i = hashFunction(key);

        while (hashTable[i] != null) {
            if (hashTable[i].equals(key)) {
                return i;
            }
            if(i>=size-1){
                return -1;
            }
            i++;
        }

        return -1;
    }

    public void printAll(){
        for (int i=0;i<hashTable.length;i++){
            System.out.println( "position: " + i + " element: "+hashTable[i]);
        }
    }
}