package Question2;

public class ArrayHashTable extends HashTable{
    Object[][] table;
    int chainSize;
    int[] counts;
    
    public ArrayHashTable(){
        capacity = 10;
        chainSize = 5;
        table = new Object[capacity][];
        counts = new int[capacity];
    }
    @Override
    boolean add(Object obj) {
        int code = obj.hashCode() % capacity;
        if(table[code] == null){
            table[code] = new Object[chainSize];
            table[code][0] = obj;
            counts[code]++;
            return true;
        }else{
            //check if entry already exists
            if(chainContains(table[code], obj)){
                return false;
            }
            //find where to add entry and add it
            if(chain(table[code], obj)){
                counts[code]++;
                return true;
            }
            //if this line is reached the chain must be full
            // so double chain array size
            Object[] temp = new Object[table[code].length*2];
            System.arraycopy(table[code], 0, temp, 0, table[code].length-1);
            table[code] = temp;            
            //find where to add entry and add it
            if(chain(table[code], obj)){
                counts[code]++;
                return true;
            }
        }
        //if something went wrong
        System.out.println("add failed!");
        return false;
    }
    
    private boolean chain(Object[] chain, Object obj){
        for(int i = 0; i < chain.length-1; i++){
            if(chain[i] == null){
                chain[i] = obj;
                return true;                    
            }
        } 
        return false;
    }
    
    private boolean chainContains(Object[] chain, Object obj){
        if(chain != null){//make sure chain exists                
            for(int i = 0; i < chain.length-1; i++){
                if(chain[i] != null){//stop null pointer for .equals()
                    if(chain[i].equals(obj)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    boolean contains(Object obj) {               
        return chainContains(table[obj.hashCode() % capacity], obj);
    }

    @Override
    boolean remove(Object obj) {
        int code = obj.hashCode() % capacity;
        int removed = -1;
        // if element is not present return false
        if(table[code] == null || !chainContains(table[code], obj)){
            return false;           
        }else{//if element is present remove it
            for(int i = 0; i < table[code].length-1; i++){
                if(table[code][i] == obj){
                    table[code][i]=null;
                    removed = i;
                }
            }
            if(removed!=-1){//if removed move every element below up 1 position
                for(int i = removed; i < table[code].length-1; i++){
                    table[code][i]=table[code][i+1];
                }
            }
            counts[code]--;
            //if nothing in the chain, remove the chain
            if(counts[code] == 0){
                table[code] = null;
                //if the chain is now less than half full half the chain size.
            }else if(counts[code] < table[code].length/2 
                    && table[code].length/2 >= chainSize){
                Object[] temp = new Object[table[code].length/2];
                System.arraycopy(table[code], 0,
                        temp, 0, table[code].length/2);
                table[code] = temp;   
            }
            return true;
        }    
    }
    
//    public String printHash() {
//        String aString;     
//        aString = "";
//        int column;
//        int row;
//
//        for (row = 0; row < table.length; row++) {
//            for (column = 0; column < table[row].length; column++ ) {
//                aString = aString + " \t" + table[row][column];
//            }
//        aString = aString + "\n";
//        }
//
//        return aString;
//    }
}
