import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sorting
{
    void insertionsort(int n, File file)  throws IOException{

        FileReader fr = new FileReader(file); //create file reader
        BufferedReader br = new BufferedReader(fr); //create a buffer reader

        int[] S = new int[n]; //n is size of file (# of lines)
        int m = 0; 
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S[m] = Integer.parseInt(line.replace(" ", "")); //store each line into an array
            m++;
        }

        br.close(); //close buffer reader

        for (int i = 1; i < n; i++) { // Corrected loop condition
            int x = S[i];
            int j = i - 1;
            while (j >= 0 && S[j] > x) { // Corrected loop condition
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
        sorter.insertionsort(lines, file);

        FileReader sortedReader = new FileReader(file);
        BufferedReader sortedBReader = new BufferedReader(sortedReader);
        String line;
        while ((line = sortedBReader.readLine()) != null) {
            System.out.println(line);
        }
        sortedReader.close();

    }

}