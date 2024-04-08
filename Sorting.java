import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sorting
{
    public Node sortedInsert(Node sorted, Node newNode) { //helper method
        if (sorted == null || sorted.data >= newNode.data) { 
            newNode.next = sorted;
            return newNode;
        } else {
            Node current = sorted;
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            return sorted;
        }
    }
     
    public void merge(File outFile, File leftFile, File rightFile, int h, int m) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        BufferedReader leftReader = new BufferedReader(new FileReader(leftFile));
        BufferedReader rightReader = new BufferedReader(new FileReader(rightFile));

        String leftLine = leftReader.readLine();
        String rightLine = rightReader.readLine();

        while (leftLine != null && rightLine != null) {
            int leftValue = Integer.parseInt(leftLine);
            int rightValue = Integer.parseInt(rightLine);

            if (leftValue <= rightValue) {
                    writer.write(Integer.toString(leftValue));
                    writer.newLine();
                    leftLine = leftReader.readLine();
            } 
            else {
                writer.write(Integer.toString(rightValue));
                writer.newLine();
                rightLine = rightReader.readLine();
            }
        }

        // Write remaining lines from leftFile
        while (leftLine != null) {
            writer.write(leftLine);
            writer.newLine();
            leftLine = leftReader.readLine();
        }

        // Write remaining lines from rightFile
        while (rightLine != null) {
            writer.write(rightLine);
            writer.newLine();
            rightLine = rightReader.readLine();
        }

        writer.close();
        leftReader.close();
        rightReader.close();
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

        for (int i = 1; i < n; i++) { //implementation of insertion sort
            int x = S[i];
            int j = i - 1;
            while (j >= 0 && S[j] > x) { 
                S[j + 1] = S[j];
                j--;
            }
            S[j + 1] = x;
        }

        FileWriter fw = new FileWriter(file); //create file writer
        BufferedWriter bw = new BufferedWriter(fw); //create buffer writer

        for (int i = 0; i < n; i++) { //overwrite lines with sorted values
            bw.write(Integer.toString(S[i]));
            bw.newLine();
        }

        bw.close(); //close buffer writer
    }

    public void insertionsortLinkedList(int n, File file) throws IOException{
        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader

        LinkedList S = new LinkedList();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S.append(Integer.parseInt(line.replace(" ", ""))); //store each line into linked list
        }

        br.close(); //close buffer reader

        for(int i = 0; i < n; i++){ //insertion sort implementation
            if (S.head == null || S.head.next == null){
                return;
            }
            Node sorted = null;
            Node current = S.head;
            while (current != null) {
                Node next = current.next;
                sorted = sortedInsert(sorted, current);
                current = next;
            }
            S.head = sorted;

        }

        FileWriter fw = new FileWriter(file); //create file writer
        BufferedWriter bw = new BufferedWriter(fw); //create buffer writer

        for (int i = 0; i < n; i++) { //overwrite lines with sorted values
            bw.write(Integer.toString(S.getElementAt(i)));
            bw.newLine();
        }

        bw.close(); //close buffer writer

    }

    public void mergeSort(int n, File file) throws IOException{
        if (n > 1) {
            int h = n / 2;
            int m = n - h;

            // Create temporary files for left and right halves, as well as app. file readers / writers
            File leftFile = File.createTempFile("left", ".txt");
            File rightFile = File.createTempFile("right", ".txt");

            BufferedWriter leftWriter = new BufferedWriter(new FileWriter(leftFile));
            BufferedWriter rightWriter = new BufferedWriter(new FileWriter(rightFile));
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Split the original file into left and right halves
            int count = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (count < h) {
                    leftWriter.write(line);
                    leftWriter.newLine();
                } else {
                    rightWriter.write(line);
                    rightWriter.newLine();
                }
                count++;
            }

            leftWriter.close();
            rightWriter.close();
            br.close();

            // Recursively sort left and right halves
            mergeSort(h, leftFile);
            mergeSort(m, rightFile);

            // Merge the sorted halves
            merge(file, leftFile, rightFile, h, m);

            // Delete temporary files
            leftFile.delete();
            rightFile.delete();
        }

    }

    public static void main(String[] args) throws IOException{
        File file = new File("test.txt"); 

        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        int lines = 0;
        while (bReader.readLine() != null){
            lines++;
        }
        reader.close();
        bReader.close();

        Sorting sorter = new Sorting();
        sorter.mergeSort(lines, file);

        FileReader sortedReader = new FileReader(file);
        BufferedReader sortedBReader = new BufferedReader(sortedReader);
        String line;
        while ((line = sortedBReader.readLine()) != null) {
            System.out.println(line);
        }
        sortedReader.close();
        sortedBReader.close();

    }

}