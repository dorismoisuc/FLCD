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

    /*
        addElement:
            input <- key
            output -> if the element is already in the hash table: "Element is already added on position " {and the position}
                      if the element is not in the hash table: "Element added on position " {and the position}
    */
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

    /*
        searchElement:
            we search for an element in the hash table by computing the hash value and searching for the element in
            that position. if we don't find it in that position we increase until the element is found, if it's not found
            "Element not found" string is returned
            input <- key
            output -> if we reached the end of the hashTable: "Element not found"
                      if the element is found: "Element" + key + "position" + pos
     */
    public String searchElement(String key){
        int i = hashFunction(key);
        while (hashTable[i] != null) {
            if (hashTable[i].equals(key)) {
                return "Element " + hashTable[i] + " found at position " + i;
            }
            i++;
            if (i>=size){
                i = 0;
            }
        }
        return "Element not found";
    }

    public void printAll(){
        for (int i=0;i<hashTable.length;i++){
            System.out.println( "position: " + i + " element: "+hashTable[i]);
        }
    }
}