import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Sorting
{
    void insertionsort(int n, File file)  throws IOException{

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int[] S = new int[n];
        int m = 0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            S[m] = Integer.parseInt(line);
            m++;
        }

        br.close();

        for(int i = 1; i <= n; i++){
            int x = S[i];
            int j = i - 1;
            while(j > 0 && S[j] > x){
                S[j + 1] = S[j];
                j --;
            }
            S[j + 1] = x;
        }
    }

    



    public static void main(String[] args){
    }

}