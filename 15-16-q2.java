class HashTableData{
    private String key;
    public HashTableData(String key){
        this.key = key;

    }
    String getKey(){
        return key;
    }
}


class HashTable {
    private static final int DEF_MAX_HASH_SIZE = 10;
    HashTableData[] array;
    private int numOfElements;
    private int size;

    public HashTable() {
        setup(DEF_MAX_HASH_SIZE);
    }
    public HashTable(int maxNumber) {
        setup(maxNumber);
    }
    private void setup(int maxNumber) {
        size = maxNumber;
        array = new HashTableData[maxNumber];
        numOfElements = 0;
    }
    private int hashFunction(HashTableData data) {
        return data.getKey().hashCode()%size;

    }
    public boolean retrieve(HashTableData searchElem) {
        int index = hashFunction(searchElem);
        int numOfTrys = 0;

        while (array[index] != null && !array[index].getKey().equals(searchElem.getKey())) {
            if (++numOfTrys == size) {
                return false;
            }
            index++;
        }
        if (array[index] == null) {
            return false;
        }
        return true;
    }
    public boolean insert(HashTableData newElem) {
        int index = getFirstEmptyIndex(newElem);
        if (index == -1) {
            return false;
        }
        array[index] = newElem;
        return true;
    }

    private int getFirstEmptyIndex(HashTableData newElem) {
        int index = hashFunction(newElem);
        int numOfTrys = 0;
        while (array[index++] != null) {
            if (++numOfTrys == size) {
                return -1;
            }
        }
        return index;
    }

    public boolean remove(HashTableData remElem) {
        int index = hashFunction(remElem);
        int numOfTrys = 0;

        while (array[index] != null && !array[index].getKey().equals(remElem.getKey())) {
            if (++numOfTrys == size) {
                return false;
            }
            index++;
        }
        if (array[index] == null) {
            return false;
        }
        array[index] = null;
        return true;
    }
    public void clear() {
        array = new HashTableData[size];
    }
    public boolean isEmpty() {
        return numOfElements == 0;

    }
    public boolean isFull(){
        return numOfElements == size;

    }
}