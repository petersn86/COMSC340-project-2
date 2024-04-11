import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SortingBasicOps
{
    int sortedInsertOps = 0;
    int insertionsortLinkedListOps = 0;
    public Node sortedInsert(Node sorted, Node newNode) { //helper method
        if (sorted == null || sorted.data >= newNode.data) { 
            newNode.next = sorted;
            sortedInsertOps += 4; // 3 for if statement, 1 for return
            return newNode;
        } 
        else {
            Node current = sorted;
            sortedInsertOps += 3; //from the original if
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
                sortedInsertOps += 3; //for each run of the while loop
            }
            newNode.next = current.next;
            current.next = newNode;
            sortedInsertOps += 1; //another for return statement
            return sorted;
        }
    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
    
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
    
        while (i <= mid) {
            temp[k++] = array[i++];
        }
    
        while (j <= end) {
            temp[k++] = array[j++];
        }
    
        for (i = start, k = 0; i <= end; i++, k++) {
            array[i] = temp[k];
        }
    }
    
    public static void insertionsortArray(int n, File file)  throws IOException{
        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader
        int insertionsortArrayOps = 0;
        int[] S = new int[n]; //n is size of file (# of lines)
        int m = 0; 
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S[m] = Integer.parseInt(line.replace(" ", "")); //store each line into an array
            m++;
        }

        br.close(); //close buffer reader



        long start = System.nanoTime();
        for (int i = 1; i < n; i++) { //implementation of insertion sort
            
            int x = S[i];
            int j = i - 1;
            insertionsortArrayOps++;
            while (j >= 0 && S[j] > x) { 
                S[j + 1] = S[j];
                j--;
                insertionsortArrayOps += 4; //2 for while loop ops, 2 for ops bellow it
            }
            S[j + 1] = x;
            insertionsortArrayOps += 1;
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Insertion w/ Arrays " + timeElapsed + " nanoseconds");
        System.out.println("Number of Basic Operations (Insertion w/ Arrays): " + insertionsortArrayOps);
    }

    public void insertionsortLinkedList(int n, File file) throws IOException{

        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader

        LinkedList S = new LinkedList();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S.append(Integer.parseInt(line.replace(" ", ""))); //store each line into linked list
        }

        br.close(); //close buffer reader

        long start = System.nanoTime();
        insertionsortLinkedListOps += 3; //adds 3 for if statment
        if (S.head == null || S.head.next == null) {
            insertionsortLinkedListOps += 1; //another 1 for the return statement
            return; // Exit if the list is empty or has only one element
        }
        
        Node sorted = null;
        Node current = S.head;
        while (current != null) {
            insertionsortLinkedListOps += 1; // for while check
            Node next = current.next;
            sorted = sortedInsert(sorted, current);
            insertionsortLinkedListOps += sortedInsertOps; //for the sorted insert ops
            current = next;
        }
        S.head = sorted;
        
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Insertion w/ LL " + timeElapsed + " nanoseconds");
        System.out.println("Number of Basic Operations (Insertion w/ LL): " + insertionsortLinkedListOps);
    }

    public void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    public static void main(String[] args) throws IOException{
        File file = new File("datafiles/random5k.txt"); 

        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        int lines = 0;

        while (bReader.readLine() != null){
            lines++;
        }

        int[] S = new int[lines]; //n is size of file (# of lines)
        int m = 0; 
        for (String line = bReader.readLine(); line != null; line = bReader.readLine()) {
            S[m] = Integer.parseInt(line.replace(" ", "")); //store each line into an array
            m++;
        }


        reader.close();
        bReader.close();


        SortingBasicOps sorter = new SortingBasicOps();
        insertionsortArray(lines, file);
        sorter.insertionsortLinkedList(lines, file);

        long start = System.nanoTime();
        sorter.mergeSort(S, 0, lines-1);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        System.out.println("Merge Sort " + timeElapsed + " nanoseconds");

    }

}