import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sorting{
    void insertionsortArray(int n, File file)  throws IOException{

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

    void insertionsortLinkedList(int n, File file) throws IOException{
        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader

        LinkedList S = new LinkedList();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S.append(Integer.parseInt(line.replace(" ", ""))); //store each line into linked list
        }

        br.close(); //close buffer reader

        Node current = S.head.next; // Start from the second node
        while (current != null) {
            int key = current.data;
            Node prev = current;
            Node temp = S.head;
    
            // Find the correct position to insert the current node
            while (temp != current && temp.data < key) {
                prev = temp;
                temp = temp.next;
            }
    
            if (temp != current) { // If the correct position is not the current position
                prev.next = current.next;
                current.next = temp;
                if (temp == S.head) {
                    S.head = current;
                } else {
                    prev.next = current;
                }
            } else { // If the correct position is the current position
                current = current.next;
            }
        }
        
        FileWriter fw = new FileWriter(file); //create file writer
        BufferedWriter bw = new BufferedWriter(fw); //create buffer writer

        for (int i = 0; i < n; i++) { //overwrite lines with sorted values
            bw.write(Integer.toString(S.getElementAt(i)));
            bw.newLine();
        }

        bw.close(); //close buffer writer

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

        Sorting sorter = new Sorting();
        sorter.insertionsortLinkedList(lines, file);

        FileReader sortedReader = new FileReader(file);
        BufferedReader sortedBReader = new BufferedReader(sortedReader);
        String line;
        while ((line = sortedBReader.readLine()) != null) {
            System.out.println(line);
        }
        sortedReader.close();

    }

}