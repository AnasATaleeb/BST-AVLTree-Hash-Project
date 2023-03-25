package application;

public class Hash<T extends Comparable<T>> {

    private HNode<T>[] nodes = new HNode[11];
    private int tableSize = 11;
    private int currentSize = 0;
    
    static int counter = 0;

    public Hash() {
        super();
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new HNode<T>(null, 'E');
        }
    }

    public int hashCode(T data){
        return data.hashCode() % (tableSize);
    }

    public void insert(T s) {
        if(s != null) {
            if (currentSize == tableSize / 2) {
                reHash();
                System.out.println("Rehashing " + counter + " times");
            }
            int index = hashCode(s);
            int currIndex = index;
            int i = 0;
            while (nodes[currIndex].getF() == 'F') {
                currIndex = (int) (index + Math.pow(i, 2)) % tableSize;
                i++;
            }
            nodes[currIndex].setF('F');
            nodes[currIndex].setData(s);
            currentSize++;
        }
    }

    public T search(T data) {
        if(data != null && currentSize > 0) {
            int index = hashCode(data);
            int currIndex = index;
            int i = 0;
            while (nodes[currIndex].getF() == 'F' && nodes[currIndex].getData().compareTo(data) != 0) {
                currIndex = (int) (index + Math.pow(i, 2)) % tableSize;
                i++;
            }
            if(nodes[currIndex].getData() != null) {
                if(nodes[currIndex].getData().compareTo(data) == 0) {
                   return nodes[currIndex].getData();
                }
            }
        }
        return null;
    }


    public HNode<T>[] getNodes() {
        return nodes;
    }

    public void setNodes(HNode<T>[] nodes) {
        this.nodes = nodes;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public void delete(T s) {
        System.out.println("Deleting " + s);
        if(s != null) {
            System.out.println("Deleting " + s);
            if(currentSize < tableSize / 4) {
                shrink();
                System.out.println("Shrinking");
            }
            int index = hashCode(s);
            int currIndex = index;
            int i = 0;
            while (nodes[currIndex].getF() == 'F' && nodes[currIndex].getData().compareTo(s) != 0) {
                currIndex = (int) (index + Math.pow(i, 2)) % tableSize;
                i++;
            }
            if(nodes[currIndex].getData() != null) {
                if(nodes[currIndex].getData().compareTo(s) == 0) {
                    nodes[currIndex].setF('D');
                    currentSize--;
                }
            }
        }
    }

    public void shrink() {
        HNode<T>[] oldNodes = nodes;
        nodes = new HNode[tableSize / 2];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new HNode<T>(null, 'E');
        }
        tableSize = nodes.length;
        currentSize = 0;
        for (int i = 0; i < oldNodes.length; i++) {
            if(oldNodes[i].getF() == 'F') {
                insert(oldNodes[i].getData());
            }
        }
    }


    public void reHash() {
        HNode<T>[] oldNodes = nodes;
        counter++;
        nodes = new HNode[nextPrime(tableSize * 2)];
        tableSize = nodes.length;
        currentSize = 0;
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new HNode<T>(null, 'E');
        }
        for (int i = 0; i < oldNodes.length; i++) {
            if (oldNodes[i].getF() == 'F') {
                insert(oldNodes[i].getData());
            }
        }
    }

    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        for (; !isPrime(n); n += 2)
            ;
        return n;
    }

    public int getHash(T key) {
        return key.hashCode();
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n / 2; i += 2)
            if (n % i == 0)
                return false;
        return true; 
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < nodes.length; i++) {
            s += nodes[i].toString() + "\n";
        }
        System.out.println(currentSize + " elements in the hash table" + tableSize);
        return s;
    }

}