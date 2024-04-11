import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Sorting
{
    private static long mergeCount;
    private static long insertArraysCount;
    private static long insertLLCount;

    public Node sortedInsert(Node sorted, Node newNode) { //helper method
        if (sorted == null || sorted.data >= newNode.data) { 
            newNode.next = sorted;
            insertLLCount += 4;
            return newNode;
        } 
        else {
            Node current = sorted;
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
                insertLLCount += 3;
            }
            newNode.next = current.next;
            current.next = newNode;
            insertLLCount ++;
            return sorted;
        }
    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
    
        mergeCount += 3; // Account for end - start, then the +1, then the mid + 1
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
            mergeCount += 6;
        }
    
        while (i <= mid) {
            temp[k++] = array[i++];
            mergeCount += 3;
        }
    
        while (j <= end) {
            temp[k++] = array[j++];
            mergeCount += 3;
        }
    
        for (i = start, k = 0; i <= end; i++, k++) {
            array[i] = temp[k];
            mergeCount +=3;
        }
    }
    
    public void insertionsortArray(int n, File file)  throws IOException{

        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader

        int[] S = new int[n]; //n is size of file (# of lines)
        int m = 0; 
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S[m] = Integer.parseInt(line.replace(" ", "")); //store each line into an array
            m++;
        }

        br.close(); //close buffer reader

        insertArraysCount = 0;
        long start = System.nanoTime();
        for (int i = 1; i < n; i++) { //implementation of insertion sort
            int x = S[i];
            int j = i - 1;
            insertArraysCount += 3;
            while (j >= 0 && S[j] > x) { 
                S[j + 1] = S[j];
                j--;
                insertArraysCount += 5;
            }
            S[j + 1] = x;
            insertArraysCount ++;
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Insertion w/ Arrays " + timeElapsed + " nanoseconds");
        System.out.println("Insertion w/ Arrays Count " + insertArraysCount);

    }

    public void insertionsortLinkedList(int n, File file) throws IOException{
        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader

        LinkedList S = new LinkedList();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S.append(Integer.parseInt(line.replace(" ", ""))); //store each line into linked list
        }

        br.close(); //close buffer reader

        insertLLCount = 0;
        long start = System.nanoTime();

        if (S.head == null || S.head.next == null) {
            insertLLCount ++;
            return; // Exit if the list is empty or has only one element
        }
        
        Node sorted = null;
        Node current = S.head;
        while (current != null) {
            Node next = current.next;
            sorted = sortedInsert(sorted, current);
            current = next;
            insertLLCount += 1;
        }
        S.head = sorted;
        
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Insertion w/ LL " + timeElapsed + " nanoseconds");
        System.out.println("Insertion w/ LL count " + insertLLCount);

    }

    public void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeCount += 3; // Account for comparison, start + end, and /2
            mergeSort(array, start, mid);
            mergeCount += 1; // Account for mid + 1
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    public static void main(String[] args) throws IOException{
        String[] fileNames = {"inorder5k.txt", "inorder10k.txt", "inorder100k.txt", "random5k.txt", "random10k.txt", "random100k.txt", "rev5k.txt", "rev10k.txt", "rev100k.txt"};
        int counter = 0;
        for (int index = 0; index < fileNames.length; index++ ){
        File file = new File("datafiles/" + fileNames[counter]); 

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


        Sorting sorter = new Sorting();
        sorter.insertionsortArray(lines, file);
        sorter.insertionsortLinkedList(lines, file);

        mergeCount = 0;
        long start = System.nanoTime();
        sorter.mergeSort(S, 0, lines-1);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;

        System.out.println("Merge Sort " + timeElapsed + " nanoseconds");
        System.out.println("Merge Sort Count " + mergeCount);

        counter++;
        }
    }

}