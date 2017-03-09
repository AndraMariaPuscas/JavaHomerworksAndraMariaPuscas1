package filesmanagement;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilesManagementMethods {

    String file;

    FilesManagementMethods(String file) {
        this.file = file;
    }
    

    //method that reads from a specified file
    public String[] readFile(String fileToRead) throws IOException {

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int numberOfLines = readNoOfLines();
        String[] array = new String[numberOfLines];
        int i;
        for (i = 0; i < numberOfLines; i++) {
            array[i] = br.readLine();
        }

        return array;
    }

    int readNoOfLines() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        //String line= null;

        int numberOfLines = 0;
        while (br.readLine() != null) {
            numberOfLines++;
        }
        br.close();
        return numberOfLines;
    }
}
